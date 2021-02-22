package io.nzbee.test.integration.entity.product.shipping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.shipping.ShippingProductDTO;
import io.nzbee.entity.product.shipping.ShippingProductEntity;
import io.nzbee.test.integration.entity.beans.product.shipping.IShippingProductEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_ShippingProductEntityRepositoryIntegrationTest {

	@TestConfiguration
	static class ShippingProductEntityRepositoryIntegrationTest { 

	}

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	private IShippingProductEntityBeanFactory shippingProductEntityBeanFactory;

	@Autowired
	private IProductService productService;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private static ShippingProductEntity shippingProduct = null;

	private static boolean setUpIsDone = false;

	public void persistNewShippingProduct() {

		shippingProduct = shippingProductEntityBeanFactory.getBean();

		// persist a new transient test shippingProduct
		productService.save(shippingProduct);
	}

	@Before
	public void persistANewShippingProduct() {
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
		this.persistNewShippingProduct();
		setUpIsDone = true;
	}

	@Test
	@Rollback(false)
	public void whenFindById_thenReturnShippingProductEntity() {

		// when
		Optional<ProductEntity> found = productService.findById(shippingProduct.getProductId());

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnShippingProductEntity() {

		// when
		Optional<ProductEntity> found = productService.findByCode("123456789");

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnShippingProductDTO() {

		// when
		Optional<ProductDTO> found = productService.findByCode(Constants.localeENGB, Constants.currencyHKD, "123456789");

		// then
		assertFoundDTO(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByDesc_thenReturnShippingProductDTO() {

		// when
		Optional<ProductDTO> found = productService.findByDesc(Constants.localeENGB, Constants.currencyHKD, "Test shipping destination description");

		// then
		assertFoundDTO(found);
	}


	private void assertFoundEntity(Optional<ProductEntity> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getProductUPC()).isEqualTo("123456789");
		assertThat(found.get().getAttributes().stream().filter(t -> t.getLclCd().equals(Constants.localeENGB)).findAny()
				.get().getProductDesc()).isEqualTo("Test shipping destination description");
	}

	private void assertFoundDTO(Optional<ProductDTO> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		ShippingProductDTO spDto = (ShippingProductDTO) found.get();
		
		assertThat(spDto.getProductUPC()).isEqualTo("123456789");
		assertThat(spDto.getProductDesc()).isEqualTo("Test shipping destination description");

	}

}
