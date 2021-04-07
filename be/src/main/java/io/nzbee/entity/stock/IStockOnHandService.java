package io.nzbee.entity.stock;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IStockOnHandService extends IService<StockOnHandEntity> {

	Optional<StockOnHandEntity> findByProductCode(String productCode);
	
}
