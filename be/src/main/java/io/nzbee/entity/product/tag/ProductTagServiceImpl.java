package io.nzbee.entity.product.tag;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "productTagService")
public class ProductTagServiceImpl implements IProductTagService {

	@Autowired
	private IProductTagDao productTagDAO;
	
	@Override
	public List<ProductTag> findAll(String locale, Double priceStart, Double priceEnd,
			String priceType, String currency, Date priceDateStart, Date priceDateEnd, List<String> categoryCodes, List<String> brandCodes) {
		// TODO Auto-generated method stub
		return productTagDAO.findAll(locale, 
				priceStart, 
				priceEnd, 
				priceType, 
				currency, 
				priceDateStart, 
				priceDateEnd, 
				categoryCodes, 
				brandCodes);
	}

	@Override
	public List<ProductTag> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return productTagDAO.findAll(locale, currency);
	}
	
	@Override
	public List<ProductTag> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return productTagDAO.findAll(locale, currency, codes);
	}

	@Override
	public Optional<ProductTag> findById(String locale, String currency, long id) {
		// TODO Auto-generated method stub
		return productTagDAO.findById(locale, currency, id);
	}

	@Override
	public Optional<ProductTag> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return productTagDAO.findByCode(locale, currency, code);
	}
	
	@Override
	public Optional<ProductTag> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return productTagDAO.findByDesc(locale, currency, desc);
	}

	@Override
	public void save(ProductTag t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProductTag t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProductTag t) {
		// TODO Auto-generated method stub
		
	}
	
}
