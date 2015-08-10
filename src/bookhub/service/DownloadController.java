package bookhub.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookhub.dao.BookDAO;
import bookhub.dao.UserDAO;
import bookhub.entity.Book;

/**
 * Servlet implementation class DownloadController
 */
@WebServlet("/DownloadController")
public class DownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String filePath;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		Book b = BookDAO.getBook(id);
		Date downloadDate = new Date(); 
		if(b.getFilePath()==null)
		{
			request.setAttribute("message","File not found");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{
		String FilePath = filePath+File.separator+b.getFilePath();
		File newFile = new File(FilePath);
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename="+b.getFilePath());
		response.setContentLength((int)newFile.length());
		
		FileInputStream fis = new FileInputStream(newFile);
		ServletOutputStream os = response.getOutputStream();
		int bytes=0;
		while((bytes=fis.read())!=-1)
			os.write(bytes);
		
		
		os.flush();
		os.close();
		fis.close();
		int userid=(int)request.getSession().getAttribute("userid");
		UserDAO.downloadCount(userid);
		UserDAO.downloadHistory(userid,id,downloadDate);
		
		}
	}

@Override
public void destroy() {
	// TODO Auto-generated method stub
	super.destroy();
}



@Override
public void init() throws ServletException {
	filePath=getServletContext().getInitParameter("file-upload");
	
}
}
