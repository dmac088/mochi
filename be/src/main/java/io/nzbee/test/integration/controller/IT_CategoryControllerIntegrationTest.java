package io.nzbee.test.integration.controller;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.security.WebSecurityConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(/*addFilters = false*/)
@ContextConfiguration(classes = {CategoryController.class, 
							     CategoryServiceImpl.class,
							     io.nzbee.dto.category.CategoryServiceImpl.class,
							     io.nzbee.entity.category.CategoryServiceImpl.class,
							     io.nzbee.entity.category.CategoryDaoPostgresImpl.class,
							     io.nzbee.entity.DataSourceBeanMochiDev.class,
							     io.nzbee.security.DataSourceBeanSecurityDev.class,
							     io.nzbee.WebMvcConfigDev.class,
							     io.nzbee.security.user.UserService.class,
							     io.nzbee.security.user.IUserRepository.class
							     })

@WebMvcTest(CategoryController.class)
@Import(WebSecurityConfig.class)
@ActiveProfiles(profiles = "dev")
public class IT_CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void setUp() throws Exception {
    	
    	
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                //.apply(springSecurity())
                .build();
    }
    
    @Test
    public void testFindAll() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/Category/en-GB/HKD")
                //.with(user(TEST_USER_ID))
               //.with(csrf())
                //.content(birthday)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn();
        
        
        String response = result.getResponse().getContentAsString();
        
        
        assertNotNull(response);
       // assertEquals(dow, resultDOW);
    }
    

}
