package io.nzbee.test.unit.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.department.DepartmentServiceImpl;
import io.nzbee.domain.department.IDepartmentService;
import io.nzbee.domain.ports.IDepartmentPortService;
import io.nzbee.entity.adapters.PostgresDepartmentAdapter;
import io.nzbee.test.unit.domain.beans.department.DepartmentDoBeanFactory;
import io.nzbee.test.unit.domain.beans.department.IDepartmentDoBeanFactory;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "ut")
public class UT_DepartmentTest {

	@TestConfiguration
	static class ProductDepartmentDomainServiceImplUnitTest {
		// the beans that we need to run this test
		
		@Bean
		public DepartmentDoBeanFactory departmentDoBeanFactory() {
			return new DepartmentDoBeanFactory();
		}
		
		@Bean
		public IDepartmentPortService departmentPortService() {
			return new PostgresDepartmentAdapter();
		}
		
		@Bean 
		public IDepartmentService departmentService() {
			return new DepartmentServiceImpl();
		}
		
	}
	
	@Autowired
	private IDepartmentService departmentService;

	@MockBean
	private IDepartmentPortService departmentPortService;

	@Autowired
	private IDepartmentDoBeanFactory departmentDoBeanFactory;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);

		Department department = departmentDoBeanFactory.getBean();

		// need to fill more of the properties here
		Mockito.when(departmentPortService.findByCode(Constants.localeENGB,
												department.getDepartmentCode())).thenReturn(department);
		
		Mockito.when(departmentPortService.findByDesc(Constants.localeENGB,
												department.getDepartmentDesc())).thenReturn(department);
	}

	@Test
	public void whenFindByCode_thenProductDepartmentIsFound() {
		String code = "TST01";

		Department found = departmentService.findByCode(Constants.localeENGB,
											  code);

		assertFound(found);
	}
	
	@Test
	public void whenFindByDesc_thenProductDepartmentIsFound() {
		String desc = "test department";

		Department found = departmentService.findByDesc(Constants.localeENGB,
											  desc);

		assertFound(found);
	}
	
	
    private void assertFound(Department found) {

    	assertNotNull(found);
    	
    	assertThat(found.getDepartmentCode())
        .isEqualTo("TST01");
    	
	    assertThat(found.getDepartmentDesc())
	    .isEqualTo("test department");
    }


}
