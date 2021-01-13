package io.nzbee.cache;

import java.io.IOException;

import javax.cache.Caching;

import org.ehcache.jsr107.EhcacheCachingProvider;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
@ConfigurationProperties
public class CacheConfiguration {

	  @Bean
	  public JCacheCacheManager jCacheCacheManager() throws IOException {
	    return new JCacheCacheManager(cacheManager());
	  }

	  @Bean(destroyMethod = "close")
	  public javax.cache.CacheManager cacheManager() throws IOException {
	    XmlConfiguration xmlConfig = new XmlConfiguration(new ClassPathResource("ehcache.xml").getURL());
	    EhcacheCachingProvider provider = (EhcacheCachingProvider) Caching.getCachingProvider();
	    return provider.getCacheManager(provider.getDefaultURI(), xmlConfig);

	  }
	
}
