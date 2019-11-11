package io.nzbee.test.integration.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.dto.category.ICategoryService;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import io.nzbee.variables.GeneralVars;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.category.CategoryServiceImpl;

@RunWith(SpringRunner.class)
public class IT_CategoryDtoServiceImplIntegrationTest {

	@TestConfiguration
    static class CategoryDtoServiceImplIntegrationTest {
		//the beans that we need to run this integration test
        @Bean(value = "categoryDtoService")
        public ICategoryService categoryDtoService() {
            return new CategoryServiceImpl();
        }
        
        @Bean(value = "categoryEntityService")
        public io.nzbee.entity.category.ICategoryService categoryService() {
            return new io.nzbee.entity.category.CategoryServiceImpl();
        }
    }

	@Autowired
    private ICategoryService categoryDtoService;
 
	@MockBean
    private io.nzbee.entity.category.ICategoryService categoryEntityService;
	
    @Before
    public void setUp() {
    	//we setup a mock so that when 
    	MockitoAnnotations.initMocks(this);
        
        io.nzbee.entity.category.Category 			category 		= new CategoryProduct();
        CategoryType 								categoryType 	= new CategoryType();
        io.nzbee.entity.category.Category 			parentCategory 	= new CategoryProduct();
        
        categoryType.setId(new Long(1));
    	Hierarchy 		hierarchy 		= parentCategory.getHierarchy();
    	
    	category.setCategoryCode("TST01");
    	category.setCategoryLevel(new Long(1));
    	category.setCategoryType(categoryType);
    	category.setParent(parentCategory);
    	category.setHierarchy(hierarchy);
    	
    	List<CategoryAttribute> categoryAttributes = new ArrayList<CategoryAttribute>();
    	CategoryAttribute categoryAttribute = new CategoryAttribute();
    	categoryAttribute.setCategory(Optional.ofNullable(category));
    	categoryAttribute.setCategoryId(category.getCategoryId());
    	categoryAttribute.setCategoryDesc("testCategory");
    	categoryAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
    	categoryAttributes.add(categoryAttribute);
    	category.setAttributes(categoryAttributes);
    	category.setCategoryAttribute(categoryAttribute);
        
        
        //need to fill more of the properties here
        
        Mockito.when(categoryEntityService.findByCode("en-GB", "HKD", category.getCategoryCode()))
          .thenReturn(Optional.ofNullable(category));
    }
    
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String code = "TST01";
        String desc = "testCategory";
        
        Optional<Category> found = categoryDtoService.findByCode("en-GB", "HKD", code);
      
        
         assertThat(found.get().getCategoryCode())
          .isEqualTo(code);
         assertThat(found.get().getCategoryDesc())
          .isEqualTo(desc);
     }
    
}
