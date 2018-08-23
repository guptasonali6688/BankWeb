<%@page import="com.zycus.bankapp.bo.*"%>
<%@page import="com.zycus.bankapp.service.impl.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="../StylingCss/default.css">
</head>
<body>
	<div id="main">
	<%
		AccountCreationService accountService = new AccountCreationService();
		CustomerService customerService = new CustomerService();
		
		Integer customerId = (Integer) session.getAttribute("cust_id");
		if(customerId == null) {
			response.sendRedirect("/BankWebApp/index.html");
		}
		
		int accNumber = accountService.getAccountIdByCustId(customerId);
		session.setAttribute("AccNo", accNumber);
		Customer customer = new Customer();
		customer = customerService.findByCustomerId(customerId);
			
		Account account = new Account();
		account  = accountService.findByAccountId(accNumber);
			
		if(account != null && customer != null) {
			
	%>
		<h2>Welcome, <%= customer.getFirstname()+" "+customer.getLastname()%></h2><hr /><br/>
		<form action="/BankWebApp/logout.do" method="get">
    		<input type="submit" value="Logout" />
		</form>
		<br/>
		<table>
		<tr>
			<th>Account Type : <%= account.getAccountType() %> </th>
			<th>Balance : <%= account.getBalance() %></th>
		</tr>
		
		</table>
		<br/>
		<a href="/BankWebApp/jsp/facility.jsp">Add Facility</a><br/><br />
		<a href="/BankWebApp/jsp/facilityview.jsp">View your Facility Preference</a><br/><br />
		<a href="/BankWebApp/jsp/display-fac-pay.jsp">Display previous facility payment history</a><br/><br />
		
	<%
		}else {
			out.println("Something went wrong, try again....");
		}
	%>
		
		
	</div>
</body>
</html>