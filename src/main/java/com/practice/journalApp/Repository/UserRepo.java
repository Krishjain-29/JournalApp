package com.practice.journalApp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.practice.journalApp.Entity.User;
@Repository
public interface UserRepo extends MongoRepository<User,String> {

	User findByUserName(String userName);

	void deleteUserByUserName(String userName);
	
}
