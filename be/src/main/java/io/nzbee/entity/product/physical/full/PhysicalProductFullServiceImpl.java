package io.nzbee.entity.product.physical.full;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhysicalProductFullServiceImpl implements IPhysicalProductFullService {

	@Autowired
	private IPhysicalProductFullDao productDao;
	
	@Override
	public Optional<PhysicalProductFullDTO> findByCode(String locale, String currency, String code) {
		return productDao.findByCode(locale, currency, code);
	}
	

}
