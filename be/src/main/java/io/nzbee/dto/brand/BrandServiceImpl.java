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
    	List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, lcl).get())
		.collect(Collectors.toList());
    	return lb;
	}	
    
    @Override
	@Transactional
	@Cacheable
	public List<Brand> findAll(String category, String lcl) {
    	List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll(category);
    	List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, lcl).get())
		.collect(Collectors.toList());
    	return lb;
	}	

	@Override
	@Transactional
	@Cacheable
	public Optional<Brand> findOne(String lcl, Long brandId) {
    	io.nzbee.entity.brand.Brand pb = brandService.findById(brandId).get();
     	return	createBrandDO(pb, lcl);
	}
	
	
	@Override
	public Optional<Brand> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return this.entityToDO(locale, brandService.findByCode(code).get());
	}

	@Override
	public Optional<Brand> findOneByDesc(String lcl, String brandDesc) {
		// TODO Auto-generated method stub
		return createBrandDO(brandService.findByDesc(brandDesc, lcl).get(), lcl);
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
		List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, locale).get()).collect(Collectors.toList());		
     	return lb;
	}
    
	
 	@Cacheable
    private Optional<Brand> createBrandDO(final io.nzbee.entity.brand.Brand b, final String lcl) {
    	final Brand bDO = new Brand();
    	bDO.setBrandCode(b.getCode());
    	bDO.setBrandDesc(
	    	b.getAttributes().stream()
			.filter(ba -> ba.getLclCd().equals(lcl)
			).collect(Collectors.toList()).get(0).getBrandDesc());
    	return Optional.ofNullable(bDO);
    }



	@Override
	public Optional<Brand> findById(long brandId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Brand> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Brand> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void entityToDTO(String locale, Brand object) {
		// TODO Auto-generated method stub
		
	}
}