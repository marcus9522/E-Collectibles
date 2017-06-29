<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%String enter= (String) session.getAttribute("enter");
       String email = (String) session.getAttribute("email");
        if((enter==null)||(enter.equals("admin")))
        {
          response.sendRedirect("login.jsp");
        } %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<title>E-COLLECTIBLES</title>
</head>
<body>
<%@ include file="nav2.jsp" %>
<%@ include file="search.jsp" %>
<%@ include file="categorie.jsp" %>
<div class="user" align="center">
<br>
<h2>AREA UTENTE </h2>
<a href="http://localhost:8080/e-collectibles/ordine?action=acquisti">I MIEI ACQUISTI</a><br><br>
<a href="http://localhost:8080/e-collectibles/lista?azione=leggil">LISTA DESIDERI</a><br><br>
<a href="http://localhost:8080/e-collectibles/utente2?azione=leggi1">I MIEI DATI</a><br>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>