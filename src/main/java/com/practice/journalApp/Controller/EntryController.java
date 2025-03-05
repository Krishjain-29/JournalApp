package com.practice.journalApp.Controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.journalApp.Entity.Entry;
import com.practice.journalApp.Service.EntryService;
import com.practice.journalApp.Service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/app")
public class EntryController {

    @Autowired
    private EntryService entryService;
    @Autowired
    private UserService  userService;

//    @RequestMapping(value = "/")
//    public void redirect(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/swagger-ui.html");
//    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        return entryService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEntry(@RequestBody Entry myEntry) {
        return entryService.createEntry(myEntry);
    }

    @GetMapping("/get/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable String myId) {
        return entryService.getEntryById(myId);
    }

    @DeleteMapping("/delete/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String myId,String userName) {
        return entryService.deleteEntryById(myId,userName);
    }

    @PutMapping("/update/{myId}")
    public ResponseEntity<?> updateEntry(@PathVariable String myId, @RequestBody Entry newEntry) {
        return entryService.updateEntry(myId, newEntry);
    }
    
    @GetMapping
    public ResponseEntity<?> getEntryOfUser(){
    	return userService.getEntryOfUser();
    }
    
    
}
