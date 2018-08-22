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
import com.zycus.bankapp.dao.impl.CustomerDAO;

/**
 * Servlet implementation class AccountCreationServlet
 */
@WebServlet("/acc-creation.do")
public class AccountCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList errorlist = new ArrayList();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCreationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("account-creation.html");
	}
	
	//For checking input is empty or null
	private boolean checkInput(String parameter, String name) {
		if(parameter.equals("") || parameter == null) {
				errorlist.add(name+" can't be empty \n");
				return false;
		}
		return true;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		errorlist.clear();
		PrintWriter out = response.getWriter();
		String accType = request.getParameter("accType");
		String minBalancestr = request.getParameter("min-amount");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");
		int minBalance = 0;
		
		//For Account Type
		if(!("Saving".equals(accType) || "Current".equals(accType))) {
			errorlist.add("Select Account Type");
		}
		//For Minimum Balance
		if(checkInput(minBalancestr,"Minimum Balance") & checkInput(password, "Password") & checkInput(confirmpassword, "Confirm Password")) {
			//To check password and confirm password is equal or not
			if(!password.equals(confirmpassword)) {
				errorlist.add("Password and Confirm Password don't match");
			}else {
				//For Minimum Balance validation, whether it contains Number or String
				if(!Pattern.matches("[0-9]+", minBalancestr )) {
					//System.out.println(Pattern.matches("[\\d]", age)+ "    "+age);
					errorlist.add("Enter a valid amount");
				}else {
					minBalance = Integer.parseInt(minBalancestr);
					if(minBalance < 500) {
						errorlist.add("Minimum Balance should be greater than equal to 500");
					}
				}
			}
		}
		
		if(errorlist.size() > 0) {
			out.println("<span id='error'>"+errorlist+"</span>");
			request.getRequestDispatcher("account-creation.html").include(request, response);
		}else {
			
			HttpSession session = request.getSession();
			
			String email = (String) session.getAttribute("email");
			
			int customerId = CustomerDAO.getIdFromEmail(email);
			
			AccountDAO accDAO = new AccountDAO();
			
			Account account = new Account();
			account.setCustomerId(customerId);
			account.setAccountType(accType);
			account.setBalance(minBalance);
			account.setPassword(confirmpassword);
			
			accDAO.create(account);
			
			session.setAttribute("cust_id", customerId);
			
			out.println("<script type=\"text/javascript\">");
		    out.println("alert('Your account creation is under processing... ');");
		    out.println("location='acc-home.jsp';");
		    out.println("</script>");
		}
		out.close();
	}

}
