package al.copycat.domain.download.origin.common.service;

import al.copycat.domain.download.origin.common.exception.OriginException;
import al.copycat.domain.download.origin.common.model.Origin;
import al.copycat.domain.download.origin.common.model.OriginEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Service
public class OriginReaderDelegateService {

	private Map<Class, OriginReader> readers = new HashMap<>();

	private final ApplicationContext applicationContext;

	@Autowired
	public OriginReaderDelegateService(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@PostConstruct
	public void initialize() {
		String[] beanNames = applicationContext.getBeanNamesForType(OriginReader.class);
		Arrays.stream(beanNames).forEach(beanName -> {
			OriginReader originReader = applicationContext.getBean(beanName, OriginReader.class);
			Class<?>[] types = GenericTypeResolver.resolveTypeArguments(originReader.getClass(), OriginReader.class);
			Class<?> originType = types[0];
			log.info("OriginReader bean(name: {}, type: {}) registered", beanName, originType);
			readers.put(originType, originReader);
		});
	}

	public List<OriginEntry> read(Origin origin) {
		OriginReader reader = Optional.ofNullable(readers.get(origin.getClass()))
			.orElseThrow(() -> new OriginException("Unsupported origin type: " + origin.getClass()));
		return reader.read(origin);
	}

}
