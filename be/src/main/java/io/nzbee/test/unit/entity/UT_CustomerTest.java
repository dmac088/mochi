package io.nzbee.test.unit.entity;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
public class UT_CustomerTest {

	/*
	@TestConfiguration
    static class BrandCategoryServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
        @Bean(value = "customerEntityService")
        public IPartyService categoryService() {
            return new PartyServiceImpl();
        }
        
        @Bean(value = "customerEntityPostgresDao")
        public IPartyDao partyDao() {
            return new PartyDaoPostgresImpl();
        }
        
        @Bean(value = "customerEntityBeanFactory")
        public PartyEntityBeanFactory partyFactoryBean() {
            return new PartyEntityBeanFactory();
        }

    }
    */
	
}
