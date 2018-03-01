package al.copycat.interfaces.api.download.service;

import al.copycat.config.DownloadProperties;
import al.copycat.domain.download.source.common.service.DownloaderDelegateService;
import al.copycat.domain.download.source.simple.model.MultipartFileSource;
import al.copycat.domain.download.source.simple.model.UrlSource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.nio.file.Paths;

@Service
public class DownloadFacadeService {

	private final DownloaderDelegateService downloaderDelegateService;

	private final DownloadProperties downloadProperties;

	@Autowired
	public DownloadFacadeService(DownloaderDelegateService downloaderDelegateService, DownloadProperties downloadProperties) {
		this.downloaderDelegateService = downloaderDelegateService;
		this.downloadProperties = downloadProperties;
	}

	public Mono<Void> download(MultipartFile file) {
		return Mono.fromCallable(() -> Paths.get(downloadProperties.getRoot(), file.getOriginalFilename()))
			.map(downloadPath -> MultipartFileSource.of(file, downloadPath))
			.flatMap(source -> Mono.fromRunnable(() -> downloaderDelegateService.startDownload(source)));
	}

	public Mono<Void> download(String url) {
		return Mono.fromCallable(() -> FilenameUtils.getName(url))
			.map(fileName -> Paths.get(downloadProperties.getRoot(), fileName))
			.map(downloadPath -> UrlSource.of(url, downloadPath))
			.flatMap(source -> Mono.fromRunnable(() -> downloaderDelegateService.startDownload(source)));
	}

}
