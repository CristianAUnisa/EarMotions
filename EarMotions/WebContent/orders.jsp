<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,it.unisa.model.OrderBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link href="css/ProductStyle.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">
<title>Ordini utente</title>
</head>
<body>
	<% 
		if (session.getAttribute("username") == null)
			response.sendRedirect("login-form.jsp");
	%>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<br>
	<h2>
		Benvenuto <%=session.getAttribute("username") %>
	</h2>
	<br>
	<h5><a href="Logout">Logout</a></h5>
	
	<table id="ordini">
	</table>
	
	<table id="prodotti">
	</table>
	
	<div id="prodotto">
		<img src="">
		<div id="datiprod">
		</div>
	</div>
	<footer>
		<%@ include file="footer.html"%>
	</footer>
	
	<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="scripts/loadorders.js"></script>
	
</body>
</html>