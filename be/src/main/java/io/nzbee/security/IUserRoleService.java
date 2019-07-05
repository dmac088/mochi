package io.nzbee.security;

public interface IUserRoleService {

	    public UserRole loadUserRoleByRoleName(String roleName);
	    
		public void addUser(UserRole userRole);
}
