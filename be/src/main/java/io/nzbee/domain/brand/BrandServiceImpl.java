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
    private io.nzbee.dto.brand.IBrandService brandService;

	
	@Override
	@Transactional
	@Cacheable
	public Optional<Brand> findById(String locale, String currency, Long Id) {
    	io.nzbee.dto.brand.Brand pb = brandService.findById(locale, currency, Id).get();
     	return	Optional.ofNullable(this.dtoToDO(pb));
	}
	
	@Override
	public Optional<Brand> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(dtoToDO(brandService.findByCode(locale, currency, code)));
	}

	@Override
	public Optional<Brand> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(dtoToDO(brandService.findByDesc(locale, currency, desc)));
	}
  
    @Override
	@Transactional
	@Cacheable
	public List<Brand> findAll(String locale, String currency) {
    	List<io.nzbee.dto.brand.Brand> lpb = brandService.findAll(locale, currency);
    	List<Brand> lb = lpb.stream().map(pb -> dtoToDO(pb))
		.collect(Collectors.toList());
    	return lb;
	}	
    

	@Override
	public List<Brand> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
    
    @Override
	@Transactional
	@Cacheable
	public List<Brand> findAllByCategory(String locale, String category) {
//    	List<io.nzbee.dto.brand.Brand> lpb = brandService.findAll(category);
//    	List<Brand> lb = lpb.stream().map(pb -> dtoToDO(pb))
//		.collect(Collectors.toList());
//    	return lb;
    	return null;
	}	
    
    @Override
	@Transactional
	//@Cacheable
	public List<Brand> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Tag> tags) {
		//get a list of brands for the selected categories and tags
//		List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll(
//																	categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
//																	tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList())
//																	);
//		List<Brand> lb = lpb.stream().map(pb -> dtoToDO(pb)).collect(Collectors.toList());		
//     	return lb;
    	return null;
	}
    
	@Override
	public Brand dtoToDO(Object dto) {
		// TODO Auto-generated method stub
		io.nzbee.dto.brand.Brand brandDTO = (io.nzbee.dto.brand.Brand) dto;
		final Brand bDO = new Brand();
    	bDO.setBrandCode(brandDTO.getBrandCode());
    	bDO.setBrandDesc(brandDTO.getBrandDesc());
    	return bDO;
	}
}