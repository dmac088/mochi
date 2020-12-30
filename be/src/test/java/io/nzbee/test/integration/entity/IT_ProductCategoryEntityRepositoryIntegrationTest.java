package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.category.CategoryDTO;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductDTO;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.test.integration.entity.beans.category.CategoryEntityBeanFactory;

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
public class IT_ProductCategoryEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class ProductCategoryEntityRepositoryIntegrationTest {
        
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
 
    @Autowired
    private ICategoryService categoryService;
   
    
    private CategoryEntity category = null;
    
    @Before
    public void setUp() { 
    	this.persistNewCategory();
    }
    
    
	public io.nzbee.entity.category.CategoryEntity persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getProductCategoryEntityBean();
	    
	    //persist a new transient test category
	    entityManager.persist(category);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return category;
	}
   
    
    @Test
    public void whenFindById_thenReturnProductCategoryEntity() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findById(category.getCategoryId());
     
        // then
    	assertFoundEntity(found);
    }
    
    @Test
    public void whenFindByCode_thenReturnProductCategoryEntity() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findByCode("TST02");
     
        // then
    	assertFoundEntity(found);
    }
    
   
    @Test
    public void whenFindByDesc_thenReturnProductCategoryEntity() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findEntityByDesc(Constants.localeENGB,
				 														  "test product category");
     
        //then
    	assertFoundEntity(found);
    }
    
    @Test
    public void whenFindByCode_thenReturnProductCategoryDTO() {
    	
        // when
    	Optional<CategoryDTO> found = categoryService.findByCode("en-GB", "TST02");
     
        // then
    	assertFoundDTO(found);
    }
    
    @Test
    public void whenFindFruitCategory_thenReturnProductCategoryDTO() {
    	
        // when
    	Optional<CategoryDTO> found = categoryService.findByCode("en-GB", "FRT01");
     
        // then
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	CategoryProductDTO cp = (CategoryProductDTO) found.get();
    	
    	assertThat(cp.getCategoryCode())
        .isEqualTo("FRT01");
    	
	    assertThat(cp.getCategoryLevel())
	    .isEqualTo(new Long(1));
	    
	    assertThat(cp.getCategoryDesc())
	    .isEqualTo("Fruit");
	    
	    assertThat(cp.getChildCategoryCount())
	    .isEqualTo(7);
	    
	    assertThat(cp.getCount())
	    .isEqualTo(12);
    }
    
    @Test
    public void whenFindVegetableCategory_thenReturnProductCategoryDTO() {
    	
        // when
    	Optional<CategoryDTO> found = categoryService.findByCode("en-GB", "VEG01");
     
        // then
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	CategoryProductDTO cp = (CategoryProductDTO) found.get();
    	
    	assertThat(cp.getCategoryCode())
        .isEqualTo("VEG01");
    	
	    assertThat(cp.getCategoryLevel())
	    .isEqualTo(new Long(1));
	    
	    assertThat(cp.getCategoryDesc())
	    .isEqualTo("Vegetables");
	    
	    assertThat(cp.getChildCategoryCount())
	    .isEqualTo(7);
	    
	    assertThat(cp.getCount())
	    .isEqualTo(12);
    }
    
    @Test
    public void whenFindRedAndOrangeVegetablesCategory_thenReturnProductCategoryDTO() {
    	
        // when
    	Optional<CategoryDTO> found = categoryService.findByCode("en-GB", "ROV01");
     
        // then
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	CategoryProductDTO cp = (CategoryProductDTO) found.get();
    	
    	assertThat(cp.getCategoryCode())
        .isEqualTo("ROV01");
    	
	    assertThat(cp.getCategoryLevel())
	    .isEqualTo(new Long(2));
	    
	    assertThat(cp.getCategoryDesc())
	    .isEqualTo("Red and Orange");
	    
	    assertThat(cp.getChildCategoryCount())
	    .isEqualTo(2);
	    
	    assertThat(cp.getCount())
	    .isEqualTo(1);
    }
    
    @Test
    public void whenFindPomesCategory_thenReturnProductCategoryDTO() {
    	
        // when
    	Optional<CategoryDTO> found = categoryService.findByCode("en-GB", "POM01");
     
        // then
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	CategoryProductDTO cp = (CategoryProductDTO) found.get();
    	
    	assertThat(cp.getCategoryCode())
        .isEqualTo("POM01");
    	
	    assertThat(cp.getCategoryLevel())
	    .isEqualTo(new Long(2));
	    
	    assertThat(cp.getCategoryDesc())
	    .isEqualTo("Pomes");
	    
	    assertThat(cp.getChildCategoryCount())
	    .isEqualTo(0);
	    
	    assertThat(cp.getCount())
	    .isEqualTo(3);
    }
    
    @Test
    public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	Set<String> tags = new HashSet<String>();
    	
    	//when
    	List<CategoryDTO> lc = categoryService.findAll(	Constants.localeENGB, 
		    											Constants.currencyUSD, 
		    											"FRT01",
		    											categories,
		    											brands, 
		    											tags, 
		    											null);

        //then
    	assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(7);
    }
    
    @Test
    public void whenFindAllWithBrandFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	brands.add("ENZ01");
    	Set<String> tags = new HashSet<String>();
    	
    	//when
    	List<CategoryDTO> lc = categoryService.findAll(	Constants.localeENGB, 
    													Constants.currencyUSD, 
    													"FRT01", 
    													categories,
    													brands, 
    													tags, 
    													null);
     
        //then
    	assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(1);
    }
    
    @Test
    public void whenFindAllWithTagFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	Set<String> tags = new HashSet<String>();
    	tags.add("GFR01");
    	
    	//when
    	List<CategoryDTO> lc = categoryService.findAll(		Constants.localeENGB, 
		    												Constants.currencyUSD, 
		    												"FRT01", 
		    												categories,
		    												brands, 
		    												tags, 
		    												null);
     
        //then
		assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(1);
    }
    
  
    private void assertFoundEntity(Optional<CategoryEntity> found) {
    	
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	CategoryProductEntity cp = (CategoryProductEntity) found.get();
    	
    	assertThat(cp.getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(cp.getCategoryLevel())
	    .isEqualTo(new Long(1));
	    
	    assertThat(cp.getCategoryDescENGB())
	    .isEqualTo("test product category");
    }
    
    private void assertFoundDTO(Optional<CategoryDTO> found) {
    	
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	CategoryProductDTO cp = (CategoryProductDTO) found.get();
    	
    	assertThat(cp.getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(cp.getCategoryLevel())
	    .isEqualTo(new Long(1));
	    
	    assertThat(cp.getCategoryDesc())
	    .isEqualTo("test product category");
	    
    }

 
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}