package io.javabrains.springbootstarter.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

	@Autowired
	private RoleRepository RoleRepository; 
	
	@PreAuthorize("hasAuthority('ROLE_READER')")
	@Transactional(readOnly = true)
	public List<Role> getAllRoles() {
		List<Role> Roles = new ArrayList<>();
		Iterator<Role> i = RoleRepository.findAll().iterator();
		while(i.hasNext()) {
			Roles.add(i.next());
		}
		return Roles;
	}

	@PreAuthorize("hasAuthority('ROLE_READ')")
	@Transactional(readOnly = true)
	public Optional<Role> getRole(Long id) {
		Optional<Role> p = RoleRepository.findById(id);
		return p;
	}
	
	@PreAuthorize("hasAuthority('ROLE_CREATE')")
	@Transactional
	public void addRole(Role Role) {
		RoleRepository.save(Role);
	}
	
	@PreAuthorize("hasAuthority('ROLE_UPDATE')")
	@Transactional
	public void updateRole(String id, Role Role) {
		RoleRepository.save(Role);
	}
	
	@PreAuthorize("hasAuthority('ROLE_DELETE')")
	@Transactional
	public void deleteRole(Long id) {
		RoleRepository.deleteById(id);
	}
	
}
