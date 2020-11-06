package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.After;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.Constants;
import io.nzbee.entity.product.department.DepartmentDTO;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.test.integration.beans.DepartmentEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
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
public class IT_DepartmentEntityRepositoryIntegrationTest {
	
	@TestConfiguration
    static class ProductProductTypeEntityRepositoryIntegrationTest {

    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private DepartmentEntityBeanFactory departmentEntityBeanFactory;
	
    @Autowired
    private IDepartmentService departmentService;
	
    private DepartmentEntity department = null;
    
	@Before
    public void setUp() { 
		department = this.persistNewProductType();
    }
	
	public DepartmentEntity persistNewProductType() {
    	
		department = departmentEntityBeanFactory.getDepartmentEntityBean();
	   
	    //persist a new transient test category
	    entityManager.persist(department);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return department;
	}

	 @Test
	 public void whenFindById_thenReturnDepartment() {
	    	
	        // when
		 	DepartmentDTO found = departmentService.findById(	Constants.localeENGB,
	    												  		department.getId()).get();
	     
	        // then
	    	assertFound(found);
	 }
	 
	 @Test 
	 public void whenFindByCode_thenReturnDepartment() {
	    	
	        // when
		 	DepartmentDTO found = departmentService.findDTOByCode(	Constants.localeENGB,
																	"TST01").get();
	     
	        // then
	    	assertFound(found);
	 }
	 
	 @Test
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
	 
	 @After
	 public void closeConnection() {
	  	entityManager.close();
	 }
	 
}
