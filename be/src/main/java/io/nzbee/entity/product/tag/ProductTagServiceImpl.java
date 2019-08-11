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
	public List<ProductTag> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductTag> findOne(Long id) {
		// TODO Auto-generated method stub
		return productTagDAO.findById(id);
	}

	@Override
	public Optional<ProductTag> findOne(String code) {
		// TODO Auto-generated method stub
		return productTagDAO.findByCode(code);
	}
	
	@Override
	public Optional<ProductTag> findOne(String desc, String locale) {
		// TODO Auto-generated method stub
		return productTagDAO.findByDesc(desc, locale);
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
