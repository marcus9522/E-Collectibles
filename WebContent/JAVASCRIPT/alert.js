function myalert(messaggio){
	window.alert(messaggio);
}

function info(codice){
	window.location.href = "product2?action=leggip&id="+codice;  
}
function info2(email){
	window.location.href = "utente2?azione=leggi&email="+email;  
}
function info3(cod){
	window.location.href = "ordine?action=leggio&id="+cod;  
}
function random(){
	document.getElementById("cod").value = Math.round(Math.floor(Math.random() * 900000)); 
}
function redirect(){
	window.location.href = history.go(-1);  
}
function myalert2(enter){
	if(enter==null) window.alert("Effettua il login per aggiungere prodotti al carrello");
}
function myalert3(enter){
	if(enter==null) window.alert("Effettua il login per aggiungere prodotti alla tua lista desideri");
}

