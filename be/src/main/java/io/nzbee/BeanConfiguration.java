package io.nzbee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.nzbee.domain.brand.BrandServiceImpl;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.ProductServiceImpl;
import io.nzbee.domain.tag.ITagService;
import io.nzbee.domain.tag.TagServiceImpl;


@Configuration
public class BeanConfiguration {
 
    @Bean(value="productDomainService")
    public IProductService productService() {
        return new ProductServiceImpl();
    }
    
    @Bean(value="brandDomainService")
    public IBrandService brandService() {
        return new BrandServiceImpl();
    }
    
    @Bean(value="tagDomainService")
    public ITagService tagService() {
        return new TagServiceImpl();
    }
}