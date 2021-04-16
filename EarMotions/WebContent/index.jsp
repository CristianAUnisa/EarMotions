<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
if (products == null) {
	response.sendRedirect("./product");
	return;
}

ProductBean product = (ProductBean) request.getAttribute("product");

%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.PurchaseBean,it.unisa.model.Cart,java.math.BigDecimal"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link href="css/ProductStyle.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<title>EarMotions</title>
</head>

<body>
	<header>
		<%@ include file="header.jsp" %>
	</header>
	<%
		// Check user credentials
		String role = (String) session.getAttribute("role");
		if (role != null)
		{
	%>
		<br><br><h3>Benvenuto <%=session.getAttribute("role")%>!</h3>
	<%
		}			
	%>
	<div id="productsgrid">
		<div>
	<% if (products != null && products.size() != 0) {
		Iterator<?> it = products.iterator();
		while (it.hasNext()) {
		ProductBean bean = (ProductBean) it.next(); %>
		<div class="card">
			<img src="./getPicture?id=<%=bean.getCode() %>" class="picproduct" style="width:100%" onclick="location.href='product?action=read&id=<%=bean.getCode()%>'">
			<h2><%=bean.getName() %></h2>
			<p class="price">€ <%=bean.getTaxedPrice() %></p>
			<p class="descprod"><%=bean.getDescription() %></p>
			<p><button onclick="location.href='product?action=addC&id=<%=bean.getCode()%>';">Aggiungi al Carrello</button></p>
		</div>
	<%
		} 
	}
	%>
		</div>
	</div>
	<footer>
		<%@ include file="footer.html"%>
	</footer>
	
	<script src="scripts/jquery.js"></script>
	<script src="scripts/index.js"></script>
	
</body>
</html>