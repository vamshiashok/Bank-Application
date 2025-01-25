<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Balance View Page</title>
<style>
#bal_container {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	text-align: center;
	margin-top: 240px;
}

#bal_div {
	width: 400px;
	height: 100px;
	font-size: 20px;
	border: 2px solid #8cd3ed;
	border-radius: 10px;
	box-shadow: 0 4px 28px #8cd3ed;
	backdrop-filter: blur(5px);
	padding: 20px;
}

#bal_div a {
	text-decoration: none;
	width: 100px;
	height: 30px;
	background-color: black;
	color: white;
	padding: 10px;
	border-radius: 10px;
}

#bal_div a:hover {
	transform: scale(1.2);
	background-color: yellow;
	color: black;
}
</style>
</head>
<body
	style="background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvPG1PkvlOdz4uZUAkW48bInAjDxyNh7yvVg&amp;s'); background-size: cover; background-repeat: no-repeat; background-position: center;">
	<div id="bal_container">
		<div id="bal_div">
			<%
			session = request.getSession();
			out.println("<br>"+" Available Balance: " + session.getAttribute("cust_bank_bal"));
			%>
			<br><br> <a href="Home.html">Goto Home</a>
		</div>
	</div>
</body>
</html>