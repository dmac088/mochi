package io.nzbee.dto.brand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.nzbee.dto.IDto;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.tag.Tag;

@Service(value="brandDtoService")
@CacheConfig(cacheNames="brands")
public class BrandServiceImpl implements IBrandService {
    
	@Autowired
    private io.nzbee.entity.brand.IBrandService brandService;
    
  
	
    @Override
	@Cacheable
	public List<Brand> findAll(String locale, String currency) {
    	List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll(locale, currency);
    	return lpb.stream().map(pb -> this.entityToDTO(locale, currency, Optional.ofNullable(pb)).get())
    								 .collect(Collectors.toList());
	}	
    

	@Override
	public List<Brand> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll(locale, currency, codes);
    	return lpb.stream().map(pb -> this.entityToDTO(locale, currency, Optional.ofNullable(pb)).get())
    								 .collect(Collectors.toList());
	}
    
	@Override
	@Cacheable
	public Optional<Brand> findById(String locale, String currency, long Id) {
     	return this.entityToDTO(locale, currency, brandService.findById(locale, currency, Id));
	}
	
	@Override
	public Optional<Brand> findByCode(String locale, String currency, String code) {
		return this.entityToDTO(locale, currency, brandService.findByCode(locale, currency, code));
	}

	@Override
	public Optional<Brand> findByDesc(String locale, String currency, String desc) {
		return this.entityToDTO(locale, currency, brandService.findByDesc(locale, currency, desc));
	}
	
	@Override
	public List<Brand> findAll(String locale, String currency, String categoryCode) {
		// TODO Auto-generated method stub
		List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll(locale, currency, categoryCode);
		return lpb.stream().map(pb -> entityToDTO(locale, currency, Optional.ofNullable(pb)).get()).collect(Collectors.toList());
	}
 
	@Override
	//@Cacheable
	public List<Brand> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Tag> tags) {
		//get a list of brands for the selected categories and tags
		List<io.nzbee.entity.brand.Brand> lpb 
			= brandService.findAll(
							locale, 
							currency, 
							categories.stream().map(c -> c.getCategoryCode()).collect(Collectors.toList()), 
							tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList())
							);
		return lpb.stream().map(pb -> entityToDTO(locale, currency, Optional.ofNullable(pb)).get()).collect(Collectors.toList());		
	}
	

	@Override
	public Optional<Brand> entityToDTO(String locale, String currency, Optional<io.nzbee.entity.brand.Brand> entity) {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		io.nzbee.entity.brand.Brand b = ((io.nzbee.entity.brand.Brand) entity.get());
		brand.setBrandCode(b.getBrandCode());
		brand.setBrandDesc(b.getBrandAttribute().getBrandDesc());		
		brand.setLocale(b.getLocale());
		brand.setCurrency(b.getCurrency());
		return Optional.ofNullable(brand);
	}


	@Override
	public Optional<Brand> doToDto(Optional<io.nzbee.domain.brand.Brand> dO) {
		// TODO Auto-generated method stub
		io.nzbee.domain.brand.Brand brandDO = (io.nzbee.domain.brand.Brand) dO.get();
		Brand brandDTO = new Brand();
		brandDTO.setBrandCode(brandDO.getBrandCode());
		brandDTO.setBrandDesc(brandDO.getBrandDesc());
		
		return Optional.ofNullable(brandDTO);
	}


	@Override
	public void save(IDto dto) {
		// TODO Auto-generated method stub
		io.nzbee.entity.brand.attribute.BrandAttribute ba = new io.nzbee.entity.brand.attribute.BrandAttribute();
		ba.setBrandDesc(((Brand) dto).getBrandDesc());
		ba.setLclCd(((Brand) dto).getLocale());
		
		io.nzbee.entity.brand.Brand b = new io.nzbee.entity.brand.Brand();
		b.setBrandCode(((Brand) dto).getBrandCode());		
		b.setLocale(((Brand) dto).getLocale());
		b.setCurrency(((Brand) dto).getCurrency());
		b.setBrandAttribute(ba);
		ba.setBrand(b);
	
		brandService.save(b);
		
	}
	
	


}