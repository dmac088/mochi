	package io.nzbee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Encoders {
	
	public final static int userRounds = 8;
	public final static int clientRounds = 4;
	
	private transient BCryptPasswordEncoder clientPasswordEncoder = new BCryptPasswordEncoder(clientRounds);
	
	private transient BCryptPasswordEncoder userPasswordEncoder = new BCryptPasswordEncoder(userRounds);
	
    @Bean
    public PasswordEncoder oauthClientPasswordEncoder() {
        return clientPasswordEncoder;
    }
    
    @Bean(name = "userPasswordEncoder")
    public PasswordEncoder userPasswordEncoder() {
        return userPasswordEncoder;
    }
}  