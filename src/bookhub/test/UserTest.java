package bookhub.test;

import java.util.Date;

import org.junit.Test;

import bookhub.dao.BookDAO;
import bookhub.dao.UserDAO;
import bookhub.entity.Book;
import bookhub.entity.User;
import bookhub.utility.PasswdEncryption;

public class UserTest {

	/*@Test
	public static void addAdminUser()
	{
		Date date = new Date();
		
		User u = new User("Hemant","Revankar","hkr@gmail.com","9130348528","itsmypassword",true,1,date);
		UserDAO.addUser(u);
	}*/
	
	@Test
	public static void addNormalUser()
	{
		Date date = new Date();
		
		User u = new User("Ritesh","Deshmukh","ritesh_deshmukh@gmail.com","7259715691","ritesh",false,0,date);
		UserDAO.addUser(u);
	}
	
	@Test
	public static void addBook()
	{
		Date date = new Date();
		
		Book b = new Book("11124587","Complete Reference Java","A deep dive into Core Java", "Herbert Schildt",1);
		
		BookDAO.addBook(b);
	}
	
	/*@Test
	public static void findUser()
	{
		String status=UserDAO.findUser("viraj@gmail.com","viraj");
		System.out.println(status);
	}*/

	@Test
	public static int Update()
	{
		int id=1;
		int r=UserDAO.update(id,"hkr@gmail.com","9130348528","hemantrevankar");
		return r;
	}
	
	public static void downloadCount()
	{
		UserDAO.downloadCount(4);
	}
	
	public static void top5users()
	{
		for(User u : UserDAO.top5Users())
			System.out.println(u.getFirstName()+"  "+u.getLastName());
	}
	
	public static void total()
	{
		System.out.println(UserDAO.totalDownloadsToday());
	}
	
	public static void addAdminUser(String firstName,String lastName,String emailId,String password,String phoneNo)
	{
		short i = 0;
		User user =new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmailId(emailId);
		user.setPassword(PasswdEncryption.encrypt(password));
		user.setCreated(new Date());
		user.setPhoneNo(phoneNo);
		user.setRole(true);
		user.setStatus(1);
		user.setTotal_downloads(i);
		UserDAO.addUser(user);
	}
}
