package bookhub.entity;

import java.util.Date;

public class UserDownload {
	private int id;
private int userid;
private int bookid;
private Date created;
private Date modified;


public UserDownload(int userid, int bookid, Date created) {
	super();
	this.userid = userid;
	this.bookid = bookid;
	this.created = created;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public int getBookid() {
	return bookid;
}
public void setBookid(int bookid) {
	this.bookid = bookid;
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
