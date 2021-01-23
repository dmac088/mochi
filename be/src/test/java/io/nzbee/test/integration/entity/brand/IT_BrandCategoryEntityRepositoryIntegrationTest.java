package io.nzbee.test.integration.entity.brand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
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
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.resources.product.ProductLightResource;
import io.nzbee.test.integration.entity.beans.category.CategoryEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_BrandCategoryEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class BrandCategoryEntityRepositoryIntegrationTest {
            
        @Bean(value = "pagedProductResourceAssembler")
        public PagedResourcesAssembler<ProductLightResource> pagedProductResourceAssembler() {
        	return new PagedResourcesAssembler<ProductLightResource>(null, null);
        }
        
        @Bean(value = "pagedProductAssembler")
        public PagedResourcesAssembler<Product> pagedProductAssembler() {
        	return new PagedResourcesAssembler<Product>(null, null);
        }
        
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
 
    @Autowired
    private ICategoryService categoryService;
    
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
 
	private static CategoryEntity category = null;
	
	private static boolean setUpIsDone = false;
	
	@MockBean
    private JavaMailSender mailSender;
    
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
		this.persistNewCategory();
		setUpIsDone = true;
	}
	
    
	public io.nzbee.entity.category.CategoryEntity persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getBrandCategoryEntityBean();
	    	
	    //persist a new transient test category
	    entityManager.persist(category);
	    
	    return category;
	}
   
    
    @Test
    @Rollback(false)
    public void whenFindById_thenReturnBrandCategory() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findById(category.getCategoryId());
     
        // then
    	assertFound(found);
    }
    
    // write test cases here
    @Test
    @Rollback(false)
    public void whenFindByCode_thenReturnBrandCategory() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findByCode("TST03");
     
        // then
    	assertFound(found);
    }
    
    
    private void assertFound(Optional<CategoryEntity> found) {
    	
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getClass().getSimpleName())
    	.isEqualTo(CategoryBrandEntity.class.getSimpleName());
    	
    	CategoryBrandEntity cb = (CategoryBrandEntity) found.get();
    	
    	assertThat(cb.getCategoryCode())
        .isEqualTo("TST03");
    	
//	    assertThat(cb.getCount())
//	    .isEqualTo(new Long(2));
	    
	    assertThat(cb.getCategoryDescENGB())
	    .isEqualTo("test brand category");
	    
	  
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
 
}