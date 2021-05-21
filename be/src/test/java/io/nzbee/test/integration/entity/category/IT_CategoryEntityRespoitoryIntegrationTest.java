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
import java.util.stream.Collectors;
import javax.sql.DataSource;
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
import com.google.common.collect.Ordering;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.view.facet.IProductCategoryFacetDTOService;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTO;
import io.nzbee.test.integration.entity.beans.category.ICategoryEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class IT_CategoryEntityRespoitoryIntegrationTest {

	@TestConfiguration
	static class CategoryEntityRepositoryIntegrationTest {

	}

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IProductCategoryFacetDTOService categoryFacetService;
	
	@Autowired
	private ICategoryEntityBeanFactory categoryEntityBeanFactory;
	
	@Autowired
    @Qualifier("mochiDataSourceOwner")
    private DataSource database;
	
	private static boolean setUpIsDone = false;
	
    @Before
	public void persistANewCategory() {
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
    
    private CategoryEntity category = null;
    
	public void persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getBean();
		
	    //persist a new transient test category
	    categoryService.save(category);
	}
	
    @Test
    @Rollback(false)
    public void whenFindByCode_thenReturnCategoryEntity() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findByCode("TST02");
     
        // then
    	assertFoundEntity(found);
    }
    
	@Test
	@Rollback(false)
	public void whenFindAll_thenReturnAllCategories() {

		// when
		List<ProductCategoryFacetDTO> found = categoryFacetService.findAll(Constants.localeENGB, Constants.primaryProductRootCategoryCode);

		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(81);
		assertTrue(isOrdered(found));
	}
	
	@Test
	@Rollback(false)
	public void whenFindAllWithListOfProductCodesAndCurrency_thenReturnRequestedCategories() {

		Set<String> ls = new HashSet<String>();
		ls.add("POM01");
		ls.add("CIT01");
		
		// when
		List<ProductCategoryFacetDTO> found = categoryFacetService.findAll(	Constants.localeENGB,
													  						Constants.currencyHKD, 
													  						Constants.primaryProductRootCategoryCode,
													  						new StringCollectionWrapper(ls));

		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(2);	
		assertTrue(isOrdered(found));
	}
	
	@Test
	@Rollback(false)
	public void whenFindAllWithListOfCategoryCodes_thenReturnRequestedCategories() {

		Set<String> ls = new HashSet<String>();
		ls.add("POM01");
		ls.add("CIT01");
		
		// when
		List<ProductCategoryFacetDTO> found = categoryFacetService.findAll(Constants.localeENGB, Constants.currencyHKD, Constants.primaryProductRootCategoryCode, new StringCollectionWrapper(ls));

		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(2);	
		assertTrue(isOrdered(found));
	}
	
	@Test
	@Rollback(false)
	public void whenFindAllForListOfSelectedCategoryCodes_thenIgnoreSelectedCategories() {

		Set<String> ls = new HashSet<String>();
		ls.add("POM01");
		ls.add("CIT01");
		
		// when
		List<ProductCategoryFacetDTO> found = categoryFacetService.findAll( 	Constants.localeENGB, 
																  				Constants.currencyHKD, 
																  				"FRT01", 
																  				new StringCollectionWrapper(ls), 
																  				new StringCollectionWrapper(new HashSet<String>()), 
																  				new StringCollectionWrapper(new HashSet<String>()), 
																  				null);	

		// then only children
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(7);	
		assertTrue(isOrdered(found));
	}
	
	@Test
	@Rollback(false)
	public void whenFindAllCategories_thenReturnAllCategories() {
		
		//when
		List<ProductCategoryFacetDTO> found = categoryFacetService.findAll( Constants.localeENGB, 
																			Constants.currencyHKD, 
																			"PRM01", 
																			new StringCollectionWrapper(new HashSet<String>()), 
																			new StringCollectionWrapper(new HashSet<String>()),
																			new StringCollectionWrapper(new HashSet<String>()), 
																			null);
		
		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(3);	
		assertTrue(isOrdered(found));
		assertTrue(found.stream().filter(f -> f.getCategoryCode().equals("FRT01")).findAny().isPresent());
		assertTrue(found.stream().filter(f -> f.getCategoryCode().equals("VEG01")).findAny().isPresent());
		//CategoryProductDTO cp = found.stream().filter(f -> f.getCategoryCode().equals("FRT01")).findAny().get();
		
	}
	
	@Test
	@Rollback(false)
	public void whenFindAllCategoriesWithNullPrice_thenReturnAllCategories() {
		
		//when
		List<ProductCategoryFacetDTO> found = categoryFacetService.findAll(  Constants.localeENGB, 
																			Constants.currencyHKD, 
																			"PRM01", 
																			new StringCollectionWrapper(new HashSet<String>()), 
																			new StringCollectionWrapper(new HashSet<String>()),
																			new StringCollectionWrapper(new HashSet<String>()), 
																			null);
		
		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(3);	
		assertTrue(isOrdered(found));
	}

//	@Test
//	@Rollback(false)
//	public void whenFindAllBrandCategories_thenReturnAllBrandCategories() {
//
//		// when
//		List<ProductCategoryFacetDTO> found = categoryFacetService.findAll(Constants.localeENGB, Constants.primaryBrandRootCategoryCode, CategoryBrandEntity.class);
//
//		// then
//		assertNotNull(found);
//		assertTrue(!found.isEmpty());
//		assertThat(found.size()).isEqualTo(1);
//		assertTrue(isOrdered(found));
//	}

//	@Test
//	@Rollback(false)
//	public void whenFindAllProductCategories_thenReturnAllProductCategories() {
//
//		// when
//		List<ProductCategoryFacetDTO> found = categoryFacetService.findAll(Constants.localeENGB, Constants.primaryProductRootCategoryCode, CategoryProductEntity.class);
//
//		// then
//		assertNotNull(found);
//		assertTrue(!found.isEmpty());
//		assertThat(found).size().isEqualTo(27);
//		assertTrue(isOrdered(found));
//	}

	@Test
	@Rollback(false)
	public void whenGetMaxPriceForFruitCategory_thenReturnCorrectMaxPriceInHKD() {

		// when
		Double found = categoryFacetService.getMaxPrice(	Constants.localeENGB, 
															Constants.currencyHKD, 
															"FRT01",
															new StringCollectionWrapper(new HashSet<String>()), 
															new StringCollectionWrapper(new HashSet<String>()), 
															new StringCollectionWrapper(new HashSet<String>()));

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(Double.valueOf("162.0"));
	}
	
	@Test
	@Rollback(false)
	public void whenGetMaxPriceForCoolStarionaryCategory_thenReturnCorrectMaxPriceInHKD() {

		Set<String> ls = new HashSet<String>();
		ls.add("FCOO1");
		
		// when
		Double found = categoryFacetService.getMaxPrice(Constants.localeENGB, 
														Constants.currencyHKD, "FAS01",
														new StringCollectionWrapper(ls), 
														new StringCollectionWrapper(new HashSet<String>()), 
														new StringCollectionWrapper(new HashSet<String>()));

		// then
		System.out.println(found);
		assertNotNull(found);
		assertThat(found).isGreaterThan(Double.valueOf(0));
		assertThat(found).isEqualTo(Double.valueOf(12));
	}

	@Test
	@Rollback(false)
	public void whenGetMaxPriceForVegetablesCategory_thenReturnCorrectMaxPriceInHKD() {

		// when
		Double found = categoryFacetService.getMaxPrice(Constants.localeENGB, 
														Constants.currencyHKD, "VEG01",
														new StringCollectionWrapper(new HashSet<String>()), 
														new StringCollectionWrapper(new HashSet<String>()), 
														new StringCollectionWrapper(new HashSet<String>()));

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(Double.valueOf("108.0"));
	}

	@Test
	@Rollback(false)
	public void whenGetMaxPriceForAllCategory_thenReturnCorrectMaxPriceInHKD() {

		// when
		Double found = categoryFacetService.getMaxPrice(Constants.localeENGB, 
														Constants.currencyHKD, 
														Constants.primaryProductRootCategoryCode,
														new StringCollectionWrapper(new HashSet<String>()), 
														new StringCollectionWrapper(new HashSet<String>()), 
														new StringCollectionWrapper(new HashSet<String>()));

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(Double.valueOf("855.0"));
	}

	@Test
	@Rollback(false)
	public void whenGetMaxPriceForPomesCategory_thenReturnCurrectMaxPriceHKD() {

		// when
		Double found = categoryFacetService.getMaxPrice(Constants.localeENGB, 
														Constants.currencyHKD, 
														"POM01",
														new StringCollectionWrapper(new HashSet<String>()), 
														new StringCollectionWrapper(new HashSet<String>()), 
														new StringCollectionWrapper(new HashSet<String>()));

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(Double.valueOf("85.5"));
	}

	@Test
	@Rollback(false)
	public void whenGetMaxPriceForFruitCategory_thenReturnCorrectMaxPriceInUSD() {

		// when
		Double found = categoryFacetService.getMaxPrice(	Constants.localeENGB, 
															Constants.currencyUSD, 
															"FRT01",
															new StringCollectionWrapper(new HashSet<String>()), 
															new StringCollectionWrapper(new HashSet<String>()), 
															new StringCollectionWrapper(new HashSet<String>()));

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(Double.valueOf("20.8"));
	}

	@Test
	@Rollback(false)
	public void whenGetMaxPriceForVegetablesCategory_thenReturnCorrectMaxPriceInUSD() {

		// when
		Double found = categoryFacetService.getMaxPrice(	Constants.localeENGB, 
															Constants.currencyUSD, 
															"VEG01",
															new StringCollectionWrapper(new HashSet<String>()), 
															new StringCollectionWrapper(new HashSet<String>()), 
															new StringCollectionWrapper(new HashSet<String>()));

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(Double.valueOf("13.8"));
	}

	@Test
	@Rollback(false)
	public void whenGetMaxPriceForAllCategory_thenReturnCorrectMaxPriceInUSD() {

		// when
		Double found = categoryFacetService.getMaxPrice(	Constants.localeENGB, 
															Constants.currencyUSD, 
															Constants.primaryProductRootCategoryCode,
															new StringCollectionWrapper(new HashSet<String>()), 
															new StringCollectionWrapper(new HashSet<String>()), 
															new StringCollectionWrapper(new HashSet<String>()));

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(Double.valueOf("109.6"));
	}

	@Test
	@Rollback(false)
	public void whenGetMaxPriceForPomesCategory_thenReturnCurrectMaxPriceUSD() {

		// when
		Double found = categoryFacetService.getMaxPrice(	Constants.localeENGB, 
															Constants.currencyUSD, 
															"POM01",
															new StringCollectionWrapper(new HashSet<String>()), 
															new StringCollectionWrapper(new HashSet<String>()), 
															new StringCollectionWrapper(new HashSet<String>()));

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(Double.valueOf("11.0"));
	}
	
	private void assertFoundEntity(Optional<CategoryEntity> found) {
    	
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(found.get().getCategoryDescENGB())
	    .isEqualTo("test product category");
	    
	    assertThat(found.get().getCategoryDescZHHK())
	    .isEqualTo("測試產品類別");
	    
    }
	
	private boolean isOrdered(List<ProductCategoryFacetDTO> list) {
		return Ordering.from(String.CASE_INSENSITIVE_ORDER).isOrdered(list.stream().map(c -> c.getCategoryDesc()).collect(Collectors.toList())); 
	}

}
