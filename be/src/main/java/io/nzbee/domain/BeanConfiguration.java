package io.nzbee.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import io.nzbee.domain.department.DepartmentServiceImpl;
import io.nzbee.domain.department.IDepartmentService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.ProductServiceImpl;
import io.nzbee.domain.promotion.IPromotionService;
import io.nzbee.domain.promotion.PromotionServiceImpl;
import io.nzbee.domain.tag.ITagService;
import io.nzbee.domain.tag.TagServiceImpl;
import io.nzbee.dto.bag.item.BagItemDTOMapperImpl;
import io.nzbee.dto.bag.item.IBagItemDTOMapper;


@Configuration
public class BeanConfiguration {
 
    @Bean
    public IProductService productService() {
        return new ProductServiceImpl();
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
    
    @Bean(name="customerDomainService")
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
    public IBagItemDTOMapper bagItemDtoMapper() {
		return new BagItemDTOMapperImpl();
    }
  
}