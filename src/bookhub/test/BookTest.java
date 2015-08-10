package bookhub.test;

import java.util.Date;

import org.junit.Test;

import bookhub.dao.BookDAO;
import bookhub.entity.Book;

public class BookTest{
	@Test
	public void addBook()
	{
		
	}
	
	@Test
	public static void ListBooks()
	{
		for(Book b:BookDAO.listBooks())
			System.out.println(b.getId()+" "+b.getIsbn()+" "+b.getTitle()+" "+b.getAuthor());
	}
	
	public static int update()
	{
		Date d= new Date();
		Book b = new Book("111111","GGGG","description"," author",1);
		b.setCreated(d);
		b.setFilePath("www.txt");
		b.setModified(d);
		b.setPicPath("pic.png");
		b.setId(17);
		int f = BookDAO.update(b);
		return f;
	}
	
	
	public static void findBook()
	{
		for(Book b :BookDAO.findBook("HTML","",""))
			System.out.println(b);
	}
	
	public static void bookcount()
	{
		System.out.println(BookDAO.totalBooks());
	}
}