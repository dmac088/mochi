package io.nzbee.domain.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.domain.IDomainObject;
import io.nzbee.domain.IFacetService;

@Service(value = "brandDomainService")
@Transactional
@CacheConfig(cacheNames="brands")
public class BrandServiceImpl implements IBrandService, IFacetService {
    
	@Autowired
    private io.nzbee.dto.brand.IBrandService brandService;
	
	@Override
	@Transactional
	@Cacheable
	public Optional<Brand> findById(String locale, String currency, Long Id) {
		
		brandService.f
		
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
	public Set<Brand> findAll(String locale, String currency) {
    	List<io.nzbee.dto.brand.Brand> lpb = brandService.findAll(locale, currency);
    	Set<Brand> lb = lpb.stream().map(pb -> dtoToDO(pb))
    			.collect(Collectors.toSet());
    	return lb;
	}	
    
    @Override
	public Set<Brand> findAll(String locale, String currency, String category) {
    	List<io.nzbee.dto.brand.Brand> lpb = brandService.findAll(locale, currency, category);
    	Set<Brand> lb = lpb.stream().map(pb -> dtoToDO(pb))
    			.collect(Collectors.toSet());
		return lb;
	}
	
	@Override
	public Set<Brand> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		List<io.nzbee.dto.brand.Brand> lpb = brandService.findAll(locale, currency, codes);
    	Set<Brand> lb = lpb.stream().map(pb -> dtoToDO(pb))
		.collect(Collectors.toSet());
    	return lb;
	}
	
	@Override
	public Set<Brand> findAll(String locale, String currency, String categoryDesc, List<IDomainObject> lDo) {
		// TODO Auto-generated method stub
		return null;//brandService.finall;
	}

	
	@Override
	public Brand dtoToDO(io.nzbee.dto.brand.Brand dto) {
		// TODO Auto-generated method stub
		io.nzbee.dto.brand.Brand brandDTO = (io.nzbee.dto.brand.Brand) dto;
		final Brand bDO = new Brand();
    	bDO.setBrandCode(brandDTO.getBrandCode());
    	bDO.setBrandDesc(brandDTO.getBrandDesc());
    	return bDO;
	}

	@Override
	public String tokenToCode(String token) {
		// TODO Auto-generated method stub
		return token;
	}

	@Override
	public String getFacetField() {
		// TODO Auto-generated method stub
		return "brandCode";
	}

	@Override
	public String getFacetCategory() {
		// TODO Auto-generated method stub
		return "BrandFR";
	}


	
}