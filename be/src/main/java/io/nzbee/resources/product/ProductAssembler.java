package io.nzbee.resources.product;

import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import io.nzbee.domain.product.Product;
import io.nzbee.resource.controllers.ProductController;

public class ProductAssembler extends ResourceAssemblerSupport<Product, ProductResource> {

    public ProductAssembler() {
        super(ProductController.class, ProductResource.class);
    }

    @Override
    public ProductResource toResource(Product product) {
        ProductResource resource = new ProductResource(product);

        Link selfLink = linkTo(
                methodOn(ProductController.class).getProduct(product.getLclCd(), product.getCurrency(), product.getProductUPC()))
                .withSelfRel();
        resource.add(selfLink);

        return resource;
    }
    
}