package com.practice.journalApp.Controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.journalApp.Entity.User;
import com.practice.journalApp.Service.UserService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/user")
@Slf4j

public class UserController {
	
	@Autowired
	 private UserService userService;
	@GetMapping("/all")
	public ResponseEntity<?> getUser(){
		return userService.getUser();
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody User user){
		log.info("Done");
		return userService.createUser(user);
		
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id){
		log.info("USER FETCHED");
		return userService.getUserById(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?>  updateUser(@RequestBody User user) {
		return userService.updateUser(user);
		 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id){
		return userService.deleteUser(id);
	}
	@DeleteMapping("/deleteByUsername")
	public ResponseEntity<?> deleteUserByUsername(){
		return userService.deleteUserByUsername();
	}
	
	@GetMapping("/greet")
	public ResponseEntity<?> greeting(){
		return userService.greeting();
	}
	
	

}
