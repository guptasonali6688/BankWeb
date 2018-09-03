package com.zycus.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import com.zycus.bankapp.bo.Facility;
import com.zycus.bankapp.dao.impl.FacilityDAO;
import com.zycus.bankapp.service.impl.FacilityService;

/**
 * Servlet implementation class FacilityServlet
 */
@WebServlet("/facility.do")
public class FacilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList errorlist = new ArrayList();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacilityServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		HttpSession session = request.getSession();
		Integer customerId = (Integer) session.getAttribute("cust_id");
		if(customerId == null) {
			response.sendRedirect("/BankWebApp/index.html");
		}

		PrintWriter out = response.getWriter();
		errorlist.clear();
		response.setContentType("text/html");
		out.println("<html>"
				+ "<head>"
				+ "<title>Facility Service</title>"
				+ "<link rel='stylesheet' type='text/css' href='"+request.getContextPath()+"/StylingCss/default.css' />"
				+ "</head>");
		out.println("<body><div id='main'>");
		
		String billType = request.getParameter("billType");
		String consumerNostr = request.getParameter("consumerId");
		int consumerNo = 0;
		String provider = "";
		
		if(billType.equals("Electric")) {
			provider = request.getParameter("provider1");
		}else if(billType.equals("Phone")){
			provider = request.getParameter("provider2");
		}else {
			checkInput(provider, "Provider");
		}
		if(checkInput(billType,"Bill Type") & checkInput(provider, "Provider") & checkInput(consumerNostr, "Consumer ID")){
			if(!java.util.regex.Pattern.matches("[0-9]+", consumerNostr)) {
				errorlist.add("Enter valid Consumer-ID");
			}else {
				consumerNo = Integer.parseInt(consumerNostr);
			}
		}
	
		if(errorlist.size() > 0) {
			out.println("<span id='error'>"+errorlist+"</span>");
			request.getRequestDispatcher("/BankWebApp/user/add-facility").include(request, response);
		}else {
			
			Integer accNo = (Integer)session.getAttribute("AccNo");
			
			FacilityService facilityService = new FacilityService();
			facilityService.createFacility(new Facility(accNo.intValue(), billType, provider, consumerNo));
			
			out.println("<script type=\"text/javascript\">");
		    out.println("alert('Your prefrence have been added successfully... ');");
		    out.println("</script>");
		    System.out.println(accNo);
		    
		    out.println("<a href='/BankWebApp/jsp/facility.jsp'>Add a new Preference ?</a><br/><br/>"); 
		    
		    List<Facility> faclist = FacilityService.getFacilityFromAccountNo(accNo);
	
		    out.println("<table>");
		    out.println("<tr><td>Facility ID</td><td>Bill Type</td><td>Provider</td><td>Consumer ID</td><td></td></tr>");
			if(faclist != null) {
				for(Facility fac: faclist){
					
					out.println("<tr><td>"+fac.getId()+"</td><td>"+fac.getBillType()+"</td>");
					out.println("<td>"+fac.getProvider()+"</td><td>"+fac.getConsumerNo()+"</td><td><a href='/BankWebApp/jsp/pay.jsp?id="+fac.getId()+"'>Pay</a></td>");
					out.println("</tr>");
				} 
			}else {
				System.out.println("Something went wrong  !!");
			}
			
			out.println("</table></div></body></html>");		
		}
		out.close();
	}

}
