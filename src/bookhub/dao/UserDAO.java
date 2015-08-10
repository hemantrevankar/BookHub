package bookhub.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import bookhub.entity.Book;
import bookhub.entity.User;
import bookhub.entity.UserDownload;
import bookhub.entity.UserType;
import bookhub.utility.HibernateUtil;

public class UserDAO{
	private static Session session;
	private static SessionFactory sessionFactory;
	
	static{
		sessionFactory=HibernateUtil.getSessionFactory();
		session=sessionFactory.openSession();
	}
	
	
	public static void addUser(User u)
	{
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction tx = currentSession.beginTransaction();
		currentSession.save(u);
		tx.commit();
	}
	
	public static UserType findUser(String username,String password)
	{
		
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("emailId",username));
		criteria.add(Restrictions.eq("password",password));
	
		User u = (User) criteria.uniqueResult();
		
		if(u!=null)
		{
			if(u.getRole()==true)
				return new UserType("admin",u.getUserId());
			else
				return new UserType("normal",u.getUserId());
		}
		return new UserType("invalid",-1);
	}
	
	public static List<User> listUsers()
	{
		List<User> users = new ArrayList<User>();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("role",false));
		users = criteria.list();
		return users;
	}
	
	public static User getUser(int userid)
	{
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userId",userid));
	
		User u = (User) criteria.uniqueResult();
		
		return u;
	}
	
	public static int update(int id,String email,String phonenumber,String password)
	{
		Session ses = HibernateUtil.getSessionFactory().openSession();
		int result=5;
		String hql="UPDATE User set";
		if(!email.equalsIgnoreCase(""))
		{
			hql+=" emailId=:email";
			
			if(!phonenumber.equalsIgnoreCase(""))
				hql+=", phoneNo=:pn";
			if(!password.equalsIgnoreCase(""))
				hql+=", password=:passwd";
		}
		else if(!phonenumber.equalsIgnoreCase(""))
			{	
				hql+=" phoneNo=:pn";
				if(!password.equalsIgnoreCase(""))
					hql+=", password=:passwd";
			}
		else if(!password.equalsIgnoreCase(""))
			hql+=" password=:passwd";
		
		hql+=" WHERE userId=:id";
		
		if(email.equalsIgnoreCase("") && phonenumber.equalsIgnoreCase("") && password.equalsIgnoreCase(""))
		{
			Query query=session.createQuery(hql);
			if(!email.equalsIgnoreCase(""))
				query.setParameter("email",email);
			if(!phonenumber.equalsIgnoreCase(""))
				query.setParameter("pn",phonenumber);
			if(!password.equalsIgnoreCase(""))
				query.setParameter("passwd",password);
		
			query.setParameter("id",id);
			 result = query.executeUpdate();
			Transaction tx = ses.beginTransaction();
			tx.commit();
			ses.close();
		}
		else result=0;
		return result;
	}
	
	public static int numberOfUsersRegisteredToday()
	{
		int num=0;
		Date d = new Date();
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("created",d));
		c.add(Restrictions.eq("status",1));
		num=c.list().size();
		return num;
	}
	
	public static void downloadCount(int id)
	{
		Session ses= HibernateUtil.getSessionFactory().openSession();
		String hql="UPDATE User SET total_downloads= total_downloads+ 1 WHERE id =:id";
		Query q = session.createQuery(hql);
		q.setParameter("id",id);
		int result= q.executeUpdate();
		System.out.println(result);
		Transaction tx = ses.beginTransaction();
		tx.commit();
		ses.close();
		
	}
	
	public static List<User> top5Users()
	{
		
		String hql="select user from User user where status=:status group by total_downloads order by total_downloads desc limit 5";
		Query q = session.createQuery(hql);
		q.setParameter("status",1);
		
		List<User> users  = new ArrayList<User>();
		users=q.list();
		
		return users;
	}
	
	public static void downloadHistory(int userid,int bookid,Date created)
	{
		
		UserDownload ud=new UserDownload(userid, bookid, created);
		Session currentSession = sessionFactory.getCurrentSession();
		Transaction tx = currentSession.beginTransaction();
		currentSession.save(ud);
		tx.commit();
		
	}
	
	
	public static int totalDownloadsToday()
	{
		Criteria c = session.createCriteria(UserDownload.class);
		c.add(Restrictions.eq("created",new Date()));
		int num=c.list().size();
		return num;
	}
	
	/*public static List<Book> top5downloads()
	{
		String hql="select e from Employee e inner join e.team"
				 SELECT B FROM Book B INNER JOIN B.book_
AND B.status =1
GROUP BY B.tittle DESC 
LIMIT 5

	}*/
	
	
}