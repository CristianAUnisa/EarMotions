<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>Login</title>
<link href="css/ProductStyle.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<form action="Login" method="post"> 
		<div class="field">
			<fieldset>
				<legend>Accesso</legend>
				<label for="username">Username</label>
				<input id="username" type="text" name="username" placeholder="Inserisci lo username"> 
				<br>   
				<label for="password">Password</label>
				<input id="password" type="password" name="password" placeholder="Inserisci la password"> 
				<br>
				<button type="submit">Accedi</button>
				<br>
				<button type="button" onclick="window.history.back();" class="cancelbtn">Annulla</button>
				<button type="button" onclick="location.href='registrazione.jsp'"class="registrazione">Non sei ancora registrato?</button>
			</fieldset>
		</div>
	</form>

</body>
</html>
