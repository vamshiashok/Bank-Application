<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Success</title>
<link rel="stylesheet" href="Style.css">
<style>
#loan_suc_div {
	width: 800px;
	height: 200px;
	align-content: center;
	margin-top: 15%;
	margin-left: 18%;
	text-align: center;
	font-size: 20px;
	border: 2px solid #8cd3ed;
	border-radius: 10px;
	box-shadow: 0 4px 28px #8cd3ed;
	background-color: #53bfdb;
	color: black;
	padding: 20px;
}
a {
	text-decoration: none;
	width: 100px;
	cursor: pointer;
	font-size: 16px;
	background-color: black;
	padding: 7px;
	border: 1px solid white;
	color: white;
	border-radius: 5px;
	font-size: 18px;
}

a:hover {
	background-color: white;
	color: black;
	border: 2px solid black;
}
</style>
</head>
<body>
	<div id="loan_suc_div">
		<%
	session = request.getSession();
	out.println("Dear " +session.getAttribute("cust_name")+" ,Thank you for showing your interest on the loan.");
	out.println("<br>");
	out.println("Our executive will contact you soon on your email address mentioned below");
	out.println("<br>");
	out.println("Your Email: "+session.getAttribute("cust_email")+"<br><br><br>");
	%>
	<a href="Home.html">Goto Home</a>
	</div>
	
</body>
</html>