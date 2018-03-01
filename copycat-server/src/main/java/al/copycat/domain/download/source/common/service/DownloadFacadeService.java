package al.copycat.domain.download.source.common.service;

import al.copycat.domain.download.source.common.model.Source;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Slf4j
@Service
public class DownloadFacadeService {

	private final DownloaderDelegateService downloaderDelegateService;

	@Autowired
	public DownloadFacadeService(DownloaderDelegateService downloaderDelegateService) {
		this.downloaderDelegateService = downloaderDelegateService;
	}

	public <T extends Source> Path download(T source) {
		return downloaderDelegateService.startDownload(source);
	}

}
