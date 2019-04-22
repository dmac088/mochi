package io.javabrains.springbootstarter.security;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

public class CustomJdbcTokenStore extends JdbcTokenStore{

	private static final Logger LOG = LogManager.getLogger(CustomJdbcTokenStore.class.getName());
	
	public CustomJdbcTokenStore(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	public OAuth2AccessToken readAccessToken(String tokenValue) {
	    OAuth2AccessToken accessToken = null;

	    try {
	        accessToken = new DefaultOAuth2AccessToken(tokenValue);
	    }
	    catch (EmptyResultDataAccessException e) {
	        if (LOG.isInfoEnabled()) {
	            LOG.info("Failed to find access token for token "+tokenValue);
	        }
	    }
	    catch (IllegalArgumentException e) {
	        LOG.warn("Failed to deserialize access token for " +tokenValue,e);
	        removeAccessToken(tokenValue);
	    }

	    return accessToken;
	}

}
