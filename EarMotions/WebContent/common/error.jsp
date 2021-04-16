<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
</head>
<body>
<h1>ERRORE <%=response.getStatus() %></h1>
<%
	if (response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
%>
	Non sei autorizzato ad accedere alla pagina richiesta.
<% }
%>
	<h2><a href="/EarMotions/index.jsp">Torna alla home page</a></h2>
</body>
</html>