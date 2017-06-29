<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%String enter= (String) session.getAttribute("enter");
       if((enter==null)||(enter.equals("user")))
       {
         response.sendRedirect("login.jsp");
       }  %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<title>E-COLLECTIBLES</title>
</head>
<body>
<%@ include file="nav3.jsp" %>
<%@ include file="search.jsp" %>
<%@ include file="categorie.jsp" %>
<div class="admin" align="center">
<br>
<h2>AREA AMMINISTRATORE </h2>
<a href="http://localhost:8080/e-collectibles/ordine?action=ordini">GESTISCI ORDINI</a><br><br>
<a href="http://localhost:8080/e-collectibles/utente2?azione=leggi">GESTISCI UTENTI</a><br><br>
<a href="http://localhost:8080/e-collectibles/product2?action=prodotti">GESTISCI PRODOTTI</a><br><br>
<a href="http://localhost:8080/e-collectibles/inserisciprodotto.jsp">AGGIUNGI PRODOTTO</a><br><br>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>