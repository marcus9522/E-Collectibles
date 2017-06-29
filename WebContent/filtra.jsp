<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <head>    
  <% String order = (String) request.getAttribute("order");
     int offerta = (Integer) request.getAttribute("offerta");
     int min = (Integer) request.getAttribute("min");
     int max = (Integer) request.getAttribute("max");
     String enter= (String) session.getAttribute("enter");
  %>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<title>Filtra Prodotti</title>
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
<%@ include file="categorie.jsp" %>
<div class="filtra" align=center  >
<form action="product2?action=filtra" id="search" method="post" >
<label for="nome">MIN:</label>
<input type="num" name="min" required  min=1  value=1 width="10" > 
<label for="nome">MAX:</label>
<input type="num" name="max" required  value=1 width="10%" > 
<label for="offerta">OFFERTA: </label>
  <input id="offerta" type="radio" name="offerta" value="1" checked> SI
  <input id="offerta" type="radio" name="offerta" value="0"> NO
  <small>Ordina per: </small>
	     <select name="order">
	     <option value="codice">Codice</option>
	     <option value="nome">Nome</option>
	     <option value="prezzo">Prezzo</option>
	     </select>

<input id="filtra" type="submit" value="Filtra">
<br>
</form>
</div>
</body>
</html>
