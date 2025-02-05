package com.practice.journalApp.Entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
	@Id
	private String id;
	@Indexed(unique = true)
	@NonNull
	private String userName;
	@NonNull
	private String password;
	private String  email;
	
	private boolean sentimentAnalysis;
	
	private List<String> roles;
	//creating reference of Entry in users collection 
	@DBRef
	private List<Entry> entries=new ArrayList<Entry>();
	public void addEntry(Entry entry) {
		// TODO Auto-generated method stub
		entries.add(entry);
		
	}
	public void removeEntry(Entry entry) {
		entries.remove(entry);
	}
	

}
