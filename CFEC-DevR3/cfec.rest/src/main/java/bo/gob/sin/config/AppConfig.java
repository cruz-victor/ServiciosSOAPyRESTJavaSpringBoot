package bo.gob.sin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@ComponentScan(value={"bo.gob.sin","bo.gob.sin.recaudaciones.facturacion.model","bo.gob.sin.parametricas.model"})
public class AppConfig {
	
}