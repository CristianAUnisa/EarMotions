/**
 * 
 */

$(document).ready(function() {
	$.post('OrdersControl', { "type" : "ordini"},
            function(resp) { // on sucess
    			// We need 2 methods here due to the different ways of 
    			// handling a JSON object.
    			printOrders(resp);
            })
            .fail(function() { // on failure
                alert("Request failed.");
            });
})

function printOrders(json) {
	
	// First empty the <div> completely and add a title.
	$("#ordini").empty()
		.append("<tr><th class='codice'>Numero ordine</th><th>Data ordine</th><th>Stato ordine</th><th>Operazioni</th></tr>");
		
	$.each(json, function(i, orderObject) {
		var ending;
		if (orderObject.stato.localeCompare("ATTESA_PAGAMENTO") !== 0) {
			ending = "<td><a href='StampaPDF'>Stampa fattura</a></td>";
		} else { 
			ending = "<td>Fattura non disponibile</td>";
		}
		$("#ordini").append("<tr onclick='loadPurchases(" + orderObject.numOrder + ")'><td>" + orderObject.numOrder + "</td><td>" + orderObject.date + "</td><td>" + orderObject.stato + "</td>" + ending +"</tr>");
	});
};

function loadPurchases(numOrder) {
	$.post('OrdersControl', { "type" : "prodotti", "numOrder" : numOrder },
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
	$("#prodotti").empty()
		.append("<tr><th class='codice'>Codice Prodotto</th><th>Prezzo</th><th>Quantità</th><th>IVA</th><th class='nomeprod'>Nome</th></tr>");
		
	// Then add every band name contained in the list.	
	$.each(json, function(i, productObject) {
		$("#prodotti").append("<tr onclick='loadSinglePurchase(" + productObject.code + ", " + productObject.numOrder + ")'><td>" + productObject.code + "</td><td>" + (productObject.price + (productObject.price * (productObject.iva / 100))) + " &euro;</td><td>" + productObject.numItems + "</td><td>" + productObject.iva + "&percnt;</td><td>" + productObject.name + "</td></tr>");
	});
};

function loadSinglePurchase(code, numOrder) {
	$.post('OrdersControl', { "type" : "prodotto", "code" : code, "numOrder" : numOrder },
            function(resp) { // on sucess
    			// We need 2 methods here due to the different ways of 
    			// handling a JSON object.
    			printProduct(resp);
            })
            .fail(function() { // on failure
                alert("Request failed.");
            });
}

function printProduct(object) {
	
	// First empty the <div> completely and add a title.
	$("#datiprod").empty();
			
	// Then add every band name contained in the list.	
	$("#datiprod").append("<h2>" + object.name + "</h2><div id='descrizione'><img id='picprod' src='getPicture?id=" + object.code + "&numOrder=" + object.numOrder + "' onerror='imgError(this)'>" + object.description + "</div>");
};

function imgError(image) {
	image.onerror = "";
    image.src = "./imgs/nophoto.png";
    return true;
}
