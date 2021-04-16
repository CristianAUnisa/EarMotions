<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.PurchaseBean,it.unisa.model.Cart,java.math.BigDecimal"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link href="/EarMotions/css/ProductStyle.css" rel="stylesheet" type="text/css">
<link href="/EarMotions/css/responsive.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

<title>Insert title here</title>
</head>
<body>
	<div id="logo">
		<a href="/EarMotions"><img id="logoEM" src="/EarMotions/imgs/Logo.png"/></a>
	</div>
	<nav>
		<a href="#" class="hamburger" onclick="makeNavResponsive()"><i class="fa fa-bars"></i></a>
		<a href="/EarMotions/product?category=Cuffie%20aperte">Cuffie aperte</a>
		<a href="/EarMotions/product?category=Cuffie%20chiuse">Cuffie chiuse</a>
		<a href="/EarMotions/product?category=Earphones">Earphones</a>
		<a href="/EarMotions/product?category=TWS">TWS</a>
		<div class="destranav">
			<a href="#" class="butcarrello" onclick="showCart()"><i class="fa fa-shopping-cart"></i></a>
			<%
				if (session.getAttribute("username") == null) {
			%>
				<button type="button" class="navbut" onclick="location.href='login-form.jsp'" type="button" >Effettua il login!</button>
			<%
				} else {
			%>
				<button type="button" class="navbut" onclick="location.href='orders.jsp'" type="button" >Account</button>
			<%
				}
			%>
		</div>
	</nav>
	<div class="dropcarrello">
	<%
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null || cart.getProducts().size() == 0) {
	%>
		<span class="vuoto">Il carrello è vuoto!</span>
	<% 
		} else {
	%>
	<%
			List<PurchaseBean> prodcart = cart.getProducts();
			BigDecimal price = BigDecimal.ZERO;
			for (PurchaseBean purchase : prodcart) {
				price = price.add(purchase.getTaxedPrice().multiply(new BigDecimal (purchase.getNumItems())));
			
	%>
		<p class="cartprod">
			<img src="./getPicture?id=<%=purchase.getCode() %>"/>
			<span class="nomeprodcarrello"><%=purchase.getName() %></span><br>
			<span class="prezzoprodcarrello">&euro; <%=purchase.getTaxedPrice() %> | Quantità: <%=purchase.getNumItems()%></span>
			<a href="/EarMotions/product?action=deleteC&id=<%=purchase.getCode() %>"><i class="fa fa-close"></i></a>
		</p>
		<hr>
	<%
			}
	%>
		<p class="totale">Prezzo totale: &euro; <%=price %>
		<button type="button" class="checkout" onclick="location.href='checkout.jsp'" type="button" >Checkout!</button>
		<button type="button" class="checkout" onclick="emptyCart()" type="button" >Svuota</button>
		</p>
	<%
		}
	%>
	</div>
	<script type="text/javascript" src="/EarMotions/scripts/jquery.js"></script>
	<script src="/EarMotions/scripts/nav.js"></script> 
</body>
</html>