package io.nzbee.entity.role.supplier;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private ISupplierRepository supplierRespository;
	
	@Override
	public List<Supplier> findAll() {
		return supplierRespository.findAll();
	}

	@Override
	public Optional<Supplier> findById(Long id) {
		return supplierRespository.findById(id);
	}

	@Override
	public Optional<Supplier> findByCode(String code) {
		return supplierRespository.findBySupplierNumber(code);
	}

	@Override
	public void save(Supplier t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Supplier t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Supplier t) {
		// TODO Auto-generated method stub
		
	}

}
