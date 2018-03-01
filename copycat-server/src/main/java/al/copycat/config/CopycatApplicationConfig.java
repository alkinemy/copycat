package al.copycat.config;

import al.copycat.domain.CopycatDomains;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = { CopycatDomains.class })
@EnableConfigurationProperties({ DownloadProperties.class })
public class CopycatApplicationConfig {

	@Bean
	public RestTemplate restTemplate() {
		// FIXME: set backoff strategy, timeout
		return new RestTemplate();
	}

}
