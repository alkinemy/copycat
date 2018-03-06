package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.source.torrent.exception.TorrentException;
import al.copycat.domain.download.source.torrent.model.TorrentMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Service
public class TorrentInspectDelegateService {

	private final ApplicationContext applicationContext;

	private Map<Class, TorrentInspector> torrentInspectors = new HashMap<>();

	@Autowired
	public TorrentInspectDelegateService(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@PostConstruct
	public void initialize() {
		String[] beanNames = applicationContext.getBeanNamesForType(TorrentInspector.class);
		Arrays.stream(beanNames).forEach(beanName -> {
			TorrentInspector<?> inspector = applicationContext.getBean(beanName, TorrentInspector.class);
			Class<?> torrentType = GenericTypeResolver.resolveTypeArgument(inspector.getClass(), TorrentInspector.class);
			log.info("TorrentInspector bean(name: {}, type: {}) registered", beanName, torrentType);
			torrentInspectors.put(torrentType, inspector);
		});
		torrentInspectors = Collections.unmodifiableMap(torrentInspectors);
	}

	public <T> TorrentMetadata getMetadata(T t) {
		TorrentInspector torrentInspector = Optional.ofNullable(torrentInspectors.get(t.getClass()))
			.orElseThrow(() -> new TorrentException("Unsupported torrent type: " + t.getClass()));
		return torrentInspector.getMetadata(t);
	}

}
