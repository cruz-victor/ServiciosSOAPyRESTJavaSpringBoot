package bo.gob.sin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value={"bo.gob.sin",
		"bo.gob.sin.sre.fac.model", "bo.gob.sin.sre.fac.domain",
		"bo.gob.sin.parametricas.model"})
public class AppConfig {
	
}