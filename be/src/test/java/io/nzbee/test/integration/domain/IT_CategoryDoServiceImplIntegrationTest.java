package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.category.Category;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_CategoryDoServiceImplIntegrationTest {

	@Autowired
	private ICategoryService categoryService;

	@Test
	public void whenFindAll_thenReturnAllCategories() {

		// when
		Set<Category> found = categoryService.findAll(	GeneralVars.LANGUAGE_ENGLISH, 
														GeneralVars.CURRENCY_USD);

		// then
		assertFound(found);
	}

	private void assertFound(final Set<Category> found) {

		assertThat(found).isNotNull();
		assertThat(found).size().isEqualTo(43);
	}

}
