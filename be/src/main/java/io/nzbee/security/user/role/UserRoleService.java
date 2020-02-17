package io.nzbee.security.user.role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleService implements IUserRoleService  {

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    @Transactional(readOnly = true)
    public UserRole findByName(String roleName) {
    	return userRoleRepository.findByName(roleName);
    }
    
}