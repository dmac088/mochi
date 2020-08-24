package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.bag.item.BagItem;
import io.nzbee.entity.bag.Bag;
import io.nzbee.entity.bag.item.IBagItemService;
import io.nzbee.entity.bag.status.BagStatus;
import io.nzbee.entity.bag.status.IBagStatusService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.Person;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.role.customer.Customer;
import io.nzbee.test.integration.beans.BagEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
@SqlGroup({
	@Sql(scripts = "/database/mochi_schema.sql",
			config = @SqlConfig(dataSource = "mochiDataSourceOwner", 
			transactionManager = "mochiTransactionManagerOwner",
			transactionMode = TransactionMode.ISOLATED)), 
	@Sql(scripts = "/database/mochi_data.sql",
			config = @SqlConfig(dataSource = "mochiDataSource", 
			transactionManager = "mochiTransactionManager",
			transactionMode = TransactionMode.ISOLATED))
})
public class IT_BagItemEntityRepositoryIntegrationTest {
 
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private BagEntityBeanFactory bagEntityBeanFactory;
 
    @Autowired
    private IBagItemService bagItemService;
    
    @Autowired
    private IProductService productService;
    
    @Autowired
    private IBagStatusService bagStatusService;
    
	@Autowired
    private IPersonService personService;
 
	private BagItem bagItem = null;
	
	@MockBean
    private JavaMailSender mailSender;
    
    @Before
    public void setUp() { 
    	this.persistNewBag();
    }
    
	public BagItem persistNewBag() {
		
		Optional<Person> p = personService.findByUsernameAndRole("dmac088", Customer.class);
    	Optional<BagStatus> bs = bagStatusService.findByCode(Constants.bagStatusCodeNew);
    	
		Bag bag = bagEntityBeanFactory.getBagEntityBean(p.get());
	    bag.setBagStatus(bs.get());
	    
	    Product product = productService.findByCode("23464789").get();
	        
	    bagItem = new BagItem(product);
	    bagItem.setQuantity(2);
	    bag.addItem(bagItem);
	    
	    entityManager.persist(bag);
	    
	    return bagItem;
	}
   
    
    @Test
	@WithUserDetails(value = "admin")
    public void whenFindById_thenReturnBagItem() {
    	
    	Long itemId = bagItem.getBagItemId();
    	
        // when
    	BagItem found = bagItemService.findById(itemId).get();
     
        // then
    	assertFound(found);
    }
 
    
    private void assertFound(final BagItem found) {
    	assertNotNull(found);
    	
    	assertThat(found.getQuantity())
	    .isEqualTo(2);
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
 
}