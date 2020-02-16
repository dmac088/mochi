package io.nzbee.security.user.role;

public interface IUserRoleService {

	    public UserRole findByName(String roleName);
	    
		public void addUser(UserRole userRole);
}
