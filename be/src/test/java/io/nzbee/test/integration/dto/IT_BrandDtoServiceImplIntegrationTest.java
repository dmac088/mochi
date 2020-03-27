package io.nzbee.test.integration.dto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.brand.IBrandService;
import io.nzbee.test.integration.beans.BrandDtoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
@SqlGroup({
	@Sql(scripts = "/database/mochi_schema.sql",
			config = @SqlConfig(dataSource = "mochiDataSourceOwner", 
			transactionManager = "mochiTransactionManagerOwner",
			transactionMode = TransactionMode.ISOLATED)), 
	@Sql(scripts = "/database/mochi_data.sql",
			config = @SqlConfig(dataSource = "mochiDataSource", 
			transactionManager = "mochiTransactionManager",
			transactionMode = TransactionMode.ISOLATED))
})
public class IT_BrandDtoServiceImplIntegrationTest {

    @Autowired
    private IBrandService brandService;
	
	@Autowired
	private BrandDtoBeanFactory brandDtoBeanFactory;
    
	private Brand brand = null;
	
    @Before
    public void setUp() { 
    	
    	this.persistNewBrand();
    	
    }
    
	public void persistNewBrand() {
    	
		brand = brandDtoBeanFactory.getBrandDtoBean();
		
		brandService.save(brand);
		
	}
	
    @Test
    public void whenFindById_thenReturnBrand() {
    	
        // when
    	Brand found = brandService.findByCode("en-GB", 
											  "HKD",  
											  brand.getBrandCode()).get();
     
        // then
    	assertFound(found);
    	
    }

	private void assertFound(Brand found) {
		
		assertThat(found.getBrandCode())
    	.isEqualTo("TST02");
	}
	
}
