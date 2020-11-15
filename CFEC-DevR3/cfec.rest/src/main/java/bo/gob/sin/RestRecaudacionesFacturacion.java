package bo.gob.sin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableCaching
@EnableAsync
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,HibernateJpaAutoConfiguration.class })
public class RestRecaudacionesFacturacion {

	public static void main(String[] args) {
		SpringApplication.run(RestRecaudacionesFacturacion.class, args);
	}
	
	@Bean
	  public TaskExecutor taskExecutor() {
	    return new ThreadPoolTaskExecutor();
	  }
}
