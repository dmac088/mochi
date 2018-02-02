package io.javabrains.springbootstarter.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 
	@Autowired
	DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/","/**").permitAll()
                .anyRequest().authenticated()
                .and()
                
            .formLogin()
                .loginPage("/signin")
                .permitAll()
                .and()
     
            .logout()
            	.logoutUrl("/signout")
                .permitAll()
        		.and()
        		
        	.csrf().disable();
    }
    
    @Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select pty_usr_nm, pty_pwd, true from mochi.party where pty_usr_nm=?")
				.authoritiesByUsernameQuery("select pty_usr_nm, 'default' as role from mochi.party where pty_usr_nm=?");
	}
}
