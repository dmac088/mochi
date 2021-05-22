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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import io.nzbee.Constants;
import io.nzbee.WebMvcConfig;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.view.BrandFacetAdapterImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTODaoImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOMapperImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOServiceImpl;
import io.nzbee.resources.brand.browseFacet.BrandBrowseFacetResourceAssembler;
import io.nzbee.resources.brand.searchFacet.BrandSearchFacetResourceAssembler;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.view.product.brand.facet.BrandFacetViewServiceImpl;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc()
@ContextConfiguration(classes = {BrandController.class,
								 BrandFacetViewServiceImpl.class,
								 BrandFacetAdapterImpl.class,
								 BrandFacetDTOServiceImpl.class,
								 BrandFacetDTODaoImpl.class,
								 BrandFacetDTOMapperImpl.class,
								 DataSourceBeanMochi.class,
							     DataSourceBeanSecurity.class,
							     WebMvcConfig.class,
							     BrandSearchFacetResourceAssembler.class,
							     BrandBrowseFacetResourceAssembler.class})
@WebMvcTest(BrandController.class)
@Import(WebSecurityConfig.class)
@ActiveProfiles(profiles = "it")
public class IT_BrandControllerIntegrationTest {

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
    public void testFindAllBrands() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/api/Brand/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/FRT01")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
        		.andDo(print()).andExpect(status().isOk())
        		.andExpect(content().contentType("application/hal+json"))
        		.andExpect(jsonPath("$._embedded.categoryResources.length()", is(7)));
        
    }
    
	
}
