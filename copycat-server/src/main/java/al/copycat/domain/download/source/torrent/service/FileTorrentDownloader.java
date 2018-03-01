package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.common.service.Downloader;
import al.copycat.domain.download.source.torrent.model.FileTorrentSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Slf4j
@Service
public class FileTorrentDownloader implements Downloader<FileTorrentSource> {

	@Override
	public Path startDownload(FileTorrentSource torrent) {
		try {
			TorrentClient client = TorrentClient.fromFile(torrent);
			log.debug("Download torrent contents to: {}", torrent.getDestination());
			client.startDownload();
			return torrent.getDestination();
		} catch (Exception e) {
			log.error("Fail to start downloading torrent: {}", torrent.getSource().getAbsolutePath(), e);
			throw new DownloadException("Fail to start downloading torrent", e);
		}
	}

}
