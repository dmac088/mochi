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

import io.nzbee.domain.Brand;
import io.nzbee.dto.SidebarFacetDTO;
import io.nzbee.entity.BrandRepository;
import io.nzbee.entity.Category;
import io.nzbee.entity.CategoryAttribute;
import io.nzbee.entity.CategoryAttributeRepository;
import io.nzbee.entity.ProductRepository;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

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
	public List<SidebarFacetDTO> getBrands(final String lcl, String currency) {
    	List<io.nzbee.entity.Brand> lpb = brandRepository.findAll();
    	List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, lcl, currency))
		.sorted((pb1, pb2) -> pb2.getProductCount().compareTo(pb1.getProductCount()))
		.collect(Collectors.toList());
    	return lb.stream().map(b -> createBrandDTO(b)).collect(Collectors.toList());
	}	

	@Override
	@Transactional
	@Cacheable
	public Brand getBrand(String lcl, String curr, Long brandId) {
    	io.nzbee.entity.Brand pb = brandRepository.findByBrandId(brandId);
     	return	createBrandDO(pb, lcl, curr);
	}
 
	@Override
	@Transactional
	//@Cacheable
	public List<SidebarFacetDTO> getBrandsForCategory(String hierarchyCode, String locale, String currency, String categoryDesc, List<SidebarFacetDTO> categoryFacets) {
		
		CategoryAttribute ca = categoryAttributeRepository.findByCategoryHierarchyCodeAndLclCdAndCategoryDesc(hierarchyCode, locale, categoryDesc);
		

		if(ca == null) { return null; }
		if(!ca.getCategory().isPresent()) { return null; }
		
		List<Category> allChildCategories = ProductService.recurseCategories(new ArrayList<Category>(), ca.getCategory().get());
		List<Long> allChildIds = allChildCategories.stream().map(c -> c.getCategoryId()).collect(Collectors.toList());
		
		List<Long> selectedCategoryIds = 
				(categoryFacets.size() > 0) 
				? categoryFacets.stream().map(c -> { return c.getId(); }).collect(Collectors.toList())
				: allChildIds;
		
		List<Long> categoryIds = (categoryFacets.size() > 0) ? selectedCategoryIds : allChildIds;
		
		List<io.nzbee.entity.Brand> lpb = brandRepository.findDistinctByProductsCategoriesCategoryIdIn(categoryIds);

		List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, locale, currency)).collect(Collectors.toList());
		
		lb.stream().forEach(bDO -> {
			List<Long> lid = new ArrayList<Long>();
			lid.add(bDO.getBrandId());
			bDO.setProductCount(
					productRepository.count(CategoryVars.PRIMARY_HIERARCHY_CODE, 
											CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
											categoryDesc, 
											locale,
											currency,
											ProductVars.MARKDOWN_SKU_DESCRIPTION,
											lid.size() == 0 ? Arrays.asList(new Long(1)) : lid ,
											(lid.size() == 0 ? 0 : 1)));
		});
		
		List<SidebarFacetDTO> lsfdto = lb.stream().map(b -> createBrandDTO(b)).collect(Collectors.toList())
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
    	bDto.setFacetingName("BrandFR");
    	bDto.setFieldName("brandDesc");
    	bDto.setToken(b.getBrandCode());
    	bDto.setLevel(new Long(0));
    	bDto.setDesc(b.getBrandDesc());
    	bDto.setId(b.getBrandId());
    	bDto.setProductCount(b.getProductCount());
		return bDto;
    }

}