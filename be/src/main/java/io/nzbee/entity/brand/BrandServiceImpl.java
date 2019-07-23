package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="brandEntityService")
public class BrandServiceImpl implements IBrandService {

	@Autowired
	private IBrandRepository brandRepository; 
	
	@Override
	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}
	
	@Override
	public Optional<Brand> getBrand(Long Id) {
		return brandRepository.findById(Id);
	}
	
	@Override
	public Optional<Brand> getBrand(String brandCode) {
		return brandRepository.findByBrandCode(brandCode);
	}
	
}