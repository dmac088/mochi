package io.nzbee.entity.product.attribute;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.Globals;

@Service
public class ProductAttributeServiceImpl implements IProductAttributeService {

	@Autowired
	private ProductAttributeRepository productAttributeRepository; 
	
	@Autowired
	private Globals globalVars;
	
	@Override
	public Optional<ProductAttribute> findById(String locale, String currency, long id) {
		return productAttributeRepository.findById(id);
	}
	
	@Override
	public List<ProductAttribute> findAll(String locale, String currency) {
		return null;//productAttributeRepository.findAll();
	}
	

	@Override
	public Optional<ProductAttribute> findByCode(String locale, String currency, String code) {
		return productAttributeRepository.findByLclCdAndProductProductUPC(locale, code);
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
		productAttributeRepository.save(t);
	}

	@Override
	public void update(ProductAttribute t, String[] params) {
		productAttributeRepository.save(t);
	}

	@Override
	public void delete(ProductAttribute t) {
		productAttributeRepository.delete(t);		
	}

	@Override
	public Optional<ProductAttribute> getProductAttribute(Long id, String locale) {
		return productAttributeRepository.findByLclCdAndProductProductId(locale, id);
	}
	
	@Override
	public Optional<ProductAttribute> getProductAttributeEN(Long id) {
		return productAttributeRepository.findByLclCdAndProductProductId(globalVars.getLocaleENGB(), id);
	}
	
	@Override
	public Optional<ProductAttribute> getProductAttributeHK(Long id) {
		return productAttributeRepository.findByLclCdAndProductProductId(globalVars.getLocaleZHHK(), id);
	}

	@Override
	public ProductAttribute objectToEntity(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductAttribute objectToEntity(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
