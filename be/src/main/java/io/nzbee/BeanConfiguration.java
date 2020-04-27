package io.nzbee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.nzbee.domain.brand.BrandServiceImpl;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.customer.CustomerServiceImpl;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.ProductServiceImpl;
import io.nzbee.domain.tag.ITagService;
import io.nzbee.domain.tag.TagServiceImpl;

@Configuration
public class BeanConfiguration {
 
    @Bean(value="brandDomainService")
    public IBrandService brandService() {
        return new BrandServiceImpl();
    }
    
    @Bean(value="categoryDomainService")
    public ICategoryService categoryService() {
        return new CategoryServiceImpl();
    }
    
    @Bean(value="tagDomainService")
    public ITagService tagService() {
        return new TagServiceImpl();
    }
    
    @Bean(value="customerDomainService")
    public ICustomerService customerService() {
        return new CustomerServiceImpl();
    }
    
    @Bean(value="productDomainService")
    public IProductService productDomainService() {
        return new ProductServiceImpl();
    }
}