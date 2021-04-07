package io.nzbee.entity.stock;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockOnHandServiceImpl implements IStockOnHandService {

	@Autowired
	private IStockOnHandRepository stockOnHandRepository;
	
	@Override
	public List<StockOnHandEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<StockOnHandEntity> findById(Long id) {
		return stockOnHandRepository.findById(id);
	}

	@Override
	public Optional<StockOnHandEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(StockOnHandEntity t) {
		stockOnHandRepository.save(t);
	}

	@Override
	public void update(StockOnHandEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(StockOnHandEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<StockOnHandEntity> findByProductCode(String productCode) {
		return stockOnHandRepository.findByProductProductUPC(productCode);
	}

	
}
