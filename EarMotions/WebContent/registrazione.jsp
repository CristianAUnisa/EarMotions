<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link href="css/ProductStyle.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">
<title>Registrazione</title>
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<div class="boxregistrazione">
	  <form action="SignUp" onsubmit="event.preventDefault(); validate(this)" method="post">
	    <div class="row">
	    	<div>
	        	<label for="email">E-Mail</label>
	        </div>
			<div>
				<input type="email" id="email" name="email" placeholder="La tua e-mail..." required>
			</div>
			<div>
	        	<label for="username">Username</label>
	        </div>
			<div>
				<input type="text" id="username" name="username" placeholder="Il tuo username..." required>
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
	    <div class="row">
	      <input type="submit" value="Submit">
	    </div>
	  </form>
	</div>
	<footer>
		<%@ include file="footer.html"%>
	</footer>
	<script src="scripts/jquery.js"></script>
	<script src="scripts/signup.js"></script>
</body>
</html>