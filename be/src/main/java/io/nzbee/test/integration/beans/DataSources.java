package io.nzbee.test.integration.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariDataSource;

@Configuration(value = "ownerDataSourceBeans")
@Profile("dev")
public class DataSources {

	
	@Bean(name = "mochiOwnerDataSourceProperties")
    @ConfigurationProperties("spring.datasource.mochi.owner")
    public DataSourceProperties mochiDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean(name = "mochiOwnerDataSource")
    @ConfigurationProperties("spring.datasource.mochi.owner")
    public HikariDataSource mochiDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder()
        		.type(HikariDataSource.class)
        		.driverClassName("org.postgresql.Driver")
                .build();
    }
	
	
	@Bean(name = "securityOwnerDataSourceProperties")
    @ConfigurationProperties("spring.datasource.security.owner")
    public DataSourceProperties securityDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean(name = "securityOwnerDataSource")
    @ConfigurationProperties("spring.datasource.security.owner")
    public HikariDataSource securityDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder()
        		.type(HikariDataSource.class)
        		.driverClassName("org.postgresql.Driver")
                .build();
    }
	
	
}
