package io.javabrains.springbootstarter.security;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRoleRepository userRoleRepository;
	
  @Autowired
  private UserRepository userRepository;
    
  @Override 
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String userName)
    throws UsernameNotFoundException {
      User user = userRepository.findByUsername(userName);
      if (user == null) {
          return new org.springframework.security.core.userdetails.User(
            " ", " ", true, true, true, true, 
            getAuthorities(Arrays.asList(userRoleRepository.findByName("CUSTOMER"))));
      }

      return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), user.isEnabled(), true, true, 
        true, getAuthorities(user.getUserRoles()));
  }
  
	private Collection<? extends GrantedAuthority> getAuthorities(
	Collection<UserRole> roles) {  	
	  return getGrantedAuthorities(getPrivileges(roles));  
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
	  List<GrantedAuthority> authorities = new ArrayList<>();
	  for (String privilege : privileges) {
	      authorities.add(new SimpleGrantedAuthority(privilege));
	  }
	  return authorities;
	}
	
	private List<String> getPrivileges(Collection<UserRole> roles) {
	  List<String> privileges = new ArrayList<>();
	  List<Authority> collection = new ArrayList<>();
	  for (UserRole role : roles) {
	      collection.addAll(role.getAuthorities());
	  }
	  for (Authority item : collection) {
	      privileges.add(item.getAuthority());
	  }
	  return privileges;
	}
	
	

//    @Override 
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if (user != null) {
//            return user;
//        }
//
//        throw new UsernameNotFoundException(username);
//    }
    
    
	@Transactional
	public void addUser(User user) {
		userRepository.save(user);
	}
}

//package io.javabrains.springbootstarter.security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//@Service
//@Transactional
//public class UserService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//    
//    @Autowired
//    private UserRoleRepository userRoleRepository;
//
//    @Override 
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String userName)
//      throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(userName);
//        if (user == null) {
//            return new org.springframework.security.core.userdetails.User(
//              " ", " ", true, true, true, true, 
//              getAuthorities(Arrays.asList(userRoleRepository.findByName("CUSTOMER"))));
//        }
// 
//        return new org.springframework.security.core.userdetails.User(
//          user.getUsername(), user.getPassword(), user.isEnabled(), true, true, 
//          true, getAuthorities(user.getUserRoles()));
//    }
// 
//    private Collection<? extends GrantedAuthority> getAuthorities(
//      Collection<UserRole> roles) {  	
//        return getGrantedAuthorities(getPrivileges(roles));
//        
//    }
//    
//    private List<String> getPrivileges(Collection<UserRole> roles) {
//        List<String> privileges = new ArrayList<>();
//        List<Authority> collection = new ArrayList<>();
//        for (UserRole role : roles) {
//            collection.addAll(role.getAuthorities());
//        }
//        for (Authority item : collection) {
//            privileges.add(item.getAuthority());
//        }
//        return privileges;
//    }
//    
//    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String privilege : privileges) {
//            authorities.add(new SimpleGrantedAuthority(privilege));
//        }
//        return authorities;
//    }
//    
//	@Transactional
//	public void addUser(User user) {
//		userRepository.save(user);
//	}
//}