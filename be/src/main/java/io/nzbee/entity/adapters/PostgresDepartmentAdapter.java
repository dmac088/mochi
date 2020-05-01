package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.nzbee.domain.department.Department;
import io.nzbee.domain.ports.IDepartmentPortService;
import io.nzbee.entity.product.department.IDepartmentMapper;
import io.nzbee.entity.product.department.IDepartmentService;

@Component
public class PostgresDepartmentAdapter implements IDepartmentPortService {

	@Autowired 
	private IDepartmentService departmentService;
	
	@Autowired
	@Qualifier(value = "departmentMapper")
	private IDepartmentMapper departmentMapper;
	
	@Override
	public Optional<Department> findByProductCode(String locale, String currency, String productCode) {
		return Optional.ofNullable((Department) departmentMapper.entityToDo(departmentService.findByProductCode(locale, currency, productCode), locale, currency).get());		
	}

	@Override
	public Optional<Department> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Department> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Department> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Department> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Department> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Department domainObject) {
		// TODO Auto-generated method stub
		
	}
	
}
