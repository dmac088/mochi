package io.nzbee.entity.brand;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.variables.GeneralVars;

@Service
public class BrandAttributeService {

	@Autowired
	private BrandAttributeRepository brandAttributeRepository; 
	
	public List<BrandAttribute> getBrandAttributes() {
		return brandAttributeRepository.findAll();
	}
	
	public BrandAttribute getBrandAttributesEN(Long Id) {
		return brandAttributeRepository.findByBrandIdAndLclCd(Id, GeneralVars.LANGUAGE_ENGLISH);
	}
	
	public BrandAttribute getBrandAttributesHK(Long Id) {
		return brandAttributeRepository.findByBrandIdAndLclCd(Id, GeneralVars.LANGUAGE_HK);
	}

//	public List<Product> getProduct(Long productId) {
//		return 
//	}

}