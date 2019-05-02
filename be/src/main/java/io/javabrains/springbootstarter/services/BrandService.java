package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.javabrains.springbootstarter.domain.Brand;
import io.javabrains.springbootstarter.entity.BrandRepository;
import io.javabrains.springbootstarter.entity.Category;
import io.javabrains.springbootstarter.entity.CategoryAttributeRepository;
import io.javabrains.springbootstarter.entity.ProductRepository;

@Service
@Transactional
@CacheConfig(cacheNames="brands")
public class BrandService implements IBrandService {
    
    @Autowired
    private BrandRepository brandRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryAttributeRepository categoryAttributeRepository;
    
    @Override
	@Transactional
	@Cacheable
	public List<Brand> getBrands(final String lcl, String currency) {
    	List<io.javabrains.springbootstarter.entity.Brand> lpb = brandRepository.findAll();
    	return lpb.stream().map(pb -> createBrandDO(pb, lcl, currency))
    			.sorted((pb1, pb2) -> pb2.getProductCount().compareTo(pb1.getProductCount()))
    			.collect(Collectors.toList());
	}	

	@Override
	@Transactional
	@Cacheable
	public Brand getBrand(String lcl, String curr, Long brandId) {
    	io.javabrains.springbootstarter.entity.Brand pb = brandRepository.findByBrandId(brandId);
     	return	createBrandDO(pb, lcl, curr);
	}

	@Override
	@Transactional
	//@Cacheable
	public List<Brand> getBrandsForCategory(String lcl, String curr, String categoryDesc) {
		io.javabrains.springbootstarter.entity.CategoryAttribute ca = categoryAttributeRepository.findByLclCdAndCategoryDesc(lcl, categoryDesc);
		List<Category> cl = IProductService.recurseCategories(new ArrayList<Category>(), ca.getCategory());
		List<io.javabrains.springbootstarter.entity.Brand> lpb = brandRepository.findDistinctByProductsCategoriesCategoryIdIn(cl.stream().map(c -> c.getCategoryId()).collect(Collectors.toList()));
		List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, lcl, curr)).collect(Collectors.toList());
		lb.stream().forEach(bDto -> {
			bDto.setProductCount(productRepository.countByCategoriesCategoryCodeAndBrandBrandCode(ca.getCategory().getCategoryCode(), bDto.getBrandCode()));
		});
     	return lb;
	}
    
 	//@Cacheable
    private Brand createBrandDO(final io.javabrains.springbootstarter.entity.Brand b, final String lcl, final String currency) {
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