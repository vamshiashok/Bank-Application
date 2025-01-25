<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statement Page</title>
<link rel="stylesheet" href="Style.css">
<style>
#stmt_div {
	border: 2px solid #8cd3ed;
	width: 500px;
	height: 250px;
	padding: 10px;
	align-content: center;
	margin-left: 30%;
	margin-top: 12%;
	font-size: 20px;
	background-color: #8cd3ed;
	color: black;
	border-radius: 10px;
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
	margin-left: 40%;
}

a:hover {
	background-color: white;
	color: black;
	border: 2px solid black;
}
}
</style>
</head>
<body>
	<div id="stmt_div">
		<%
		out.println("<b><center><u>Account Statement</u></center></b><br><br>");
		session = request.getSession();
		out.println("Sender's Account Number: "+ session.getAttribute("sal"));
		out.println("<br><br>");
		out.println("Receiver's Account Number: "+ session.getAttribute("ral"));
		out.println("<br><br>");
		out.println("Amount: "+ session.getAttribute("al")+"<br><br>");
	
		%>
		<a href="Home.html">Goto Home</a>
	</div>
</body>
</html>