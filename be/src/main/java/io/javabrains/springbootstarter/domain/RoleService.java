package io.javabrains.springbootstarter.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleRepository RoleRepository; 
	
	
	public List<Role> getAllRoles() {
		List<Role> Roles = new ArrayList<>();
		Iterator<Role> i = RoleRepository.findAll().iterator();
		while(i.hasNext()) {
			Roles.add(i.next());
		}
		return Roles;
	}

	/*
	public List<Role> getAllRoles(Long roleTypeId) {
		List<Role> Roles = new ArrayList<>();
		Iterator<Role> i = RoleRepository.findByRoleTypeID(roleTypeId).iterator();
		while(i.hasNext()) {
			Roles.add(i.next());
		}
		return Roles;
	}
	*/
	
	public Optional<Role> getRole(Long id) {
		Optional<Role> p = RoleRepository.findById(id);
		return p;
	}
	
	public void addRole(Role Role) {
		RoleRepository.save(Role);
	}
	
	public void updateRole(String id, Role Role) {
		RoleRepository.save(Role);
	}
	
	public void deleteRole(Long id) {
		RoleRepository.deleteById(id);
	}
	
}
