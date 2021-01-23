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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.bag.item.BagItemEntity;
import io.nzbee.Constants;
import io.nzbee.entity.bag.BagDTO;
import io.nzbee.entity.bag.BagEntity;
import io.nzbee.entity.bag.IBagService;
import io.nzbee.entity.bag.item.IBagItemService;
import io.nzbee.entity.bag.status.BagItemStatus;
import io.nzbee.entity.bag.status.IBagItemStatusService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.test.integration.entity.beans.bag.IBagEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_BagItemEntityRepositoryIntegrationTest {
	
	@Autowired
	private IBagEntityBeanFactory bagEntityBeanFactory;

    @Autowired
    private IBagService bagService;
	
    @Autowired
    private IBagItemService bagItemService;
    
    @Autowired
    private IBagItemStatusService bagItemStatus;
    
    @Autowired
    private IProductService productService;
    
	@Autowired
    private IPersonService personService;
 
	private BagItemEntity bagItem = null;
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
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
		this.persistNewBag();
		setUpIsDone = true;
	}
 
    
	public BagItemEntity persistNewBag() {
		
		Optional<PersonEntity> p = personService.findByUsernameAndRole("bob@bob", "Customer");
    	
		BagEntity bag = bagEntityBeanFactory.getBean(p.get());
	    
	    ProductEntity product = productService.findByCode("76477789").get();
	        
	    Optional<BagItemStatus> bis = bagItemStatus.findByCode(Constants.bagStatusCodeNew);
	    
	    bagItem = new BagItemEntity(product);
	    bagItem.setQuantity(2);
	    bagItem.setBagItemStatus(bis.get());
	    bag.addItem(bagItem);
	    
	    bagService.save(bag);
	    
	    return bagItem;
	}
   
    
    @Test
	@WithUserDetails(value = "admin")
    public void whenFindById_thenReturnBagItem() {
    	
    	Long itemId = bagItem.getBagItemId();
    	
        // when
    	BagItemEntity found = bagItemService.findById(itemId).get();
     
        // then
    	assertFound(found);
    }
 
    @Test
 	@WithUserDetails(value = "admin")
     public void whenFindByUsername_thenReturnBagDTOWithCorrectItems() {
     	
     	//persist a bag and then make sure we can retrieve it by username which is the natural key of the bag
     	Optional<BagDTO> found = bagService.findByCode(Constants.localeENGB, Constants.currencyHKD, "bob@bob");
     	
     	//then
     	assertDTOFound(found);
     }
    
    private void assertFound(final BagItemEntity found) {
    	assertNotNull(found);
    	
    	assertThat(found.getQuantity())
	    .isEqualTo(2);
    }
    
    private void assertDTOFound(Optional<BagDTO> bag) {
    	assertNotNull(bag);
    	
    	assertTrue(bag.isPresent());
    	
    	BagDTO bDto = bag.get();
    	
    	assertTrue(bDto.getBagItems().stream().filter(bi -> bi.getProduct().getProductUPC().equals("76477789")).findAny().isPresent());
    	
    	assertThat(bDto.getBagItems().stream().filter(bi -> bi.getProduct().getProductUPC().equals("76477789")).findAny().get().getQuantity()).isEqualTo(2);
    	
    	assertThat(bDto.getBagItems().stream().filter(bi -> bi.getProduct().getProductUPC().equals("76477789"))
				.findAny().get()
				.getProduct().getPromotions()
				.stream().filter(promo -> promo.getPromotionCode().equals("B2G50")).findAny().isPresent()).isTrue();
    	
    }

 
}