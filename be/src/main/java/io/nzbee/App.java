package io.nzbee;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
@SpringBootApplication
@EnableCaching
@ComponentScan({"io.nzbee.*"})
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