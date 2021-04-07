package io.nzbee.view.ports;
import java.util.List;
import java.util.Optional;
import io.nzbee.view.product.shipping.ShippingProductView;
import io.nzbee.view.product.shipping.destination.ShippingDestinationView;
import io.nzbee.view.product.shipping.type.ShippingTypeView;

public interface IShippingProductPortService extends IPortService<ShippingProductView> {


	List<ShippingDestinationView> findAllActiveByBagWeight(String locale, Double totalWeight);

	List<ShippingTypeView> findAll(String locale);

	Optional<ShippingTypeView> findTypeByCode(String locale, String providerCode);
	
	Optional<ShippingDestinationView> findDestinationByCode(String locale, String destinationCode);

	List<ShippingTypeView> findAllTypesByDestinationAndWeight(String locale, String destinationCode, Double totalWeight);

	ShippingProductView findByDestinationAndTypeAndBagWeight(String locale, String currency,
			String code, String type, Double totalWeight);

}
