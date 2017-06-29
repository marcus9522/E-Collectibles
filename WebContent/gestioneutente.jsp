<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,utente.UtenteBean"%>
    <% UtenteBean utente = (UtenteBean) request.getAttribute("utente");
       String done = (String) request.getAttribute("done");
       Collection<?> utenti = (Collection<?>) request.getAttribute("utenti");%>
   <%String enter= (String) session.getAttribute("enter");
        if((enter==null)||(enter.equals("user")))
        {
          response.sendRedirect("login.jsp");
        } %>
<!DOCTYPE html >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<title>GestioneUtente</title>
</head>
<body>
<%if(enter==null){%>
<%@ include file="nav.jsp" %>
<%} 
 else if(enter.equals("user")){%>
<%@ include file="nav2.jsp" %>
<%} 
 else if(enter.equals("admin")){%>
<%@ include file="nav3.jsp" %>
<%}  %>
<%@ include file="search.jsp" %>
<%@ include file="categorie.jsp" %>
<section id="utenti">
<%if(utenti!=null){ %>
<script src="JAVASCRIPT/alert.js"></script>
<select onChange="info2(this.value)">
<option> Scegli un utente </option>
  <%Iterator<?> it = utenti.iterator();
     while(it.hasNext()){
     UtenteBean bean = (UtenteBean) it.next();%>
     	
  <option value="<%=bean.getEmail()%>"> <%=bean.getEmail()%></option>
  
<%} } %>
</select>
<%if((utente!=null) && !(utente.getEmail().equals(""))){ %>
<div class="utente" align=center> 
<form action="utente2?azione=update" name="updateutente" method="post">

		<input name="email" type="text" maxlength="40" required value="<%=utente.getEmail() %>" hidden="true" ><br>
<label for="password">PASSWORD:</label><br> 
		<input name="password" type="password" maxlength="20" required value="<%=utente.getPassword() %>"><br>
  <br>
  <label for="ruolo">RUOLO: </label><br>
  <input type="radio" name="ruolo" value="normale" required > Normale<br>
  <input type="radio" name="ruolo" value="amministratore" required> Amministratore<br>
  <br>
<label for="codicefiscale">CODICE FISCALE:</label><br> 
		<input name="cod_fiscale" type="text" maxlength="16" required value="<%=utente.getCod_fiscale() %>" ><br>
<label for="nome">NOME:</label><br> 
		<input name="nome" type="text" maxlength="20" required value="<%=utente.getNome() %>"><br>
<label for="cognome">COGNOME:</label><br> 
		<input name="cognome" type="text" maxlength="20" required value="<%=utente.getCognome() %>"><br>
<label for="data">DATA DI NASCITA:</label><br> 
		<input name="data_nascita" type="date" max="<%=new java.sql.Date(System.currentTimeMillis()) %>" required value="<%=utente.getData_nascita() %>" ><br>
<label for="telefono">TELEFONO1:</label><br> 
		<input name="tel1" type="number" maxlength="15" required value="<%=utente.getTel1() %>"><br>
<label for="telefono">TELEFONO2:</label><br> 
		<input name="tel2" type="number"  maxlength="15" value="<%=utente.getTel2() %>"><br>		
<label for="cap">CAP:</label><br> 
		<input name="cap" type="number" maxlength="5" required value=<%=utente.getCap() %>><br>
<label for="via">VIA:</label><br> 
		<input name="via" type="text" maxlength="30" required value="<%=utente.getVia() %>"><br>
<label for="civico">CIVICO:</label><br> 
		<input name="civico" type="number" maxlength="5" required value=<%=utente.getCivico() %>><br>
<label for="citta">CITTA:</label><br> 
		<input name="citta" type="text" maxlength="25" required value="<%=utente.getCitta() %>"><br>
<label for="provincia">PROVINCIA:</label><br> 
		<input name="provincia" type="text" maxlength="25" required value="<%=utente.getProvincia() %>"><br>
<label for="name">NAZIONE:</label><br> 
		<input name="nazione" type="text" maxlength="25" required value="<%=utente.getNazione() %>"><br>
		<p></p>
   <input id="ute" type="submit" value="Invia">	 
  </form>
  <a href="utente2?azione=delete&email=<%=utente.getEmail() %> "><button>Elimina Utente</button></a>	 
</div>
<%} %>

<%done=request.getParameter("done"); %>
<%if(done!=null){ 
if(done.equalsIgnoreCase("yes")){ %>
<script>
myalert("Utente Aggiornato");
</script><%}%>
<% if(done.equalsIgnoreCase("no")){ %>
<script>
myalert("Email già in uso");
history.go(-1);
</script><%}%>
<% if(done.equalsIgnoreCase("eliminated")){ %>
<script>
myalert("Utente Eliminato");
</script><%} } %>
</section>
<%@ include file="footer.jsp" %>	
</body>
</html>