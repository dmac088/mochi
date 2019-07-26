package io.nzbee.domain.services.tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.Tag;
import io.nzbee.domain.services.category.CategoryService;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.tag.IProductTagService;
import io.nzbee.ui.component.web.sidebar.Sidebar;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

public class TagService implements ITagService {

	@Autowired
	ICategoryService categoryService;
	
	@Autowired
	IProductTagService productTagService;
	
	@Autowired
	IProductService productService;
	
	@Override
	public List<Tag> findAll(String locale, String currency, String categoryDesc, List<Sidebar> selectedFacets) {
			
			//categories
			List<Sidebar> selectedCategories = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME);}).collect(Collectors.toList());
			List<Category> lpc = selectedCategories.stream().map(f-> {
																		return categoryService.findByCategoryDesc(CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, f.getDesc(), locale).get();
																	 }).collect(Collectors.toList());
			List<Category> lpcf = new ArrayList<Category>();
			lpc.stream().forEach(pc -> { 
				lpcf.addAll(CategoryService.recurseCategories(new ArrayList<Category>(), pc)); 
			});
			List<Long> facetCategoryIds = lpcf.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());

			//brands
			List<Sidebar> selectedBrands = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME);}).collect(Collectors.toList());
			List<Long> selectedBrandIds = selectedBrands.stream().map(b -> {return b.getId();}).collect(Collectors.toList());
				
			
			return null;
			//return productTagService.findAll(facetCategoryIds, locale, null, null, ProductVars.MARKDOWN_SKU_DESCRIPTION, currency, new Date(), new Date(), selectedBrandIds);
			
		
//			List<Sidebar> lf = pt.stream().map(t -> {
//											Sidebar f = new Sidebar();
//											f.setFacetingName(CategoryVars.TAG_FACET_NAME);
//											f.setId(t.getTagId());
//											f.setToken(t.getTag().get().getCode());
//											f.setDesc(t.getTagDesc());
//											f.setProductCount(productService.getCountForTags( 
//																								CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
//																								categoryDesc, 
//																								locale, 
//																								currency, 
//																								ProductVars.MARKDOWN_SKU_DESCRIPTION, 
//																								ProductVars.ACTIVE_SKU_CODE,
//																								((!selectedBrandIds.isEmpty()) 	? selectedBrandIds 	: Arrays.<Long>asList(new Long(-1))), 
//																								((!selectedBrandIds.isEmpty()) 	? 1 : 0), 
//																								((!facetCategoryIds.isEmpty()) 	? facetCategoryIds 	: Arrays.<Long>asList(new Long(-1))), 
//																								((!facetCategoryIds.isEmpty()) 	? 1 : 0), 
//																								Arrays.<Long>asList(new Long(t.getTagId())), 
//																								1));
//											return f;
//										}).collect(Collectors.toList())
//										.stream().filter(t -> t.getProductCount() > 0).collect(Collectors.toList());
//			
//			return lf;	
	}
	
	@Override
	public List<Tag> findAll(String locale, String currency, String categoryDesc, Double price, List<Sidebar> selectedFacets) {
		
		//all categories (if non selected in facets
		Optional<Category> parent = categoryService.findByCategoryDesc(CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, categoryDesc, locale);
		List<Category> allCategories = CategoryService.recurseCategories(new ArrayList<Category>(), parent.get());
		List<Long> allCategoryIds = allCategories.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());
						
		//Facets
		List<Sidebar> selectedCategories = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME);}).collect(Collectors.toList());
		List<Category> lpc = selectedCategories.stream().map(f-> {return categoryService.findByCategoryDesc(CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, f.getDesc(), locale).get();}).collect(Collectors.toList());
							
		List<Category> lpcf = new ArrayList<Category>();
		lpc.stream().forEach(pc -> { lpcf.addAll(CategoryService.recurseCategories(new ArrayList<Category>(), pc)); });

		List<Long> facetCategoryIds = lpcf.stream().map(sc -> { return sc.getCategoryId(); }).collect(Collectors.toList());

		List<Sidebar> selectedBrands = selectedFacets.stream().filter(f -> {return f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME);}).collect(Collectors.toList());
		List<Long> selectedBrandIds = selectedBrands.stream().map(b -> {return b.getId();}).collect(Collectors.toList());
				     	
		List<Long> categoryIds = (selectedCategories.size() > 0) ? facetCategoryIds : allCategoryIds;
		
		return null;
		//return productTagService.findAll(categoryIds, locale, new Double(0), price, ProductVars.MARKDOWN_SKU_DESCRIPTION, currency, new Date(), new Date(), selectedBrandIds);
		
//		List<ProductTagAttribute> pt = productTagService.findAll(categoryIds, locale, new Double(0), price, ProductVars.MARKDOWN_SKU_DESCRIPTION, currency, new Date(), new Date(), selectedBrandIds);
		
//		List<Sidebar> lf = pt.stream().map(t -> {
//										Sidebar f = new Sidebar();
//										f.setFacetingName(CategoryVars.TAG_FACET_NAME);
//										f.setId(t.getTagId());
//										f.setToken(t.getTag().get().getCode());
//										f.setDesc(t.getTagDesc());
//										f.setProductCount(new Long(0));
//										return f;
//									}).collect(Collectors.toList())
//									.stream().filter(t -> t.getProductCount() > 0).collect(Collectors.toList());;
//		
//		return lf;	
	}
	
	@Override
	public Tag findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag findOne(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag load() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Tag t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Tag t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tag t) {
		// TODO Auto-generated method stub
		
	}

}
