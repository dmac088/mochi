package io.nzbee.entity.product.department;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
    private IDepartmentDao departmentDao;
	
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
	public Optional<Department> findById(String locale, long id) {
		return departmentDao.findById(locale, id) ;
	}

	@Override
	public Optional<Department> findByCode(String locale, String code) {
		return departmentDao.findByCode(locale, code);
	}

	@Override
	public Optional<Department> findByDesc(String locale, String desc) {
		return departmentDao.findByDesc(locale, desc);
	}
	
	@Override
	public Optional<Department> findByProductCode(String locale, String productCode) {
		return departmentDao.findByProductCode(locale, productCode);
	}


	@Override
	public void save(Department t) {
		departmentDao.save(t);
	}

	@Override
	public void update(Department t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Department t) {
		departmentDao.save(t);
	}

}
