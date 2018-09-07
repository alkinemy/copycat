package al.copycat.domain.download.execution.torrent.service;

import al.copycat.domain.base.exception.Exceptions;
import al.copycat.domain.download.common.exception.DownloadErrorCode;
import al.copycat.domain.download.execution.common.service.Downloader;
import al.copycat.domain.download.execution.simple.model.UrlDownloadForm;
import al.copycat.domain.download.execution.simple.service.UrlDownloader;
import al.copycat.domain.download.execution.torrent.model.FileTorrentDownloadForm;
import al.copycat.domain.download.execution.torrent.model.UrlTorrentDownloadForm;
import al.copycat.domain.download.source.simple.model.UrlSource;
import al.copycat.domain.download.source.torrent.model.FileTorrentSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Optional;

@Slf4j
@Service
public class UrlTorrentDownloader implements Downloader<UrlTorrentDownloadForm> {

	private final UrlDownloader urlDownloader;

	private final FileTorrentDownloader fileTorrentDownloader;

	@Autowired
	public UrlTorrentDownloader(UrlDownloader urlDownloader,
		FileTorrentDownloader fileTorrentDownloader) {
		this.urlDownloader = urlDownloader;
		this.fileTorrentDownloader = fileTorrentDownloader;
	}

	@Override
	public Path startDownload(UrlTorrentDownloadForm downloadForm) {
		try {
			Path torrentFilePath = downloadTorrentFileFromUrl(downloadForm);
			return downloadTorrentContentFromTorrentFile(downloadForm, torrentFilePath);
		} catch (Exception e) {
			log.error("Fail to start downloading url torrent: {}", downloadForm.getFrom().getSource(), e);
			throw Exceptions.newException(DownloadErrorCode.E500_DOWNLOAD_FAILED_URL_TORRENT, e);
		}
	}

	private Path downloadTorrentFileFromUrl(UrlTorrentDownloadForm downloadForm) {
		UrlDownloadForm urlDownloadForm = Optional.of(downloadForm.getFrom().getSource())
			.map(UrlSource::of)
			.map(from -> UrlDownloadForm.of(from, downloadForm.getTorrentFileDownloadTo()))
			.orElseThrow(() -> Exceptions.newException("Fail to create UrlDownloadForm from url: " + downloadForm.getFrom().getSource(),
				DownloadErrorCode.E400_INVALID_DOWNLOAD_REQUEST_PARAMETER));
		return urlDownloader.startDownload(urlDownloadForm);
	}

	private Path downloadTorrentContentFromTorrentFile(UrlTorrentDownloadForm downloadForm, Path torrentFilePath) {
		FileTorrentDownloadForm fileTorrentDownloadForm = Optional.of(torrentFilePath)
			.map(Path::toFile)
			.map(torrentFile -> FileTorrentSource.of(torrentFile, downloadForm.getFrom().getMetadata()))
			.map(source -> FileTorrentDownloadForm.of(source, downloadForm.getTorrentContentDownloadTo()))
			.orElseThrow(() -> Exceptions.newException("Fail to create FileTorrentDownloadForm from file: " + torrentFilePath,
				DownloadErrorCode.E400_UNSUPPORTED_DOWNLOAD_TYPE));
		return fileTorrentDownloader.startDownload(fileTorrentDownloadForm);
	}

}
