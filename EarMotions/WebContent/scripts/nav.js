/* Toggle between adding and removing the "responsive" class to topnav when the user clicks on the icon */
function makeNavResponsive() {
  var x = document.getElementsByTagName("NAV")[0];
  if (typeof x !== 'undefined' && x.className == '') {
    x.className += " responsive";
  } else {
    x.className = "";
  }
}

/*
function showCart() {
	var carr = document.getElementsByClassName("dropcarrello")[0];
	if (!carr.classList.contains("active"))
		carr.
	else
		carr.classList.remove("active");
}
*/

function showCart() {
	$(".dropcarrello").slideToggle("fast");
}

function emptyCart() {
	$.post('product', { "action" : "delCart"},
            function(resp) { // on sucess
    			// We need 2 methods here due to the different ways of 
    			// handling a JSON object.
    			location.reload();
				return;
            })
            .fail(function() { // on failure
                alert("Request failed.");
            });
}