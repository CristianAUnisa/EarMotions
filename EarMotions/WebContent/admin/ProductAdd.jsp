<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inserisci prodotto</title>
</head>
<body>
	<h2>Inserisci</h2>
	<form action="/EarMotions/product" class="productadd" method="post" enctype="multipart/form-data">
		<div>
			<p>
				<label for="name"> Nome:</label>
				<input type="hidden" name="action" value="insert">
				<input name="name" type="text" maxlength="100" required placeholder="enter name">
			</p>
			<p>
				<label for="description">Descrizione:</label>
			<textarea name="description" maxlength="1000" rows="3" required placeholder="enter description"></textarea>
			</p>
			<p>
				<label for="price">Prezzo:</label>
				<input name="price" type="number" min="0" value="0" required>
			</p>
			<p>
				<label for="price">IVA:</label>
				<input name="iva" type="number" min="0" value="22" required>
			</p>
			<p>
				<label for="category">Categoria:</label>
				<select name="category">
					<option value="Cuffie aperte">Cuffie aperte</option>
					<option value="Cuffie chiuse">Cuffie chiuse</option>
					<option value="Earbuds">Earbuds</option>
					<option value="TWS">TWS</option>
				</select>
			</p>
			<p>
				<label for="quantity">Quantità:</label>
				<input name="quantity" type="number" min="1" value="1" required><br>
			</p>
			<p>
			<label for="picture">Inserisci la foto (solo jpg)</label>
			<input type="file" name="picture" accept="image/jpg, image/jpeg">
			</p>
		</div>
		<input type="submit" value="Aggiungi"><input type="reset" value="Reset">
	</form>
</body>
</html>