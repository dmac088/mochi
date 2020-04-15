package io.nzbee.resources.product;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.ProductController;

@Component(value = "ProductResourceAssembler")
public class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {

    public ProductResourceAssembler() {
        super(ProductController.class, ProductResource.class);
    }

    @Override
    public ProductResource toResource(Product product) {
        ProductResource pr = new ProductResource(product);

        
        pr.add(linkTo(methodOn(ProductController.class).get(product.getLclCd(), 
									                		product.getCurrency(), 
									                		product.getProductUPC()
                										)).withSelfRel(),
        		linkTo(methodOn(CategoryController.class).getCategories(product.getLclCd(), 
												                		product.getCurrency(), 
												                		product.getProductUPC()
        												)).withRel("categories"));
        return pr;
    }
    
}