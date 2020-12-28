package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.junit.After;
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
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import com.google.common.collect.Ordering;
import io.nzbee.Constants;
import io.nzbee.entity.category.CategoryDTO;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@SqlGroup({
		@Sql(scripts = "/database/mochi_schema.sql", config = @SqlConfig(dataSource = "mochiDataSourceOwner", transactionManager = "mochiTransactionManagerOwner", transactionMode = TransactionMode.ISOLATED)),
		@Sql(scripts = "/database/mochi_data.sql", config = @SqlConfig(dataSource = "mochiDataSource", transactionManager = "mochiTransactionManager", transactionMode = TransactionMode.ISOLATED)) })
public class IT_CategoryEntityRespoitoryIntegrationTest {

	@TestConfiguration
	static class CategoryEntityRepositoryIntegrationTest {

	}

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private ICategoryService categoryService;

	@Test
	public void whenFindAll_thenReturnAllCategories() {

		// when
		List<CategoryDTO> found = categoryService.findAll(Constants.localeENGB);

		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(88);
		assertTrue(isOrdered(found));
	}
	
	@Test
	public void whenFindAllWithListOfProductCodesAndCurrency_thenReturnRequestedCategories() {

		Set<String> ls = new HashSet<String>();
		ls.add("POM01");
		ls.add("CIT01");
		
		// when
		List<CategoryDTO> found = categoryService.findAll(	Constants.localeENGB,
													  		Constants.currencyHKD, 
													  		ls);

		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(2);	
		assertTrue(isOrdered(found));
	}
	
	@Test
	public void whenFindAllWithListOfCategoryCodes_thenReturnRequestedCategories() {

		Set<String> ls = new HashSet<String>();
		ls.add("POM01");
		ls.add("CIT01");
		
		// when
		List<CategoryDTO> found = categoryService.findAll(Constants.localeENGB, ls);

		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(2);	
		assertTrue(isOrdered(found));
	}
	
	@Test
	public void whenFindAllForListOfSelectedCategoryCodes_thenIgnoreSelectedCategories() {

		Set<String> ls = new HashSet<String>();
		ls.add("POM01");
		ls.add("CIT01");
		
		// when
		List<CategoryDTO> found = categoryService.findAll( 	Constants.localeENGB, 
														  	Constants.currencyHKD, 
															"FRT01", 
															ls, 
															new HashSet<String>(), 
															new HashSet<String>(), 
															null);	

		// then only children
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(8);	
		assertTrue(isOrdered(found));
	}
	
	@Test
	public void whenFindAllCategories_thenReturnAllCategories() {
		
		//when
		List<CategoryDTO> found = categoryService.findAll(  Constants.localeENGB, 
															Constants.currencyHKD, 
															"PRM01", 
															new HashSet<String>(), 
															new HashSet<String>(),
															new HashSet<String>(), 
															null);
		
		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(36);	
		assertTrue(isOrdered(found));
	}
	
	@Test
	public void whenFindAllCategoriesWithNullPrice_thenReturnAllCategories() {
		
		//when
		List<CategoryDTO> found = categoryService.findAll(  Constants.localeENGB, 
															Constants.currencyHKD, 
															"PRM01", 
															new HashSet<String>(), 
															new HashSet<String>(),
															new HashSet<String>(), 
															null);
		
		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(36);	
		assertTrue(isOrdered(found));
	}

	@Test
	public void whenFindAllBrandCategories_thenReturnAllBrandCategories() {

		// when
		List<CategoryDTO> found = categoryService.findAll(Constants.localeENGB, CategoryBrandEntity.class);

		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found.size()).isEqualTo(1);
		assertTrue(isOrdered(found));
	}

	@Test
	public void whenFindAllProductCategories_thenReturnAllProductCategories() {

		// when
		List<CategoryDTO> found = categoryService.findAll(Constants.localeENGB, CategoryProductEntity.class);

		// then
		assertNotNull(found);
		assertTrue(!found.isEmpty());
		assertThat(found).size().isEqualTo(87);
		assertTrue(isOrdered(found));
	}

	@Test
	public void whenGetMaxPriceForFruitCategory_thenReturnCorrectMaxPriceInHKD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyHKD, "FRT01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(new Double("162.0"));
	}
	
	@Test
	public void whenGetMaxPriceForCoolStarionaryCategory_thenReturnCorrectMaxPriceInHKD() {

		Set<String> ls = new HashSet<String>();
		ls.add("FCOO1");
		
		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyHKD, "FAS01",
				ls, new HashSet<String>(), new HashSet<String>());

		// then
		System.out.println(found);
		assertNotNull(found);
		assertThat(found).isGreaterThan(new Double(0));
		assertThat(found).isEqualTo(new Double(12));
	}

	@Test
	public void whenGetMaxPriceForVegetablesCategory_thenReturnCorrectMaxPriceInHKD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyHKD, "VEG01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(new Double("108.0"));
	}

	@Test
	public void whenGetMaxPriceForAllCategory_thenReturnCorrectMaxPriceInHKD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyHKD, "PRM01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(new Double("855.0"));
	}

	@Test
	public void whenGetMaxPriceForPomesCategory_thenReturnCurrectMaxPriceHKD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyHKD, "POM01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(new Double("85.5"));
	}

	@Test
	public void whenGetMaxPriceForFruitCategory_thenReturnCorrectMaxPriceInUSD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyUSD, "FRT01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(new Double("20.8"));
	}

	@Test
	public void whenGetMaxPriceForVegetablesCategory_thenReturnCorrectMaxPriceInUSD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyUSD, "VEG01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(new Double("13.8"));
	}

	@Test
	public void whenGetMaxPriceForAllCategory_thenReturnCorrectMaxPriceInUSD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyUSD, "PRM01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(new Double("109.6"));
	}

	@Test
	public void whenGetMaxPriceForPomesCategory_thenReturnCurrectMaxPriceUSD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyUSD, "POM01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertNotNull(found);
		assertThat(found).isEqualTo(new Double("11.0"));
	}

	@After
	public void closeConnection() {
		entityManager.close();
	}
	
	private boolean isOrdered(List<CategoryDTO> list) {
		return Ordering.from(String.CASE_INSENSITIVE_ORDER).isOrdered(list.stream().map(c -> c.getCategoryDesc()).collect(Collectors.toList())); 
	}

}
