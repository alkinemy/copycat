package al.copycat.domain.download.origin.common.service;

import al.copycat.domain.base.exception.Exceptions;
import al.copycat.domain.download.common.exception.DownloadErrorCode;
import al.copycat.domain.download.origin.common.model.Origin;
import al.copycat.domain.download.origin.common.model.OriginEntry;
import com.google.common.collect.Maps;
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

	private Map<Class, OriginReader<? extends Origin, ? extends OriginEntry>> readers = new HashMap<>();

	private final ApplicationContext applicationContext;

	@Autowired
	public OriginReaderDelegateService(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void initialize() {
		Map<Class, OriginReader<? extends Origin, ? extends OriginEntry>> readers = Maps.newHashMap();
		String[] beanNames = applicationContext.getBeanNamesForType(OriginReader.class);
		Arrays.stream(beanNames).forEach(beanName -> {
			OriginReader originReader = applicationContext.getBean(beanName, OriginReader.class);
			Class<?>[] types = GenericTypeResolver.resolveTypeArguments(originReader.getClass(), OriginReader.class);
			Class<?> originType = types[0];
			log.info("OriginReader bean(name: {}, type: {}) registered", beanName, originType);
			readers.put(originType, originReader);
		});
		this.readers = Collections.unmodifiableMap(readers);
	}

	public <T extends Origin, S extends OriginEntry> List<S> read(T origin) {
		OriginReader<T, S> reader = findOriginReader(origin);
		return reader.read(origin);
	}

	@SuppressWarnings("unchecked")
	private <T extends Origin, S extends OriginEntry> OriginReader<T, S> findOriginReader(T origin) {
		return (OriginReader<T, S>) Optional.ofNullable(readers.get(origin.getClass()))
				.orElseThrow(() -> Exceptions.newException(
					"Unsupported origin type: " + origin.getClass(),
					DownloadErrorCode.E400_UNSUPPORTED_ORIGIN_TYPE)
				);
	}

}
