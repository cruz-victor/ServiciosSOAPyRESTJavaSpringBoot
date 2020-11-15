package bo.gob.sin.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfig {

	@Autowired
	private Environment env;

	/**
	 * Initialize dataSource
	 * 
	 * @return DataSource
	 */
	@Primary
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("datasource.driver"));
		dataSource.setUrl(env.getRequiredProperty("datasource.url"));
		dataSource.setUsername(env.getRequiredProperty("datasource.username"));
		dataSource.setPassword(env.getRequiredProperty("datasource.password"));
		return dataSource;
	}

	/**
	 * Initialize hibernate properties
	 * 
	 * @return Properties
	 */
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.jdbc.batch_size", env.getRequiredProperty("hibernate.batch.size"));

		properties.put("hibernate.bytecode.use_reflection_optimizer", env.getRequiredProperty("hibernate.bytecode.use_reflection_optimizer"));
		properties.put("hibernate.connection.driver_class", env.getRequiredProperty("hibernate.connection.driver_class"));
		properties.put("hibernate.connection.username", env.getRequiredProperty("hibernate.connection.username"));
		properties.put("hibernate.connection.password", env.getRequiredProperty("hibernate.connection.password"));
		properties.put("hibernate.connection.url", env.getRequiredProperty("hibernate.connection.url"));
		properties.put("hibernate.jdbc.lob.non_contextual_creation", true);
		properties.put("org.hibernate.envers.audit_table_suffix", "_AUDIT_LOG");
//		properties.put("hibernate.c3p0.acquire_increment", 1);
//		properties.put("hibernate.c3p0.idle_test_period", 60);
//		properties.put("hibernate.c3p0.min_size", 5);
//		properties.put("hibernate.c3p0.max_size", 10);
//		properties.put("hibernate.c3p0.max_statements", 50);
//		properties.put("hibernate.c3p0.timeout", 50);
//		properties.put("hibernate.c3p0.acquireRetryAttempts", 1);
//		properties.put("hibernate.c3p0.acquireRetryDelay", 250);
//		properties.put("hibernate.c3p0.validate", true);
//		properties.put("hibernate.connection.provider_class",
//				"org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider");

		return properties;
	}

	/**
	 * Initialize SessionFactory
	 * 
	 * @return LocalSessionFactoryBean
	 */
	@Primary
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] {"bo.gob.sin","bo.gob.sin.sre.fac"});

		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	/**
	 * Initialize Transaction Manager
	 * 
	 * @param sessionFactory
	 * @return HibernateTransactionManager
	 */
	@Primary
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

}