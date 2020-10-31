package io.nzbee.entity.product.attribute;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;

@Service
public class ProductAttributeServiceImpl implements IProductAttributeService {

	@Autowired
	private ProductAttributeRepository productAttributeRepository; 
	
	@Override
	public Optional<ProductAttributeEntity> findById(String locale, String currency, Long id) {
		return productAttributeRepository.findById(id);
	}
	
	@Override
	public List<ProductAttributeEntity> findAll(String locale, String currency) {
		return null;//productAttributeRepository.findAll();
	}
	

	@Override
	public Optional<ProductAttributeDTO> findByCode(String locale, String code) {
		return null;//productAttributeRepository.findByLclCdAndProductProductUPC(locale, code);
	}

	@Override
	public Optional<ProductAttributeEntity> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ProductAttributeEntity> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void save(ProductAttributeEntity t) {
		productAttributeRepository.save(t);
	}

	@Override
	public void update(ProductAttributeEntity t, String[] params) {
		productAttributeRepository.save(t);
	}

	@Override
	public void delete(ProductAttributeEntity t) {
		productAttributeRepository.delete(t);		
	}

	@Override
	public Optional<ProductAttributeEntity> getProductAttribute(Long id, String locale) {
		return productAttributeRepository.findByLclCdAndProductProductId(locale, id);
	}
	
	@Override
	public Optional<ProductAttributeEntity> getProductAttributeEN(Long id) {
		return productAttributeRepository.findByLclCdAndProductProductId(Constants.localeENGB, id);
	}
	
	@Override
	public Optional<ProductAttributeEntity> getProductAttributeHK(Long id) {
		return productAttributeRepository.findByLclCdAndProductProductId(Constants.localeZHHK, id);
	}

	@Override
	public Optional<ProductAttributeDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductAttributeDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ProductAttributeDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ProductAttributeDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductAttributeDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductAttributeDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductAttributeDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductAttributeDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
//	@Override
//	public ProductAttributeEntity objectToEntity(Object[] o, String locale, String currency) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ProductAttributeEntity objectToEntity(Tuple t, String locale, String currency) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ProductAttributeEntity objectToEntity(Object[] o, String locale) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ProductAttributeEntity objectToEntity(Tuple t, String locale) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<ProductAttributeEntity> findById(String locale, Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<ProductAttributeEntity> findByDesc(String locale, String desc) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Set<ProductAttributeEntity> findAll(String locale) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Set<ProductAttributeEntity> findAll(String locale, Set<String> codes) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
}
