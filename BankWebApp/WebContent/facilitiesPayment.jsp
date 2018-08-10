<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.zycus.bankWebApp.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facility Prefrence</title>
<link rel="stylesheet" type="text/css" href="StylingCss/default.css">
</head>
<body>
<div id="main">
	<%
	ArrayList errorlist = new ArrayList();
	
	private boolean checkInput(String parameter, String name) {
		if(parameter.equals("") || parameter == null) {
				errorlist.add(name+" can't be empty ");
				return false;
		}
		return true;
	}
		
	String billType = request.getParameter("billType");
	String provider = request.getParameter("provider");
		
	if(checkInput(billType,"Bill Type") & checkInput(provider, "Provider")) {
		
	}
		
	%>
	<hr />
	<p><strong>Bill Type: </strong><%=billType %></p>
	<p><strong>Provider: </strong><%=provider %></p>
	<hr />
	<%
		Integer accNo = (Integer)session.getAttribute("AccNo");
		FacilityPaymentDAO facpay = new FacilityPaymentDAO();
		FacilityPayment facpayobject = new FacilityPayment(accNo, billType, provider);
		facpay.create(facpayobject);
		out.println("<script type=\"text/javascript\">");
	    out.println("alert('Your prefrence have been added successfully... ');");
	    out.println("</script>");
	    
	    List<FacilityPayment> facpaylist = FacilityPaymentDAO.getDataFromAccNo(accNo);
	    Iterator<FacilityPayment> it= facpaylist.iterator();
	    out.println("<table>");
	    out.println("<tr><td>Bill Type</td><td>Provider</td><td>Amount</td><td></td></tr>");
		out.println("<form action='' method=''>");
		while(it.hasNext()){

			out.println("<tr><td><input type='text' value='"+it.next().getBillType()+"'/></td><td><input type='text' value='"+it.next().getProvider()+"'/></td>");
			out.println("<td><input type='text' name='amount'/></td><td><input type='submit' value='pay'/></td>");
			out.println("</tr>");
		} 
		out.println("</form>");
		out.println("</table>");
	%>
	
	
</div>
	
</body>
</html>