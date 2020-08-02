package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.test.integration.beans.CategoryEntityBeanFactory;

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
public class IT_ProductCategoryEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class ProductCategoryEntityRepositoryIntegrationTest {
        
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
 
    @Autowired
    private ICategoryService categoryService;
   
    
    private io.nzbee.entity.category.Category category = null;
    
    @Before
    public void setUp() { 
    	this.persistNewCategory();
    }
    
    
	public io.nzbee.entity.category.Category persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getProductCategoryEntityBean();
	    
	    //persist a new transient test category
	    entityManager.persist(category);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return category;
	}
   
    
    @Test
    public void whenFindById_thenReturnProductCategory() {
    	
        // when
    	Category found = categoryService.findById(Constants.localeENGB, 
												  Constants.currencyUSD,  
												  category.getCategoryId()).get();
     
        // then
    	assertFound(found);
    }
    
    @Test
    public void whenFindByCode_thenReturnProductCategory() {
    	
        // when
    	Category found = categoryService.findByCode(Constants.localeENGB, 
				  									Constants.currencyUSD, 
				 									"TST02").get();
     
        // then
    	assertFound(found);
    }
    
    @Test
    public void whenFindByDesc_thenReturnProductCategory() {
    	
        // when
    	Category found = categoryService.findByDesc(Constants.localeENGB, 
				  									Constants.currencyUSD, 
				 									"test product category").get();
     
        //then
    	assertFound(found);
    }
    
    @Test
    public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	Set<String> tags = new HashSet<String>();
    	
    	//when
    	List<Category> lc = categoryService.findAll(Constants.localeENGB, 
    												Constants.currencyUSD, 
    												"FRT01",
    												categories,
    												brands, 
    												tags, 
    												null);
     
        //then
    	assertNotNull(lc);
    	assertThat(lc.size())
        .isEqualTo(7);
    }
    
    @Test
    public void whenFindAllWithBrandFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	brands.add("ENZ01");
    	Set<String> tags = new HashSet<String>();
    	
    	//when
    	List<Category> lc = categoryService.findAll(Constants.localeENGB, 
    												Constants.currencyUSD, 
    												"FRT01", 
    												categories,
    												brands, 
    												tags, 
    												null);
     
        //then
    	assertNotNull(lc);
    	assertThat(lc.size())
        .isEqualTo(1);
    }
    
    @Test
    public void whenFindAllWithTagFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	Set<String> tags = new HashSet<String>();
    	tags.add("GFR01");
    	
    	//when
    	List<Category> lc = categoryService.findAll(Constants.localeENGB, 
    												Constants.currencyUSD, 
    												"FRT01", 
    												categories,
    												brands, 
    												tags, 
    												null);
     
        //then
		assertNotNull(lc);
    	assertThat(lc.size())
        .isEqualTo(1);
    }
    
  
    private void assertFound(final Category found) {
    	
    	assertThat(found.getCategoryCode())
        .isEqualTo("TST02");
	    assertThat(found.getCategoryLevel())
	    .isEqualTo(new Long(1));
	    assertThat(found.getCategoryType().getCategoryTypeCode())
	    .isEqualTo("PRD01");
	    assertThat(found.getAttributes().stream().filter(a -> a.getLclCd().equals("en-GB")).findFirst().get().getCategoryDesc())
	    .isEqualTo("test product category");
    }

 
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}