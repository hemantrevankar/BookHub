package bookhub.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookhub.dao.UserDAO;
import bookhub.entity.User;
import bookhub.utility.PasswdEncryption;

/**
 * Servlet implementation class UserController
 */
@WebServlet(description = "servlet handles the user management", urlPatterns = { "/UserController" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equalsIgnoreCase("edit"))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			User u = UserDAO.getUser(id);
			request.setAttribute("user",u);
			request.getRequestDispatcher("editUser.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="users.jsp";
		if(request.getParameter("action").equalsIgnoreCase("add"))
		{
			User u = new User();
			Date d = new Date();
			Short s = 0;
			u.setFirstName(request.getParameter("firstname"));
			u.setLastName(request.getParameter("lastname"));
			u.setEmailId(request.getParameter("email"));
			u.setPhoneNo(request.getParameter("phonenumber"));
			u.setPassword(PasswdEncryption.encrypt(request.getParameter("password")));
			u.setCreated(d);
			u.setRole(false);
			u.setStatus(1);
			u.setTotal_downloads(s);
			
			UserDAO.addUser(u);
			if(request.getSession().getAttribute("isAdmin")==null)
				url="index.jsp";
			else{
				if(request.getSession().getAttribute("isAdmin").equals(true) && request.getSession().getAttribute("loginStatus").equals(true))
					url="users.jsp";
				}
			
		}
		else if(request.getParameter("action").equalsIgnoreCase("edit"))
		{
		int status=UserDAO.update(Integer.parseInt(request.getParameter("id")),request.getParameter("email"),request.getParameter("phonenumber"),request.getParameter("password"));
		if(status==1)
		request.setAttribute("DBmessage","update succesfull");
		else
			request.setAttribute("DBmessage","update unsuccesfull");
		if(request.getSession().getAttribute("isAdmin").equals(true) && request.getSession().getAttribute("loginStatus").equals(true))
		{
			url="users.jsp";
		}
		else if(request.getSession().getAttribute("isAdmin").equals(false) && request.getSession().getAttribute("loginStatus").equals(true))
		{
			url="index.jsp";
		}
		
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
