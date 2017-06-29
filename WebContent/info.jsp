<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%String enter= (String) session.getAttribute("enter");%>
    
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Info</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="CSS/index.css">
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
<section id="info">
<br>
<h3>E-collectibles è un e-commerce dedicato ai collezionisti che sono alla ricerca di oggetti rari, il sito
    permette agli utenti di sfogliare il catalogo dei prodotti con la possibilità di filtrarli in base alle proprie 
    esigenze.
    Per l'acquisto occorre registrarsi e poi loggarsi una volta fatto basta aggiungere i prodotti
    desiderati al carrello e procedere con la conferma dell'ordine</h3>
</section>
<%@ include file="footer.jsp" %>	
</body>
</html>