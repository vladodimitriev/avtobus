package mk.mladen.avtobusi;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DbConfig {

	@javax.annotation.Resource
	private Environment environment;
	
	@Value("classpath:db/sql/0001-insert-data.sql")
	private Resource sql0001insertDataScript;
	
	@Value("classpath:db/sql/0002-places.sql")
	private Resource sql0002places;
	
	@Value("classpath:db/sql/0003-carriers.sql")
	private Resource sql0003carriers;
	
	@Value("classpath:db/sql/1004-negotino-skopje.sql")
	private Resource sql1004negotinoSkopje;
	
	@Value("classpath:db/sql/1005-ohrid-skopje.sql")
	private Resource sql1005ohridSkopje;
	
	@Value("classpath:db/sql/0004-from-skopje.sql")
	private Resource sql0004fromSkopje;

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
		dataSource.setDriverClassName(environment.getRequiredProperty("postgres.jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("postgres.jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("postgres.jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("postgres.jdbc.password"));
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
	
	@Bean
	public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
	    final DataSourceInitializer initializer = new DataSourceInitializer();
	    initializer.setDataSource(dataSource);
	    //initializer.setDatabasePopulator(databasePopulator());
	    return initializer;
	}

	private DatabasePopulator databasePopulator() {
	    final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	    populator.addScript(sql0001insertDataScript);
	    //populator.addScript(sql0002places);
	    //populator.addScript(sql0003carriers);
	    //populator.addScript(sql1004negotinoSkopje);
	    //populator.addScript(sql1005ohridSkopje);
	    //populator.addScript(sql0004fromSkopje);
	    return populator;
	}

	protected Properties additionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("postgres.hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("postgres.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("postgres.hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("postgres.hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", environment.getRequiredProperty("postgres.hibernate.dialect"));
		properties.put("hibernate.cache.provider_class", environment.getRequiredProperty("derby.hibernate.cache.provider_class"));
		properties.put("hibernate.enable_lazy_load_no_trans", environment.getRequiredProperty("postgres.hibernate.enable_lazy_load_no_trans"));
		return properties;
	}

}
