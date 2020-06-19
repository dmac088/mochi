package io.nzbee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(AuthorizationServerSecurityConfigurer.class)
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("securityDataSource")
    private DataSource dataSource;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private PasswordEncoder oauthClientPasswordEncoder;
    
    
	@Bean
    public JdbcTokenStore tokenStore() {
    	return new JdbcTokenStore(dataSource);
    }    
	
    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
        	.tokenKeyAccess("permitAll()")
        	.checkTokenAccess("isAuthenticated()")
        	.passwordEncoder(oauthClientPasswordEncoder);
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }
    
    @Bean
    public TokenEnhancer tokenEnhancer() {
       return new CustomTokenEnhancer();
    }
    
    @Bean
    public DefaultAccessTokenConverter accessTokenConverter() {
        return new DefaultAccessTokenConverter();
    }
    
   
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
        	.tokenStore(tokenStore())
        	.reuseRefreshTokens(false)
        	.tokenEnhancer(tokenEnhancer())
        	.accessTokenConverter(accessTokenConverter())
        	.authenticationManager(authenticationManager)
        	.userDetailsService(userDetailsService);
        	
    }
   
}