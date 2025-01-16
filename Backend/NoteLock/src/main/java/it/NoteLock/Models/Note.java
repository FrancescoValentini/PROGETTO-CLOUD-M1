package it.NoteLock.Models;

import java.util.Date;

public class Note {
	private String id;
	private Date timestamp;
	private String title;
	private String body;
	
	public Note(String id, Date timestamp , String title , String body) {
		this.id = id;
		this.timestamp = timestamp;
		this.title = title;
		this.body = body;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
