package io.nzbee.entity.product.department;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
    private IDepartmentDao departmentDao;
	
	@Override
	public List<Department> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Department> findById(String locale, String currency, long id) {
		// TODO Auto-generated method stub
		return departmentDao.findById(locale, currency, id) ;
	}

	@Override
	public Optional<Department> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return departmentDao.findByCode(locale, currency, code);
	}

	@Override
	public Optional<Department> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return departmentDao.findByDesc(locale, currency, desc);
	}

	@Override
	public void save(Department t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Department t) {
		// TODO Auto-generated method stub
		departmentDao.save(t);
	}

}
