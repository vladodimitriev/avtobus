package mk.mladen.avtobusi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("mk.mladen.avtobusi")
@EnableTransactionManagement
@PropertySource("classpath:application-test.properties")
public class ApplicationTestConfig {

	@Resource
    private Environment environment;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "mk.mladen.avtobusi.entity" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
    public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("hsqldb.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("hsqldb.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("hsqldb.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("hsqldb.jdbc.password"));
		return dataSource;
    }

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	protected Properties additionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hsqldb.hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hsqldb.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hsqldb.hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hsqldb.hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", environment.getRequiredProperty("hsqldb.hibernate.dialect"));
		properties.put("hibernate.cache.provider_class", environment.getRequiredProperty("hsqldb.hibernate.cache.provider_class"));
		properties.put("hibernate.enable_lazy_load_no_trans", environment.getRequiredProperty("hsqldb.hibernate.enable_lazy_load_no_trans"));
		return properties;
	}
}
