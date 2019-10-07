package io.nzbee.dto.brand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.tag.Tag;

@Service(value="brandDtoService")
@Transactional
@CacheConfig(cacheNames="brands")
public class BrandServiceImpl implements IBrandService {
    
	@Autowired
    private io.nzbee.entity.brand.IBrandService brandService;
    
  
    @Override
	@Transactional
	@Cacheable
	public List<Brand> findAll(String locale, String currency) {
    	List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll(locale, currency);
    	return lpb.stream().map(pb -> this.entityToDTO(locale, currency, pb))
    								 .collect(Collectors.toList());

	}	
    
	@Override
	@Transactional
	@Cacheable
	public Optional<Brand> findById(String locale, String currency, long Id) {
    	io.nzbee.entity.brand.Brand pb = brandService.findById(Id).get();
     	return	Optional.ofNullable(this.entityToDTO(locale, currency, pb));
	}
	
	@Override
	public Optional<Brand> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.entityToDTO(locale, currency, brandService.findByCode(code).get()));
	}

	@Override
	public Optional<Brand> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.entityToDTO(locale, currency, brandService.findByDesc(locale, desc).get()));
	}
 
	@Override
	@Transactional
	//@Cacheable
	public List<Brand> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Tag> tags) {
		//get a list of brands for the selected categories and tags
		List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll(
																	categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
																	tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList())
																	);
		return lpb.stream().map(pb -> entityToDTO(locale, currency, pb)).collect(Collectors.toList());		
	}

	@Override
	public Brand entityToDTO(String locale, String currency, Object entity) {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		io.nzbee.entity.brand.Brand b = ((io.nzbee.entity.brand.Brand) entity);
		brand.setBrandCode(b.getCode());
		brand.setBrandDesc(b.getBrandAttribute().getBrandDesc());		
		return brand;
	}

}