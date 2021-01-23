package io.nzbee.test.integration.entity.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class IT_ProductCategoryEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class ProductCategoryEntityRepositoryIntegrationTest {
        
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
 
    @Autowired
    private ICategoryService categoryService;
    
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
   
    private static CategoryEntity category = null;
    
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
		this.createCategory();
		setUpIsDone = true;
	}
    
    public void createCategory() { 
    	this.persistNewCategory();
    }
    
    
	public io.nzbee.entity.category.CategoryEntity persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getProductCategoryEntityBean();
	    
	    //persist a new transient test category
		categoryService.save(category);
	    	
	    return category;
	}
   
    
    @Test
    @Rollback(false)
    public void whenFindById_thenReturnProductCategoryEntity() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findById(category.getCategoryId());
     
        // then
    	assertFoundEntity(found);
    }
    
    @Test
    @Rollback(false)
    public void whenFindByCode_thenReturnProductCategoryEntity() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findByCode("TST02");
     
        // then
    	assertFoundEntity(found);
    }
   
    
    @Test
    @Rollback(false)
    public void whenFindByCode_thenReturnProductCategoryDTO() {
    	
        // when
    	Optional<CategoryDTO> found = categoryService.findByCode("en-GB", "TST02");
     
        // then
    	assertFoundDTO(found);
    }
    
    @Test
    @Rollback(false)
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
    @Rollback(false)
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
    @Rollback(false)
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
    @Rollback(false)
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
    @Rollback(false)
    public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	Set<String> tags = new HashSet<String>();
    	
    	//when
    	List<CategoryDTO> lc = categoryService.findAll(	Constants.localeENGB, 
		    											Constants.currencyUSD, 
		    											"FRT01",
		    											new StringCollectionWrapper(categories),
		    											new StringCollectionWrapper(brands), 
		    											new StringCollectionWrapper(tags), 
		    											null);

        //then
    	assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(7);
    }
    
    @Test
    @Rollback(false)
    public void whenFindAllWithBrandFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	brands.add("ENZ01");
    	Set<String> tags = new HashSet<String>();
    	
    	//when
    	List<CategoryDTO> lc = categoryService.findAll(	Constants.localeENGB, 
    													Constants.currencyUSD, 
    													"FRT01", 
    													new StringCollectionWrapper(categories),
    													new StringCollectionWrapper(brands), 
    													new StringCollectionWrapper(tags), 
    													null);
     
        //then
    	assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(1);
    }
    
    @Test
    @Rollback(false)
    public void whenFindAllWithTagFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	Set<String> tags = new HashSet<String>();
    	tags.add("GFR01");
    	
    	//when
    	List<CategoryDTO> lc = categoryService.findAll(		Constants.localeENGB, 
		    												Constants.currencyUSD, 
		    												"FRT01", 
		    												new StringCollectionWrapper(categories),
		    												new StringCollectionWrapper(brands), 
		    												new StringCollectionWrapper(tags), 
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

}