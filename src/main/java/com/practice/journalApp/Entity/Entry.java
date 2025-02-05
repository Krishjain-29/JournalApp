package com.practice.journalApp.Entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@Getter
//@Setter
@Document(collection="journal_entries")
public class Entry {
	@Id
	private String id;
	private String title;
	private String content;
	private LocalDateTime date;
	
	public Entry() {};
	public Entry(String title,String content) {
		this.title=title;
		this.content=content;
	}

	

}
