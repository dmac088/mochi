package io.nzbee.test.integration.entity.promotion.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.util.promotion.regular.PromotionProductMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class IT_PromotionProductUploadForCreateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	private PromotionProductMasterService pms;

	@Autowired
	private IPromotionService promotionService;
	
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
    	this.loadPromotions();
        setUpIsDone = true;
	}
	
	public void loadPromotions() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writePromotionRegularMaster(file.getAbsolutePath() + "/data/promotion/regular/create/promotion.tsv");
	}

	@Test
	@Rollback(false)
	public void whenPromotionUploadedForCreate_thenReturnCorrectlyCreatedPromotion_ENGB() {
		// when
		Optional<PromotionEntity> found = promotionService.findByCode("RB1G1F");

		// then
		assertFound_ENGB(found);
	}

	@Test
	@Rollback(false)
	public void whenPromotionUploadedForCreate_thenReturnCorrectlyCreatedPromotion_ZHHK() {
		// when
		Optional<PromotionEntity> found = promotionService.findByCode("RB2G1F");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<PromotionEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		PromotionEntity cp = (PromotionEntity) found.get();
		
		assertThat(cp.getPromotionCode())
		.isEqualTo("RB1G1F");
		
		assertThat(cp.getAttributes().size()).isEqualTo(2);
		
		assertThat(cp.getAttributes().stream().filter(a -> a.getLocale().equals(Constants.localeENGB)).findAny().get().getPromotionDesc())
		.isEqualTo("Buy 1 get 1 free");
		
	}

	private void assertFound_ZHHK(Optional<PromotionEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		PromotionEntity cp = (PromotionEntity) found.get();
		
		assertThat(cp.getPromotionCode())
		.isEqualTo("RB2G1F");
		
		assertThat(cp.getAttributes().size()).isEqualTo(2);
		
		assertThat(cp.getAttributes().stream().filter(a -> a.getLocale().equals(Constants.localeZHHK)).findAny().get().getPromotionDesc())
		.isEqualTo("買二送一");
		
	}

}
