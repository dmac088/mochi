package io.nzbee.security;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "securityEntityManagerFactory", 
        transactionManagerRef = "securityTransactionManager"
)
@Order(2)
public class DataSourceBeanSecurity {
	
	@Bean(name = "securityDataSourceProperties")
    @ConfigurationProperties("spring.datasource.security.application")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean(name = "securityDataSourcePropertiesOwner")
    @ConfigurationProperties("spring.datasource.security.owner")
    public DataSourceProperties dataSourcePropertiesOwner() {
		DataSourceProperties dsp = new DataSourceProperties();
		return dsp;
    }
	
	@Bean(name = "securityDataSource")
    @ConfigurationProperties("spring.datasource.security.application")
    public HikariDataSource dataSource(@Qualifier("securityDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
        		.driverClassName("org.postgresql.Driver")
                .build();
    }
	
	@Bean(name = "securityDataSourceOwner")
    @ConfigurationProperties("spring.datasource.security.owner")
    public HikariDataSource dataSourceOwner(@Qualifier("securityDataSourcePropertiesOwner") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder()
        		.type(HikariDataSource.class)
        		.driverClassName("org.postgresql.Driver")
                .build();
    }
	
	
	private Properties additionalJpaProperties(){
		Properties properties = new Properties();
		//properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("hibernate.show_sql", "true");
		
		return properties; 
	} 
     
	@Bean(name = "securityEntityManagerFactory") 
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("securityDataSource") HikariDataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em 
         = new LocalContainerEntityManagerFactoryBean();
       em.setDataSource(dataSource);
       em.setPackagesToScan(new String[] {
    		   								"io.nzbee.*"
    		   							 });
       JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       em.setJpaVendorAdapter(vendorAdapter);
       em.setJpaProperties(additionalJpaProperties());
       return em;
    }
	
	@Bean(name = "securityEntityManagerFactoryOwner") 
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryOwner(@Qualifier("securityDataSourceOwner") HikariDataSource dataSource) {
       LocalContainerEntityManagerFactoryBean em 
         = new LocalContainerEntityManagerFactoryBean();
       em.setDataSource(dataSource);
       em.setPackagesToScan(new String[] 
    		   {"io.nzbee.*"}
        );
       JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       em.setJpaVendorAdapter(vendorAdapter);
       em.setJpaProperties(additionalJpaProperties());
       return em;
    }
	
	@Bean(name = "securityTransactionManager")
    public PlatformTransactionManager TransactionManager(@Qualifier("securityEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		entityManagerFactory.getObject());
        return transactionManager;
    }
	
	@Bean(name = "securityTransactionManagerOwner")
    public PlatformTransactionManager TransactionManagerOwner(@Qualifier("securityEntityManagerFactoryOwner") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		entityManagerFactory.getObject());
        return transactionManager;
    }
} 
