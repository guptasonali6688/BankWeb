package com.zycus.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zycus.bankapp.bo.Account;
import com.zycus.bankapp.bo.Payment;
import com.zycus.bankapp.dao.impl.AccountDAO;
import com.zycus.bankapp.dao.impl.PaymentDAO;
import com.zycus.bankapp.service.impl.AccountCreationService;
import com.zycus.bankapp.service.impl.PaymentService;

import sun.rmi.server.Dispatcher;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/payment.do")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList errorlist = new ArrayList();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer customerId = (Integer) session.getAttribute("cust_id");
		if(customerId == null) {
			request.getRequestDispatcher("/BankWebApp/index.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	private boolean checkInput(String parameter, String name) {
		if(parameter.equals("") || parameter == null) {
				errorlist.add(name+" can't be empty ");
				return false;
		}
		return true;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer customerId = (Integer) session.getAttribute("cust_id");
		if(customerId == null) {
			response.sendRedirect("/BankWebApp/index.html");
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><title>Payment</title></head>"
				+ "<link rel='stylesheet' type='text/css' href='"+request.getContextPath()+"/StylingCss/default.css' />"
						+ "<body><div id='main'>");
		errorlist.clear();
		int facId = Integer.parseInt(request.getParameter("facId"));
		String amountstr = request.getParameter("amount");
		float amount = 0;
		long millis=System.currentTimeMillis();
		Date time = new Date(millis);
		
		if(checkInput(amountstr, "Amount")) {
			if(!Pattern.matches("[0-9]+", amountstr)) {
				errorlist.add("Enter a valid amount");
			}else {
				amount = Float.parseFloat(amountstr);
			}
		}
		if(errorlist.size() > 0) {
			out.println("<span id='error'>"+errorlist+"</span>");
			request.getRequestDispatcher("/BankWebApp/jsp/pay.jsp").include(request, response);
		}else {
			
			Integer accNo = (Integer)session.getAttribute("AccNo");
			System.out.println(accNo);
			AccountCreationService accountService = new AccountCreationService();
			Account acc = new Account();
			
			acc = accountService.findByAccountId(accNo.intValue());
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/pay.jsp?id="+facId);
			if(acc != null) {
				float balance = acc.getBalance();
				if(balance < amount) {
					//request.setAttribute("error", "<span id='error'>Insufficient Balance, as your current balance is "+acc.getBalance()+"</span>");
					out.println("<span id='error'>Insufficient Balance, as your current balance is "+acc.getBalance()+"</span>");
					rd.include(request, response);
				}else {
					float tempamt = balance - amount;
					if(tempamt > 0) {
						PaymentService payService = new PaymentService();
						payService.createPayment(new Payment(facId, amount, time));					
						accountService.updateAccountBalance(tempamt, accNo.intValue());
						out.println("<script type=\"text/javascript\">");
					    out.println("alert('Your payment successfully done... ');");
					    out.println("location='/BankWebApp/jsp/acc-home.jsp';");
					    out.println("</script>");
					    
					   // RequestDispatcher rd = request.getRequestDispatcher("facility.jsp");
					   // rd.forward(request, response);
					}else {
						
						out.println("<h2>Payment can't be proceed further... Insufficient Balance</h2>");
						rd.include(request, response);
					}	
				}			    	    
			}else {
				out.println("Account does not exists..");
			}
			
		}
		out.println("</body></html>");
		out.close();
	}

}
