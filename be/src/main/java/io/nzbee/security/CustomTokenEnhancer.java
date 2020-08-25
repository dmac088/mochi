package io.nzbee.security;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import io.nzbee.security.user.User;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    	
        User user = (User) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();

        additionalInfo.put("userName", user.getUsername());
        additionalInfo.put("authenticated", true);
        
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.sssZ"); 
        Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
        calendar.add(Calendar.SECOND, 1000);
        
        ((DefaultOAuth2AccessToken) accessToken).setExpiration(calendar.getTime());
        String expiryDate = formatter.format(calendar.getTime());
        
        additionalInfo.put("accessTokenExpiryDate", expiryDate);
        
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        
        return accessToken;
    }

}
