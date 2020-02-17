package io.nzbee.security.user.role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userRoleService")
public class UserRoleService implements IUserRoleService  {

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public UserRole findByName(String roleName) {
    	return userRoleRepository.findByName(roleName);
    }
    
}