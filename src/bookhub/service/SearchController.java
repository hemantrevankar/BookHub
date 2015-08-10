package bookhub.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookhub.dao.BookDAO;
import bookhub.entity.Book;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title="";
		
		String author="";
		String isbn="";
		
		if(!request.getParameter("title").equalsIgnoreCase(""))
			title=request.getParameter("title");
		if(!request.getParameter("author").equalsIgnoreCase(""))
			author=request.getParameter("author");
		if(!request.getParameter("isbn").equalsIgnoreCase(""))
			isbn=request.getParameter("isbn");
		
		List<Book> books = new ArrayList<Book>();
		books=BookDAO.findBook(title,author,isbn);
		
		request.setAttribute("books",books);
		if(request.getSession().getAttribute("loginStatus")==null)
		request.getRequestDispatcher("index.jsp").forward(request, response);
		else
			request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
