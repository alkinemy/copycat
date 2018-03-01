package al.copycat.config.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { CopycatApiConfig.class })
public class CopycatApiConfig {
}
