package al.copycat.domain.base.entity;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorInjector implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return "self|admin";
	}

}

