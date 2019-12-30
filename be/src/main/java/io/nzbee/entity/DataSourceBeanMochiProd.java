package io.nzbee.entity;



import java.util.Properties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
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
        entityManagerFactoryRef = "mochiEntityManagerFactory", 
        transactionManagerRef 	= "mochiTransactionManager"
        )
@Profile("prod")
@Order(3)
public class DataSourceBeanMochiProd {
	
	@Primary
	@Bean(name = "mochiDataSourcePropertiesProd")
    @ConfigurationProperties("spring.datasource.mochi")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Primary
	@Bean(name = "mochiDataSource")
    @ConfigurationProperties("spring.datasource.mochi")
    public HikariDataSource dataSource(@Qualifier("mochiDataSourcePropertiesProd") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder()
        		.type(HikariDataSource.class)
        		.driverClassName("org.postgresql.Driver")
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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("mochiDataSource") HikariDataSource dataSource) {
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
    
	@Primary
	@Bean(name = "mochiTransactionManager")
    public PlatformTransactionManager TransactionManager(@Qualifier("mochiEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		entityManagerFactory.getObject());
        return transactionManager;
    }
} 