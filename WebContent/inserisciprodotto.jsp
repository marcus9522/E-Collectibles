<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,prodotto.ProductBean"%>
    <% ProductBean prodotto = (ProductBean) request.getAttribute("prodotto2");
       String done = (String) request.getAttribute("done");%>
   <%String enter= (String) session.getAttribute("enter");
        if((enter==null)||(enter.equals("user")))
        {
          response.sendRedirect("login.jsp");
        } %>
<!DOCTYPE html >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="CSS/index.css"></head>
<title>Aggiungi Prodotto</title>
<body>
<script src="JAVASCRIPT/alert.js"></script>
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
<section id="insprodotto">
<div class="insprodotto" align=center> 
<br>
<h3>INSERISCI PRODOTTO</h3>
<form action="product2?action=insert" id="gestioneprodotto" method="post" >
<label for="nome">CODICE:</label><br>
		<input name="codice" type="number" required placeholder="Codice Prodotto"  ><br>
<label for="nome">NOME:</label><br> 
		<input name="nome" type="text" maxlength="30" required placeholder="Nome del Prodotto"  ><br>
<label for="prezzo">PREZZO:</label><br> 
		<input name="prezzo" type="number" required placeholder="Prezzo"><br>
<label for="categoria">CATEGORIA:</label><br> 
		<input name="categoria" type="text" maxlength="20" required placeholder="Categoria" > <br>
<label for="descrizione">DESCRIZIONE:</label><br> 
		<input name="descrizione" type="text" required placeholder="Descrizione" ><br>
<label for="quantita">QUANTITA:</label><br> 
		<input name="quantita" type="number" required min=1 placeholder="Quantita" ><br>
  <label for="offerta">OFFERTA: </label><br>
  <input type="radio" name="offerta" value="1" required> SI<br>
  <input type="radio" name="offerta" value="0" required> NO<br>
  <label for="foto">FOTO:</label><br> 
		<input name="foto" type="text" required placeholder="URL foto" ><br>
  <br>

   <input id="insprod" type="submit" value="Invia">
  <input id="insprod" type ="reset" value="Reset">
  </form>
<%done=request.getParameter("done"); %>
<%if(done!=null){ 
if(done.equalsIgnoreCase("yes")){ %>
<script>
myalert("Prodotto Inserito");
</script><%}%>
<% if(done.equalsIgnoreCase("no")){ %>
<script>
history.go(-1);
myalert("Codice già utilizzato");
</script>
<%} } %>
</section>
<%@ include file="footer.jsp" %>	
</body>
</html>