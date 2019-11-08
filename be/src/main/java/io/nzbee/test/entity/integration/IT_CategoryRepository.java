package io.nzbee.test.entity.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.ICategoryTypeRepository;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_CategoryRepository {
 
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
 
    @Autowired
    private ICategoryService categoryService;
    
    @Autowired
    private ICategoryTypeRepository categoryTypeRepository;
    
    public Category persistNewCategory() {
    	Category 		category 		= new CategoryProduct();
    	CategoryType 	categoryType 	= categoryTypeRepository.findById(new Long(1)).get();
    	Category 		parentCategory 	= categoryService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
    																 	 GeneralVars.CURRENCY_USD, 
    																 	 "PRM01").get();
    	Hierarchy 		hierarchy 		= parentCategory.getHierarchy();
    	
    	System.out.println(hierarchy.getHierarchyCode());
    	
    	category.setCategoryCode("TST01");
    	category.setCategoryLevel(new Long(1));
    	category.setCategoryType(categoryType);
    	category.setParent(parentCategory);
    	category.setHierarchy(hierarchy);
    	entityManager.persist(category);
    	
    	List<CategoryAttribute> categoryAttributes = new ArrayList<CategoryAttribute>();
    	CategoryAttribute categoryAttribute = new CategoryAttribute();
    	categoryAttribute.setCategory(Optional.ofNullable(category));
    	categoryAttribute.setCategoryId(category.getCategoryId());
    	categoryAttribute.setCategoryDesc("testCategory");
    	categoryAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
    	categoryAttributes.add(categoryAttribute);
    	category.setAttributes(categoryAttributes);
    	entityManager.persist(categoryAttribute);
    	entityManager.flush();
    	
    	return category;
    }
    
    // write test cases here
    @Test
    public void whenFindByCode_thenReturnCategory() {
    	Category category = this.persistNewCategory();
    	
        // when
    	Category found = categoryService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
				 									GeneralVars.CURRENCY_USD, 
				 									category.getCategoryCode()).get();
     
        // then
    	assertFound(category, found);
    }
    
    @Test
    public void whenFindById_thenReturnCategory() {
    	Category category = this.persistNewCategory();
       
    	
        // when
    	Category found = categoryService.findById(GeneralVars.LANGUAGE_ENGLISH, 
												  GeneralVars.CURRENCY_USD,  
    											  category.getCategoryId()).get();
     
        // then
    	assertFound(category, found);
    }
    
    private void assertFound(final Category category, final Category found) {

    	
    	assertThat(found.getCategoryCode())
        .isEqualTo(category.getCategoryCode());
//	    assertThat(found.getCategoryLevel())
//	    .isEqualTo(category.getCategoryLevel());
//	    assertThat(found.getCategoryType().getCode())
//	    .isEqualTo(category.getCategoryType().getCode());
	  //  assertThat(found.getAttributes().stream().filter(a -> a.getLclCd().equals(GeneralVars.LANGUAGE_ENGLISH)).findFirst().get().getCategoryDesc())
	  //  .isEqualTo(category.getAttributes().stream().filter(a -> a.getLclCd().equals(GeneralVars.LANGUAGE_ENGLISH)).findFirst().get().getCategoryDesc());
    }
 
}