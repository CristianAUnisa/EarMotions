<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

ProductBean product = (ProductBean) request.getAttribute("product");

%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.PurchaseBean,it.unisa.model.Cart"%>

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
		<%@ include file="header.jsp"%>
	</header>
	<div class="product">
		<div class="description">
			<h2><%=product.getName() %></h2>
			<%=product.getDescription() %>
			<div class="rowaggiungi">
				<span id="prezzo">€ <%=product.getTaxedPrice() %></span>
				<div class="atcproduct">
					<form action="product" method="get">
						<input type="submit" value="Aggiungi al Carrello">
						<input type="hidden" name="action" value="addC">
						<input type="hidden" name="id" value=<%=product.getCode() %>>
						<br>
						<label for="quantity">Quantità</label>
						<input type="number" id="quantity" name="quantity" value="1" min="1" max=<%=product.getQuantity() %>>
					</form>
					<br>
					Disponibili: <%=product.getQuantity() %>
				</div>
			</div>
		</div>
		<%
		if (product.getPicture() != null) {
		%>
		<div>
			<img src="./getPicture?id=${param.id}" id="mainpicture"/>
		</div>
		<%
		}
		%>
	</div>
	<footer>
	<%@ include file="footer.html"%>
	</footer>
	
	 <script src="scripts/hamburger.js"></script> 
	 <script src="scripts/product.js"></script> 
</body>
</html>