package io.nzbee.entity.role;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl {

	@Autowired
	private IRoleRepository RoleRepository; 
	
	@PreAuthorize("hasAuthority('ROLE_READER')")
	public List<Role> getAllRoles() {
		List<Role> Roles = new ArrayList<>();
		Iterator<Role> i = RoleRepository.findAll().iterator();
		while(i.hasNext()) {
			Roles.add(i.next());
		}
		return Roles;
	}

	@PreAuthorize("hasAuthority('ROLE_READ')")
	public Optional<Role> getRole(Long id) {
		Optional<Role> p = RoleRepository.findById(id);
		return p;
	}
	
	@PreAuthorize("hasAuthority('ROLE_CREATE')")
	public void addRole(Role Role) {
		RoleRepository.save(Role);
	}
	
	@PreAuthorize("hasAuthority('ROLE_UPDATE')")
	public void updateRole(String id, Role Role) {
		RoleRepository.save(Role);
	}
	
	@PreAuthorize("hasAuthority('ROLE_DELETE')")
	public void deleteRole(Long id) {
		RoleRepository.deleteById(id);
	}
	
}
