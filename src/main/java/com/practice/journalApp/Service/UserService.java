package com.practice.journalApp.Service;

import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.journalApp.Entity.Entry;
import com.practice.journalApp.Entity.User;
import com.practice.journalApp.Repository.EntryRepo;
import com.practice.journalApp.Repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EntryRepo entryRepo;
	
	private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	public void saveUser(User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
	    user.setRoles(Arrays.asList("USER")); // Set default role if applicable
	    userRepo.save(user);
	}

	
	public void saveNewUser(User user) {
		userRepo.save(user);
	}

	public ResponseEntity<?> getUser() {
		
		return ResponseEntity.ok(userRepo.findAll());
	}

//	public ResponseEntity<?> createUser(User user) {
//		userRepo.save(user);
//		return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
//		
//	}
	public ResponseEntity<?> createUser(User user) {
		 if (userRepo.findByUserName(user.getUserName()) != null) {
		        return ResponseEntity.status(HttpStatus.CONFLICT).body("User with username '" + user.getUserName() + "' already exists");
		    }
	    user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
	    user.setRoles(Arrays.asList("USER")); // Set default role if applicable
	    userRepo.save(user);
	    return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
	}


	public ResponseEntity<?> getUserById(String id) {
		
		Optional<User> userOptional=userRepo.findById(id);
		if(userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}

	public ResponseEntity<?> updateUser(User user) {
		// TODO Auto-generated method stub
//		Optional<User> userOptional=userRepo.findByUser(userName);
//		if(userOptional.isPresent()) {
//			User oldUser =userOptional.get();
//			oldUser.setId(newUser.getId()!=null && !newUser.getId().equals("") ?newUser.getId():oldUser.getId());
//			oldUser.setUserName(newUser.getUserName()!=null && !newUser.getUserName().equals("")?newUser.getUserName():oldUser.getUserName());
//			oldUser.setPassword(newUser.getPassword()!=null && !newUser.getPassword().equals("")?newUser.getPassword():oldUser.getPassword());
//			userRepo.save(oldUser);
			//return ResponseEntity.ok(oldUser);
		//}
		org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		User userInDB=userRepo.findByUserName(userName);
		if(userInDB!=null) {
			userInDB.setUserName(user.getUserName());
			userInDB.setPassword(user.getPassword());
			//userService.saveUser(userInDB);
			saveUser(userInDB);
			userRepo.save(userInDB);
			return ResponseEntity.ok(userInDB);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
	}

	public ResponseEntity<?> deleteUser(String id) {
		// TODO Auto-generated method stub
		if(userRepo.existsById(id)){
			userRepo.deleteById(id);
			return ResponseEntity.ok("User deleted successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
	}
 
	public ResponseEntity<?> getEntryOfUser() {
		// TODO Auto-generated method stub
		org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		
		User user=userRepo.findByUserName(userName);
		List<Entry> list=entryRepo.findAll();
		return ResponseEntity.ok("Details get successfully");
	}

	public ResponseEntity<?> deleteUserByUsername() {
		// TODO Auto-generated method stub
		org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		userRepo.deleteUserByUserName(authentication.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}



	public ResponseEntity<?> greeting() {
		// TODO Auto-generated method stub
		org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		return new ResponseEntity<>("Hi  " + userName,HttpStatus.OK);
	}
	

}
