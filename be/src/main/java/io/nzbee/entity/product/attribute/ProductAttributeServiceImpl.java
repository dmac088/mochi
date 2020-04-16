package io.nzbee.entity.product.attribute;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.variables.GeneralVars;

@Service
public class ProductAttributeServiceImpl implements IProductAttributeService {

	@Autowired
	private ProductAttributeRepository productAttributeRepository; 
	
	@Override
	public Optional<ProductAttribute> findById(String locale, String currency, long id) {
		// TODO Auto-generated method stub
		return productAttributeRepository.findById(id);
	}
	
	@Override
	public List<ProductAttribute> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;//productAttributeRepository.findAll();
	}
	

	@Override
	public Optional<ProductAttribute> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductAttribute> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ProductAttribute> findAll(String locale, String currency, Set<String> codes) {
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
		return productAttributeRepository.findByLclCdAndProductProductId(GeneralVars.LANGUAGE_ENGLISH, id);
	}
	
	@Override
	public Optional<ProductAttribute> getProductAttributeEN(Long id) {
		return productAttributeRepository.findByLclCdAndProductProductId(GeneralVars.LANGUAGE_ENGLISH, id);
	}
	
	@Override
	public Optional<ProductAttribute> getProductAttributeHK(Long id) {
		return productAttributeRepository.findByLclCdAndProductProductId(GeneralVars.LANGUAGE_HK, id);
	}

	@Override
	public ProductAttribute objectToEntity(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
