package io.nzbee.entity.stock;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IStockOnHandRepository extends CrudRepository<StockOnHandEntity, Long> {
	
	Optional<StockOnHandEntity> findByProductProductUPC(String productUPC);
	
}

