package io.nzbee.resources.category.browse.facet;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.resources.controllers.CategoryController;
import io.nzbee.resources.controllers.ProductController;
import io.nzbee.resources.controllers.TagController;
import io.nzbee.view.category.product.ProductCategoryView;

@Component
public class CategoryBrowseFacetModelAssembler
		extends RepresentationModelAssemblerSupport<ProductCategoryView, CategoryBrowseFacetModel> {

	public CategoryBrowseFacetModelAssembler() {
		super(CategoryController.class, CategoryBrowseFacetModel.class);
	}

	@Override
	public CategoryBrowseFacetModel toModel(ProductCategoryView category) {
		CategoryBrowseFacetModel cr = new CategoryBrowseFacetModel(category);

		cr.add(linkTo(methodOn(ProductController.class).getProducts(null, null, category.getCategoryCode(), null, null,
				null, null)).withRel("products"));

		cr.add(linkTo(
				methodOn(BrandController.class).getBrands(category.getLocale(), null, category.getCategoryCode(), null))
						.withRel("brands"));

		cr.add(linkTo(
				methodOn(TagController.class).getTags(category.getLocale(), null, category.getCategoryCode(), null))
						.withRel("tags"));

		cr.add(linkTo(methodOn(CategoryController.class).getChildCategories(category.getLocale(), null,
				category.getCategoryCode(), null)).withRel("children"));

		cr.add(linkTo(methodOn(CategoryController.class).getChildCategoryFacets(category.getLocale(), null,
				category.getCategoryCode(), null)).withRel("childFacets"));

		cr.add(linkTo(methodOn(BrandController.class).getBrandFacets(category.getLocale(), null,
				category.getCategoryCode(), null)).withRel("brandFacets"));

		cr.add(linkTo(methodOn(TagController.class).getTags(category.getLocale(), null, category.getCategoryCode(),
				null)).withRel("tagFacets"));

		cr.add(linkTo(methodOn(CategoryController.class).getMaxPrice(category.getLocale(), null,
				category.getCategoryCode(), null)).withRel("maxPrice"));

		cr.add(linkTo(methodOn(CategoryController.class).getMaxPriceFacet(category.getLocale(), null,
				category.getCategoryCode(), null)).withRel("maxPriceFacet"));

		return cr;
	}

}