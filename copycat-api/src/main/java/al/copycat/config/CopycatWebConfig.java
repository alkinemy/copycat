package al.copycat.config;

import al.copycat.interfaces.CopycatWebs;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { CopycatWebs.class })
public class CopycatWebConfig {
}
