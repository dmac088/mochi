package io.nzbee.entity.stock;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IStockOnHandService extends IService<StockOnHand> {

	Optional<StockOnHand> findByProductCode(String productCode);
	
}
