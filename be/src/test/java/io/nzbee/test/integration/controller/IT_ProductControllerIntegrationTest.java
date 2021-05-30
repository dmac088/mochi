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
import io.nzbee.domain.bag.BagServiceImpl;
import io.nzbee.entity.promotion.PromotionServiceImpl;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.domain.BagAdapter;
import io.nzbee.entity.adapters.view.PhysicalProductLightAdapterImpl;
import io.nzbee.entity.bag.BagDaoPostgresImpl;
import io.nzbee.entity.bag.BagMapperImpl;
import io.nzbee.entity.bag.item.BagItemMapperImpl;
import io.nzbee.entity.bag.status.BagItemStatusServiceImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTODaoImpl;
import io.nzbee.entity.brand.view.facet.BrandFacetDTOServiceImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTODaoImpl;
import io.nzbee.entity.category.product.view.facet.ProductCategoryFacetDTOServiceImpl;
import io.nzbee.entity.party.person.CustomerMapperImpl;
import io.nzbee.entity.party.person.PersonDaoImpl;
import io.nzbee.entity.party.person.PersonServiceImpl;
import io.nzbee.entity.product.ProductDaoPostgresImpl;
import io.nzbee.entity.product.ProductMapperImpl;
import io.nzbee.entity.product.ProductServiceImpl;
import io.nzbee.entity.product.physical.PhysicalProductDomainObjectMapperImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDaoImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightMapperImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightServiceImpl;
import io.nzbee.entity.product.shipping.ShippingProductMapperImpl;
import io.nzbee.entity.promotion.PromotionDaoPostgresImpl;
import io.nzbee.entity.promotion.PromotionMapperImpl;
import io.nzbee.entity.promotion.order.PromotionOrderMapperImpl;
import io.nzbee.entity.promotion.product.PromotionProductMapperImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTODaoImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOServiceImpl;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModelAssembler;
import io.nzbee.search.FacetServicesImpl;
import io.nzbee.search.SearchServiceImpl;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.IUserRepository;
import io.nzbee.security.user.UserService;
import io.nzbee.view.product.physical.full.PhysicalProductFullServiceImpl;
import io.nzbee.view.product.physical.light.PhysicalProductLightViewServiceImpl;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc()
@ContextConfiguration(classes = {ProductController.class,
								 PhysicalProductLightViewServiceImpl.class,
								 PhysicalProductLightAdapterImpl.class,
								 PhysicalProductLightServiceImpl.class,
								 PhysicalProductFullServiceImpl.class,
								 PhysicalProductLightDaoImpl.class,
								 PhysicalProductLightMapperImpl.class,
								 SearchServiceImpl.class,
								 DataSourceBeanMochi.class,
							     DataSourceBeanSecurity.class,
							     WebMvcConfig.class,
							     UserService.class,
							     Globals.class,
							     PhysicalProductLightModelAssembler.class,
							     SecurityBeanConfiguration.class,
							     JavaMailSender.class,
							     DataSourceBeanMochi.class,
							     DataSourceBeanSecurity.class,
							     WebMvcConfig.class,
							     UserService.class,
							     IUserRepository.class,
							     OAuth2ResourceServerConfig.class,
							     ProductCategoryFacetDTOServiceImpl.class,
							     BrandFacetDTOServiceImpl.class,
							     TagFacetDTOServiceImpl.class,
							     FacetServicesImpl.class,
							     ProductCategoryFacetDTODaoImpl.class,
							     BrandFacetDTODaoImpl.class,
							     TagFacetDTODaoImpl.class,
							     BagServiceImpl.class,
							     BagAdapter.class,
							     BagServiceImpl.class,
							     io.nzbee.entity.bag.BagServiceImpl.class,
							     BagDaoPostgresImpl.class,
							     BagMapperImpl.class,
							     CustomerMapperImpl.class,
							     BagItemMapperImpl.class,
							     ProductMapperImpl.class,
							     ShippingProductMapperImpl.class,
							     PromotionMapperImpl.class,
							     PromotionOrderMapperImpl.class,
							     PromotionProductMapperImpl.class,
							     PhysicalProductDomainObjectMapperImpl.class,
							     ProductServiceImpl.class,
							     ProductDaoPostgresImpl.class,
							     BagItemStatusServiceImpl.class,
							     PersonServiceImpl.class,
							     PersonDaoImpl.class,
							     PromotionServiceImpl.class,
							     PromotionDaoPostgresImpl.class
							     })
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
	public void testFindAllProducts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/FRT01?page=0&size=10&sort=nameAsc")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(2)));
	}

}
