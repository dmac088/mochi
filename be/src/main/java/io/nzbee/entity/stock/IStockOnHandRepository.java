package io.nzbee.entity.stock;

import org.springframework.data.repository.CrudRepository;

public interface IStockOnHandRepository extends CrudRepository<StockOnHand, Long> {
	
}

