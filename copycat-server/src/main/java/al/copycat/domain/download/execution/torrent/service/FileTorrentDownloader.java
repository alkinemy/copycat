package al.copycat.domain.download.execution.torrent.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.execution.common.service.Downloader;
import al.copycat.domain.download.execution.torrent.model.FileTorrentDownloadForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Slf4j
@Service
public class FileTorrentDownloader implements Downloader<FileTorrentDownloadForm> {

	@Override
	public Path startDownload(FileTorrentDownloadForm downloadForm) {
		try {
			TorrentClient client = TorrentClient.fromFile(downloadForm);
			log.debug("Download torrent contents to: {}", downloadForm.getTorrentContentDownloadTo());
			client.startDownload();
			return downloadForm.getTorrentContentDownloadTo();
		} catch (Exception e) {
			log.error("Fail to start downloading file torrent: {}", downloadForm.getFrom().getSource().getAbsolutePath(), e);
			throw new DownloadException("Fail to start downloading torrent", e);
		}
	}

}
