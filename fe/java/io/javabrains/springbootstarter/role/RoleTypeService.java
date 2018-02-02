package io.javabrains.springbootstarter.role;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleTypeService {

	@Autowired
	private RoleTypeRepository roleTypeRepository; 
	
	public List<RoleType> getAllRoleTypes() {
		List<RoleType> RoleTypes = new ArrayList<>();
		Iterator<RoleType> i = roleTypeRepository.findAll().iterator();
		while(i.hasNext()) {
			RoleTypes.add(i.next());
		}
		return RoleTypes;
	}
	
	public RoleType getRoleType(Long id) {
		RoleType p = roleTypeRepository.findOne(id);
		return p;
	}
	
	public RoleType getRoleType(String RoleTypeDesc) {
		RoleType p = roleTypeRepository.findByRoleTypeDesc(RoleTypeDesc);
		return p;
	}
	
	public void addRoleType(RoleType RoleType) {
		roleTypeRepository.save(RoleType);
	}
	
	public void updateRoleType(String id, RoleType RoleType) {
		roleTypeRepository.save(RoleType);
	}
	
	public void deleteRoleType(Long id) {
		roleTypeRepository.delete(id);
	}
	
}
