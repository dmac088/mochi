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
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.department.IDepartmentRepository;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.test.integration.beans.ProductEntityBeanFactory;
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
public class IT_ProductAttributeEntityRepositoryIntegrationTest {

	@TestConfiguration
    static class ProductEntityRepositoryIntegrationTest {
        
        @Bean(value = "productEntityService")
        public io.nzbee.entity.product.IProductService productService() {
            return new io.nzbee.entity.product.ProductServiceImpl();
        }
        
        @Bean(value = "productEntityBeanFactory")
        public ProductEntityBeanFactory productFactoryBean() {
            return new ProductEntityBeanFactory();
        }
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private ProductEntityBeanFactory productEntityBeanFactory;
 
    @Autowired
    private IProductService productService;
    
    @Autowired
    private IBrandService brandService;
    
    @Autowired
    private IDepartmentRepository productTypeRepository;
    
    @Autowired
    private IProductStatusRepository productStatusRepository;
    
    @Autowired
    private ICategoryService categoryService;
    
    private ProductAttribute pa = null;
    
	public ProductAttribute persistNewProduct() {
    	
		pa = productEntityBeanFactory.getProductAttributeEntityBean();
	    
		//we need a brand
		pa.getProduct().setBrand(brandService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
														 GeneralVars.CURRENCY_HKD, 
														 "PLA01").get());
		
		
		//we need a type
		pa.getProduct().setDepartment(productTypeRepository.findByDepartmentCode("FOO01").get());
		
		//we need a status
		pa.getProduct().setProductStatus(productStatusRepository.findByProductStatusCode("ACT01").get());
		
		//we need a category
		CategoryProduct cp = (CategoryProduct) categoryService.findByCode(	GeneralVars.LANGUAGE_ENGLISH, 
																			GeneralVars.CURRENCY_HKD,
																			"FRT01").get();
		
		
		//add the product to the category
		pa.getProduct().addProductCategory(cp);
		
	    entityManager.persist(pa.getProduct());
	    entityManager.flush();
	    	
	    return pa;
	}
	
	@Before
	public void persistANewProduct() {
		this.persistNewProduct();
	}
	
	@Test
	public void whenFindById_thenReturnProduct() {
		 // when
    	Product found = productService.findById(  GeneralVars.LANGUAGE_ENGLISH, 
												  GeneralVars.CURRENCY_USD,  
												  pa.getProductId()).get();
     
        // then
    	assertFound(found);
	}
	
	
	@Test
	public void whenFindByCode_thenReturnProduct() {
		 // when
    	Product found = productService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
												  GeneralVars.CURRENCY_USD,  
												  "123456789").get();
     
        // then
    	assertFound(found);
	}
	 
    private void assertFound(final Product found) {
    	assertThat(found.getUPC())
        .isEqualTo("123456789");
    	assertThat(found.getCategories().stream().filter(f -> f.getCategoryCode().equals("FRT01")).findFirst().isPresent())
    	.isTrue();
    	assertThat(found.getDepartment().getCode())
    	.isEqualTo("FOO01");
    	assertThat(found.getProductStatus().getCode())
    	.isEqualTo("ACT01");
    	assertThat(found.getBrand().getBrandCode())
    	.isEqualTo("PLA01");
    	assertThat(found.getProductAttribute().getProductDesc())
    	.isEqualTo("test product");
    	assertThat(found.getAttributes().stream().filter(pa -> pa.getLclCd().equals("en-GB")).findFirst().get().getProductDesc())
    	.isEqualTo("test product");
    	assertThat(found.getAttributes().stream().filter(pa -> pa.getLclCd().equals("zh-HK")).findFirst().get().getProductDesc())
    	.isEqualTo("測試產品");
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}
