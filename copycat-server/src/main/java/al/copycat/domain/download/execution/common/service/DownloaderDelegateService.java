package al.copycat.domain.download.execution.common.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.execution.common.model.DownloadForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class DownloaderDelegateService {

	private final ApplicationContext applicationContext;

	private Map<Class, Downloader> downloaders = new HashMap<>();

	@Autowired
	public DownloaderDelegateService(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@PostConstruct
	public void initialize() {
		String[] beanNames = applicationContext.getBeanNamesForType(Downloader.class);
		Arrays.stream(beanNames).forEach(beanName -> {
			Downloader downloader = applicationContext.getBean(beanName, Downloader.class);
			Class<?> downloadType = GenericTypeResolver.resolveTypeArgument(downloader.getClass(), Downloader.class);
			log.info("Downloader bean(name: {}, type: {}) registered", beanName, downloadType);
			downloaders.put(downloadType, downloader);
		});
	}

	public Path startDownload(DownloadForm downloadForm) {
		Downloader downloader = Optional.ofNullable(downloaders.get(downloadForm.getClass()))
			.orElseThrow(() -> new DownloadException("Unsupported download source: " + downloadForm.getClass()));
		return downloader.startDownload(downloadForm);
	}

}