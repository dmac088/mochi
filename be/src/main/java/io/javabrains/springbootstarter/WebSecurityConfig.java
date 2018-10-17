package io.javabrains.springbootstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Import(Encoders.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 
	@Autowired
	@Qualifier("mochiDataSource")
	DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	 
	@Autowired
	private PasswordEncoder userPasswordEncoder;
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(userPasswordEncoder);
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
	    .authorizeRequests()
	    	/*access to any files with certain expensions*/
	        .antMatchers("/**/*.{js,html,css}").permitAll()
	        
	        /*api permits*/
	        .antMatchers("/", "/api/**").permitAll()
	        .anyRequest().authenticated();
    }
    
  //  @Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("select pty_usr_nm, pty_pwd, true from mochi.party where pty_usr_nm=?")
//				.authoritiesByUsernameQuery("select pty_usr_nm, 'default' as role from mochi.party where pty_usr_nm=?");
//	}
}
