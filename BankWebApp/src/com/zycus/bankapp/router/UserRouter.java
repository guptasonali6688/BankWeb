package com.zycus.bankapp.router;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserRouter
 */
@WebServlet("/user/*")
public class UserRouter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRouter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String path = request.getPathInfo();
		System.out.println(path);
		
		if (path.equals("/home"))
		{
			request.getRequestDispatcher("/WEB-INF/jsp/acc-home.jsp").include(request, response);
		}
		else if (path.equals("/add-facility"))
		{
			request.getRequestDispatcher("/WEB-INF/jsp/facility.jsp").include(request, response);
		}else if(path.equals("/display-fac")) {
			request.getRequestDispatcher("/WEB-INF/jsp/display-fac-pay.jsp").include(request, response);
		}else if(path.equals("/view-facility")) {
			request.getRequestDispatcher("/WEB-INF/jsp/facilityview.jsp").include(request, response);
		}else if(path.equals("/pay")) {
			request.getRequestDispatcher("/WEB-INF/jsp/pay.jsp").include(request, response);
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
