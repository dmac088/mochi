package io.nzbee.domain.product.shipping;


public interface IShippingProductService {

	ShippingProduct findByDestinationAndTypeAndWeight(String locale, String currency, String destinationCode,
			String type, Double weightKg);

}
