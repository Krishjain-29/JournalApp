package com.practice.journalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.practice.journalApp.Entity.User;
import com.practice.journalApp.Repository.UserRepo;
@Component
public class CustomUserDetailsImpl  implements UserDetailsService{
	@Autowired
	
	private UserRepo userRepo;
	@Override
	public  UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
		User user=userRepo.findByUserName(username);
		if(user!=null) {
		UserDetails userDetails=org.springframework.security.core.userdetails.User.builder().username(user.getUserName())
			.password(user.getPassword())
			.roles(user.getRoles().toArray(new String[0]))
			.build();
		return userDetails;
		}
		throw new UsernameNotFoundException("User not found with username:"+ username);

	
	}
	

}
