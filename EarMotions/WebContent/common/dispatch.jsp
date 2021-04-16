<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/EarMotions/css/ProductStyle.css" rel="stylesheet" type="text/css">
<link href="/EarMotions/css/responsive.css" rel="stylesheet" type="text/css">
<title>Reindirizzamento</title>
</head>
<body>
	<header>
		<%@ include file="/header.jsp" %>
	</header>
	<%
		if (request.getAttribute("error") != null && ((String) request.getAttribute("error")).equals("true")) {
	%>
	C'è stato un problema.
	
	<%
		} else if (request.getParameter("registration") != null && request.getParameter("registration").equalsIgnoreCase("ok")) {
	%>

	<h3>Registrazione effettuata! Sarai reindirizzato alla home.</h3>
	<script type="text/javascript">
		var myVar=setInterval(function () {myTimer()}, 3000);
	
		function myTimer() {
		    window.location="../index.jsp"
		}
	</script>
	
	<%
		} else {
	%>
	<h2>Il tuo ordine n° <%=request.getAttribute("order")%> è stato effettuato.</h2>
	
	<table id="prodotti" onload="loadPurchases(<%=request.getAttribute("order")%>)">
	</table>
	<% 
		}
	%>
	<footer>
		<%@ include file="../footer.html"%>
	</footer>
	
	<script type="text/javascript" src="/EarMotions/scripts/loadorders.js"></script>
</body>
</html>