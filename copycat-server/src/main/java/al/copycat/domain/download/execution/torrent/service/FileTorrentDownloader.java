package al.copycat.domain.download.execution.torrent.service;

import al.copycat.domain.base.exception.Exceptions;
import al.copycat.domain.base.util.FileUtils;
import al.copycat.domain.download.common.exception.DownloadErrorCode;
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
			FileUtils.createParentDirectories(downloadForm.getTorrentContentDownloadTo());

			TorrentClient client = TorrentClient.fromFile(downloadForm);
			log.debug("Download torrent contents to: {}", downloadForm.getTorrentContentDownloadTo());
			client.startDownload();
			return downloadForm.getTorrentContentDownloadTo();
		} catch (Exception e) {
			log.error("Fail to start downloading file torrent: {}", downloadForm.getFrom().getSource().getAbsolutePath(), e);
			throw Exceptions.newException(DownloadErrorCode.E500_DOWNLOAD_FAILED_FILE_TORRENT, e);
		}
	}

}
