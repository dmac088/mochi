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
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.entity.adapters.domain.CategoryAdapter;
import io.nzbee.entity.category.CategoryMapperImpl;
import io.nzbee.entity.category.brand.CategoryBrandDaoImpl;
import io.nzbee.entity.category.brand.CategoryBrandMapperImpl;
import io.nzbee.entity.category.brand.CategoryBrandServiceImpl;
import io.nzbee.entity.category.product.CategoryProductDaoImpl;
import io.nzbee.entity.category.product.CategoryProductMapperImpl;
import io.nzbee.entity.category.product.CategoryProductServiceImpl;
import io.nzbee.resources.category.CategoryFacetMapper;
import io.nzbee.resources.category.CategoryFacetResourceAssembler;
import io.nzbee.resources.category.CategoryResourceAssembler;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.product.PriceFacetMapper;
import io.nzbee.resources.product.PriceFacetResourceAssembler;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(/*addFilters = false*/)
@ContextConfiguration(classes = {CategoryController.class, 
							     CategoryServiceImpl.class,
							     CategoryAdapter.class,
							     CategoryProductServiceImpl.class,
							     CategoryProductDaoImpl.class,
							     CategoryBrandServiceImpl.class,
							     CategoryBrandDaoImpl.class,
							     CategoryMapperImpl.class,
							     CategoryProductMapperImpl.class,
							     CategoryBrandMapperImpl.class,
							     CategoryResourceAssembler.class,  
							     CategoryFacetMapper.class,
							     CategoryFacetResourceAssembler.class,
							     PriceFacetMapper.class,
							     PriceFacetResourceAssembler.class,
							     SecurityBeanConfiguration.class,
							     Globals.class,
							     JavaMailSender.class,
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
@ActiveProfiles(profiles = "it")
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
    @Transactional
    public void testFindAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Category/en-GB/HKD")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
        		.andDo(print()).andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$._embedded.categoryResources.length()", is(87)));
        
    }
    
    
    @Test
    @Transactional
    public void testFindOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Category/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/code/FRT01")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
        		.andDo(print())
        		.andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$.data.categoryCode").value("FRT01"))
        		.andExpect(jsonPath("$.data.categoryDesc").value("Fruit"))
        		.andExpect(jsonPath("$.data.locale").value(Constants.localeENGB));
    }
    

}
