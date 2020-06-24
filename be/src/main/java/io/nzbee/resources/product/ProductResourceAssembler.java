package io.nzbee.resources.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.ProductController;

@Component(value = "ProductResourceAssembler")
public class ProductResourceAssembler extends RepresentationModelAssemblerSupport<Product, ProductResource> {

    public ProductResourceAssembler() {
        super(ProductController.class, ProductResource.class);
    }

    @Override
    public ProductResource toModel(Product product) {
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