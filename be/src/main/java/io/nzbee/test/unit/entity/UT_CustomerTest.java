package io.nzbee.test.unit.entity;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.nzbee.entity.party.IPartyDao;
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.PartyDaoImpl;
import io.nzbee.entity.party.PartyServiceImpl;


@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
public class UT_CustomerTest {

	
	@TestConfiguration
    static class BrandCategoryServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
        @Bean(value = "partyyService")
        public IPartyService partyService() {
            return new PartyServiceImpl();
        }
        
        @Bean(value = "partyDao")
        public IPartyDao partyDao() {
            return new PartyDaoImpl();
        }
        
//        @Bean(value = "customerEntityBeanFactory")
//        public PartyEntityBeanFactory partyFactoryBean() {
//            return new PartyEntityBeanFactory();
//        }

    }
    
	
	
	
}
