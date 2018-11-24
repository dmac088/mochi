package io.javabrains.springbootstarter.security;

import io.javabrains.springbootstarter.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	@PostMapping("/User")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		System.out.println("calling addPuser");
		userService.addUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
}
