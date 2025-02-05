package com.practice.journalApp.Repository;

import java.util.List;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import com.practice.journalApp.Entity.User;

public class CriteriaRepo {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<User> getUserForSA(){
		org.springframework.data.mongodb.core.query.Query query=new org.springframework.data.mongodb.core.query.Query();
		query.addCriteria(Criteria.where("email").exists(true));
		query.addCriteria(Criteria.where("email").ne(null));
		query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
		 List<User> users =mongoTemplate.find(query, User.class);
		return users;
		
	}

}
