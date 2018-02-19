package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.common.service.Downloader;
import al.copycat.domain.download.source.torrent.model.MagnetTorrentSource;

public class MagnetTorrentDownloader implements Downloader<MagnetTorrentSource> {

	@Override
	public void startDownload(MagnetTorrentSource magnet) {
		try {
			TorrentClient client = TorrentClient.fromMagnet(magnet);
			client.startDownload();
		} catch (Exception e) {
			throw new DownloadException("Fail to startDownload downloading torrent: " + magnet.getSource(), e);
		}
	}

}
