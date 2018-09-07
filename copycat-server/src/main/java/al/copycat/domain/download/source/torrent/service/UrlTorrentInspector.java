package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.base.exception.Exceptions;
import al.copycat.domain.download.common.exception.DownloadErrorCode;
import al.copycat.domain.download.source.torrent.model.TorrentMetadata;
import bt.metainfo.MetadataService;
import bt.metainfo.Torrent;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

@Slf4j
public class UrlTorrentInspector implements TorrentInspector<URL> {

	private final MetadataService metadataService;

	private UrlTorrentInspector() {
		this.metadataService = new MetadataService();
	}

	public static UrlTorrentInspector create() {
		return new UrlTorrentInspector();
	}

	@Override
	public TorrentMetadata getMetadata(URL url) {
		try {
			Torrent torrent = metadataService.fromUrl(url);
			return TorrentMetadata.builder()
				.id(torrent.getTorrentId().toString())
				.name(torrent.getName())
				.size(torrent.getSize())
				.build();
		} catch (Exception e) {
			log.error("Fail to inspect torrent url: {}", url, e);
			throw Exceptions.newException(DownloadErrorCode.E500_INSPECT_FAILED_URL_TORRENT, e);
		}
	}
}
