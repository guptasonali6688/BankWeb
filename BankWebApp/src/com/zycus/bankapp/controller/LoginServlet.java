package com.zycus.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zycus.bankapp.bo.Account;
import com.zycus.bankapp.dao.impl.AccountDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList errorlist = new ArrayList();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private boolean checkInput(String parameter, String name) {
		if(parameter.equals("") || parameter == null) {
				errorlist.add(name+" can't be empty ");
				return false;
		}
		return true;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		errorlist.clear();
		response.setContentType("text/html");
		String accNostr = request.getParameter("accNo");
		String password = request.getParameter("password");
		int accNo = 0;
		
		if(checkInput(accNostr, "Account ID") & checkInput(password, "Password")) {
			if(!Pattern.matches("[0-9]+", accNostr)){
				errorlist.add("Enter a valid Account Number");
			}else {
				accNo = Integer.parseInt(accNostr);
			}
		}
		
		if(errorlist.size() > 0) {
			out.println("<span id='error'>"+errorlist+"</span>");
			request.getRequestDispatcher("login.html").include(request, response);
		}else {
			
			AccountDAO accdao = new AccountDAO();
			Account account = new Account();
			account = accdao.findById(accNo);
			
			if(account != null) {
				System.out.println(account.toString());
				if(password.equals(account.getPassword())) {
					HttpSession session = request.getSession();
					session.setAttribute("cust_id", account.getCustomerId());
					
					out.println("<script type=\"text/javascript\">");
				    out.println("alert('You successfully logged in... ');");
				    out.println("location='acc-home.jsp';");
				    out.println("</script>");		
				}else {
					out.println("Invalid password.. Try Again");
					request.getRequestDispatcher("login.html").include(request, response);
				}
			}else {
				out.println("Try Again.. No such Account is found");
				request.getRequestDispatcher("login.html").include(request, response);
			}
		}
		
		
	}

}
