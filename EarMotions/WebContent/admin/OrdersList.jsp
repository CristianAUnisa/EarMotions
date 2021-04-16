<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ordini</title>
</head>
<body>
	<div id="listaordini">
		<label for="stato">Stato</label>
		<select name="stato">
			<option value="tutti" selected>Tutti</option>
			<option value="attesa_pagamento">Attesa pagamento</option>
			<option value="in_preparazione">In preparazione</option>
			<option value="spedito">Spedito</option>
			<option value="consegnato">Consegnato</option>
		</select>
		<input type="date" name="inizio">
		<input type="date" name="fine">
		<input type="text" name="utente" placeholder="Utente">
		<button onclick="loadOrders()">Mostra</button>
		
		<table id="ordinitable">
		</table>
	
		<table id="dettordine">
		</table>
	
		<table id="prodottitable">
		</table>

	</div>
	
	<script type="text/javascript" src="../scripts/jquery.js"></script>
	<script type="text/javascript" src="../scripts/controlpanel.js"></script>
</body>
</html>