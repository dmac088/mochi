package io.nzbee.entity.adapters;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public Department findByProductCode(String locale, String currency, String productCode) {
		return (Department) departmentMapper.DTOToDo(departmentService.findByProductCode(locale, productCode).get());		
	}

	@Override
	public Set<Department> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Department> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Department findByCode(String locale, String code) {
		return (Department) departmentMapper.DTOToDo(departmentService.findByCode(locale, code).get());	
	}

	@Override
	public Department findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Department domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Department domainObject) {
		// TODO Auto-generated method stub
		
	}
	
}
