package io.nzbee.entity.product;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.variables.CategoryVars;

@Service(value = "productEntityService")
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductDaoImpl productDAO;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Override
	public List<Product> findAll() {
		return productDAO.getAll();
	}
	
	@Override
	public List<Product> findAll(String locale, String currency, List<Long> productIds) {
		// TODO Auto-generated method stub
		return productDAO.getAll(locale, currency, productIds);
	}
	
	@Override
	public Page<Product> findAll(	String categoryDesc,
									List<String> categoryCodes, 
									String locale, 
									Double priceStart, 
									Double priceEnd, 
									String priceType, 
									String currency, 
									Date priceDateStart, 
									Date priceDateEnd, 
									Pageable pageable, 
									List<String> brandCodes, 
									List<String> tagCodes) {
		
			return productDAO.findAllActiveSKUByPrimaryHierarchyByCode(	
					categoryCodes.isEmpty() ? this.getAllChildCodes(categoryDesc, locale).stream().collect(Collectors.toList())
											: categoryCodes,
					locale,
					priceStart,
					priceEnd,
					priceType,
					currency,
					priceDateStart,
					priceDateEnd,
					pageable,
					brandCodes,
					tagCodes
				 );
	}
	
	
	@Override
	public Page<Product> findAll(	String categoryDesc,
									List<String> categoryCodes, 
									String locale, 
									String priceType, 
									String currency, 
									Date priceDateStart, 
									Date priceDateEnd, 
									Pageable pageable, 
									List<String> brandCodes, 
									List<String> tagCodes) {
		
			return productDAO.findAllActiveSKUByPrimaryHierarchyByCode(	
					categoryCodes.isEmpty() 	? this.getAllChildCodes(categoryDesc, locale).stream().collect(Collectors.toList())
												: categoryCodes,
					locale,
					new Double(-1),
					new Double(-1),
					priceType,
					currency,
					priceDateStart,
					priceDateEnd,
					pageable,
					brandCodes,
					tagCodes
				 );
	}

	@Override
	public Optional<Product> findOne(Long Id) {
		return productDAO.findById(Id);
	}
	
	@Override
	public Optional<Product> findOne(String upc) {
		return productDAO.findByUPC(upc);
	}

	@Override
	public Double getMaxMarkDownPrice(
									String categoryTypeCode, 
									String categoryDesc, 
									String locale, 
									String currencyCode, 
									String productStatusCode, 
									List<String> brandCodes,
									List<String> categoryCodes) {
		
			List<String> children = this.getAllChildCodes(categoryDesc, locale).stream().collect(Collectors.toList());
		
			return productRepository.maxMarkDownPrice(
					categoryTypeCode, 
					categoryDesc, 
					locale, 
					currencyCode, 
					productStatusCode, 
					brandCodes, 
					brandCodes.isEmpty() ? 0 : -1,
					categoryCodes.isEmpty()	? children
											: categoryCodes,
					categoryCodes.isEmpty() && children.isEmpty()
											? 0
											: -1);
	
	}

	@Override
	public Double getMaxMarkDownPriceForTags(
				String categoryTypeCode, 
				String categoryDesc, 
				String locale,
				String currencyCode, 
				String productStatusCode, 
				List<String> brandCodes, 
				List<String> categoryCodes, 
				List<String> tagCodes) {
		
			List<String> children = this.getAllChildCodes(categoryDesc, locale).stream().collect(Collectors.toList());;
			
			// TODO Auto-generated method stub
			return productRepository.maxMarkDownPriceForTags(
					categoryTypeCode, 
					categoryDesc, 
					locale, 
					currencyCode, 
					productStatusCode, 
					brandCodes, 
					brandCodes.isEmpty() ? 0 : -1, 
					categoryCodes, 
					categoryCodes.isEmpty() && children.isEmpty()
					? 0
					: -1, 
					tagCodes, 
					tagCodes.isEmpty() ? 0 : -1);
	}
	
	
	@Override
	public Double getMaxMarkDownPriceForCategory(String categoryCode, String currencyCode) {
		return productRepository.maxMarkDownPriceForCategory(categoryCode, currencyCode);
	}
	
	@Override
	public Long getCountForCategory(String categoryCode) {
		return productRepository.countForCategory(categoryCode);
	}

	@Override
	public Long getCount(
				String categoryDesc, 
				String locale,
				String productStatusCode, 
				List<String> brandCodes, 
				List<String> categoryCodes
				) {
		
		// TODO Auto-generated method stub
		List<String> children = this.getAllChildCodes(categoryDesc, locale).stream().collect(Collectors.toList());;
		
		return productRepository.count(
				categoryDesc, 
				locale, 
				productStatusCode, 
				brandCodes, 
				brandCodes.isEmpty() ? 0 : -1,
				categoryCodes.isEmpty() 	? children
										 	: categoryCodes,
				categoryCodes.isEmpty() && children.isEmpty()
											? 0
											: -1
		);
	}

	@Override
	public Long getCountForTags(String categoryDesc, String locale,
			String productStatusCode, List<String> brandCodes,
			List<String> categoryCodes, List<String> tagCodes) {
		
			List<String> children = this.getAllChildCodes(categoryDesc, locale).stream().collect(Collectors.toList());
			// TODO Auto-generated method stub
			
			return productRepository.countForTags(	categoryDesc,
													locale, 
													productStatusCode, 
													brandCodes,
													brandCodes.isEmpty() ? 0 : -1,
													categoryCodes.isEmpty() 	? children
																				: categoryCodes,
													categoryCodes.isEmpty() && children.isEmpty()
													? 0
													: -1,							
													tagCodes,
													tagCodes.isEmpty() ? 0 : -1);
	}

	
	@Override
	public void save(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub
		
	}
	
	private Set<Long> getAllChildIds(String categoryDesc, String locale ) {
		Category pc = categoryService.findByCategoryDesc(
				CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
				categoryDesc, 
				locale).get();

		Set<Category> lc = new HashSet<Category>();
		return categoryService.recurseCategories(lc, pc)
				.stream().map(c -> c.getCategoryId()).collect(Collectors.toSet());
	}
	
	
	private Set<String> getAllChildCodes(String categoryDesc, String locale ) {
		Category pc = categoryService.findByCategoryDesc(
				CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
				categoryDesc, 
				locale).get();

		Set<Category> lc = new HashSet<Category>();
		return categoryService.recurseCategories(lc, pc)
				.stream().map(c -> c.getCategoryCode()).collect(Collectors.toSet());
	}


}