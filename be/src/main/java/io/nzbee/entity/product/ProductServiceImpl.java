package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import io.nzbee.dto.IDto;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.tag.Tag;
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
									List<IDto> ldto) {
		
		return productDAO.findAll(locale,
						 		  currency,
						 		  page, 
						 		  size,
						 		  categoryDesc,
						 		  this.getCodes(ldto, Category.class),
						 		  this.getCodes(ldto, Brand.class), 
						 		  this.getCodes(ldto, Tag.class),
						 		  "1");
	}
	
	private <T> List<String> getCodes(List<IDto> l, Class<T> classType) {
		return l.stream().filter(dto -> dto.getClass().equals(classType)).map(c -> c.getCode()).collect(Collectors.toList());
	}
	
	@Override
	public Page<Product> findAll(	
									String locale, 
									String currency, 
									String priceType, 
									int page,
									int size,
									String categoryDesc,
									List<IDto> ldto) {
		
			return productDAO.findAll(locale,
							 		  currency,
							 		  page, 
							 		  size,
							 		  categoryDesc,
							 		  this.getCodes(ldto, Category.class),
							 		  this.getCodes(ldto, Brand.class), 
							 		  this.getCodes(ldto, Tag.class),
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