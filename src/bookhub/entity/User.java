package bookhub.entity;

import java.util.Date;

public class User{
	private int userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNo;
	private String password;
	private Boolean role;
	private int status=1;
	private Date created;
	private Date modified;
	private short total_downloads;
	
	public User() {
		super();
	}

	public User(String firstName, String lastName, String emailId,
			String phoneNo, String password,Boolean role, int status, Date created) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.password = password;
		this.role = role;
		this.status = status;
		this.created = created;
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getRole() {
		return role;
	}

	public void setRole(Boolean role) {
		this.role = role;
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

	public short getTotal_downloads() {
		return total_downloads;
	}

	public void setTotal_downloads(short total_downloads) {
		this.total_downloads = total_downloads;
	}
	
	
	
}