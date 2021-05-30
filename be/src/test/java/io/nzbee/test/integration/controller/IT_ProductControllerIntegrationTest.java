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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import io.nzbee.Constants;
import io.nzbee.Globals;
import io.nzbee.WebMvcConfig;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.IUserRepository;
import io.nzbee.security.user.UserService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc()
@ContextConfiguration(classes = { ProductController.class, 
								  DataSourceBeanMochi.class, 
								  DataSourceBeanSecurity.class,
								  WebMvcConfig.class, 
								  UserService.class, 
								  Globals.class, 
								  SecurityBeanConfiguration.class, 
								  JavaMailSender.class,
								  DataSourceBeanMochi.class, 	
								  DataSourceBeanSecurity.class, 
								  WebMvcConfig.class, 
								  UserService.class,
								  IUserRepository.class, 
								  OAuth2ResourceServerConfig.class })
@WebMvcTest(ProductController.class)
@Import(WebSecurityConfig.class)
@ActiveProfiles(profiles = "it")
public class IT_ProductControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	@Test
	@Transactional
	public void testFindAllTagFacets() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/FRT01?page=0&size=10&sort=nameAsc")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(2)));
	}

}
