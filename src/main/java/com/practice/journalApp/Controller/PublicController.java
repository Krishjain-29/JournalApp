package com.practice.journalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.journalApp.Entity.User;
import com.practice.journalApp.Service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	@Autowired
	private UserService userService;
	@GetMapping("/health-check")
  public String  healthCheck() {
	  return "OK";
  }
	
	@PostMapping("/create")
	public void createUser(@RequestBody User user) {
		userService.createUser(user);
	}
}
