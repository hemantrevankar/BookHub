package bookhub.entity;

import java.util.Date;

public class Book{
	private int id;
	private String isbn;
	private String title;
	private String description;
	private String author;
	private String picPath;
	private String FilePath;
	private int status;
	private Date created;
	private Date modified;
	
	
	public Book() {
		super();
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title
				+ ", description=" + description + ", author=" + author
				+ ", picPath=" + picPath + ", FilePath=" + FilePath
				+ ", status=" + status + ", created=" + created + ", modified="
				+ modified + "]";
	}


	public Book(String isbn, String title, String description, String author,
			int status) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.description = description;
		this.author = author;
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPicPath() {
		return picPath;
	}


	public void setPicPath(String pic) {
		this.picPath = pic;
	}


	public String getFilePath() {
		return FilePath;
	}


	public void setFilePath(String filePath) {
		FilePath = filePath;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getModified() {
		return modified;
	}


	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	
	
	
	
}