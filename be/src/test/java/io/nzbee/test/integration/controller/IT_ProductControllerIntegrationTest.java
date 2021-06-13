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
import io.nzbee.domain.bag.BagConfiguration;
import io.nzbee.domain.bag.BagServiceImpl;
import io.nzbee.domain.product.shipping.ShippingProductServiceImpl;
import io.nzbee.entity.promotion.PromotionServiceImpl;
import io.nzbee.entity.DataSourceBeanMochi;
import io.nzbee.entity.adapters.domain.BagAdapter;
import io.nzbee.entity.adapters.domain.ProductAdapter;
import io.nzbee.entity.adapters.view.BrandAdapterImpl;
import io.nzbee.entity.adapters.view.PhysicalProductLightAdapterImpl;
import io.nzbee.entity.adapters.view.ShippingProductAdapterImpl;
import io.nzbee.entity.bag.BagDaoPostgresImpl;
import io.nzbee.entity.bag.BagMapperImpl;
import io.nzbee.entity.bag.item.BagItemMapperImpl;
import io.nzbee.entity.bag.status.BagItemStatusServiceImpl;
import io.nzbee.entity.brand.view.BrandDTOMapperImpl;
import io.nzbee.entity.brand.view.BrandDTOServiceImpl;
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
import io.nzbee.entity.product.attribute.ProductAttributeServiceImpl;
import io.nzbee.entity.product.currency.CurrencyServiceImpl;
import io.nzbee.entity.product.physical.PhysicalProductDomainObjectMapperImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDaoImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightMapperImpl;
import io.nzbee.entity.product.physical.light.PhysicalProductLightServiceImpl;
import io.nzbee.entity.product.price.ProductPriceServiceImpl;
import io.nzbee.entity.product.price.ProductPriceTypeService;
import io.nzbee.entity.product.shipping.ShippingProductDTOServiceImpl;
import io.nzbee.entity.product.shipping.ShippingProductMapperImpl;
import io.nzbee.entity.product.shipping.ShippingProductViewMapperImpl;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationDaoImpl;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationServiceImpl;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationViewMapperImpl;
import io.nzbee.entity.product.shipping.type.ShippingTypeServiceImpl;
import io.nzbee.entity.product.shipping.type.ShippingTypeViewMapperImpl;
import io.nzbee.entity.promotion.PromotionDaoPostgresImpl;
import io.nzbee.entity.promotion.PromotionMapperImpl;
import io.nzbee.entity.promotion.order.PromotionOrderMapperImpl;
import io.nzbee.entity.promotion.product.PromotionProductMapperImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTODaoImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOServiceImpl;
import io.nzbee.resources.brand.BrandViewModelAssembler;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.resources.product.physical.full.PhysicalProductFullResourceAssembler;
import io.nzbee.resources.product.physical.light.PhysicalProductLightModelAssembler;
import io.nzbee.resources.product.shipping.ShippingProductResourceAssembler;
import io.nzbee.resources.product.shipping.destination.ShippingDestinationResourceAssembler;
import io.nzbee.resources.product.shipping.type.ShippingTypeResourceAssembler;
import io.nzbee.search.FacetServicesImpl;
import io.nzbee.search.SearchServiceImpl;
import io.nzbee.security.DataSourceBeanSecurity;
import io.nzbee.security.OAuth2ResourceServerConfig;
import io.nzbee.security.SecurityBeanConfiguration;
import io.nzbee.security.WebSecurityConfig;
import io.nzbee.security.user.IUserRepository;
import io.nzbee.security.user.UserService;
import io.nzbee.view.product.brand.BrandViewServiceImpl;
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
							     PromotionDaoPostgresImpl.class,
							     BagConfiguration.class,
							     BrandViewServiceImpl.class,
							     BrandAdapterImpl.class,
							     BrandViewServiceImpl.class,
							     BrandDTOServiceImpl.class,
							     BrandDTOMapperImpl.class,
							     ShippingProductAdapterImpl.class,
							     ShippingProductServiceImpl.class,
							     ShippingProductDTOServiceImpl.class,
							     ShippingDestinationServiceImpl.class,
							     ShippingDestinationDaoImpl.class,
							     ShippingTypeServiceImpl.class,
							     ShippingDestinationViewMapperImpl.class,
							     ShippingTypeViewMapperImpl.class,
							     ShippingProductViewMapperImpl.class,
							     ShippingDestinationResourceAssembler.class,
							     ShippingTypeResourceAssembler.class,
							     ShippingProductResourceAssembler.class,
							     BrandViewModelAssembler.class,
							     PhysicalProductFullResourceAssembler.class,
							     ProductAdapter.class,
							     ProductAttributeServiceImpl.class,
							     ProductPriceServiceImpl.class,
							     CurrencyServiceImpl.class,
							     ProductPriceTypeService.class
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
	public void testBrowseAllProductsForFruitCategoryWithPaginationAndOrderOfNameAscending() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/FRT01?page=0&size=10&sort=nameAsc")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				
				.andExpect(jsonPath("$.searchResults._embedded.products").exists())
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(10)))
				
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productUPC").value("18911676"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productDesc").value("Apple"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productRetail").value("72"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productMarkdown").value("64.8"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.brandDesc").value("Planters"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productImage").value("apple.jpg"))
				
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productUPC").value("19037164"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productDesc").value("Strawberry"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productRetail").value("180"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productMarkdown").value("162.0"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.brandDesc").value("Shine"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productImage").value("strawberry.jpg"))
				
				.andExpect(jsonPath("$.searchResults.page").exists())
				.andExpect(jsonPath("$.searchResults.page.size").value("10"))
				.andExpect(jsonPath("$.searchResults.page.totalElements").value("12"))
				.andExpect(jsonPath("$.searchResults.page.totalPages").value("2"))
				.andExpect(jsonPath("$.searchResults.page.number").value("0"));
	}
	
	@Test
	@Transactional
	public void testBrowseAllProductsForFruitCategoryWithPaginationAndOrderOfNameDescending() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/FRT01?page=0&size=10&sort=nameDesc")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				
				.andExpect(jsonPath("$.searchResults._embedded.products").exists())
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(10)))
				
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productUPC").value("15483827"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productDesc").value("Water Melon"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productRetail").value("28"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productMarkdown").value("25.2"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.brandDesc").value("Driscolls"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productImage").value("water-melon.jpg"))
				
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productUPC").value("10760430"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productDesc").value("Grapes"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productRetail").value("60"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productMarkdown").value("54.0"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.brandDesc").value("Enza"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productImage").value("grapes.jpg"))
				
				.andExpect(jsonPath("$.searchResults.page").exists())
				.andExpect(jsonPath("$.searchResults.page.size").value("10"))
				.andExpect(jsonPath("$.searchResults.page.totalElements").value("12"))
				.andExpect(jsonPath("$.searchResults.page.totalPages").value("2"))
				.andExpect(jsonPath("$.searchResults.page.number").value("0"));
	}
	
	@Test
	@Transactional
	public void testBrowseAllProductsForFruitCategoryWithPaginationAndOrderOfPriceAscending() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/FRT01?page=0&size=10&sort=priceAsc")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				
				.andExpect(jsonPath("$.searchResults._embedded.products").exists())
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(10)))
				
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productUPC").value("25556789"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productDesc").value("tomato"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productRetail").value("16"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productMarkdown").value("14.4"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.brandDesc").value("Shine"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productImage").value("tomato.jpg"))
				
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productUPC").value("17235347"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productDesc").value("Pomegranate"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productRetail").value("95"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productMarkdown").value("85.5"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.brandDesc").value("Adora"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productImage").value("pomegranate.jpg"))
				
				.andExpect(jsonPath("$.searchResults.page").exists())
				.andExpect(jsonPath("$.searchResults.page.size").value("10"))
				.andExpect(jsonPath("$.searchResults.page.totalElements").value("12"))
				.andExpect(jsonPath("$.searchResults.page.totalPages").value("2"))
				.andExpect(jsonPath("$.searchResults.page.number").value("0"));
	}
	
	
	@Test
	@Transactional
	public void testBrowseAllProductsForFruitCategoryWithPaginationAndOrderOfPriceDescending() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/Product/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Category/Code/FRT01?page=0&size=10&sort=priceDesc")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				
				.andExpect(jsonPath("$.searchResults._embedded.products").exists())
				.andExpect(jsonPath("$.searchResults._embedded.products.length()", is(10)))
				
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productUPC").value("19037164"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productDesc").value("Strawberry"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productRetail").value("180"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productMarkdown").value("162.0"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.brandDesc").value("Shine"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[0].data.productImage").value("strawberry.jpg"))
				
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productUPC").value("10688155"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productDesc").value("Musk Melon"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productRetail").value("36"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productMarkdown").value("32.4"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.brandDesc").value("Planters"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.inStock").value("true"))
				.andExpect(jsonPath("$.searchResults._embedded.products[9].data.productImage").value("musk-melon.jpg"))
				
				.andExpect(jsonPath("$.searchResults.page").exists())
				.andExpect(jsonPath("$.searchResults.page.size").value("10"))
				.andExpect(jsonPath("$.searchResults.page.totalElements").value("12"))
				.andExpect(jsonPath("$.searchResults.page.totalPages").value("2"))
				.andExpect(jsonPath("$.searchResults.page.number").value("0"));
	}
	
	@Test
	@Transactional
	public void testGetAllShippingProvidersByTypeCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/Product/Shipping/Provider/" + Constants.localeENGB + "/" + Constants.currencyHKD + "/Code/AIR_PAR_1")
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$.data").exists());
		
		
	}
	

	@Test
	@Transactional
	public void testGetAllShippingProviders() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/Product/Shipping/Provider/" + Constants.localeENGB + "/" + Constants.currencyHKD)
				.with(csrf()).contentType(MediaType.APPLICATION_JSON).content("[]").accept(MediaType.ALL))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$._embedded").exists())
				.andExpect(jsonPath("$._embedded.brands").exists())
				.andExpect(jsonPath("$._embedded.brands[0].data").exists())
				.andExpect(jsonPath("$._embedded.brands[0].data.brandCode").exists())
				.andExpect(jsonPath("$._embedded.brands[0].data.brandCode").value("HKP01"));
				
		
	}			
	

}
