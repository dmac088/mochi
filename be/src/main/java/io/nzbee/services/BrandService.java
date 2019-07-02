package io.nzbee.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.dao.BrandDAO;
import io.nzbee.domain.Brand;
import io.nzbee.dto.SidebarFacetDTO;
import io.nzbee.entity.ProductRepository;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

@Service
@Transactional
@CacheConfig(cacheNames="brands")
public class BrandService implements IBrandService {
    
	@Autowired
    private BrandDAO brandDAO;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
	@Transactional
	@Cacheable
	public List<SidebarFacetDTO> getBrands(final String lcl, String currency) {
    	List<io.nzbee.entity.Brand> lpb = brandDAO.getAll();
    	List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, lcl, currency))
		.sorted((pb1, pb2) -> pb2.getProductCount().compareTo(pb1.getProductCount()))
		.collect(Collectors.toList());
    	return lb.stream().map(b -> createBrandDTO(b)).collect(Collectors.toList());
	}	

	@Override
	@Transactional
	@Cacheable
	public Brand getBrand(String lcl, String curr, Long brandId) {
    	io.nzbee.entity.Brand pb = brandDAO.get(brandId).get();
     	return	createBrandDO(pb, lcl, curr);
	}
 
	@Override
	@Transactional
	//@Cacheable
	public List<SidebarFacetDTO> getBrands(String hierarchyCode, String locale, String currency, String categoryDesc, List<SidebarFacetDTO> facets) {
				
		List<Long> categoryIds = facets.stream().filter(c -> c.getFacetingName().equals(CategoryVars.PRIMARY_CATEGORY_FACET_NAME)).collect(Collectors.toList())
				.stream().map(c -> { return c.getId(); }).collect(Collectors.toList());
		
		List<Long> tagIds = facets.stream().filter(c -> c.getFacetingName().equals(CategoryVars.TAG_FACET_NAME)).collect(Collectors.toList())
				.stream().map(t -> { return t.getId();}).collect(Collectors.toList());
		
		//get a list of brands for the selected categories and tags
		List<io.nzbee.entity.Brand> lpb = brandDAO.getAll(categoryIds, locale, tagIds);
		List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, locale, currency)).collect(Collectors.toList());
		
		lb.stream().forEach(bDO -> {
			List<Long> lid = new ArrayList<Long>();
			lid.add(bDO.getBrandId());
			bDO.setProductCount(
					(tagIds.isEmpty()) 
									? productRepository.count(CategoryVars.PRIMARY_HIERARCHY_CODE, 
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
									: productRepository.countForTags(CategoryVars.PRIMARY_HIERARCHY_CODE, 
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
		
		List<SidebarFacetDTO> lsfdto = lb.stream().filter(b -> b.getProductCount() > 0).map(b -> createBrandDTO(b)).collect(Collectors.toList())
								.stream().sorted((o1, o2) -> o1.getDesc().compareTo(o2.getDesc()))
								.collect(Collectors.toList());
		
     	return lsfdto;
	}
    
	
 	@Cacheable
    private Brand createBrandDO(final io.nzbee.entity.Brand b, final String lcl, final String currency) {
    	final Brand bDO = new Brand();
    	bDO.setBrandId(b.getBrandId());
    	bDO.setBrandCode(b.getBrandCode());
    	bDO.setBrandDesc(
	    	b.getAttributes().stream()
			.filter(ba -> ba.getLclCd().equals(lcl)
			).collect(Collectors.toList()).get(0).getBrandDesc());
    	return bDO;
    }

 	@Cacheable
    private SidebarFacetDTO createBrandDTO(final Brand b) {
    	final SidebarFacetDTO bDto = new SidebarFacetDTO();
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