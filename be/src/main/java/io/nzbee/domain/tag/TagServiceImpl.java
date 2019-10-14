package io.nzbee.domain.tag;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.tag.IProductTagService;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.Category;


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
	public Optional<Tag> findById(String locale, String currency, Long Id) {
		// TODO Auto-generated method stub
		ProductTag pt = productTagService.findById(Id).get();
		return Optional.ofNullable(this.dtoToDO(pt));
	}

	@Override
	public Optional<Tag> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		ProductTag pt = productTagService.findByCode(code).get();
		return Optional.ofNullable(this.dtoToDO(pt));
	}

	@Override
	public Optional<Tag> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		ProductTag pt = productTagService.findByDesc(locale, desc).get();
		return Optional.ofNullable(this.dtoToDO(pt));
	}

	@Override
	public List<Tag> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Tag> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> findAll(String locale, String currency, String categoryDesc, List<Category> categories,
			List<Brand> brands) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> findAll(String locale, String currency, String categoryDesc, Double price,
			List<Category> categories, List<Brand> brands) {
		// TODO Auto-generated method stub
		return null;
	}	
	
//	@Override
//	public List<Tag> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Brand> brands) {
//		return productTagService.findAll(locale, 
//										null, 
//										null, 
//										ProductVars.MARKDOWN_SKU_DESCRIPTION, 
//										currency, 
//										new Date(), 
//										new Date(), 
//										categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
//										brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()))
//				.stream().map(pt -> {
//					return dTOToDO(pt);
//				}).collect(Collectors.toList());	
//	}
//	
//	@Override
//	public List<Tag> findAll(String locale, String currency, String categoryDesc, Double price, List<Category> categories, List<Brand> brands) {
//		return productTagService.findAll(locale, 
//				new Double(0), 
//				price, 
//				ProductVars.MARKDOWN_SKU_DESCRIPTION, 
//				currency, 
//				new Date(), 
//				new Date(), 
//				categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
//				brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()))
//				.stream().map(pt -> {
//					return dTOToDO(pt);
//				}).collect(Collectors.toList());
//	}



	@Override
	public Tag dtoToDO(Object dto) {
		// TODO Auto-generated method stub
		io.nzbee.dto.tag.Tag tagDTO = (io.nzbee.dto.tag.Tag) dto;
				
		Tag t = new Tag();
		t.setTagId(tagDTO.getTagId());
		t.setTagCode(tagDTO.getTagCode());
		t.setLocale(tagDTO.getLocale());
		t.setTagDesc(tagDTO.getTagDesc());
		return t;
	}

	@Override
	public String tokenToCode(String token) {
		// TODO Auto-generated method stub
		return token;
	}
}
