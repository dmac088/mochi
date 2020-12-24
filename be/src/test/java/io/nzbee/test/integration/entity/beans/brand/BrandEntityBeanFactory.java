package io.nzbee.test.integration.entity.beans.brand;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.attribute.BrandAttributeEntity;

@Service
@Profile(value = "it")
public class BrandEntityBeanFactory implements IBrandEntityBeanFactory {
	
	@Override
	public BrandEntity getBean() {
		BrandEntity brand = new BrandEntity();
		brand.setBrandCode("TST02");

		BrandAttributeEntity brandAttributeEN = new BrandAttributeEntity();
		brandAttributeEN.setBrand(brand);
		brandAttributeEN.setBrandDesc("test brand");
		brandAttributeEN.setLclCd(Constants.localeENGB);
		brandAttributeEN.setBrand(brand);
		brand.addAttribute(brandAttributeEN);
		
		final BrandAttributeEntity brandAttributeZH = new BrandAttributeEntity();
		brandAttributeZH.setBrand(brand);
		brandAttributeZH.setBrandDesc("測試品牌");
		brandAttributeZH.setLclCd(Constants.localeZHHK);
		brandAttributeZH.setBrand(brand);
		brand.addAttribute(brandAttributeZH);
		
		return brand;
	}
	
}
