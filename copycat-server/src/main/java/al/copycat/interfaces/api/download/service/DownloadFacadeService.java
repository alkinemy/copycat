package al.copycat.interfaces.api.download.service;

import al.copycat.domain.download.source.common.service.DownloaderDelegateService;
import al.copycat.domain.download.source.simple.model.MultipartFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;

@Service
public class DownloadFacadeService {

	private final DownloaderDelegateService downloaderDelegateService;

	@Value("${copycat.download.root}")
	private String root;

	@Autowired
	public DownloadFacadeService(DownloaderDelegateService downloaderDelegateService) {
		this.downloaderDelegateService = downloaderDelegateService;
	}

	public Mono<Void> download(MultipartFile file) {
		return Mono.fromCallable(() -> new File(root + "/" + file.getOriginalFilename()))
			.map(downloadPath -> MultipartFileSource.of(file, downloadPath))
			.flatMap(source -> Mono.fromRunnable(() -> downloaderDelegateService.startDownload(source)));
	}
}
