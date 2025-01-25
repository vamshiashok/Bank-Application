<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Success Page</title>
</head>
<body>
	<%
		session = request.getSession();
		out.println("Dear, "+session.getAttribute("cust_name")+" thankyou for showing your interest in State Bank of India.");
			out.println("<br><br>");
		out.println("Our executive will reach out to you soon on your email address: "+ session.getAttribute("cust_email"));
	%>
	<br><br>
	<a href="Home.html">Goto Home</a>
</body>
</html>