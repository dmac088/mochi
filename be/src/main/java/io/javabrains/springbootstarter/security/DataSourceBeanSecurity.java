package io.javabrains.springbootstarter.security;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "securityEntityManagerFactory", 
        transactionManagerRef = "securityTransactionManager")
public class DataSourceBeanSecurity {
	
	@Autowired
	Environment env;
	
	@Bean(name = "securityDataSource")
    public DataSource dataSource() {
		return DataSourceBuilder
                .create()
                .url(env.getProperty("spring.datasource.security.url")) 
                .username(env.getProperty("spring.datasource.security.username"))
                .password(env.getProperty("spring.datasource.security.password"))
                .driverClassName(env.getProperty("spring.jpa.properties.hibernate.driver_class"))
                
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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
       LocalContainerEntityManagerFactoryBean em 
         = new LocalContainerEntityManagerFactoryBean();
       em.setDataSource(this.dataSource());
       em.setPackagesToScan(new String[] {
    		   								"io.javabrains.springbootstarter.security",
    		   								"io.javabrains.springbootstarter.domain",
    		   								"io.javabrains.springbootstarter.entity",
    		   								"io.javabrains.springbootstarter.services"
    		   							 });
       JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       em.setJpaVendorAdapter(vendorAdapter);
       em.setJpaProperties(additionalJpaProperties());
       return em;
    }
    
	
	@Bean(name = "securityTransactionManager")
    public PlatformTransactionManager TransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                this.entityManagerFactory().getObject());
        return transactionManager;
    }
} 
