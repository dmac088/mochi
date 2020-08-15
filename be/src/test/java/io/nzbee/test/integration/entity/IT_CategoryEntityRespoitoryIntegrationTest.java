package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import io.nzbee.Constants;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.product.CategoryProduct;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
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
		Set<Category> found = categoryService.findAll(Constants.localeENGB);

		// then
		assertAllCategoriesFound(found);
	}
	
	@Test
	public void whenFindAllCategories_thenReturnAllCategories() {
		
		//when
		List<Category> found = categoryService.findAll(		Constants.localeENGB, 
															Constants.currencyHKD, 
															"PRM01", 
															new HashSet<String>(), 
															new HashSet<String>(),
															new HashSet<String>(), 
															new Double(10000));
		
		// then
		assertNotNull(found);
		assertThat(found.size()).isEqualTo(14);		
	}
	
	@Test
	public void whenFindAllCategoriesWithNullPrice_thenReturnAllCategories() {
		
		//when
		List<Category> found = categoryService.findAll(		Constants.localeENGB, 
															Constants.currencyHKD, 
															"PRM01", 
															new HashSet<String>(), 
															new HashSet<String>(),
															new HashSet<String>(), 
															null);
		
		// then
		assertNotNull(found);
		assertThat(found.size()).isEqualTo(35);		
	}

	@Test
	public void whenFindAllBrandCategories_thenReturnAllBrandCategories() {

		// when
		List<Category> found = categoryService.findAll(Constants.localeENGB, CategoryBrand.class);

		// then
		assertAllBrandCategoriesFound(found);
	}

	@Test
	public void whenFindAllProductCategories_thenReturnAllProductCategories() {

		// when
		List<Category> found = categoryService.findAll(Constants.localeENGB, CategoryProduct.class);

		// then
		assertAllProductCategoriesFound(found);
	}

	@Test
	public void whenGetMaxPriceForFruitCategory_thenReturnCorrectMaxPriceInHKD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyHKD, "FRT01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertThat(found).isEqualTo(new Double("162.0"));
	}

	@Test
	public void whenGetMaxPriceForVegetablesCategory_thenReturnCorrectMaxPriceInHKD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyHKD, "VEG01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertThat(found).isEqualTo(new Double("108.0"));
	}

	@Test
	public void whenGetMaxPriceForAllCategory_thenReturnCorrectMaxPriceInHKD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyHKD, "PRM01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertThat(found).isEqualTo(new Double("855.0"));
	}

	@Test
	public void whenGetMaxPriceForPomesCategory_thenReturnCurrectMaxPriceHKD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyHKD, "POM01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertThat(found).isEqualTo(new Double("85.5"));
	}

	@Test
	public void whenGetMaxPriceForFruitCategory_thenReturnCorrectMaxPriceInUSD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyUSD, "FRT01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertThat(found).isEqualTo(new Double("20.79"));
	}

	@Test
	public void whenGetMaxPriceForVegetablesCategory_thenReturnCorrectMaxPriceInUSD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyUSD, "VEG01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertThat(found).isEqualTo(new Double("13.86"));
	}

	@Test
	public void whenGetMaxPriceForAllCategory_thenReturnCorrectMaxPriceInUSD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyUSD, "PRM01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertThat(found).isEqualTo(new Double("109.62"));
	}

	@Test
	public void whenGetMaxPriceForPomesCategory_thenReturnCurrectMaxPriceUSD() {

		// when
		Double found = categoryService.getMaxPrice(Constants.localeENGB, Constants.currencyUSD, "POM01",
				new HashSet<String>(), new HashSet<String>(), new HashSet<String>());

		// then
		assertThat(found).isEqualTo(new Double("10.98"));
	}

	private void assertAllCategoriesFound(final Set<Category> found) {

		assertThat(found).isNotNull();
		assertThat(found).size().isEqualTo(43);
	}

	private void assertAllBrandCategoriesFound(final List<Category> found) {

		assertThat(found).isNotNull();
		assertThat(found).size().isEqualTo(1);
	}

	private void assertAllProductCategoriesFound(final List<Category> found) {

		assertThat(found).isNotNull();
		assertThat(found).size().isEqualTo(42);
	}

	@After
	public void closeConnection() {
		entityManager.close();
	}

}
