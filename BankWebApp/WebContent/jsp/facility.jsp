<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facility</title>
<link rel="stylesheet" type="text/css" href="../StylingCss/default.css">
<script src="jquery-3.3.1.min.js"></script>
</head>
<body>
<div id="main">
	<h1>Add Facility</h1><hr/>
	<%
		Integer customerId = (Integer) session.getAttribute("cust_id");
		if(customerId == null) {
			response.sendRedirect("/BankWebApp/index.html");
		}
	%>
	<p><strong>
		Account Number : <%= request.getSession().getAttribute("AccNo") %>

	</strong></p>
	<form action="/BankWebApp/facility.do" method="post">
	<!--Select your Bill : <input onClick="displayOnClick();" type="radio" name="billType" value="Electric Bill"/>Electric Bill <input  onclick="displayOnClick();" type="radio" name="billType" value="Phone Bill"/>Phone Bill<br />-->
	Select your Bill :
	<select name="billType" id="billTypes">
		<option value="" selected id="default"></option>
		<option value="Electric" id="electricOptn">Electric Bill</option>
		<option value="Phone" id="phoneOptn">Phone Bill</option>
	</select><br/><br />
	Select Provider :
	<div id="elect" style="display:none;">
	For Electricity Bill : 
	<select name="provider1">
		<option value="" selected class="default"></option>
  		<option value="MSEB" class="providerOptn">MSEB</option>
  		<option value="Tata" class="providerOptn">Tata</option>
  		<option value="Reliance" class="providerOptn">Reliance</option>
	</select> 
	</div>
	<div id="phone" style="display:none;">
	For Phone Bill :
	<select name="provider2">
		<option value="" selected class="default"></option>
  		<option value="MTNL" class="providerOptn">MTNL</option>
  		<option value="Jio" class="providerOptn">Jio</option>
  		<option value="Airtel" class="providerOptn">Airtel</option>
  		<option value="Vodafone" class="providerOptn">Vodafone</option>
  		<option value="Idea" class="providerOptn">Idea</option>  		
	</select> 
	</div><br/><br />
	Consumer ID : <input type="text" name="consumerId" /><br/><br />
	<input type="submit" value="Save Prefrence"  name="submit"/>	
	
	</form>	
</div>

<script>

		var electric = document.getElementById("electricOptn");
		electric.addEventListener("click", function(){
			document.getElementById("elect").style.display="block";
			document.getElementById("phone").style.display="none";
		}, false);
		
		var phone = document.getElementById("phoneOptn");
		phone.addEventListener("click", function(){
			document.getElementById("elect").style.display="none";
			document.getElementById("phone").style.display="block";
		}, false);

</script>
</body>
</html>