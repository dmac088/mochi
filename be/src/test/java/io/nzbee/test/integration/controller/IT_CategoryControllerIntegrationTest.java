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
import io.nzbee.WebMvcConfig;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.view.ProductCategoryAdapterImpl;
import io.nzbee.entity.category.CategoryServiceImpl;
import io.nzbee.entity.category.brand.CategoryBrandDaoImpl;
import io.nzbee.entity.category.brand.CategoryBrandServiceImpl;
import io.nzbee.entity.category.product.CategoryProductDaoImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTODaoImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTOMapperImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTOServiceImpl;
import io.nzbee.resources.category.CategoryFacetMapper;
import io.nzbee.resources.category.CategoryFacetResourceAssembler;
import io.nzbee.resources.category.CategoryResourceAssembler;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.product.PriceFacetMapper;
import io.nzbee.resources.product.PriceFacetResourceAssembler;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.UserService;

import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.view.category.product.ProductCategoryViewServiceImpl;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc()
@ContextConfiguration(classes = {CategoryController.class, 
							     ProductCategoryAdapterImpl.class,
							     ProductCategoryViewServiceImpl.class,
							     CategoryProductDaoImpl.class,
							     CategoryBrandServiceImpl.class,
							     CategoryBrandDaoImpl.class,
							     CategoryResourceAssembler.class,  
							     CategoryFacetMapper.class,
							     CategoryFacetResourceAssembler.class,
							     ProductCategoryFacetDTODaoImpl.class,
							     ProductCategoryFacetDTOMapperImpl.class,
							     PriceFacetMapper.class,
							     PriceFacetResourceAssembler.class,
							     SecurityBeanConfiguration.class,
							     Globals.class,
							     JavaMailSender.class,
							     CategoryServiceImpl.class,
							     ProductCategoryFacetDTOServiceImpl.class,
							     DataSourceBeanMochi.class,
							     DataSourceBeanSecurity.class,
							     WebMvcConfig.class,
							     UserService.class,
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
    public void testFindAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Category/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
        		.andDo(print()).andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$._embedded.categoryResources.length()", is(27)));
        
    }
    
    
    @Test
    @Transactional
    public void testFindAllChildCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/Category/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Code/FRT01")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]")
                .accept(MediaType.ALL))
        		.andDo(print())
        		.andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$._embedded.categoryResources.length()", is(7)))
        		
        		//check the first child category
        		.andExpect(jsonPath("$._embedded.categoryResources[0].data.categoryCode").value("BER01"))
        		.andExpect(jsonPath("$._embedded.categoryResources[0].data.parentCode").value("FRT01"))
        		.andExpect(jsonPath("$._embedded.categoryResources[0].data.categoryDesc").value("Berries"))
        		.andExpect(jsonPath("$._embedded.categoryResources[0].data.objectCount").value("2"))
        		.andExpect(jsonPath("$._embedded.categoryResources[0].data.childCount").value("0"))
        		
        		//check the last child category
        		.andExpect(jsonPath("$._embedded.categoryResources[6].data.categoryCode").value("TRO01"))
        		.andExpect(jsonPath("$._embedded.categoryResources[6].data.parentCode").value("FRT01"))
        		.andExpect(jsonPath("$._embedded.categoryResources[6].data.categoryDesc").value("Tropical"))
        		.andExpect(jsonPath("$._embedded.categoryResources[6].data.objectCount").value("2"))
        		.andExpect(jsonPath("$._embedded.categoryResources[6].data.childCount").value("0"))
        		
        		.andExpect(jsonPath("$._embedded.categoryResources[0].data.locale").value(Constants.localeENGB));
    }
    

}
