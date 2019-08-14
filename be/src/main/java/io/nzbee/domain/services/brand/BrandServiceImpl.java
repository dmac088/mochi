package io.nzbee.domain.services.brand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Product;
import io.nzbee.domain.Tag;

@Service
@Transactional
@CacheConfig(cacheNames="brands")
public class BrandServiceImpl implements IBrandService {
    
	@Autowired
    private io.nzbee.entity.brand.IBrandService brandService;
    
  
    @Override
	@Transactional
	@Cacheable
	public List<Brand> findAll(String lcl) {
    	List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll();
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
	@Transactional
	@Cacheable
	public Optional<Brand> findOne(Product product) {
    	io.nzbee.entity.brand.Brand pb = brandService.findByCode(product.getBrand().get().getBrandCode()).get();
     	return	createBrandDO(pb, product.getLclCd());
	}
	
	@Override
	public Optional<Brand> findOneByCode(String lcl, String brandCode) {
		// TODO Auto-generated method stub
		return createBrandDO(brandService.findByCode(brandCode).get(), lcl);
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