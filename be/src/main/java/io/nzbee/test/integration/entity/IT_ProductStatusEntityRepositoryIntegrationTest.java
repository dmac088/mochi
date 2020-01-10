package io.nzbee.test.integration.entity;

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
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;

import io.nzbee.entity.category.Category;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.test.integration.beans.ProductStatusEntityBeanFactory;
import io.nzbee.variables.GeneralVars;

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
public class IT_ProductStatusEntityRepositoryIntegrationTest {

    private io.nzbee.entity.product.status.ProductStatus productStatus = null;
	
	@TestConfiguration
    static class ProductProductStatusEntityRepositoryIntegrationTest {
          
        @Bean(value = "productStatusEntityBeanFactory")
        public ProductStatusEntityBeanFactory productStatusFactoryBean() {
            return new ProductStatusEntityBeanFactory();
        }
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private ProductStatusEntityBeanFactory productStatusEntityBeanFactory;
	
	@Before
    public void setUp() { 
    	this.persistNewProductStatus();
    }
	
	public ProductStatus persistNewProductStatus() {
    	
		productStatus = productStatusEntityBeanFactory.getProductStatusEntityBean();
	   
	    //persist a new transient test category
	    entityManager.persist(productStatus);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return productStatus;
	}

	
	
}
