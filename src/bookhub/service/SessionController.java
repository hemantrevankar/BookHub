package bookhub.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookhub.dao.UserDAO;
import bookhub.entity.UserType;
import bookhub.utility.PasswdEncryption;

/**
 * Servlet implementation class SessionController
 */
@WebServlet(description = "SessionController servlet handles the operation of managing the user session such as login and logout", urlPatterns = { "/SessionController" })
public class SessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(request.getSession()!=null)
		{
			request.getSession().removeAttribute("loginStatus");
			request.getSession().removeAttribute("username");
			request.getSession().removeAttribute("userid");
			request.getSession().removeAttribute("isAdmin");
			request.getSession().invalidate();
		}
		
		request.setAttribute("LogoutStatus",true);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dashboardURL="dashboard.jsp";
		String loginURL="login.jsp";
		String homeURL="index.jsp";
		String username = request.getParameter("email");
		String password = PasswdEncryption.encrypt(request.getParameter("password"));
		Enumeration<String> e=request.getHeaderNames();
		while(e.hasMoreElements())
			System.out.println(e.nextElement());
		System.out.println(request.getContextPath());
		System.out.println(request.getMethod());
		System.out.println(request.getProtocol());
		System.out.println(request.getQueryString());
		System.out.println(request.getServerName());
		System.out.println(request.getServerPort());
		System.out.println(request.getServletContext());
		UserType login= UserDAO.findUser(username, password);
		if(login.getType().equalsIgnoreCase("admin"))
		{
			request.getSession().setAttribute("loginStatus",true);
			//request.getSession().setAttribute("userID",userId);
			request.getSession().setAttribute("username",username);
			request.getSession().setAttribute("userid",login.getId());
			request.getSession().setAttribute("isAdmin",true);
			request.getRequestDispatcher(dashboardURL).forward(request, response);
		}
		else if(login.getType().equalsIgnoreCase("normal"))
		{
			request.getSession().setAttribute("loginStatus",true);
			//request.getSession().setAttribute("userID",userId);
			request.getSession().setAttribute("username",username);
			request.getSession().setAttribute("userid",login.getId());
			request.getSession().setAttribute("isAdmin",false);
			request.getRequestDispatcher(homeURL).forward(request, response);
		}
		else if(login.getType().equalsIgnoreCase("invalid"))
		{
			System.out.println("login failed invalid password");
			request.getSession().setAttribute("loginStatus",false);
			request.getSession().invalidate();
			response.sendRedirect(request.getHeader("Referer"));
		}
	}

}
