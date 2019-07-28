package io.nzbee.domain.services.brand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.domain.Brand;
import io.nzbee.entity.product.IProductService;
import io.nzbee.ui.component.web.sidebar.Sidebar;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

@Service
@Transactional
@CacheConfig(cacheNames="brands")
public class BrandService implements IBrandService {
    
	@Autowired
    private io.nzbee.entity.brand.IBrandService brandService;
    
    @Autowired
    private IProductService productService;
    
    @Override
	@Transactional
	@Cacheable
	public List<Sidebar> getBrands(final String lcl, String currency) {
    	List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll();
    	List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, lcl, currency))
		.sorted((pb1, pb2) -> pb2.getProductCount().compareTo(pb1.getProductCount()))
		.collect(Collectors.toList());
    	return lb.stream().map(b -> createBrandDTO(b)).collect(Collectors.toList());
	}	

	@Override
	@Transactional
	@Cacheable
	public Brand getBrand(String lcl, String curr, Long brandId) {
    	io.nzbee.entity.brand.Brand pb = brandService.findById(brandId).get();
     	return	createBrandDO(pb, lcl, curr);
	}
 
	@Override
	@Transactional
	//@Cacheable
	public List<Brand> getBrands(String locale, String currency, String categoryDesc, List<Long> categoryIds, List<Long> tagIds) {
						
		//get a list of brands for the selected categories and tags
		List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll(categoryIds, tagIds);
		List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, locale, currency)).collect(Collectors.toList());
		
		lb.stream().forEach(bDO -> {
			List<Long> lid = new ArrayList<Long>();
			lid.add(bDO.getBrandId());
			bDO.setProductCount(
					(tagIds.isEmpty()) 
									? productService.getCount(
											categoryDesc, 
											locale,
											ProductVars.ACTIVE_SKU_CODE,
											lid.size() == 0 ? Arrays.asList(new Long(-1)) : lid,
											(lid.size() == 0 ? 0 : 1),
											categoryIds.size() == 0 ? Arrays.asList(new Long(-1)) : categoryIds,
											(categoryIds.size() == 0 ? 0 : 1)
											)
									: productService.getCountForTags(
											categoryDesc, 
											locale,
											ProductVars.ACTIVE_SKU_CODE,
											lid.size() == 0 ? Arrays.asList(new Long(-1)) : lid,
											(lid.size() == 0 ? 0 : 1),
											categoryIds.size() == 0 ? Arrays.asList(new Long(-1)) : categoryIds,
											(categoryIds.size() == 0 ? 0 : 1),
											tagIds.size() == 0 ? Arrays.asList(new Long(-1)) : tagIds,
											(tagIds.size() == 0 ? 0 : 1)
											));
		});
		
     	return lb;
	}
    
	
 	@Cacheable
    private Brand createBrandDO(final io.nzbee.entity.brand.Brand b, final String lcl, final String currency) {
    	final Brand bDO = new Brand();
    	bDO.setBrandId(b.getId());
    	bDO.setBrandCode(b.getCode());
    	bDO.setBrandDesc(
	    	b.getAttributes().stream()
			.filter(ba -> ba.getLclCd().equals(lcl)
			).collect(Collectors.toList()).get(0).getBrandDesc());
    	return bDO;
    }

 	@Cacheable
    private Sidebar createBrandDTO(final Brand b) {
    	final Sidebar bDto = new Sidebar();
    	bDto.setFacetingName(CategoryVars.BRAND_FACET_NAME);
    	bDto.setFieldName("brandDesc");
    	bDto.setToken(b.getBrandCode());
    	bDto.setLevel(new Long(0));
    	bDto.setDesc(b.getBrandDesc());
    	bDto.setId(b.getBrandId());
    	bDto.setProductCount(b.getProductCount());
		return bDto;
    }

}