package io.javabrains.springbootstarter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
@EnableCaching
@ComponentScan({"io.javabrains.springbootstarter.domain",
				"io.javabrains.springbootstarter.security",
				"io.javabrains.springbootstarter.test",
				"io.javabrains.springbootstarter.services",
				"io.javabrains.springbootstarter.dto",
				"io.javabrains.springbootstarter.entity",
				"io.javabrains.springbootstarter.controllers"})
public class App {
	
	 private static final Logger LOG = LoggerFactory.getLogger(App.class);

	    //@Value("${spring.profiles.active}")
	    //protected String springProfilesActive;

	    public void onApplicationEvent(ContextRefreshedEvent event) {
	        LOG.info("=======================================");
	     //   LOG.info("App running with active profiles: {}", springProfilesActive);
	        LOG.info("=======================================");
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}