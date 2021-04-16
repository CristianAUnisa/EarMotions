/**
 * 
 */

function loadOrders() {
	$.post('../OrdersControl', { "type" : "ordini", "inizio" : $("input[name='inizio']").val(),
								"fine" : $("input[name='fine']").val(), "stato" : $("input[name='stato']").val(), "utente" : $("input[name='utente']").val() },
            function(resp) { // on sucess
    			// We need 2 methods here due to the different ways of 
    			// handling a JSON object.
    			printOrders(resp);
            })
            .fail(function() { // on failure
                alert("Request failed.");
            });
}

function printOrders(json) {
	
	// First empty the <div> completely and add a title.
	$("#ordinitable").empty()
		.append("<tr><th class='codice'>Numero ordine</th><th>Data ordine</th><th>Stato ordine</th><th>Cliente</th></tr>");
		
	$.each(json, function(i, orderObject) {
		$("#ordinitable").append("<tr onclick='loadOrder(" + orderObject.numOrder + ")'><td>" + orderObject.numOrder + "</td><td>" + orderObject.date + "</td><td>" + orderObject.stato + "</td><td>" + orderObject.user +"</td></tr>");
	});
};

function loadOrder( order ) {
	$(".content").empty()
	$(".content").load("OrderPage.jsp?numOrder=" + order);
}

function printOrder( json, order ) {
	$("#prodottitable").empty()
		.append("<tr><th class='codice'>Codice</th><th>Nome</th><th>Descrizione</th><th>Prezzo</th><th>IVA</th><th>Quantità</th></tr>");
		
	$.each(json, function(i, purchaseObject) {
		$("#prodottitable").append("<tr><td>" + purchaseObject.code + "</td><td>" + purchaseObject.name + "</td><td>" + purchaseObject.description + "</td><td>" + purchaseObject.price + "</td><td>" + purchaseObject.iva + "</td><td>" + purchaseObject.numItems + "</td></tr>");
	});
};

function loadProductAdd() {
	$(".content").empty();
	$(".content").load("ProductAdd.jsp form");
}

function loadProductSubmenu() {
	$("#prodotti ul").toggle();
}

function loadOrderSubmenu() {
	$("#ordini ul").toggle();
}

function loadEveryProduct() {
	$.post('../product', { "action" : "readall" },
            function(resp) { // on sucess
    			// We need 2 methods here due to the different ways of 
    			// handling a JSON object.
    			printProducts(resp);
            })
            .fail(function() { // on failure
                alert("Request failed.");
            });
}

function printProducts(json) {
	
	// First empty the <div> completely and add a title.
	$(".content").empty()
		.append("<table class='productlist'><tr><th class='codice'>Codice Prodotto</th><th>Prezzo</th><th>Quantità</th><th>IVA</th><th class='nomeprod'>Nome</th></tr>");
		
	// Then add every band name contained in the list.	
	$.each(json, function(i, productObject) {
		$(".content table").append("<tr onclick='loadProductEdit(" + productObject.code + ")'><td>" + productObject.code + "</td><td>" + (productObject.price + (productObject.price * (productObject.iva / 100))) + " &euro;</td><td>" + productObject.quantity + "</td><td>" + productObject.iva + "&percnt;</td><td>" + productObject.name + "</td></tr>");
	});
	$(".content").append("</table><p class='productedit'></p>");
};

function loadProductEdit(code) {
	$(".content").empty()
	$(".content").load("ProductEdit.jsp?id=" + code);
}

function loadOrdersCheck() {
	$(".content").empty()
	$(".content").load("OrdersList.jsp");
}

function changeStatus(numOrder) {
	var status = $("#cambioStato").val();
	$.post('/EarMotions/OrdersControl', { "type" : "cambioStato", "numOrder" : numOrder, "status" : status},
            function(resp) { // on sucess
    			// We need 2 methods here due to the different ways of 
    			// handling a JSON object.
    			loadOrder( numOrder );
            })
            .fail(function() { // on failure
                alert("Request failed.");
            });
}