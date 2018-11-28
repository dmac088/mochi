package io.javabrains.springbootstarter.security;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;	
	
	@GetMapping("/User/{username}")
	public UserDetails getUser(@PathVariable String username) {
		System.out.println("calling getUser");
		return userService.loadUserByUsername(username);
	}
	
}
