package io.nzbee.entity.product;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
@Service(value = "productEntityService")
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private IProductDao productDAO;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	@Qualifier("categoryEntityService")
	private ICategoryService categoryService;
	
	@Override
	public List<Product> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return productDAO.findAll(locale, currency);
	}
	
	@Override
	public List<Product> findAll(String locale, String currency, List<String> productCodes) {
		// TODO Auto-generated method stub
		return productDAO.findAll(locale, currency, productCodes);
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
		
			return productDAO.findAll(
					locale,
					currency,
					categoryCodes.isEmpty() ? this.getAllChildCodes(locale, currency, categoryDesc).stream().collect(Collectors.toList())
											: categoryCodes,
					priceStart,
					priceEnd,
					priceType,
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
		
			return productDAO.findAll(	
					categoryCodes.isEmpty() 	? this.getAllChildCodes(locale, currency, categoryDesc).stream().collect(Collectors.toList())
												: categoryCodes,
					locale,
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
	public Optional<Product> findById(long Id) {
		return productDAO.findById(Id);
	}
	
	@Override
	public Optional<Product> findByCode(String code) {
		return productDAO.findByCode(code);
	}
	
	@Override
	public Optional<Product> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return productDAO.findByDesc(locale, desc);
	}

	@Override
	public Double getMaxMarkDownPrice(
									String categoryTypeCode, 
									String categoryDesc, 
									String locale, 
									String currency, 
									String productStatusCode, 
									List<String> brandCodes,
									List<String> categoryCodes) {
		
			List<String> children = this.getAllChildCodes(locale, currency, categoryDesc).stream().collect(Collectors.toList());
		
			//default element
			List<String> de = Collections.singletonList("0");
			
			return productRepository.maxMarkDownPrice(
					categoryTypeCode, 
					categoryDesc, 
					locale, 
					currency, 
					productStatusCode, 
					brandCodes.isEmpty() ? de : brandCodes, 
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
				String currency, 
				String productStatusCode, 
				List<String> brandCodes, 
				List<String> categoryCodes, 
				List<String> tagCodes) {
		
			List<String> children = this.getAllChildCodes(locale, currency, categoryDesc).stream().collect(Collectors.toList());;
			
			//default element
			List<String> de = Collections.singletonList("0");
			
			// TODO Auto-generated method stub
			return productRepository.maxMarkDownPriceForTags(
					categoryTypeCode, 
					categoryDesc, 
					locale, 
					currency, 
					productStatusCode, 
					brandCodes.isEmpty() ? de : brandCodes, 
					brandCodes.isEmpty() ? 0 : -1, 
					categoryCodes.isEmpty() ? de : categoryCodes, 
					categoryCodes.isEmpty() && children.isEmpty()
					? 0
					: -1, 
					tagCodes.isEmpty() ? de : tagCodes, 
					tagCodes.isEmpty() ? 0 : -1);
	}
	
	
	@Override
	public Long getCount(
				String categoryDesc, 
				String locale,
				String currency,
				String productStatusCode, 
				List<String> brandCodes, 
				List<String> categoryCodes
				) {
		
		// TODO Auto-generated method stub
		List<String> children = this.getAllChildCodes(locale, currency, categoryDesc).stream().collect(Collectors.toList());;
		
		//default element
		List<String> de = Collections.singletonList("0");
		
		return productRepository.count(
				categoryDesc, 
				locale, 
				productStatusCode, 
				brandCodes.isEmpty() ? de : brandCodes, 
				brandCodes.isEmpty() ? 0 : -1,
				categoryCodes.isEmpty() 	? children.isEmpty()
											  ? de
											  : children
										 	: categoryCodes,
				categoryCodes.isEmpty() && children.isEmpty()
											? 0
											: -1
		);
	}

	@Override
	public Long getCountForTags(String categoryDesc, String locale, String currency,
			String productStatusCode, List<String> brandCodes,
			List<String> categoryCodes, List<String> tagCodes) {
		
			List<String> children = this.getAllChildCodes(locale, currency, categoryDesc).stream().collect(Collectors.toList());
			// TODO Auto-generated method stub
			
			//default element
			List<String> de = Collections.singletonList("0");
			
			return productRepository.countForTags(	categoryDesc,
													locale, 
													productStatusCode, 
													brandCodes.isEmpty() ? de : brandCodes,
													brandCodes.isEmpty() ? 0 : -1,
													categoryCodes.isEmpty() 	? 
																				  children.isEmpty()
																				  ? de
																				  : children
																				: categoryCodes,
													categoryCodes.isEmpty() && children.isEmpty()
													? 0
													: -1,							
													tagCodes.isEmpty() ? de : tagCodes,
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
	
	private Set<String> getAllChildCodes(String locale, String currency, String categoryDesc) {
		Category pc = categoryService.findByDesc( 
				categoryDesc, 
				locale).get();

		Set<Category> lc = new HashSet<Category>();
		return categoryService.recurseCategories(currency, lc, pc)
				.stream().map(c -> c.getCategoryCode()).collect(Collectors.toSet());
	}

	

}