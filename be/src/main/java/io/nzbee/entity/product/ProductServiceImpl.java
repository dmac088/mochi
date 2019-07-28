package io.nzbee.entity.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
									List<Long> categoryIds, 
									String locale, 
									Double priceStart, 
									Double priceEnd, 
									String priceType, 
									String currency, 
									Date priceDateStart, 
									Date priceDateEnd, 
									Pageable pageable, 
									List<Long> brandIds, 
									List<Long> tagIds) {
		
			return productDAO.findAllActiveSKUByPrimaryHierarchy(	
					categoryIds.size() <= 0 ? this.getAllChildIds(categoryDesc, locale)
											: categoryIds,
					locale,
					priceStart,
					priceEnd,
					priceType,
					currency,
					priceDateStart,
					priceDateEnd,
					pageable,
					brandIds,
					tagIds
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
									List<Long> brandIds, 
									int inHandlingBrands,
									List<Long> categoryIds, 
									int inHandlingCategories) {
		
			return productRepository.maxMarkDownPrice(
					categoryTypeCode, 
					categoryDesc, 
					locale, 
					currencyCode, 
					productStatusCode, 
					brandIds, 
					inHandlingBrands, 
					categoryIds.size() <= 0 ? this.getAllChildIds(categoryDesc, locale)
											: categoryIds, 
					inHandlingCategories);
	
	}

	@Override
	public Double getMaxMarkDownPriceForTags(
				String categoryTypeCode, 
				String categoryDesc, 
				String locale,
				String currencyCode, 
				String productStatusCode, 
				List<Long> brandIds,
				int inHandlingBrands, 
				List<Long> categoryIds, 
				int inHandlingCategories, 
				List<Long> tagIds,
				int inHandlingTags) {
		
			// TODO Auto-generated method stub
			return productRepository.maxMarkDownPriceForTags(
					categoryTypeCode, 
					categoryDesc, 
					locale, 
					currencyCode, 
					productStatusCode, 
					brandIds, 
					inHandlingBrands, 
					categoryIds.size() <= 0 ? this.getAllChildIds(categoryDesc, locale)
											: categoryIds, 
					inHandlingCategories, 
					tagIds, 
					inHandlingTags);
	}

	@Override
	public Long getCount(
				String categoryDesc, 
				String locale,
				String productStatusCode, 
				List<Long> brandIds, 
				int inHandlingBrands,
				List<Long> categoryIds, 
				int inHandlingCategories) {
		
		// TODO Auto-generated method stub
		
		
		return productRepository.count(
				categoryDesc, 
				locale, 
				productStatusCode, 
				brandIds, 
				inHandlingBrands, 
				categoryIds.size() <= 0 ? this.getAllChildIds(categoryDesc, locale)
										: categoryIds, 
				inHandlingCategories
		);
	}

	@Override
	public Long getCountForTags(String categoryDesc, String locale,
			String productStatusCode, List<Long> brandIds, int inHandlingBrands,
			List<Long> categoryIds, int inHandlingCategories, List<Long> tagIds, int inHandlingTags) {
			// TODO Auto-generated method stub
			return productRepository.countForTags(	categoryDesc,
													locale, 
													productStatusCode, 
													brandIds, 
													inHandlingBrands, 
													categoryIds.size() <= 0 ? this.getAllChildIds(categoryDesc, locale)
																			: categoryIds, 
													inHandlingCategories, 
													tagIds, 
													inHandlingTags);
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
	
	private List<Long> getAllChildIds(String categoryDesc, String locale ) {
		Category pc = categoryService.findByCategoryDesc(
				CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
				categoryDesc, 
				locale).get();

		List<Category> lc = new ArrayList<Category>();
		return categoryService.recurseCategories(lc, pc)
				.stream().map(c -> c.getCategoryId()).collect(Collectors.toList());
	}


}