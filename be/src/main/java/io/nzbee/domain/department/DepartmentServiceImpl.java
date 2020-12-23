package io.nzbee.domain.department;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IDepartmentPortService;


public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentPortService departmentService;
	
	@Override
	public Department findByCode(String locale, String code) {
		return departmentService.findByCode(locale, code);
	}

	@Override
	public Department findByDesc(String locale, String desc) {
		return departmentService.findByDesc(locale, desc);
	}

	@Override
	public Department findByProductCode(String locale, String currency, String departmentCode) {
		return departmentService.findByProductCode(locale, currency, departmentCode);
	}
	
	@Override
	public List<Department> findAll(String locale) {
		return departmentService.findAll(locale);
	}

	@Override
	public List<Department> findAll(String locale, Set<String> codes) {
		return departmentService.findAll(locale, codes);
	}

	@Override
	public void save(Department object) {
		departmentService.save(object);
	}

	@Override
	public void delete(Department object) {
		departmentService.delete(object);
	}

	@Override
	public void update(Department object) {
		departmentService.update(object);
	}

}
