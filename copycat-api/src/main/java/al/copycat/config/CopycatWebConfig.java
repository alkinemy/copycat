package al.copycat.config;

import al.copycat.interfaces.CopycatWebs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = { CopycatWebs.class })
public class CopycatWebConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
