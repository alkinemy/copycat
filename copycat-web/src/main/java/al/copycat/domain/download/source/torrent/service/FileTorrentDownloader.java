package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.common.service.Downloader;
import al.copycat.domain.download.source.torrent.model.FileTorrentSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileTorrentDownloader implements Downloader<FileTorrentSource> {

	@Override
	public void startDownload(FileTorrentSource torrent) {
		try {
			TorrentClient client = TorrentClient.fromFile(torrent);
			client.startDownload();
		} catch (Exception e) {
			throw new DownloadException("Fail to startDownload downloading torrent: " + torrent.getSource().getAbsolutePath(), e);
		}
	}

}
