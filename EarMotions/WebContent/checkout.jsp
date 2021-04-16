<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.PurchaseBean,it.unisa.model.Cart,java.math.BigDecimal" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link href="css/ProductStyle.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">
<title>Checkout</title>
</head>
<body>
	<form id="checkoutform" action="/EarMotions/checkout" method="post">

		<h1>Inserisci i dati per la spedizione</h1>

		<!-- One "tab" for each step in the form: -->
		<div class="tab">
			Cliente:
			<p>
				<input type="text" name="nome" placeholder="Nome" oninput="this.className = ''">
			</p>
			<p>
				<input type="text" name="cognome" placeholder="Cognome"
					oninput="this.className = ''">
			</p>
			<p>
				<input type="email" name="email" placeholder="E-mail"
					oninput="this.className = ''">
			</p>
			<p>
				<input type="text" name="telefono" placeholder="Telefono"
					oninput="this.className = ''">
			</p>
		</div>

		<div class="tab">
			Spedizione:
			<p>
				<label for="regione">Regione</label> <select id="regione"
					name="regione" onchange="loadProvincia()" required>
					<option value="" selected>Seleziona</option>
					<option value='Abruzzo'>Abruzzo</option>
					<option value='Basilicata'>Basilicata</option>
					<option value='Calabria'>Calabria</option>
					<option value='Campania'>Campania</option>
					<option value='Emilia Romagna'>Emilia Romagna</option>
					<option value='Friuli Venezia Giulia'>Friuli Venezia
						Giulia</option>
					<option value='Lazio'>Lazio</option>
					<option value='Liguria'>Liguria</option>
					<option value='Lombardia'>Lombardia</option>
					<option value='Marche'>Marche</option>
					<option value='Molise'>Molise</option>
					<option value='Piemonte'>Piemonte</option>
					<option value='Puglia'>Puglia</option>
					<option value='Sardegna'>Sardegna</option>
					<option value='Sicilia'>Sicilia</option>
					<option value='Toscana'>Toscana</option>
					<option value='Trentino'>Trentino Alto Adige</option>
					<option value='Umbria'>Umbria</option>
					<option value="Aosta">Valle d'Aosta</option>
					<option value='Veneto'>Veneto</option>
				</select>
			</p>
			<p>
				<label for="country">Provincia</label> <select id="provincia"
					name="provincia" required>
				</select>
			</p>
			<p>
				<input type="text" name="indirizzo" placeholder="Indirizzo"
					oninput="this.className = ''">
			</p>
			<p>
				<input type="text" name="citta" placeholder="Città" oninput="this.className = ''">
				<input type="text" name="cap" placeholder="Codice Postale" oninput="this.className = ''">
			</p>
		</div>

		<div class="tab">
			Pagamento:
			<p>
				Abbiate pazienza, non vendo cuffie per davvero.
				<input name="pagamento" value="1234 5678 9012 3456" readonly oninput="this.className = ''">
			</p>
		</div>

		<div class="tab">
			Rivedi l'ordine:
			<div id="review">
			<%
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				List<PurchaseBean> prodcart = cart.getProducts();
				BigDecimal price = BigDecimal.ZERO;
				for (PurchaseBean purchase : prodcart) {
					price = price.add(purchase.getTaxedPrice().multiply(new BigDecimal (purchase.getNumItems())));
					
			%>
					<p class="reviewpurchase">
						<img src="./getPicture?id=<%=purchase.getCode() %>"/>
						<span class="nomeprodcarrello"><%=purchase.getName() %></span><br>
						<span class="prezzoprodcarrello"><%=purchase.getTaxedPrice() %> &euro;</span>
						<span class="quantitaprodcarrello">Quantità: <%=purchase.getNumItems()%>
						<br>Totale: <i><%=purchase.getTaxedPrice().multiply(new BigDecimal(purchase.getNumItems())) %> &euro;</i></span>
					</p>
			<%
				}
			%>
			</div>
			<p class="totale">
				Prezzo totale: <span><%=price %> &euro;</span>
			</p>
			<p class="daticliente">
				
			</p>
		</div>
		
		<p>
			<div id="btns">
				<button type="button" id="prevBtn" onclick="nextPrev(-1)">Precedente</button>
				<button type="button" id="nextBtn" onclick="nextPrev(1)">Prossimo</button>
			</div>
		</p>

		<!-- Circles which indicates the steps of the form: -->
		<div style="text-align: center; margin-top: 40px;">
			<span class="step"></span> <span class="step"></span> <span
				class="step"></span> <span class="step"></span>
		</div>
	</form>
	<script src="scripts/jquery.js"></script>
	<script src="scripts/validations.js"></script>
	<script src="scripts/checkout.js"></script>
</body>
</html>