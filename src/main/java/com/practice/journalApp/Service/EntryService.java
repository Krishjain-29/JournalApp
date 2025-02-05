package com.practice.journalApp.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.journalApp.Entity.Entry;
import com.practice.journalApp.Entity.User;
import com.practice.journalApp.Repository.EntryRepo;
import com.practice.journalApp.Repository.UserRepo;

@Service

public class EntryService {

    @Autowired
    private EntryRepo entryRepo;
    @Autowired 
    private UserRepo userRepo;
    @Autowired
     private UserService userService;

    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(entryRepo.findAll());
    }

    public ResponseEntity<?> createEntry(Entry entry) {
//    	org.springframework.security.core.Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
//		String userName=authentication.getName();
//        entry.setDate(LocalDateTime.now());
//        entryRepo.save(entry);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Entry created successfully");
    	
    	// Get the authenticated user
        org.springframework.security.core.Authentication authentication = 
            SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        
        User user = userRepo.findByUserName(userName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        
        // Associate entry with the user
       user.addEntry(entry);
       userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Entry created successfully");
    }
    

    public ResponseEntity<?> getEntryById(String id) {
    	 org.springframework.security.core.Authentication authentication = 
    	            SecurityContextHolder.getContext().getAuthentication();
    	        String userName = authentication.getName();
    	        User user=userRepo.findByUserName(userName);
    	        List<Entry> collectEntries=user.getEntries().stream().filter(x-> x.getId().equals(id)).collect(Collectors.toList());
    	        if(collectEntries.isEmpty()) {
    	        	Optional<Entry> entry = entryRepo.findById(id);
    	            if (entry.isPresent()) {
    	                return ResponseEntity.ok(entry.get());
    	            } 
    	        }
    	       // else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found");
	           // }
        
    }
@Transactional
    public ResponseEntity<?> deleteEntryById(String id,String userName) {
	try {
		User user=userRepo.findByUserName(userName);
		boolean removed =user.getEntries().removeIf(x-> x.getId().equals(id));
		if(removed) {
			userService.saveNewUser(user);
			entryRepo.deleteById(id);
		}
		return ResponseEntity.ok("Entry deleted successfully");
//        if (entryRepo.existsById(id)) {
//            entryRepo.deleteById(id);
//            return ResponseEntity.ok("Entry deleted successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found");
//        }
	}
	catch (Exception e) {
		// TODO: handle exception
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found");
	}
    }

    public ResponseEntity<?> updateEntry(String id, Entry newEntry) {
        Optional<Entry> optionalOldEntry = entryRepo.findById(id);
        if (optionalOldEntry.isPresent()) {
            Entry oldEntry = optionalOldEntry.get();
            
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            entryRepo.save(oldEntry);
            return ResponseEntity.ok(oldEntry);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found");
        }
    }
}
