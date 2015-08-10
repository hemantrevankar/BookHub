package bookhub.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import bookhub.entity.Book;
import bookhub.utility.HibernateUtil;

public class BookDAO{
	private static Session session;
	private static SessionFactory sessionFactory;
	static Criterion titlecriteria;
	static Criterion authorCriteria;
	static Criterion isbnCriteria ;
	static LogicalExpression orExp;
	static LogicalExpression orExp1;
	static LogicalExpression orExp2;
	static{
		sessionFactory=HibernateUtil.getSessionFactory();
		session=sessionFactory.openSession();
	}
	
	
	
	public static void addBook(Book b)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction tx = currentSession.beginTransaction();
		currentSession.save(b);
		tx.commit();
	}
	
	
	public static List<Book> listBooks()
	{
		List<Book> books = new ArrayList<Book>();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq("status",1));
		books = criteria.list();
		
		
		return books;
	}
	
	public static int update(Book b)
	{
		Session ses=HibernateUtil.getSessionFactory().openSession();
		//System.out.println("from update "+b);
		String hql="update Book set isbn=:i, title=:t, description=:d, author=:a, picPath=:p, FilePath=:f, modified=:m where id=:ID";
		Query q = session.createQuery(hql);
		q.setParameter("i",b.getIsbn());
		q.setParameter("t",b.getTitle());
		q.setParameter("d",b.getDescription());
		q.setParameter("a",b.getAuthor());
		q.setParameter("p",b.getPicPath());
		q.setParameter("f",b.getFilePath());
		q.setParameter("m",b.getModified());
		q.setParameter("ID",b.getId());
	
		int result = q.executeUpdate();
		System.out.println(result);
		Transaction tx = ses.beginTransaction();
		tx.commit();
		ses.close();
		return result;
	
	}
	
	public static List<Book> findBook(String title,String author,String isbn)
	{
		
		List<Book> books = new ArrayList<Book>();	
		Criteria criteria = session.createCriteria(Book.class);
		
		
		if(!title.equalsIgnoreCase(""))
			titlecriteria = Restrictions.ilike("title", title, MatchMode.ANYWHERE);
		if(!author.equalsIgnoreCase(""))
			authorCriteria = Restrictions.ilike("author",author,MatchMode.ANYWHERE);
		if(!isbn.equalsIgnoreCase(""))
			isbnCriteria = Restrictions.eq("isbn",isbn);
		
		if(titlecriteria!=null)
		{
			if(authorCriteria!=null )
			{
				orExp = Restrictions.or(titlecriteria, authorCriteria);
				if(isbnCriteria!=null )
				{
					 orExp2 = Restrictions.or(authorCriteria, isbnCriteria);
				}
			}
			else if(isbnCriteria!=null)
			{
				orExp1 = Restrictions.or(titlecriteria, isbnCriteria);
			}
			else
			criteria.add(titlecriteria);
		}
		else if(authorCriteria!=null )
		{
			if(isbnCriteria!=null )
			{
				 orExp1 = Restrictions.or(authorCriteria, isbnCriteria);
			}
			else
				criteria.add(authorCriteria);
			
		}
		else if(isbnCriteria!=null)
			criteria.add(isbnCriteria);
		 
		
		if(orExp!=null)
			criteria.add(orExp);
		if(orExp1!=null)
			criteria.add(orExp1);
		if(orExp2!=null)
			criteria.add(orExp2);
		
		
		books = criteria.list();
		return books;
	}
	
	
	public static Book getBook(int id)
	{
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq("id",id));
	
		Book b = (Book) criteria.uniqueResult();
		
		return b;
	}
	
	
	public static long totalBooks()
	{
		
		long count = (long)session.createCriteria(Book.class).setProjection(Projections.rowCount()).uniqueResult();
		
		return count;
	}
	
}