package com.zycus.bankWebApp;

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
		response.sendRedirect("facility.jsp");
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
			out.println(errorlist);
		}else {
			HttpSession session = request.getSession();
			Integer accNo = (Integer)session.getAttribute("AccNo");
			FacilityDAO facdao = new FacilityDAO();
			Facility facility = new Facility(accNo.intValue(), billType, provider, consumerNo);
			facdao.create(facility);
			
			out.println("<script type=\"text/javascript\">");
		    out.println("alert('Your prefrence have been added successfully... ');");
		    out.println("</script>");
		    
		    List<Facility> faclist = FacilityDAO.getDataFromAccNo(accNo);
		    //Iterator<Facility> it= faclist.iterator();
		    out.println("<table>");
		    out.println("<tr><td>Facility ID</td><td>Bill Type</td><td>Provider</td><td>Consumer ID</td><td></td></tr>");
			
			for(Facility fac: faclist){
				out.println("<form action='' method=''>");
				out.println("<tr><td><input type='text' value='"+fac.getId()+"'/></td><td><input type='text' value='"+fac.getBillType()+"'/></td>");
				out.println("<td><input type='text' value='"+fac.getProvider()+"' /></td><td><input type='text' value='"+fac.getConsumerNo()+"'/></td><td><input type='submit' value='pay'/></td>");
				out.println("</tr>");
				out.println("</form>");
			} 
			
			out.println("</table></div></body></html>");		
		}
	}

}
