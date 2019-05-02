package io.javabrains.springbootstarter.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.javabrains.springbootstarter.domain.Brand;
import io.javabrains.springbootstarter.entity.BrandRepository;

@Service
@Transactional
@CacheConfig(cacheNames="brands")
public class BrandService implements IBrandService {
    
    @Autowired
    private BrandRepository brandRepository;
    
    @Override
	@Transactional
	@Cacheable
	public List<Brand> getBrands(final String lcl, String currency) {
    	List<io.javabrains.springbootstarter.entity.Brand> lpb = brandRepository.findAll();
    	return lpb.stream().map(pb -> createBrand(pb, lcl, currency))
    			.sorted((pb1, pb2) -> pb2.getProductCount().compareTo(pb1.getProductCount()))
    			.collect(Collectors.toList());
	}	


	@Override
	@Transactional
	@Cacheable
	public Brand getBrand(String lcl, String curr, Long brandId) {
    	io.javabrains.springbootstarter.entity.Brand pb = brandRepository.findByBrandId(brandId);
     	return	createBrand(pb, lcl, curr);
	}


	@Override
	@Transactional
	@Cacheable
	public List<Brand> getBrandsForCategory(String lcl, String curr, String categoryDesc) {
		List<io.javabrains.springbootstarter.entity.Brand> lpb = brandRepository.findByProductsCategoriesAttributesLclCdAndProductsCategoriesAttributesCategoryDesc(lcl, categoryDesc);
     	return lpb.stream().map(pb -> createBrand(pb, lcl, curr))
    			.sorted((pb1, pb2) -> pb2.getProductCount().compareTo(pb1.getProductCount()))
    			.collect(Collectors.toList());
	}
    
    
    
 	@Cacheable
    private Brand createBrand(final io.javabrains.springbootstarter.entity.Brand b, String categoryCode, final String lcl) {
    	final Brand bDto = new Brand();
    	bDto.setBrandId(b.getBrandId());
    	bDto.setBrandCode(b.getBrandCode());
    	bDto.setBrandDesc(
	    	b.getAttributes().stream()
			.filter(ba -> ba.getLclCd().equals(lcl)
			).collect(Collectors.toList()).get(0).getBrandDesc());
    	return bDto;
    }


	
}