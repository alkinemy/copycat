package al.copycat.domain.download.execution.torrent.service;

import al.copycat.domain.base.exception.Exceptions;
import al.copycat.domain.download.common.exception.DownloadErrorCode;
import al.copycat.domain.download.execution.torrent.model.FileTorrentDownloadForm;
import al.copycat.domain.download.execution.torrent.model.MagnetTorrentDownloadForm;
import bt.Bt;
import bt.data.Storage;
import bt.data.file.FileSystemStorage;
import bt.dht.DHTConfig;
import bt.dht.DHTModule;
import bt.runtime.BtClient;
import com.google.inject.Module;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.io.Closeable;
import java.net.MalformedURLException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TorrentClient implements Closeable {

	private BtClient client;
	private int interval = 5 * 1000; // 5 secs
	private String torrentPath;

	private TorrentClient(BtClient client, String torrentPath) {
		this.client = client;
		this.torrentPath = torrentPath;
	}

	public static TorrentClient fromMagnet(MagnetTorrentDownloadForm downloadForm) {
		Storage storage = new FileSystemStorage(downloadForm.getTorrentContentDownloadTo());
		Module dhtModule = new DHTModule(new DHTConfig() {
			@Override public boolean shouldUseRouterBootstrap() {
				return true;
			}
		});
		BtClient client = Bt.client()
			.magnet(downloadForm.getFrom().getSource().getUrl())
			.storage(storage)
			.autoLoadModules()
			.module(dhtModule)
			.stopWhenDownloaded()
			.build();
		return new TorrentClient(client, downloadForm.getFrom().getSource().getUrl());
	}

	public static TorrentClient fromFile(FileTorrentDownloadForm downloadForm) {
		Storage storage = new FileSystemStorage(downloadForm.getTorrentContentDownloadTo());
		Module dhtModule = new DHTModule(new DHTConfig() {
			@Override public boolean shouldUseRouterBootstrap() {
				return true;
			}
		});
		try {
			BtClient client = Bt.client()
				.torrent(downloadForm.getFrom().getSource().toURI().toURL())
				.storage(storage)
				.autoLoadModules()
				.module(dhtModule)
				.stopWhenDownloaded()
				.build();
			return new TorrentClient(client, downloadForm.getFrom().getSource().getAbsolutePath());
		} catch (MalformedURLException e) {
			String torrentFilePath = downloadForm.getFrom().getSource().getAbsolutePath();
			log.error("Fail to build torrent client: invalid file, {}", torrentFilePath, e);
			throw Exceptions.newException(DownloadErrorCode.E500_TORRENT_CLIENT_START_FAILED, e);
		}
	}

	public void startDownload() {
		//FIXME: db에 로그 저장
		Mono.fromFuture(client.startAsync(state -> {
			log.debug("Download torrent({}), remain: {}", torrentPath, state.getPiecesRemaining());
			if (state.getPiecesRemaining() == 0) {
				log.debug("Download completed, close client: {}", torrentPath);
				close();
			}
		}, interval))
			.subscribe();
	}

	@Override
	public void close() {
		//FIXME: 빨리 닫히지 않으면 여러번 호출되는 에러가 있다....
		log.info("Close torrent client: {}", torrentPath);
		Optional.ofNullable(client)
			.filter(BtClient::isStarted)
			.ifPresent(BtClient::stop);
	}

}
