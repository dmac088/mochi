package io.nzbee.entity.product.department;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
    private IDepartmentDao departmentDao;
	
	@Autowired
    private IDepartmentRepository departmentRepository;
	
	@Override
	public List<DepartmentDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartmentDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartmentEntity> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Optional<DepartmentEntity> findById(Long id) {
		return departmentRepository.findById(id);
	}

	@Override
	public Optional<DepartmentEntity> findByCode(String code) {
		return departmentRepository.findByDepartmentCode(code);
	}
	
	@Override
	public Optional<DepartmentDTO> findById(String locale, Long id) {
		return departmentDao.findById(locale, id) ;
	}

	@Override
	public Optional<DepartmentDTO> findByCode(String locale, String code) {
		return departmentDao.findByCode(locale, code);
	}

	@Override
	public Optional<DepartmentDTO> findByDesc(String locale, String desc) {
		return departmentDao.findByDesc(locale, desc);
	}
	
	@Override
	public Optional<DepartmentDTO> findByProductCode(String locale, String productCode) {
		return departmentDao.findByProductCode(locale, productCode);
	}


	@Override
	public void save(DepartmentEntity t) {
		departmentDao.save(t);
	}

	@Override
	public void update(DepartmentEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DepartmentEntity t) {
		departmentDao.save(t);
	}

	@Override
	public List<DepartmentEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartmentDTO> findAll(String locale, String currency, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

}
