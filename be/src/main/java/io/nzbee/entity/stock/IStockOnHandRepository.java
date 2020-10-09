package io.nzbee.entity.stock;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IStockOnHandRepository extends CrudRepository<StockOnHand, Long> {
	
	Optional<StockOnHand> findByProductProductUPC(String productUPC);
	
}

