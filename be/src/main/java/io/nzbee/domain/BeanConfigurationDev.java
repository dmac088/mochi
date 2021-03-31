package io.nzbee.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import io.nzbee.domain.bag.BagItemServiceImpl;
import io.nzbee.domain.bag.BagServiceImpl;
import io.nzbee.domain.bag.IBagItemService;
import io.nzbee.domain.bag.IBagService;
import io.nzbee.domain.brand.BrandServiceImpl;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.customer.CustomerServiceImpl;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.domain.customer.address.AddressServiceImpl;
import io.nzbee.domain.customer.address.IAddressService;
import io.nzbee.domain.department.DepartmentServiceImpl;
import io.nzbee.domain.department.IDepartmentService;
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

@Configuration
@Profile("dev")
public class BeanConfigurationDev {
 
    @Bean
    public IProductService productService() {
        return new ProductServiceImpl();
    }
    
    @Bean
    public IPhysicalProductService physicalProductService() {
        return new PhysicalProductServiceImpl();
    }
    
    @Bean
    public IOrderPromotionService promotionOrderService() {
        return new OrderPromotionServiceImpl();
    }
    
    @Bean
    public IShippingProductService shippingProductService() {
        return new ShippingProductServiceImpl();
    }
    
    @Bean
    public ICategoryService categoryService() {
        return new CategoryServiceImpl();
    }
    
    @Bean
    public IBrandService brandService() {
        return new BrandServiceImpl();
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
    public IDepartmentService departmentService() {
        return new DepartmentServiceImpl();
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