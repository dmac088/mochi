package io.nzbee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserDetailsService userService;	
	
	@GetMapping("/User/{username}")
	public UserDetails getUser(@PathVariable String username) {
		return userService.loadUserByUsername(username);
	}
	
}
