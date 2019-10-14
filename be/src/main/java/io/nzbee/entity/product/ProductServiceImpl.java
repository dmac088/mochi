package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
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
		// TODO Auto-generated method stub
		return productDAO.findById(locale, currency, id);
	}

	@Override
	public Optional<Product> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return productDAO.findByCode(locale, currency, code);
	}

	@Override
	public Optional<Product> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return productDAO.findByDesc(locale, currency, desc);
	}
	
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
	public Page<Product> findAll(	
									String locale, 
									String currency, 
									Double priceStart, 
									Double priceEnd, 
									String priceType, 
									int page,
									int size,
									String categoryDesc,
									List<String> categoryCodes, 
									List<String> brandCodes, 
									List<String> tagCodes) {
		
		return productDAO.findAll(locale,
						 		  currency,
						 		  page, 
						 		  size,
						 		  categoryDesc,
						 		  categoryCodes,
						 		  brandCodes, 
						 		  tagCodes,
						 		  "1");
	}
	
	@Override
	public Page<Product> findAll(	
									String locale, 
									String currency, 
									String priceType, 
									int page,
									int size,
									String categoryDesc,
									List<String> categoryCodes,
									List<String> brandCodes, 
									List<String> tagCodes) {
		
			return productDAO.findAll(locale,
							 		  currency,
							 		  page, 
							 		  size,
							 		  categoryDesc,
							 		  categoryCodes,
							 		  brandCodes, 
							 		  tagCodes,
							 		  "1");
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

	

}