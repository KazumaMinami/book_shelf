package model;

import java.sql.Timestamp;

public class QuoteModel {
	
	private int id;
	private int userId;
	private int bookId;
	private String quote;
	private int page;
	private int isDeleted;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private BookModel bookModel;
	
	
	
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
	
	
	public int getBookId() {
		return this.bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
	public String getQuote() {
		return this.quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	
	public int getPage() {
		return this.page;
	}
	public void setPage(int page) {
		this.page = page;
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
	
	
	public BookModel getBookModel() {
		return this.bookModel;
	}
	public void setBookModel(BookModel bookModel) {
		this.bookModel = bookModel;
	}
	

}
