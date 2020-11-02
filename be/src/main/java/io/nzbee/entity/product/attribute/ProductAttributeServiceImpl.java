package io.nzbee.entity.product.attribute;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeServiceImpl implements IProductAttributeService {

	@Autowired
	private IProductAttributeRepository productAttributeRepository; 
	
	@Override
	public Optional<ProductAttributeEntity> findById(long id) {
		return productAttributeRepository.findById(id);
	}

	@Override
	public Optional<ProductAttributeEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ProductAttributeEntity> findAll() {
		// TODO Auto-generated method stub
		return productAttributeRepository.findAll();
	}

	@Override
	public Set<ProductAttributeEntity> findAll(List<String> codes) {
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

}
