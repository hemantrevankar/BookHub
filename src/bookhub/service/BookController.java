package bookhub.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bookhub.dao.BookDAO;
import bookhub.entity.Book;

/**
 * Servlet implementation class BookController
 */
@WebServlet(description = "BookController servlet handles all the operations of book management", urlPatterns = { "/BookController" })
public class BookController extends HttpServlet {
	String filePath;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("action").equals("edit"))
		{
			int id=Integer.parseInt(request.getParameter("id"));
			Book b = BookDAO.getBook(id);
			request.setAttribute("book",b);
			request.getRequestDispatcher("editBook.jsp").forward(request, response);
		}
		
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		filePath = getServletContext().getInitParameter("file-upload");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int ID=0;
		boolean editflag=false;
		String url="books.jsp";
		if(ServletFileUpload.isMultipartContent(request))
		{
				Book b = new Book();
				Date d = new Date();
				
				   try{
					List<FileItem> fields = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				  
					for(FileItem field : fields)
					{
						if(field.isFormField())
						{
							System.out.println(field.getFieldName()+"--"+field.getString());
							String attribute=field.getFieldName();
							
							switch(attribute)
							{
							case "isbn":b.setIsbn(field.getString());break;
							case "title":b.setTitle(field.getString());break;
							case "description":b.setDescription(field.getString());break;
							case "author":b.setAuthor(field.getString());break;
							case "id":b.setId(Integer.parseInt(field.getString()));editflag=true;break;
							}
						}
						else
						{
							System.out.println(field.getFieldName()+"--"+field.getName());
							if(field.getFieldName().equalsIgnoreCase("imagefile"))
								b.setPicPath(field.getName());
							if(field.getFieldName().equalsIgnoreCase("bookfile"))
								b.setFilePath(field.getName());
							
							String filename = new File(field.getName()).getName();
					
							field.write(new File(filePath+File.separator+filename));
							
						}
					}
				   }catch(Exception ex)
							{
								request.setAttribute("message","File upload failed due to"+ex);
							}
				  
					b.setStatus(1);
					request.setAttribute("message", "File Uploaded Successfully");
				
					if(!editflag)
					{
						 b.setCreated(d);
						BookDAO.addBook(b);
					}
					else{
						b.setModified(d);
						//System.out.println(b);
						
						BookDAO.update(b);
					}
				
			request.getRequestDispatcher(url).forward(request, response);
	}
		
	}
}

