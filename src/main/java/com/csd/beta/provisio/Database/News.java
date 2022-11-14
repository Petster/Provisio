package com.csd.beta.provisio.Database;

public class News {
	private long id;
	private long userID;
	private String title;
	private String publishDate;
	private String description;
	private String image;
	
	public News() {}
	
	public News(long id, long userID, String title, String publishDate, String description, String image) {
		this.id = id;
		this.userID = userID;
		this.title = title;
		this.publishDate = publishDate;
		this.description = description;
		this.image = image;
	}
	
	public News(long userID, String title, String publishDate, String description, String image) {
		this.userID = userID;
		this.title = title;
		this.publishDate = publishDate;
		this.description = description;
		this.image = image;
	}
	
	public void setID(long i) {
		this.id = i;
	}
	
	public void setUserID(long i) {
		this.userID = i;
	}
	
	public void setTitle(String i) {
		this.title = i;
	}
	
	public void setPublishDate(String i) {
		this.publishDate = i;
	}
	
	public void setDescription(String i) {
		this.description = i;
	}
	
	public void setImage(String i) {
		this.image = i;
	}
	
	public long getID() {
		return id;
	}
	
	public long getUserID() {
		return userID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getPublishDate() {
		return publishDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getImage() {
		return image;
	}
}
