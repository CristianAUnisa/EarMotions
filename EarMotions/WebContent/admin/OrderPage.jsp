<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,it.unisa.model.OrderBean,it.unisa.model.PurchaseBean,it.unisa.model.BillingBean"%>
	<%
		OrderBean order = (OrderBean) request.getAttribute("order");
		if (order == null) {
			response.sendRedirect("/EarMotions/OrdersControl?type=dettaglioordine&numOrder=" + request.getParameter("numOrder"));
			return;
		}
		BillingBean billing = (BillingBean) request.getAttribute("billing");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordine <%=request.getParameter("numOrder")%></title>
</head>
<body>
	<table id="dettordine">
		<tr>
			<th class='codice'>Numero ordine</th>
			<th>Data ordine</th>
			<th>Stato ordine</th>
			<th>Cliente</th>
		</tr>
		<tr>
			<td><%=order.getNumOrder() %></td>
			<td><%=order.getDate() %></td>
			<td><%=order.getStato() %></td>
			<td><%=order.getUser() %></td>
		</tr>
	</table>
	
	<p>
		<label for="select">Modifica stato</label>
		<select id="cambioStato" name="statosped">
			<option value="attesa_pagamento">Attesa pagamento</option>
			<option value="in_preparazione">In preparazione</option>
			<option value="spedito">In spedizione</option>
			<option value="consegnato">Consegnato</option>
		</select>
		<button name="conferma" onclick="changeStatus(<%=order.getNumOrder() %>)">Aggiorna stato</button>
	</p>
	
	<table id="listaprodotti">
		<tr>
			<th>Codice</th>
			<th>Nome</th>
			<th>Descrizione</th>
			<th>Prezzo</th>
			<th>Iva</th>
			<th>Quantità</th>
		</tr>
		<%
		Collection<PurchaseBean> purchases = order.getPurchases(); 
		for (PurchaseBean purchase : purchases) {
		%>
		<tr>
			<td><%=purchase.getCode() %></td>
			<td><%=purchase.getName() %></td>
			<td><div class="descrizione"><%=purchase.getDescription() %></div></td>
			<td><%=purchase.getPrice() %></td>
			<td><%=purchase.getIva() %></td>
			<td><%=purchase.getNumItems() %></td>
		</tr>
		<%
		}
		%>
	</table>
	
	<div class="daticliente">
		<div class='riga'><p>Nome</p><p><%=billing.getName() %></p></div>
		<div class='riga'><p>Cognome</p><p><%=billing.getSurname() %></p></div>
		<div class='riga'><p>EMail</p><p><%=billing.getEmail() %></p></div>
		<div class='riga'><p>Telefono</p><p><%=billing.getNumtelefono() %></p></div>
		<div class='riga'><p>Regione</p><p><%=billing.getRegione() %></p></div>
		<div class='riga'><p>Provincia</p><p><%=billing.getProvincia() %></p></div>
		<div class='riga'><p>Indirizzo</p><p><%=billing.getIndirizzo() %></p></div>
		<div class='riga'><p>Città</p><p><%=billing.getCitta() %></p></div>
		<div class='riga'><p>CAP</p><p><%=billing.getCap() %></p></div>
		<div class='riga'><p>Carta pagamento</p><p><%=billing.getPagamento() %></p></div>
	</div>
	
</body>
</html>