package io.nzbee.security.user.role;

public interface IUserRoleService {

	    public UserRole loadUserRoleByRoleName(String roleName);
	    
		public void addUser(UserRole userRole);
}
