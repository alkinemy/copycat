package al.copycat.domain.download.torrent.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.common.service.Downloader;
import al.copycat.domain.download.torrent.model.FileTorrent;
import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Slf4j
@Service
public class FileTorrentDownloader implements Downloader<FileTorrent> {

	@Override
	public void startDownload(FileTorrent torrent) {
		try {
			Client client = new Client(InetAddress.getLocalHost(), SharedTorrent.fromFile(torrent.getTorrentFile(), torrent.getDestination()));
			client.setMaxDownloadRate(1024.0);
			client.setMaxUploadRate(10.0);

			client.addObserver(new TorrentProgressObserver());

			client.download();
		} catch (Exception e) {
			throw new DownloadException("Fail to start downloading torrent", e);
		}
	}

}
