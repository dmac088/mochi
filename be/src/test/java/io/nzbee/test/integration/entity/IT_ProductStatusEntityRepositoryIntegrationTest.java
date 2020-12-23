package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.test.integration.entity.beans.ProductStatusEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
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
public class IT_ProductStatusEntityRepositoryIntegrationTest {
	
	@TestConfiguration
    static class ProductProductStatusEntityRepositoryIntegrationTest {

    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private ProductStatusEntityBeanFactory productStatusEntityBeanFactory;
	
    @Autowired
    private IProductStatusRepository productStatusRepository;
	
    private ProductStatusEntity productStatus = null;
    
	@Before
    public void setUp() { 
    	productStatus = this.persistNewProductStatus();
    }
	
	public ProductStatusEntity persistNewProductStatus() {
    	
		productStatus = productStatusEntityBeanFactory.getProductStatusEntityBean();
	   
	    //persist a new transient test category
	    entityManager.persist(productStatus);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return productStatus;
	}

	 @Test
	 public void whenFindById_thenReturnProductStatus() {
	    	
	        // when
	    	ProductStatusEntity found = productStatusRepository.findById(productStatus.getProductStatusId()).get();
	     
	        // then
	    	assertFound(found);
	 }
	 
	 @Test
	 public void whenFindByCode_thenReturnProductStatus() {
	    	
	        // when
	    	ProductStatusEntity found = productStatusRepository.findByProductStatusCode(productStatus.getCode()).get();
	     
	        // then
	    	assertFound(found);
	 }
	
	 private void assertFound(final ProductStatusEntity found) {
	    	
	    	assertThat(found.getCode())
	        .isEqualTo("TST01");
	
	    	assertThat(found.getDesc())
	        .isEqualTo("test product status");
	
	 }
	 
	 @After
	 public void closeConnection() {
	  	entityManager.close();
	 }
}
