<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<meta charset="UTF-8">
<title>Pannello di controllo</title>
<link href="../css/controlpanel.css" rel="stylesheet" type="text/css">
</head>
<body>
	<aside>
		<ul>
			<li id="ordini">
			<a href="#" class="dropdown-btn" onclick="loadOrderSubmenu()">Ordini</a>
				<ul>
					<li><a href="#" onclick="loadOrdersCheck()" class="submenu-btn">Visualizza ordini</a></li>
				</ul>
			</li>
			<li id="prodotti">
				<a href="#" class="dropdown-btn" onclick="loadProductSubmenu()">Prodotti</a>
				<ul>
					<li><a href="#" class="submenu-btn" onclick="loadEveryProduct()">Visualizza prodotti</a></li>
					<li><a href="#" class="submenu-btn" onclick="loadProductAdd()">Inserisci prodotto</a></li>
				</ul>
			</li>
			<li id="#logout">
				<a href="#" class="dropdown-btn" onclick="location.href='/EarMotions/Logout'">Logout</a>
			</li>
			</ul>
	</aside>
	<div class="content">
	</div>
	
	<script src="../scripts/jquery.js"></script> 
	<script src="../scripts/controlpanel.js"></script> 
</body>
</html>