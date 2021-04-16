<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<div class="boxregistrazione">
	  <form action="SignUp" onsubmit="event.preventDefault(); validate(this)">
	    <div class="row">
	      <div>
	        <label for="fname">Nome</label>
	      </div>
	      <div>
	        <input type="text" id="fname" name="firstname" placeholder="Il tuo nome..." required>
	      </div>
	    </div>
	    <div class="row">
	      <div>
	        <label for="lname">Cognome</label>
	      </div>
	      <div>
	        <input type="text" id="lname" name="lastname" placeholder="Il tuo cognome..." required>
	      </div>
	      <div>
	        <label for="email">E-Mail</label>
	      </div>
	      <div>
	        <input type="email" id="email" name="email" placeholder="La tua e-mail..." required>
	      </div>
	      <div>
	        <label for="password">Password (deve contenere tra 8 e 128 caratteri)</label>
	      </div>
	      <div>
	        <input type="password" id="password" name="password" placeholder="..." required>
	      </div>
	      <div>
	        <label for="confirmpassword">Conferma Password</label>
	      </div>
	      <div>
	        <input type="password" id="confirmpassword" name="confirmpassword" placeholder="..." required>
	      </div>
	    </div>
	    <div class="localita">
	      <div>
	        <label for="country">Regione</label>
	      </div>
	      <div>
	        <select id="regione" name="regione" onchange="loadProvincia()" required>
	        	<option value="" selected>Seleziona</option>
				<option value='Abruzzo'>Abruzzo</option>
				<option value='Basilicata'>Basilicata</option>
				<option value='Calabria'>Calabria</option>
				<option value='Campania'>Campania</option>
				<option value='Emilia Romagna'>Emilia Romagna</option>
				<option value='Friuli Venezia Giulia'>Friuli Venezia Giulia</option>
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
	      </div>
	      <br>
	      <div>
	        <label for="country">Provincia</label>
	      </div>
	      <div>
	      	<select id="provincia" name="provincia" required>
	      	</select>
	      </div>
	    </div>
	    <div class="row">
	      <input type="submit" value="Submit">
	    </div>
	  </form>
	</div>
	<footer>
		<%@ include file="footer.html"%>
	</footer>
<script src="scripts/jquery.js"></script>
<script src="scripts/registrationscripts.js"></script>
</body>
</html>