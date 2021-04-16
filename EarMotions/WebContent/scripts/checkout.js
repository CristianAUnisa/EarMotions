/**
 * https://www.w3schools.com/howto/howto_js_form_steps.asp
 * Qui ho rubacchiato un po' di codice da DynamicForm per la validazione
 */

var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
  // This function will display the specified tab of the form ...
  var x = document.getElementsByClassName("tab");
  x[n].style.display = "block";
  // ... and fix the Previous/Next buttons:
  if (n == 0) {
    document.getElementById("prevBtn").style.display = "none";
  } else {
    document.getElementById("prevBtn").style.display = "inline";
  }
  if (n == (x.length - 1)) {
    document.getElementById("nextBtn").innerHTML = "Acquista!";
	$(".daticliente").empty();
	var datiForm = document.forms[0].elements;
	for (element in document.forms) {
		$(".daticliente").html("<div class='riga'><p>Nome</p><p>" + datiForm["nome"].value + "</p></div>" + 
							"<div class='riga'><p>Cognome</p><p>" + datiForm["cognome"].value + "</p></div>" +
							"<div class='riga'><p>EMail</p><p>" + datiForm["email"].value + "</p></div>" +
							"<div class='riga'><p>Telefono</p><p>" + datiForm["telefono"].value + "</p></div>" +
							"<div class='riga'><p>Regione</p><p>" + datiForm["regione"].value + "</p></div>" +
							"<div class='riga'><p>Provincia</p><p>" + datiForm["provincia"].value + "</p></div>" +
							"<div class='riga'><p>Indirizzo</p><p>" + datiForm["indirizzo"].value + "</p></div>" +
							"<div class='riga'><p>Città</p><p>" + datiForm["citta"].value + "</p></div>" +
							"<div class='riga'><p>CAP</p><p>" + datiForm["cap"].value + "</p></div>" +
							"<div class='riga'><p>Carta pagamento</p><p>" + datiForm["pagamento"].value + "</p></div>")
	}
  } else {
    document.getElementById("nextBtn").innerHTML = "Prossimo";
  }
  // ... and run a function that displays the correct step indicator:
  fixStepIndicator(n)
}

function nextPrev(n) {
  // This function will figure out which tab to display
  var x = document.getElementsByClassName("tab");
  // Exit the function if any field in the current tab is invalid:
  if (n == 1 && !validateForm()) return false;
  // Hide the current tab:
  x[currentTab].style.display = "none";
  // Increase or decrease the current tab by 1:
  currentTab = currentTab + n;
  // if you have reached the end of the form... :
  if (currentTab >= x.length) {
    //...the form gets submitted:
    document.getElementById("checkoutform").submit();
    return false;
  }
  // Otherwise, display the correct tab:
  showTab(currentTab);
}

function validateForm() {
  // This function deals with validation of the form fields
  var x, y, i, valid = true;
  x = document.getElementsByClassName("tab");
  y = x[currentTab].getElementsByTagName("input");
  // A loop that checks every input field in the current tab:
  for (i = 0; i < y.length; i++) {
    // If a field is empty...
    if (y[i].value == "") {
      // add an "error" class to the field:
      valid = false;
    }
	else {
		var inputtxt = y[i];
		switch (inputtxt.name)
		{
			case "nome": valid = checkNamesurname(inputtxt);
			case "cognome": valid = checkNamesurname(inputtxt);
			case "email": valid = checkEmail(inputtxt);
			case "telefono": valid = checkPhonenumber(inputtxt);
		}
	}
	if(valid == false)
		y[i].className += " error";
  }
  // If the valid status is true, mark the step as finished and valid:
  if (valid) {
    document.getElementsByClassName("step")[currentTab].className += " finish";
  }
  return valid; // return the valid status
}

function fixStepIndicator(n) {
  // This function removes the "active" class of all steps...
  var i, x = document.getElementsByClassName("step");
  for (i = 0; i < x.length; i++) {
    x[i].className = x[i].className.replace(" active", "");
  }
  //... and adds the "active" class to the current step:
  x[n].className += " active";
}

function loadProvincia() {

	var str = "";
var regione = $('#regione').val().toLowerCase();

switch (regione) {

	case "abruzzo":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Chieti">Chieti</option>';
		str += '<option value="L\'Aquila">L\'Aquila</option>';
		str += '<option value="Pescara">Pescara</option>';
		str += '<option value="Teramo">Teramo</option>';
		break;

	case "basilicata":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Matera">Matera</option>';
		str += '<option value="Potenza">Potenza</option>';
		break;

	case "calabria":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Catanzaro">Catanzaro</option>';
		str += '<option value="Cosenza">Cosenza</option>';
		str += '<option value="Crotone">Crotone</option>';
		str += '<option value="Reggio Calabria">Reggio Calabria</option>';
		str += '<option value="Vibo Valentia">Vibo Valentia</option>';
		break;

	case "campania":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Avellino">Avellino</option>';
		str += '<option value="Benevento">Benevento</option>';
		str += '<option value="Caserta">Caserta</option>';
		str += '<option value="Napoli">Napoli</option>';
		str += '<option value="Salerno">Salerno</option>';
		break;

	case "emilia romagna":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Bologna">Bologna</option>';
		str += '<option value="Ferrara">Ferrara</option>';
		str += '<option value="Forl&igrave;-Cesena">Forl&igrave;-Cesena</option>';
		str += '<option value="Modena">Modena</option>';
		str += '<option value="Parma">Parma</option>';
		str += '<option value="Piacenza">Piacenza</option>';
		str += '<option value="Ravenna">Ravenna</option>';
		str += '<option value="Repubblica di San Marino">Repubblica di San Marino</option>';
		str += '<option value="Rimini">Rimini</option>';
		break;

	case "friuli venezia giulia":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Gorizia">Gorizia</option>';
		str += '<option value="Pordenone">Pordenone</option>';
		str += '<option value="Trieste">Trieste</option>';
		str += '<option value="Udine">Udine</option>';
		break;

	case "lazio":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Frosinone">Frosinone</option>';
		str += '<option value="Latina">Latina</option>';
		str += '<option value="Rieti">Rieti</option>';
		str += '<option value="Roma">Roma</option>';
		str += '<option value="Viterbo">Viterbo</option>';
		break;

	case "liguria":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Genova">Genova</option>';
		str += '<option value="Imperia">Imperia</option>';
		str += '<option value="La Spezia">La Spezia</option>';
		str += '<option value="Savona">Savona</option>';
		break;

	case "lombardia":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Bergamo">Bergamo</option>';
		str += '<option value="Brescia">Brescia</option>';
		str += '<option value="Como">Como</option>';
		str += '<option value="Cremona">Cremona</option>';
		str += '<option value="Lecco">Lecco</option>';
		str += '<option value="Lodi">Lodi</option>';
		str += '<option value="Mantova">Mantova</option>';
		str += '<option value="Milano">Milano</option>';
		str += '<option value="Monza Brianza">Monza Brianza</option>';
		str += '<option value="Pavia">Pavia</option>';
		str += '<option value="Sondrio">Sondrio</option>';
		str += '<option value="Varese">Varese</option>';
		break;

	case "marche":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Ancona">Ancona</option>';
		str += '<option value="Ascoli Piceno">Ascoli Piceno</option>';
		str += '<option value="Macerata">Macerata</option>';
		str += '<option value="Pesaro e Urbino">Pesaro e Urbino</option>';
		break;

	case "piemonte":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Alessandria">Alessandria</option>';
		str += '<option value="Asti">Asti</option>';
		str += '<option value="Biella">Biella</option>';
		str += '<option value="Cuneo">Cuneo</option>';
		str += '<option value="Novara">Novara</option>';
		str += '<option value="Torino">Torino</option>';
		str += '<option value="Verb-Cus-Ossola">Verb-Cus-Ossola</option>';
		str += '<option value="Vercelli">Vercelli</option>';
		break;

	case "puglia":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Bari">Bari</option>';
		str += '<option value="Barletta-Andria-Trani">Barletta-Andria-Trani</option>';
		str += '<option value="Brindisi">Brindisi</option>';
		str += '<option value="Foggia">Foggia</option>';
		str += '<option value="Lecce">Lecce</option>';
		str += '<option value="Taranto">Taranto</option>';
		break;

	case "sardegna":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Cagliari">Cagliari</option>';
		str += '<option value="Nuoro">Nuoro</option>';
		str += '<option value="Olbia-Tempio">Olbia-Tempio</option>';
		str += '<option value="Oristano">Oristano</option>';
		str += '<option value="Sassari">Sassari</option>';
		break;

	case "sicilia":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Agrigento">Agrigento</option>';
		str += '<option value="Caltanissetta">Caltanissetta</option>';
		str += '<option value="Catania">Catania</option>';
		str += '<option value="Enna">Enna</option>';
		str += '<option value="Messina">Messina</option>';
		str += '<option value="Palermo">Palermo</option>';
		str += '<option value="Ragusa">Ragusa</option>';
		str += '<option value="Siracusa">Siracusa</option>';
		str += '<option value="Trapani">Trapani</option>';
		break;

	case "toscana":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Arezzo">Arezzo</option>';
		str += '<option value="Firenze">Firenze</option>';
		str += '<option value="Grosseto">Grosseto</option>';
		str += '<option value="Livorno">Livorno</option>';
		str += '<option value="Lucca">Lucca</option>';
		str += '<option value="Massa Carrara">Massa Carrara</option>';
		str += '<option value="Pisa">Pisa</option>';
		str += '<option value="Pistoia">Pistoia</option>';
		str += '<option value="Prato">Prato</option>';
		str += '<option value="Siena">Siena</option>';
		break;

	case "trentino alto adige":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Bolzano">Bolzano</option>';
		str += '<option value="Trento">Trento</option>';
		break;

	case "umbria":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Perugia">Perugia</option>';
		str += '<option value="Terni">Terni</option>';
		break;

	case "valle d'aosta":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Aosta">Aosta</option>';
		break;

	case "veneto":

		str += '<option value="" selected>Seleziona</option>';
		str += '<option value="Belluno">Belluno</option>';
		str += '<option value="Padova">Padova</option>';
		str += '<option value="Rovigo">Rovigo</option>';
		str += '<option value="Treviso">Treviso</option>';
		str += '<option value="Venezia">Venezia</option>';
		str += '<option value="Verona">Verona</option>';
		str += '<option value="Vicenza">Vicenza</option>';
		break;
	}
	
	$('#provincia').html(str);
	$('#provincia').removeAttr("disabled");
}