package al.copycat.interfaces.api.download.service;

import al.copycat.config.DownloadProperties;
import al.copycat.domain.base.util.FileUtils;
import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.common.service.DownloaderDelegateService;
import al.copycat.domain.download.source.simple.model.MultipartFileSource;
import al.copycat.domain.download.source.simple.model.UrlSource;
import al.copycat.domain.download.source.torrent.model.FileTorrentSource;
import al.copycat.interfaces.api.download.dto.MultipartFileDownloadDto;
import al.copycat.interfaces.api.download.dto.TorrentDownloadDto;
import al.copycat.interfaces.api.download.dto.UrlDownloadDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class DownloadFacadeService {

	private static final String DOWNLOAD_DIRECTORY_SUFFIX = ".download";

	private final DownloaderDelegateService downloaderDelegateService;

	private final DownloadProperties downloadProperties;

	@Autowired
	public DownloadFacadeService(DownloaderDelegateService downloaderDelegateService, DownloadProperties downloadProperties) {
		this.downloaderDelegateService = downloaderDelegateService;
		this.downloadProperties = downloadProperties;
	}

	public Mono<Void> download(MultipartFileDownloadDto downloadDto) {
		return Mono.fromCallable(() -> Paths.get(downloadProperties.getRoot(), downloadDto.getFile().getOriginalFilename()))
			.map(downloadPath -> MultipartFileSource.of(downloadDto.getFile(), downloadPath))
			.map(downloaderDelegateService::startDownload)
			.then();
	}

	public Mono<Void> download(UrlDownloadDto downloadDto) {
		return downloadUrl(downloadDto.getUrl())
			.then();
	}

	public Mono<Void> download(TorrentDownloadDto downloadDto) {
		return downloadUrl(downloadDto.getTorrent())
			.doOnNext(torrentFilePath -> FileUtils.createDirectories(getDownloadDirectoryPath(torrentFilePath)))
			.doOnError(exception -> {
				throw new DownloadException("Fail to create directories", exception);
			})
			.map(torrentFilePath -> FileTorrentSource.of(torrentFilePath.toFile(), getDownloadDirectoryPath(torrentFilePath)))
			.map(downloaderDelegateService::startDownload)
			.then();
	}

	private Mono<Path> downloadUrl(String url) {
		return Mono.fromCallable(() -> FilenameUtils.getName(url))
			.map(fileName -> Paths.get(downloadProperties.getRoot(), fileName))
			.map(downloadPath -> UrlSource.of(url, downloadPath))
			.map(downloaderDelegateService::startDownload);
	}

	private Path getDownloadDirectoryPath(Path path) {
		return Paths.get(path.toString() + DOWNLOAD_DIRECTORY_SUFFIX);
	}

}
