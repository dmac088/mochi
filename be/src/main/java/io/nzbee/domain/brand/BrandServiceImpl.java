package io.nzbee.domain.brand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.tag.Tag;

@Service(value = "brandDomainService")
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
    	List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, locale).get())
		.collect(Collectors.toList());
    	return lb;
	}	
    
    @Override
	@Transactional
	@Cacheable
	public List<Brand> findAll(String locale, String category) {
    	List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll(category);
    	List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, locale).get())
		.collect(Collectors.toList());
    	return lb;
	}	

	@Override
	@Transactional
	@Cacheable
	public Optional<Brand> findById(long brandId) {
    	io.nzbee.entity.brand.Brand pb = brandService.findById(brandId).get();
     	return	createBrandDO(pb, locale);
	}
	
	@Override
	public Optional<Brand> findByCode(String locale, String brandCode) {
		// TODO Auto-generated method stub
		return createBrandDO(brandService.findByCode(brandCode).get(), lcl);
	}

	@Override
	public Optional<Brand> findByDesc(String locale, String brandDesc) {
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
    
	@Override
	public Brand convertToBrandDO(String brandCode, String brandDesc) {
		Brand b = new Brand();
		b.setBrandCode(brandCode);
		b.setBrandDesc(brandDesc);
		return b; 
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
	public Brand converToBrandDO(io.nzbee.entity.brand.Brand brand, String locale) {
		
		return null;
	}

}