package io.nzbee.test.integration.entity.bag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.bag.BagDTO;
import io.nzbee.entity.bag.BagEntity;
import io.nzbee.entity.bag.IBagService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.test.integration.entity.beans.bag.IBagEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_BagEntityRepositoryIntegrationTest {

	
	@Autowired
	private IBagEntityBeanFactory bagEntityBeanFactory;
 
    @Autowired
    private IBagService bagService;
    
	@Autowired
    private IPersonService personService;
 	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
 
	private static BagEntity bag = null;
	
	private static boolean setUpIsDone = false;
    
	@Before
	public void setUp() {
		if (setUpIsDone) {
			return;
		}
		try (Connection con = database.getConnection()) {
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_schema.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.persistNewBagEntity();
		setUpIsDone = true;
	}

    
	public BagEntity persistNewBagEntity() {
		
		Optional<PersonEntity> p = personService.findByUsernameAndRole("bob@bob", Constants.partyRoleCustomer);
		
		bag = bagEntityBeanFactory.getBean(p.get());
	    
	    //persist a new transient test category
		bagService.save(bag);
	    
	    return bag;
	    
	}
     
    @Test
    @Rollback(false)
	@WithUserDetails(value = "admin")
    public void whenFindById_thenReturnBagEntity() {
    	
    	//persist a bag and then make sure we can retrieve it by id
    	Optional<BagEntity> found = bagService.findById(bag.getBagId());
     
        // then
    	assertEntityFound(found);
    }
    
    @Test
    @Rollback(false)
	@WithUserDetails(value = "admin")
    public void whenFindByUsername_thenReturnBagEntity() {
    	
    	//persist a bag and then make sure we can retrieve it by username which is the natural key of the bag
    	Optional<BagEntity> found = bagService.findByCode("bob@bob");
    	
    	//then
    	assertEntityFound(found);
    }
    
    @Test
    @Rollback(false)
	@WithUserDetails(value = "admin")
    public void whenFindByUsername_thenReturnBagDTO() {
    	
    	//persist a bag and then make sure we can retrieve it by username which is the natural key of the bag
    	Optional<BagDTO> found = bagService.findByCode(Constants.localeENGB, Constants.currencyHKD, Constants.primaryProductRootCategoryCode, "bob@bob");
    	
    	//then
    	assertDTOFound(found);
    }
 
    
    private void assertEntityFound(Optional<BagEntity> bag) {
    	assertNotNull(bag);
    	
    	assertTrue(bag.isPresent());
    	
    	assertNotNull(bag.get().getBagCreatedDateTime());
    	assertNotNull(bag.get().getBagUpdatedDateTime());
    }
    
    private void assertDTOFound(Optional<BagDTO> bag) {
    	assertNotNull(bag);
    	
    	assertTrue(bag.isPresent());
    	
    	BagDTO bDto = bag.get();
    	
    	assertThat(bDto.getCustomer().getCustomerNumber()).isEqualTo("1000000268");
    	
    	assertThat(bDto.getCustomer().getGivenName()).isEqualTo("bob");
    			
    	assertThat(bDto.getCustomer().getFamilyName()).isEqualTo("bob");
    			
    	assertThat(bDto.getCustomer().getUserName()).isEqualTo("bob@bob");
    	
    	assertThat(bDto.getBagItems().size()).isEqualTo(0);
    }
 
}