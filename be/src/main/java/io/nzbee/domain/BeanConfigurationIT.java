package io.nzbee.domain;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.web.PagedResourcesAssembler;
import io.nzbee.domain.bag.BagItemServiceImpl;
import io.nzbee.domain.bag.BagServiceImpl;
import io.nzbee.domain.bag.IBagItemService;
import io.nzbee.domain.bag.IBagService;
import io.nzbee.domain.customer.CustomerServiceImpl;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.domain.customer.address.AddressServiceImpl;
import io.nzbee.domain.customer.address.IAddressService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.ProductServiceImpl;
import io.nzbee.domain.product.physical.IPhysicalProductService;
import io.nzbee.domain.product.physical.PhysicalProductServiceImpl;
import io.nzbee.domain.product.shipping.IShippingProductService;
import io.nzbee.domain.product.shipping.ShippingProductServiceImpl;
import io.nzbee.domain.promotion.IPromotionService;
import io.nzbee.domain.promotion.PromotionServiceImpl;
import io.nzbee.domain.promotion.order.IOrderPromotionService;
import io.nzbee.domain.promotion.order.OrderPromotionServiceImpl;
import io.nzbee.domain.tag.ITagService;
import io.nzbee.domain.tag.TagServiceImpl;
import io.nzbee.entity.adapters.view.BrandAdapterImpl;
import io.nzbee.entity.adapters.view.PhysicalProductFullAdapterImpl;
import io.nzbee.entity.adapters.view.PhysicalProductLightAdapterImpl;
import io.nzbee.entity.adapters.view.ProductCategoryAdapterImpl;
import io.nzbee.entity.adapters.view.ShippingProductAdapterImpl;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightMapper;
import io.nzbee.entity.product.physical.light.PhysicalProductLightMapperImpl;
import io.nzbee.resources.product.physical.light.PhysicalProductLightResource;
import io.nzbee.resources.product.shipping.ShippingProductResource;
import io.nzbee.view.ports.IBrandViewPortService;
import io.nzbee.view.ports.ICategoryViewPortService;
import io.nzbee.view.ports.IPhysicalProductFullPortService;
import io.nzbee.view.ports.IPhysicalProductLightPortService;
import io.nzbee.view.ports.IShippingProductPortService;
import io.nzbee.view.product.brand.BrandViewServiceImpl;
import io.nzbee.view.product.brand.IBrandViewService;
import io.nzbee.view.product.brand.facet.BrandFacetViewServiceImpl;
import io.nzbee.view.product.brand.facet.IBrandFacetViewService;
import io.nzbee.view.product.physical.full.IPhysicalProductFullService;
import io.nzbee.view.product.physical.full.PhysicalProductFullServiceImpl;
import io.nzbee.view.product.physical.light.IPhysicalProductLightService;
import io.nzbee.view.product.physical.light.PhysicalProductLightServiceImpl;


@Configuration
@Profile("it")
public class BeanConfigurationIT {

	
	@Bean
	public ICategoryViewPortService productCategoryPortService() {
		return new ProductCategoryAdapterImpl();
	}
	
	@Bean
	public io.nzbee.entity.brand.view.facet.IBrandFacetViewMapper brandFacetViewMapper() { 
		return new io.nzbee.entity.brand.view.facet.BrandFacetViewMapperImpl();
	}
	
	@Bean
	public io.nzbee.view.ports.IBrandFacetViewPortService brandEntityViewService() {
		return new io.nzbee.entity.adapters.view.BrandFacetAdapterImpl();
	}
	
	@Bean
	public io.nzbee.entity.brand.view.IBrandViewService test() {
		return new io.nzbee.entity.brand.view.BrandViewServiceImpl();
	}
	
	@Bean
	public IBrandViewService brandViewService() {
		return new BrandViewServiceImpl();
	}
	
	@Bean
	public IBrandFacetViewService brandFacetService() {
		return new BrandFacetViewServiceImpl();
	}
	
	@Bean
	public IBrandViewPortService brandViewPortService() {
		return new BrandAdapterImpl();
	}
	
	@Bean
	public IPhysicalProductLightService physicalProductLightService() {
		return new PhysicalProductLightServiceImpl();
	}
	
	@Bean
	public IPhysicalProductFullService physicalProductFullService() {
		return new PhysicalProductFullServiceImpl();
	}
	
	@Bean
	public IShippingProductPortService shippingProductPortService() {
		return new ShippingProductAdapterImpl();
	}
	
	@Bean	
	public IPhysicalProductLightPortService productLightPortService() {
		return new PhysicalProductLightAdapterImpl();
	}
	
	@Bean
	public IPhysicalProductLightMapper productLightMapper() {
		return new PhysicalProductLightMapperImpl();
	}
	
	@Bean
	public IPhysicalProductFullPortService productFullPortService() {
		return new PhysicalProductFullAdapterImpl();
	}
	
    @Bean
    public IOrderPromotionService promotionOrderService() {
        return new OrderPromotionServiceImpl();
    }
	
	@Bean
    public PagedResourcesAssembler<ShippingProductResource> pagedShippingProductResourceAssembler() {
    	return new PagedResourcesAssembler<ShippingProductResource>(null, null);
    }
	
	@Bean
    public PagedResourcesAssembler<PhysicalProductLightResource> pagedPhysicalProductLightResourceAssembler() {
    	return new PagedResourcesAssembler<PhysicalProductLightResource>(null, null);
    }
	
    @Bean
    public IProductService productService() {
        return new ProductServiceImpl();
    }
    
    @Bean
    public IPhysicalProductService physicalProductService() {
        return new PhysicalProductServiceImpl();
    }
    
    @Bean
    public IShippingProductService shippingProductService() {
        return new ShippingProductServiceImpl();
    }
    
    @Bean
    public ITagService tagService() {
        return new TagServiceImpl();
    }
    
    @Bean
    public IPromotionService promotionService() {
        return new PromotionServiceImpl();
    }
    
    @Bean
    public ICustomerService customerService() {
        return new CustomerServiceImpl();
    }
    
    @Bean
    public IBagService bagService() {
        return new BagServiceImpl();
    }
    
    @Bean
    public IBagItemService bagItemService() {
        return new BagItemServiceImpl();
    }
    
    @Bean
    public IAddressService addressService() {
        return new AddressServiceImpl();
    }
  
}