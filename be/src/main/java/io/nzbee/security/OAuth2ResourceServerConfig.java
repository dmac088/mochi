package io.nzbee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
    //private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = "/api/**";
	
    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }
 
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
 
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }
 
    @Bean
    @Primary
    public ResourceServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http	
        		.anonymous().and().authorizeRequests()
        		.antMatchers(HttpMethod.POST,"/api/Customer/Signup").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/Product/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/Product/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/ProductAttribute/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/Brand/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/Brand/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/Category/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/Category/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/NavFacet/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/CategoryAttribute/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/images/**").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/CreateSearchIndex").permitAll()
        		.antMatchers(HttpMethod.GET,"/api/Search/**").permitAll()
        		.antMatchers(HttpMethod.POST,"/api/Search/**").permitAll()
        		.and().requestMatchers()
                .antMatchers(SECURED_PATTERN).and().authorizeRequests()
                .antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
                .anyRequest().access(SECURED_READ_SCOPE);
        
    }
}
