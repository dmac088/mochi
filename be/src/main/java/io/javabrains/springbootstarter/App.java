package io.javabrains.springbootstarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("io.javabrains.springbootstarter.config.db.mochi")
public class App {

	 private static final Logger LOG = LoggerFactory.getLogger(App.class);

	    @Value("${spring.profiles.active}")
	    protected String springProfilesActive;

	    public void onApplicationEvent(ContextRefreshedEvent event) {
	        LOG.info("=======================================");
	        LOG.info("App running with active profiles: {}", springProfilesActive);
	        LOG.info("=======================================");
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}