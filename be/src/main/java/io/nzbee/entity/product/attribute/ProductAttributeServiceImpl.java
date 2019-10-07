package io.nzbee.entity.product.attribute;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.variables.GeneralVars;

@Service
public class ProductAttributeServiceImpl implements IProductAttributeService {

	@Autowired
	private ProductAttributeRepository productAttributeRepository; 
	
	@Override
	public Optional<ProductAttribute> findById(long id) {
		// TODO Auto-generated method stub
		return productAttributeRepository.findById(id);
	}
	
	@Override
	public List<ProductAttribute> getAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;//productAttributeRepository.findAll(locale, currency);
	}

	@Override
	public List<ProductAttribute> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;//productAttributeRepository.findAll();
	}
	

	@Override
	public Optional<ProductAttribute> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductAttribute> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ProductAttribute t) {
		// TODO Auto-generated method stub
		productAttributeRepository.save(t);
	}

	@Override
	public void update(ProductAttribute t, String[] params) {
		// TODO Auto-generated method stub
		productAttributeRepository.save(t);
	}

	@Override
	public void delete(ProductAttribute t) {
		// TODO Auto-generated method stub
		productAttributeRepository.delete(t);		
	}

	@Override
	public Optional<ProductAttribute> getProductAttribute(Long id, String locale) {
		return productAttributeRepository.findByLclCdAndProductId(GeneralVars.LANGUAGE_ENGLISH, id);
	}
	
	@Override
	public Optional<ProductAttribute> getProductAttributeEN(Long id) {
		return productAttributeRepository.findByLclCdAndProductId(GeneralVars.LANGUAGE_ENGLISH, id);
	}
	
	@Override
	public Optional<ProductAttribute> getProductAttributeHK(Long id) {
		return productAttributeRepository.findByLclCdAndProductId(GeneralVars.LANGUAGE_HK, id);
	}

	
}
