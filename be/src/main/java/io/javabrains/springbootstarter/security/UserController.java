package io.javabrains.springbootstarter.security;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;



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
