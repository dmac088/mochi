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
	public List<StockOnHand> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<StockOnHand> findById(Long id) {
		return stockOnHandRepository.findById(id);
	}

	@Override
	public Optional<StockOnHand> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(StockOnHand t) {
		stockOnHandRepository.save(t);
	}

	@Override
	public void update(StockOnHand t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(StockOnHand t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<StockOnHand> findByProductCode(String productCode) {
		return stockOnHandRepository.findByProductProductUPC(productCode);
	}

	
}
