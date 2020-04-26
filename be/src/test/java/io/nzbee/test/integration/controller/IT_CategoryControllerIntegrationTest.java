package io.nzbee.test.integration.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Globals;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.entity.adapters.PostgresCategoryAdapter;
import io.nzbee.entity.category.CategoryMapper;
import io.nzbee.entity.category.brand.CategoryBrandDaoImpl;
import io.nzbee.entity.category.brand.CategoryBrandService;
import io.nzbee.entity.category.product.CategoryProductDaoImpl;
import io.nzbee.entity.category.product.CategoryProductService;
import io.nzbee.resources.category.CategoryResourceAssembler;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.security.WebSecurityConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(/*addFilters = false*/)
@ContextConfiguration(classes = {CategoryController.class, 
							     CategoryServiceImpl.class,
							     PostgresCategoryAdapter.class,
							     CategoryProductService.class,
							     CategoryProductDaoImpl.class,
							     CategoryBrandService.class,
							     CategoryBrandDaoImpl.class,
							     CategoryMapper.class,
							     CategoryResourceAssembler.class,
							     PagedResourcesAssembler.class,
							     Globals.class,
							     io.nzbee.domain.category.CategoryServiceImpl.class,
							     io.nzbee.entity.category.CategoryServiceImpl.class,
							     io.nzbee.entity.category.CategoryDaoPostgresImpl.class,
							     io.nzbee.entity.DataSourceBeanMochi.class,
							     io.nzbee.security.DataSourceBeanSecurity.class,
							     io.nzbee.WebMvcConfig.class,
							     io.nzbee.security.user.UserService.class,
							     io.nzbee.security.user.IUserRepository.class,
							     io.nzbee.security.OAuth2ResourceServerConfig.class
							     })
@WebMvcTest(CategoryController.class)
@Import(WebSecurityConfig.class)
@ActiveProfiles(profiles = "tst")
public class IT_CategoryControllerIntegrationTest {
	
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Category/en-GB/HKD")
                //.with(user(TEST_USER_ID))
                .with(csrf())
                //.content(birthday)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
        		.andDo(print()).andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
        		.andExpect(jsonPath("$._embedded.categoryResources.length()", is(43)));
        
    }
    
    
    @Test
    public void testFindOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Category/en-GB/HKD/code/FRT01")
                //.with(user(TEST_USER_ID))
                .with(csrf())
                //.content(birthday)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
        		.andDo(print()).andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
        		.andExpect(jsonPath("$.data.categoryCode").value("FRT01"));
    }
    

}
