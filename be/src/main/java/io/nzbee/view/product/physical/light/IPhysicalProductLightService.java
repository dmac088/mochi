package io.nzbee.view.product.physical.light;

import java.util.Set;

import org.springframework.data.domain.Page;
import io.nzbee.view.IViewService;

public interface IPhysicalProductLightService extends IViewService<PhysicalProductLightView> {

	Page<PhysicalProductLightView> findAll(String locale, String currency, String categoryCode,
			Set<String> categoryCodes, Set<String> brandCodes,
			Set<String> tagCodes, Double maxPrice, String page, String size, String sort);


}