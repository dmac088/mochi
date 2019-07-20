package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="brandEntityService")
public class BrandService {

	@Autowired
	private BrandRepository brandRepository; 
	
	public List<Brand> getBrandAttributes() {
		return brandRepository.findAll();
	}
	
	public Optional<Brand> getBrand(Long Id) {
		return brandRepository.findById(Id);
	}
	
	public Optional<Brand> getBrand(String brandCode) {
		return brandRepository.findByCode(brandCode);
	}
	
}