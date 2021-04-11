package io.nzbee.entity.brand.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BrandViewServiceImpl implements IBrandViewService {

	@Autowired
	private IBrandViewRepository brandRepo;
	
	@Override
	public void save(BrandViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BrandViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BrandViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BrandViewDTO> findAllByProductType(String locale, Class<?> cls) {
		return brandRepo.findAllByProductType(locale, cls.getSimpleName());
	}

	
}
