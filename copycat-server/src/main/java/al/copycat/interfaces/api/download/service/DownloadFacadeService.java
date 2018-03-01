package al.copycat.interfaces.api.download.service;

import al.copycat.config.DownloadProperties;
import al.copycat.domain.download.source.common.service.DownloaderDelegateService;
import al.copycat.domain.download.source.simple.model.MultipartFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
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
		return Mono.fromCallable(() -> getDownloadPath(file))
			.map(downloadPath -> MultipartFileSource.of(file, downloadPath))
			.flatMap(source -> Mono.fromRunnable(() -> downloaderDelegateService.startDownload(source)));
	}

	private Path getDownloadPath(MultipartFile file) {
		return Paths.get(downloadProperties.getRoot(), file.getOriginalFilename());
	}
}
