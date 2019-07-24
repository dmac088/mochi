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
import io.nzbee.entity.product.IProductRepository;
import io.nzbee.ui.component.web.sidebar.SidebarDto;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

@Service
@Transactional
@CacheConfig(cacheNames="brands")
public class BrandService implements IBrandService {
    
	@Autowired
    private io.nzbee.entity.brand.IBrandService brandService;
    
    @Autowired
    private IProductRepository productRepository;
    
    @Override
	@Transactional
	@Cacheable
	public List<SidebarDto> getBrands(final String lcl, String currency) {
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
	public List<SidebarDto> getBrands(String hierarchyCode, String locale, String currency, String categoryDesc, List<SidebarDto> facets) {
				
		List<Long> categoryIds = facets.stream().filter(c -> c.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)).collect(Collectors.toList())
				.stream().map(c -> { return c.getId(); }).collect(Collectors.toList());
		
		List<Long> tagIds = facets.stream().filter(c -> c.getFacetingName().equals(CategoryVars.TAG_FACET_NAME)).collect(Collectors.toList())
				.stream().map(t -> { return t.getId();}).collect(Collectors.toList());
		
		//get a list of brands for the selected categories and tags
		List<io.nzbee.entity.brand.Brand> lpb = brandService.findAll(categoryIds, tagIds);
		List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, locale, currency)).collect(Collectors.toList());
		
		lb.stream().forEach(bDO -> {
			List<Long> lid = new ArrayList<Long>();
			lid.add(bDO.getBrandId());
			bDO.setProductCount(
					(tagIds.isEmpty()) 
									? productRepository.count(
											CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
											categoryDesc, 
											locale,
											currency,
											ProductVars.MARKDOWN_SKU_DESCRIPTION,
											ProductVars.ACTIVE_SKU_CODE,
											lid.size() == 0 ? Arrays.asList(new Long(-1)) : lid,
											(lid.size() == 0 ? 0 : 1),
											categoryIds.size() == 0 ? Arrays.asList(new Long(-1)) : categoryIds,
											(categoryIds.size() == 0 ? 0 : 1)
											)
									: productRepository.countForTags(
											CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
											categoryDesc, 
											locale,
											currency,
											ProductVars.MARKDOWN_SKU_DESCRIPTION,
											ProductVars.ACTIVE_SKU_CODE,
											lid.size() == 0 ? Arrays.asList(new Long(-1)) : lid,
											(lid.size() == 0 ? 0 : 1),
											categoryIds.size() == 0 ? Arrays.asList(new Long(-1)) : categoryIds,
											(categoryIds.size() == 0 ? 0 : 1),
											tagIds.size() == 0 ? Arrays.asList(new Long(-1)) : tagIds,
											(tagIds.size() == 0 ? 0 : 1)
											));
		});
		
		List<SidebarDto> lsfdto = lb.stream()
				.filter(b -> b.getProductCount() > 0)
				.map(b -> createBrandDTO(b)).collect(Collectors.toList()).stream()
				.sorted((o1, o2) -> o1.getDesc().compareTo(o2.getDesc()))
				.collect(Collectors.toList());
		
     	return lsfdto;
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
    private SidebarDto createBrandDTO(final Brand b) {
    	final SidebarDto bDto = new SidebarDto();
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