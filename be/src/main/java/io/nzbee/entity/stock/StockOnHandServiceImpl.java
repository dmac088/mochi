package io.nzbee.entity.stock;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockOnHandServiceImpl implements IStockOnHandService {

	@Autowired
	private IStockOnHandRepository stockOnHandRepository;
	
	@Override
	public Optional<StockOnHandEntity> findById(Long id) {
		return stockOnHandRepository.findById(id);
	}

	@Override
	public void save(StockOnHandEntity t) {
		stockOnHandRepository.save(t);
	}

	@Override
	public void update(StockOnHandEntity t) {
		stockOnHandRepository.save(t);
	}

	@Override
	public void delete(StockOnHandEntity t) {
		stockOnHandRepository.delete(t);
	}

	@Override
	public Optional<StockOnHandEntity> findByProductCode(String productCode) {
		return stockOnHandRepository.findByProductProductUPC(productCode);
	}

	
}
