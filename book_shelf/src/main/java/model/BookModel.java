package model;

import java.sql.Date;
import java.sql.Timestamp;

public class BookModel {
	
	private int id;
	private int userId;
	private Date registrationDate;
	private Date finishedDate;
	private String title;
	private String author;
	private int status;
	private String thoughts;
	private int isDeleted;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private UserModel userModel;
	
	
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Date getRegistrationDate() {
		return this.registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public Date getFinishedDate() {
		return this.finishedDate;
	}
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getStatus() {
		return this.status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getThoughts() {
		return this.thoughts;
	}
	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}
	
	public int getIsDeleted() {
		return this.isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Timestamp getCreatedAt() {
		return this.createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public UserModel getUserModel() {
		return this.userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

}
