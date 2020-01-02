package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.party.IPartyDao;
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.PartyDaoImpl;
import io.nzbee.entity.party.PartyServiceImpl;
import io.nzbee.test.entity.beans.PartyEntityBeanFactory;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_PartyEntityRepositoryIntegrationTest {

	
	@TestConfiguration
    static class PartyServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
        @Bean(value = "partyService")
        public IPartyService partyService() {
            return new PartyServiceImpl();
        }
        
        @Bean(value = "partyDao")
        public IPartyDao partyDao() {
            return new PartyDaoImpl();
        }
        
        @Bean(value = "partyEntityBeanFactory")
        public PartyEntityBeanFactory partyFactoryBean() {
            return new PartyEntityBeanFactory();
        }

    }
    
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
    private IPartyService partyService;
 
	@MockBean
    private IPartyDao partyDao;
	
	@Autowired
	private PartyEntityBeanFactory partyEntityBeanFactory;
	
	private io.nzbee.entity.party.Party customer = null;
	
    @Before
    public void setUp() { 
    	this.persistNewCustomer();
    }
    
    
	public Party persistNewCustomer() {
    	
		customer = partyEntityBeanFactory.getCustomerEntityBean();
	    	
	    //persist a new transient test category
	    entityManager.persist(customer);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return customer;
	}
	
	
	@Test
    public void whenFindById_thenReturnCustomer() {
    	
        // when
    	Party found = partyService.findById(customer.getPartyId()).get();
     
        // then
    	assertFound(found);
    }
	
	
    private void assertFound(final Party found) {
    	System.out.println(found.getPartyType());
    	
//    	assertThat(found.getPartyType())
//        .isEqualTo("TST02");
//	    assertThat(found.getCategoryLevel())
//	    .isEqualTo(new Long(1));
//	    assertThat(found.getCategoryType().getCode())
//	    .isEqualTo("PRD01");
//	    assertThat(found.getHierarchy().getHierarchyCode())
//	    .isEqualTo("TST01");
//	    assertThat(found.getCategoryAttribute().getCategoryDesc())
//	    .isEqualTo("test product category");
    }
	
	
}
