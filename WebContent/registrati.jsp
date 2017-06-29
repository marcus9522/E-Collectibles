<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,utente.UtenteBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% String done = (String) request.getAttribute("done");%>
<%String enter= (String) session.getAttribute("enter");%>
       
<link rel="stylesheet" type="text/css" href="JAVASCRIPT/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<title>Registrazione</title>
</head>
<body>
<script src="JAVASCRIPT/jquery-3.1.0.js"></script>
<script src="JAVASCRIPT/jquery-ui.js"></script>
<script src="JAVASCRIPT/alert.js"></script>
<script type="text/javascript">
     var ncitta=["Salerno","Roma","Milano","Napoli","Avellino"];
     var nprovincia=["SA","AV","RO","MI","NA","AV"];
     $(document).ready(function(){
    	 $("#citta").autocomplete({source: ncitta});
    	 $("#provincia").autocomplete({source: nprovincia});
    	 });
</script>
<%@ include file="nav.jsp" %>
<%@ include file="search.jsp" %>
<%@ include file="categorie.jsp" %>
<section id="registrazione">
<div class="registrazione" align=center> 
<form action="utente?azione=registrati" name="registrazione" method="post">
<br>
<h3>FORM DI REGISTRAZIONE</h3>
<label for="email">E-MAIL:</label><br> 
		<input name="email" id="irec" type="text" maxlength="40" required placeholder="Inserisci e-mail" ><br>
<label for="password">PASSWORD:</label><br> 
		<input name="password" id="irec" type="password" maxlength="20" required placeholder="Inserisci password"><br>
  <br>
  <label for="ruolo">RUOLO: </label><br>
  <input id="role" type="radio" name="ruolo" value="normale" checked> Normale<br>
  <input id="role" type="radio" name="ruolo" value="amministratore"> Amministratore<br>
  <br>
<label for="codicefiscale">CODICE FISCALE:</label><br> 
		<input name="cod_fiscale" id="irec" type="text" maxlength="16" required placeholder="Inserisci codice fiscale"><br>
<label for="nome">NOME:</label><br> 
		<input name="nome" id="irec" type="text" maxlength="20" required placeholder="Inserisci il tuo nome"><br>
<label for="cognome">COGNOME:</label><br> 
		<input name="cognome" id="irec" type="text" maxlength="20" required placeholder="Inserisci il tuo cognome"><br>
<label for="data">DATA DI NASCITA:</label><br> 
		<input name="data_nascita" id="irec" type="date" max="<%=new java.sql.Date(System.currentTimeMillis()) %>" required ><br>
<label for="telefono">TELEFONO1:</label><br> 
		<input name="tel1" id="irec" type="number" maxlength="15" required placeholder="Inserisci il tuo numero"><br>
<label for="telefono">TELEFONO2:</label><br> 
<input name="tel2" type="number" id="irec"  maxlength="15" placeholder="Inserisci il tuo numero"><br>		
<label for="cap">CAP:</label><br> 
		<input name="cap" id="irec" type="number" maxlength="5" required placeholder="Inserisci il cap"><br>
<label for="via">VIA:</label><br> 
		<input name="via" id="irec" type="text" maxlength="30" required placeholder="Inserisci la via"><br>
<label for="civico">CIVICO:</label><br> 
		<input name="civico" id="irec" type="number" maxlength="5" required placeholder="Inserisci il civico"><br>
<label for="citta">CITTA:</label><br> 
		<input name="citta" id="citta" type="text" maxlength="25" required placeholder="Inserisci città"><br>
<label for="provincia">PROVINCIA:</label><br> 
		<input name="provincia" id="provincia" type="text" maxlength="25" required placeholder="Inserisci provincia"><br>
<label for="name">NAZIONE:</label><br> 
		<input name="nazione" id="irec" type="text" maxlength="25" required placeholder="Inserisci nazione"><br>
		<p></p>
   <input id="registra" type="submit" value="Invia">
  <input id="registra" type ="reset" value="Reset"/>	 
  </form>
</div>
<%if(done!=null){ 
if(done.equalsIgnoreCase("true")){%>
<script>
myalert("Email già presente nel database")
history.go(-1);
</script>
<%} %>
<% if(done.equalsIgnoreCase("false")){%>
<script>
myalert("Registrazione effettuata")
</script> 
<%}
   }  %>
   </section>
   <%@ include file="footer.jsp" %>	
   
</body>
</html>