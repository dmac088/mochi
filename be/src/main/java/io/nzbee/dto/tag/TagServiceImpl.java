package io.nzbee.dto.tag;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.tag.IProductTagService;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.variables.ProductVars;


//The Domain object service should be simple and dumb
//it received primitive types and responds with domain objects,
//that are constructed from relevant entity objects
//its simplicity helps us with unit testing

@Service
@Transactional
@CacheConfig(cacheNames="tags")
public class TagServiceImpl implements ITagService {

	@Autowired
	ICategoryService categoryService;
	
	@Autowired
	IProductTagService productTagService;
	
	@Autowired
	IProductService productService;
	
	@Override
	public List<Tag> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Brand> brands) {
		return productTagService.findAll(locale, 
										null, 
										null, 
										ProductVars.MARKDOWN_SKU_DESCRIPTION, 
										currency, 
										new Date(), 
										new Date(), 
										categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
										brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()))
				.stream().map(pt -> {
					return convertToTagDO(pt, locale);
				}).collect(Collectors.toList());	
	}
	
	@Override
	public List<Tag> findAll(String locale, String currency, String categoryDesc, Double price, List<Category> categories, List<Brand> brands) {
		return productTagService.findAll(locale, 
				new Double(0), 
				price, 
				ProductVars.MARKDOWN_SKU_DESCRIPTION, 
				currency, 
				new Date(), 
				new Date(), 
				categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
				brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()))
				.stream().map(pt -> {
					return convertToTagDO(pt, locale);
				}).collect(Collectors.toList());
	}
	
	
	private Tag convertToTagDO(ProductTag pt, String locale) {
		Tag t = new Tag();
		t.setTagId(pt.getTagId());
		t.setTagCode(pt.getCode());
		t.setLocale(locale);
		t.setTagDesc(pt.getAttributes().stream().filter(ta -> ta.getLclCd().equals(locale)).collect(Collectors.toList()).stream().findFirst().get().getTagDesc());
		return t;
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
	public Tag save(Tag t) {
		return t;
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

	@Override
	public Tag findOneById(Long id, String lcl) {
		// TODO Auto-generated method stub
		ProductTag pt = productTagService.findOne(id).get();
		return this.convertToTagDO(pt, lcl);
	}

	@Override
	public Tag findOneByCode(String code, String lcl) {
		ProductTag pt = productTagService.findOne(code).get();
		return this.convertToTagDO(pt, lcl);
	}
	
	@Override
	public Tag findOneByDesc(String desc, String lcl) {
		// TODO Auto-generated method stub
		ProductTag pt = productTagService.findOne(desc, lcl).get();
		return this.convertToTagDO(pt, lcl);
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

}
