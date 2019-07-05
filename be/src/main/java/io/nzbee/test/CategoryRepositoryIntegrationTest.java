package io.nzbee.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.Category;
import io.nzbee.entity.CategoryAttribute;
import io.nzbee.entity.CategoryRepository;
import io.nzbee.entity.CategoryType;
import io.nzbee.entity.CategoryTypeRepository;
import io.nzbee.entity.Hierarchy;
import io.nzbee.entity.HierarchyRepository;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CategoryRepositoryIntegrationTest {
 
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
 
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryTypeRepository categoryTypeRepository;

    @Autowired
    private HierarchyRepository hierarchyRepository;

    
    public Category persistNewCategory() {
    	Category 		category 		= new Category();
    	CategoryType 	categoryType 	= categoryTypeRepository.findByCategoryTypeId(new Long(1));
    	Hierarchy 		hierarchy 		= hierarchyRepository.findByCode(CategoryVars.PRIMARY_HIERARCHY_CODE);
    	Category 		parentCategory 	= categoryRepository.findByCategoryCode(CategoryVars.PRIMARY_HIERARCHY_ROOT_CODE);
    	
    	category.setCategoryCode("TST01");
    	category.setCategoryLevel(new Long(1));
    	category.setCategoryType(categoryType);
    	category.setHierarchy(hierarchy);
    	category.setParent(parentCategory);
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
    	Category found = categoryRepository.findByCategoryCode("TST01");
     
        // then
        assertThat(found.getCategoryCode())
          .isEqualTo(category.getCategoryCode());
        assertThat(found.getAttributes().stream().filter(a -> a.getLclCd().equals(GeneralVars.LANGUAGE_ENGLISH)).findFirst().get().getCategoryDesc())
        .isEqualTo(category.getAttributes().stream().filter(a -> a.getLclCd().equals(GeneralVars.LANGUAGE_ENGLISH)).findFirst().get().getCategoryDesc());
    }
    
    @Test
    public void whenFindById_thenReturnCategory() {
    	Category category = this.persistNewCategory();
        
        // when
    	Category found = categoryRepository.findByCategoryId(category.getCategoryId());
     
        // then
        assertThat(found.getCategoryCode())
          .isEqualTo(category.getCategoryCode());
        assertThat(found.getAttributes().stream().filter(a -> a.getLclCd().equals(GeneralVars.LANGUAGE_ENGLISH)).findFirst().get().getCategoryDesc())
        .isEqualTo(category.getAttributes().stream().filter(a -> a.getLclCd().equals(GeneralVars.LANGUAGE_ENGLISH)).findFirst().get().getCategoryDesc());
    }
 
}