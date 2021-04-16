<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,it.unisa.model.ProductBean"%>
	<%
		ProductBean product = (ProductBean) request.getAttribute("product");
		if (product == null) {
			response.sendRedirect("../product?action=edit&id=" + request.getParameter("id"));
			return;
		}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editing <%=request.getParameter("id")%></title>
</head>
<body>
	<h2>
		Modifica
		<%=product.getName()%></h2>
	<%
		if (product != null) {
	%>
	<table class="tableproductedit" border="1">
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Category</th>
			<th>Picture</th>
		</tr>
		<tr>
			<td><%=product.getCode()%></td>
			<td><%=product.getName()%></td>
			<td><div class="descrizione"><%=product.getDescription()%></div></td>
			<td><%=product.getPrice()%></td>
			<td><%=product.getQuantity()%></td>
			<td><%=product.getCategory() %>
			<td><img src="../getPicture?id=<%=product.getCode() %>" width="3em"></td>
			
		</tr>
	</table>
	<%
		}
	%>
	<form class="productedit" action="product" method="post" enctype="multipart/form-data">
		<div>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="id" value=<%=product.getCode() %>>
			<p>
				<label for="name">Nome:</label>
				<input name="name" type="text"
					maxlength="200" required placeholder="enter name"
					value="<%=product.getName()%>">
			</p>
			<p>
				<label for="description">Descrizione:</label>
				<textarea name="description" maxlength="1000" rows="3" required
				placeholder="enter description"><%=product.getDescription()%></textarea>
			</p>
			<p>
				<label for="price">Prezzo:</label>
				<input name="price" type="number" min="0" value=<%=product.getPrice()%> required>
			</p>
			<p>
				<label for="quantity">Quantità:</label>
				<input name="quantity" type="number" min="1" value=<%=product.getQuantity()%> required>
			</p>
			<p>
				<label for="iva">IVA &#37;</label>
				<input name="iva" type="number" min="0" max="99" value=<%=product.getIva() %> required>
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
				<label for="photo">Foto</label>
				<input type="file" name="photo">
			</p>
		</div>
		<input type="submit" value="Edita"><input type="reset" value="Reset">
		<a href="/EarMotions/product?action=delete&id=<%=product.getCode() %>"><button>Elimina</button></a>
	</form>
</body>
</html>