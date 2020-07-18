package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.ICategoryService;


@Service(value = "productEntityService")
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private IProductDao productDAO;
	
	@Autowired
	@Qualifier("categoryEntityService")
	private ICategoryService categoryService;
	
	@Override
	public Optional<Product> findById(String locale, String currency, long id) {
		return productDAO.findById(locale, currency, id);
	}

	@Override
	public Optional<Product> findByCode(String locale, String currency, String code) {
		return productDAO.findByCode(locale, currency, code);
	}

	@Override
	public Optional<Product> findByDesc(String locale, String currency, String desc) {
		return productDAO.findByDesc(locale, currency, desc);
	}
	
	@Override
	public List<Product> findAll(String locale, String currency) {
		return productDAO.findAll(locale, currency);
	}
	
	@Override
	public List<Product> findAll(String locale, String currency, Set<String> productCodes) {
		return productDAO.findAll(locale, currency, productCodes);
	}
	
	@Override
	public <T> List<Product> findAllByType(String locale, String currency, Class<T> cls) {
		return productDAO.findAllByType(locale, currency, cls);
	}

	
	@Override
	public Page<Product> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort) {
		return productDAO.findAll(
								  locale,
						 		  currency,
						 		  categoryCode,
						 		  categoryCodes,
						 		  brandCodes, 
						 		  tagCodes,
						 		  maxPrice,
						 		  page,
						 		  size,
						 		  sort
				 		  	);
	
	}


	@Override
	public void save(Product t) {
		productDAO.save(t);
		
	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub
		
	}


	
}