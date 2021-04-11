package io.nzbee.view.product.physical.full;

import java.util.List;
import io.nzbee.view.IViewService;
import io.nzbee.view.product.physical.light.PhysicalProductLightView;

public interface IPhysicalProductFullService extends IViewService<PhysicalProductFullView> {

	List<PhysicalProductLightView> findByCode(String locale, String currency, String code);

}
