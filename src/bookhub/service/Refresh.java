package bookhub.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Refresh
 */
@WebServlet("/Refresh")
public class Refresh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Refresh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setIntHeader("Refresh",1);
		Calendar c = new GregorianCalendar();
		int hour = c.get(Calendar.HOUR);
		int min= c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		
		
		String am_pm="";
		if(c.get(Calendar.AM_PM)==0)
			am_pm="AM";
		else
			am_pm="PM";
		
		String time=hour+" : "+min+" : "+sec+" "+am_pm;
		
		response.setContentType("text/html");
		PrintWriter pw= response.getWriter();
		pw.write("<!DOCTYPE html> \n <head>Time</head> \n <body> Time \n"+time+"</body></html>");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
