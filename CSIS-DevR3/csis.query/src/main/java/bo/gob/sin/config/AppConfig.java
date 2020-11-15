package bo.gob.sin.config;

import java.io.Serializable;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@ComponentScan(value={"bo.gob.sin"})
public class AppConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}