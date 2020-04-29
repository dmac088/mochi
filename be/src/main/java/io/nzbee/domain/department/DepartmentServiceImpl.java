package io.nzbee.domain.department;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IDepartmentPortService;


public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentPortService departmentService;
	
	@Override
	public Optional<Department> findByCode(String locale, String currency, String code) {
		return departmentService.findByCode(locale, currency, code);
	}

	@Override
	public Optional<Department> findByDesc(String locale, String currency, String desc) {
		return departmentService.findByDesc(locale, currency, desc);
	}

	@Override
	public Set<Department> findAll(String locale, String currency) {
		return departmentService.findAll(locale, currency);
	}

	@Override
	public Set<Department> findAll(String locale, String currency, Set<String> codes) {
		return departmentService.findAll(locale, currency, codes);
	}

	@Override
	public void save(Department object) {
		departmentService.save(object);
	}

	@Override
	public void delete(Department object) {
		// TODO Auto-generated method stub
		
	}
}
