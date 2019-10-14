package io.nzbee.dto.tag;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import io.nzbee.variables.ProductVars;


//The Domain object service should be simple and dumb
//it received primitive types and responds with domain objects,
//that are constructed from relevant entity objects
//its simplicity helps us with unit testing

@Service(value="tagDtoService")
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
	public List<Tag> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
	
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
					return entityToDTO(locale, currency, pt);
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
					return entityToDTO(locale, currency, pt);
				}).collect(Collectors.toList());
	}
	
	@Override
	public Optional<Tag> findById(String locale, String currency, long brandId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tag> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tag> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag entityToDTO(String locale, String currency, Object entity) {
		// TODO Auto-generated method stub
		io.nzbee.entity.product.tag.ProductTag tag = ((io.nzbee.entity.product.tag.ProductTag) entity);
		Tag t = new Tag();
		t.setTagId(tag.getTagId());
		t.setTagCode(tag.getCode());
		t.setLocale(locale);
		t.setTagDesc(tag.getAttributes().stream().filter(ta -> ta.getLclCd().equals(locale)).collect(Collectors.toList()).stream().findFirst().get().getTagDesc());
		return t;
	}

	@Override
	public Tag doToDto(Object dO) {
		// TODO Auto-generated method stub
		return null;
	}


}
