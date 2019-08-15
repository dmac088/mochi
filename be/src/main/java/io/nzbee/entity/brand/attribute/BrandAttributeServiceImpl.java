package io.nzbee.entity.brand.attribute;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.variables.GeneralVars;

@Service
public class BrandAttributeServiceImpl implements IBrandAttributeService {

	@Autowired
	private BrandAttributeRepository brandAttributeRepository; 
	
	@Override
	public List<BrandAttribute> getBrandAttributes() {
		return brandAttributeRepository.findAll();
	}
	
	@Override
	public Optional<BrandAttribute> getBrandAttributes(Long Id, String locale) {
		return brandAttributeRepository.findByBrandIdAndLclCd(Id, GeneralVars.LANGUAGE_ENGLISH);
	}
	
	@Override
	public Optional<BrandAttribute> getBrandAttributesEN(Long Id) {
		return brandAttributeRepository.findByBrandIdAndLclCd(Id, GeneralVars.LANGUAGE_ENGLISH);
	}
	
	@Override
	public Optional<BrandAttribute> getBrandAttributesHK(Long Id) {
		return brandAttributeRepository.findByBrandIdAndLclCd(Id, GeneralVars.LANGUAGE_HK);
	}

//	public List<Product> getProduct(Long productId) {
//		return 
//	}

}