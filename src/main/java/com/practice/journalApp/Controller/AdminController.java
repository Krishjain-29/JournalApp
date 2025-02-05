package com.practice.journalApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.practice.journalApp.Repository.UserRepo;
import com.practice.journalApp.Service.UserService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepo userRepo;
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/all-User")
	public ResponseEntity<?> getAllUser() {
	ResponseEntity<?> all=userService.getUser();
	//List<User> all =userRepo.findAll();
		if(all!=null ) {
			return ResponseEntity.ok(all);
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");

	}
}
