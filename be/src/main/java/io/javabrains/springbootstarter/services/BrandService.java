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
import io.javabrains.springbootstarter.dto.SidebarFacetDTO;
import io.javabrains.springbootstarter.entity.BrandRepository;
import io.javabrains.springbootstarter.entity.Category;
import io.javabrains.springbootstarter.entity.CategoryAttribute;
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
	public List<SidebarFacetDTO> getBrands(final String lcl, String currency) {
    	List<io.javabrains.springbootstarter.entity.Brand> lpb = brandRepository.findAll();
    	List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, lcl, currency))
		.sorted((pb1, pb2) -> pb2.getProductCount().compareTo(pb1.getProductCount()))
		.collect(Collectors.toList());
    	return lb.stream().map(b -> createBrandDTO(b)).collect(Collectors.toList());
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
	@Cacheable
	public List<SidebarFacetDTO> getBrandsForCategory(String hierarchyCode, String lcl, String curr, String categoryDesc, List<SidebarFacetDTO> categoryFacets) {
		
		List<Long> selectedCategoryIds = categoryFacets.stream().map(c -> { return c.getId(); }).collect(Collectors.toList());
		CategoryAttribute ca = categoryAttributeRepository.findByCategoryHierarchyCodeAndLclCdAndCategoryDesc(hierarchyCode, lcl, categoryDesc);
		
		if(ca == null) { return null; }
		if(!ca.getCategory().isPresent()) { return null; }
		
		List<Category> allChildCategories = IProductService.recurseCategories(new ArrayList<Category>(), ca.getCategory().get());
		List<Long> allChildIds = allChildCategories.stream().map(c -> c.getCategoryId()).collect(Collectors.toList());
		
		
		List<Long> categoryIds = (categoryFacets.size() > 0) ? selectedCategoryIds : allChildIds;
		
		List<io.javabrains.springbootstarter.entity.Brand> lpb = brandRepository.findDistinctByProductsCategoriesCategoryIdIn(categoryIds);

		List<Brand> lb = lpb.stream().map(pb -> createBrandDO(pb, lcl, curr)).collect(Collectors.toList());
		lb.stream().forEach(bDO -> {
			bDO.setProductCount(
					(categoryFacets.size() > 0) 
					? productRepository.countByCategoriesHierarchyCodeAndCategoriesCategoryCodeAndBrandBrandCode(hierarchyCode, ca.getCategory().get().getCategoryCode(), bDO.getBrandCode())
					: productRepository.countByCategoriesHierarchyCodeAndCategoriesCategoryIdInAndBrandBrandCode(hierarchyCode, allChildIds, bDO.getBrandCode())
					);
		});
		
		List<SidebarFacetDTO> lsfdto = lb.stream().map(b -> createBrandDTO(b)).collect(Collectors.toList())
								.stream().sorted((o1, o2) -> o1.getDesc().compareTo(o2.getDesc()))
								.collect(Collectors.toList());
		
     	return lsfdto;
	}
    
	
 	@Cacheable
    private Brand createBrandDO(final io.javabrains.springbootstarter.entity.Brand b, final String lcl, final String currency) {
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