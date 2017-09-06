package al.copycat.domain.download.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DownloadService {

	private final ApplicationContext applicationContext;

	private Map<Class, Downloader> downloaders = new HashMap<>();

	@Autowired
	public DownloadService(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@PostConstruct
	public void initialize() {
		String[] beanNames = applicationContext.getBeanNamesForType(Downloader.class);
		Arrays.stream(beanNames).forEach((String beanName) -> {
			Downloader downloader = applicationContext.getBean(beanName, Downloader.class);
			Class<?> downloadType = GenericTypeResolver.resolveTypeArgument(downloader.getClass(), Downloader.class);
			log.info("Downloader bean(name: {}, type: {}) registered", beanName, downloadType);
			downloaders.put(downloadType, downloader);
		});
	}

}
