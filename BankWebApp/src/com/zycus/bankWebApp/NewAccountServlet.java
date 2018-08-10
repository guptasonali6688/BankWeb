package com.zycus.bankWebApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/new-account.do")
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList errorlist = new ArrayList();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("new-account.html");
	}
	//For checking input is empty or null
	private boolean checkInput(String parameter, String name) {
		if(parameter.equals("") || parameter == null) {
			errorlist.add(name+" can't be empty \n");
			return false;
		}
		return true;
	}
	
	//for validating first name and last name
	private void checkName(String checkinput, String name) {
		//Check input firstName and LastName 
		if(!Pattern.matches("[a-zA-Z]+", checkinput)) {
			//System.out.println(Pattern.matches("[a-zA-Z]", checkinput)+ "  "+checkinput);
			errorlist.add("Enter a valid "+name);
		}
	}
	
	//for validating email
	 private static boolean isValid(String email)
	    {
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
	                            "[a-zA-Z0-9_+&*-]+)*@" +
	                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
	                            "A-Z]{2,7}$";
	                             
	        Pattern pat = Pattern.compile(emailRegex);
	        if (email == null){
	        	return false;
	        }
	            
	        return pat.matcher(email).matches();
	    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		errorlist.removeAll(errorlist);
		
		String title = request.getParameter("title");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String agestr = request.getParameter("age");
		String dob = request.getParameter("dob");		
		String email = request.getParameter("email");
		
		int age = 0;
		
	
		if(checkInput(title,"Title") & checkInput(firstname, "First Name")  & checkInput(lastname, "Last Name") &
		checkInput(agestr, "Age") & checkInput(email, "Email") & checkInput(dob, "Date of Birth")) {
			
				//to validate title
				if(!Pattern.matches("[a-zA-Z]+", title)|| !(title.trim().equalsIgnoreCase("Ms") || title.trim().equalsIgnoreCase("Mrs") || title.trim().equalsIgnoreCase("Mr") || title.trim().equalsIgnoreCase("Dr"))) {
					errorlist.add("Enter a valid title i.e Mr/Mrs/Ms/Dr");
				}
				
				//For firstname and lastname
				checkName(firstname, "First Name");
				checkName(lastname, "Last Name");
				
				//to validate age
				if(!Pattern.matches("[0-9]+", agestr)) {
					//System.out.println(Pattern.matches("[\\d]", age)+ "    "+age);
					errorlist.add("Enter a valid age");
				}else {
					age = Integer.parseInt(agestr);
					if(!(age >= 18)) {
						errorlist.add("Age should be greater than or equal to 18");
					}
				}
				//to validate email-id
				if(!isValid(email)) {
					errorlist.add("Enter a valid Email-Id");
				}
				
		}
		
		if(errorlist.size() > 0) {
			out.println("<h2>"+errorlist+"</h2>");
			
		}else {
			out.println("<script type=\"text/javascript\">");
		    out.println("alert('Your details have been saved successfully.. Provide account type');");
		    out.println("location='account-creation.html';");
		    out.println("</script>");
		    
		    CustomerDAO custDAO = new CustomerDAO();
		    Customer customer = new Customer(
		    			title, 
		    			firstname,
		    			lastname,
		    			age,
		    			email,
		    			java.sql.Date.valueOf(dob)
		    		);
		    
		    custDAO.create(customer);
		    
		    HttpSession session = request.getSession();
		    session.setAttribute("email", email);
		}
		
	}

}
