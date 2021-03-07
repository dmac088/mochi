package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.Constants;
import io.nzbee.domain.category.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
public class IT_CategoryDoServiceImplIntegrationTest {
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	private ICategoryPortService categoryService;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
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
		setUpIsDone = true;
	}
	

	@Test
	@Rollback(false)
	public void whenFindAll_thenReturnAllCategories() {

		// when
		List<Category> found = categoryService.findAll(	Constants.localeENGB);

		// then
		assertFound(found);
	}

	private void assertFound(final List<Category> found) {

		assertThat(found).isNotNull();
		
		assertThat(found).size().isEqualTo(80);
		
	}

}
