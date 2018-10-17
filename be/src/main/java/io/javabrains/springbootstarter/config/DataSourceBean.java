/*package io.javabrains.springbootstarter.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DataSourceBean {
	
	@Autowired
	Environment env;
	
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary
    public DataSource getDataSource() {
        return DataSourceBuilder
                .create()
                .url(env.getProperty("url"))
                .username(env.getProperty("username"))
                .password(env.getProperty("password"))
                .driverClassName(env.getProperty("spring.jpa.properties.hibernate.driver_class"))
                .build();
    }
}
*/