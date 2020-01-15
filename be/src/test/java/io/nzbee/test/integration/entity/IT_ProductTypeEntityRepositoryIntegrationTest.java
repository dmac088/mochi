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
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.product.type.IProductTypeRepository;
import io.nzbee.entity.product.type.ProductType;
import io.nzbee.test.integration.beans.ProductTypeEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
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
public class IT_ProductTypeEntityRepositoryIntegrationTest {
	
	@TestConfiguration
    static class ProductProductTypeEntityRepositoryIntegrationTest {
          
        @Bean(value = "productTypeEntityBeanFactory")
        public ProductTypeEntityBeanFactory productTypeFactoryBean() {
            return new ProductTypeEntityBeanFactory();
        }
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private ProductTypeEntityBeanFactory productTypeEntityBeanFactory;
	
    @Autowired
    private IProductTypeRepository productTypeRepository;
	
    private ProductType productType = null;
    
	@Before
    public void setUp() { 
    	productType = this.persistNewProductType();
    }
	
	public ProductType persistNewProductType() {
    	
		productType = productTypeEntityBeanFactory.getProductTypeEntityBean();
	   
	    //persist a new transient test category
	    entityManager.persist(productType);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return productType;
	}

	 @Test
	    public void whenFindById_thenReturnProductType() {
	    	
	        // when
	    	ProductType found = productTypeRepository.findById(productType.getId()).get();
	     
	        // then
	    	assertFound(found);
	    }
	
	 private void assertFound(final ProductType found) {
	    	
	    	assertThat(found.getCode())
	        .isEqualTo("TST01");
	
	    	assertThat(found.getDesc())
	        .isEqualTo("test product type");
	
	 }
	 
	 @After
	 public void closeConnection() {
	  	entityManager.close();
	 }
	 
}
