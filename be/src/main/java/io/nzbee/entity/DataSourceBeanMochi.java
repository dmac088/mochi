package io.nzbee.entity;



import java.util.Properties;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "mochiEntityManagerFactory", 
        transactionManagerRef = "mochiTransactionManager"
        )
public class DataSourceBeanMochi {
	
	@Autowired
	Environment env;
	
	@Primary
	@Bean(name = "mochiDataSource")
    public DataSource dataSource() {
		return DataSourceBuilder
                .create()
                .url(env.getProperty("spring.datasource.mochi.url")) 
                .username(env.getProperty("spring.datasource.mochi.username"))
                .password(env.getProperty("spring.datasource.mochi.password"))
                .driverClassName(env.getProperty("spring.jpa.properties.hibernate.driver_class"))
                .build();
    }
	
	private Properties additionalJpaProperties(){
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}
     
	@Primary
	@Bean(name = "mochiEntityManagerFactory") 
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
       LocalContainerEntityManagerFactoryBean em 
         = new LocalContainerEntityManagerFactoryBean();
       em.setDataSource(this.dataSource());
       em.setPackagesToScan(new String[] 
    		   {"io.nzbee.domain",
    			"io.nzbee.dao",
    		    "io.nzbee.security",
				"io.nzbee.services",
				"io.nzbee.entity",
				"io.nzbee.test"}
        );
       JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
       em.setJpaVendorAdapter(vendorAdapter);
       em.setJpaProperties(additionalJpaProperties());
       return em;
    }
    
	
	@Primary
	@Bean(name = "mochiTransactionManager")
    public PlatformTransactionManager TransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                this.entityManagerFactory().getObject());
        return transactionManager;
    }
} 