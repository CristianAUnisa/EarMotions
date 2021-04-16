<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.PurchaseBean,it.unisa.model.Cart,java.math.BigDecimal"%>
<HEAD>
<TITLE>Checking Out</TITLE>
</HEAD>
<BODY BGCOLOR="#FDF5E6">
	<H1 ALIGN="CENTER">Checking Out</H1>
	<PRE>
	<%
		if (request.getAttribute("error") != null && ((String) request.getAttribute("error")).equals("true")) {
	%>
	C'è stato un problema.
	<%
		} else {
	%>
	Il tuo ordine n° <%=request.getSession().getAttribute("order")%> è stato effettuato.
	</PRE>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Quantity</th>
			<th>Somma quantità</th>
		</tr>
		<%
			Cart cart = (Cart) request.getSession().getAttribute("cart");
				List<PurchaseBean> prodcart = cart.getProducts();
				BigDecimal price = BigDecimal.ZERO;
				for (PurchaseBean beancart : prodcart) {
			price = price.add(beancart.getTaxedPrice().multiply(new BigDecimal(beancart.getNumItems())));
		%>
		<tr>
			<td><%=beancart.getName()%></td>
			<td><%=beancart.getNumItems()%></td>
			<td><%=(beancart.getPrice().multiply(new BigDecimal(beancart.getNumItems())))%></td>
		</tr>
	<%
		}
	request.getSession().removeAttribute("cart");
	}
	%>
		</table>
	

	Since we have not yet calculated shipping charges, please sign the
	check but do not fill in the amount. We will generously do that for
	you.<br>
	<h3><a href="./ProductView.jsp">Ritorna alla home</a></h3>
</HTML>
