package io.nzbee.test.integration.entity.department;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

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
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.test.integration.entity.beans.department.IDepartmentEntityBeanFactory;
import io.nzbee.Constants;
import io.nzbee.entity.product.department.DepartmentDTO;
import io.nzbee.entity.product.department.DepartmentEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_DepartmentEntityRepositoryIntegrationTest {
	
	@TestConfiguration
    static class ProductProductTypeEntityRepositoryIntegrationTest {

    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	private IDepartmentEntityBeanFactory departmentEntityBeanFactory;
	
    @Autowired
    private IDepartmentService departmentService;
    
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	private static DepartmentEntity department = null;
	
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
		this.persistNewProductType();
		setUpIsDone = true;
	}
	
	public DepartmentEntity persistNewProductType() {
    	
		department = departmentEntityBeanFactory.getBean();
	   
	    //persist a new transient test category
		departmentService.save(department);
	    	
	    return department;
	}

	 @Test
	 @Rollback(false)
	 public void whenFindById_thenReturnDepartment() {
	    	
	        // when
		 	DepartmentDTO found = departmentService.findById(	Constants.localeENGB,
	    												  		department.getId()).get();
	     
	        // then
	    	assertFound(found);
	 }
	 
	 @Test 
	 @Rollback(false)
	 public void whenFindByCode_thenReturnDepartment() {
	    	
	        // when
		 	DepartmentDTO found = departmentService.findByCode(	Constants.localeENGB,
																"TST01").get();
	     
	        // then
	    	assertFound(found);
	 }
	 
	 @Test
	 @Rollback(false)
	 public void whenFindByDesc_thenReturnDepartment() {
	    	
	        // when
	    	DepartmentDTO found = departmentService.findByDesc(Constants.localeENGB,
															   "test department").get();
	     
	        // then
	    	assertFound(found);
	 }
	
	 private void assertFound(final DepartmentDTO found) {
	    	
		 	assertNotNull(found);
		 
	    	assertThat(found.getDepartmentCode())
	        .isEqualTo("TST01");
	
	    	assertThat(found.getDepartmentDesc())
	        .isEqualTo("test department");
	
	 }
	 
}
