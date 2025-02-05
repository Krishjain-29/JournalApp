package com.practice.journalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.practice.journalApp.Entity.Entry;

public interface EntryRepo extends MongoRepository<Entry, String> {

}
