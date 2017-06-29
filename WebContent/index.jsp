<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%String enter= (String) session.getAttribute("enter");%>
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" 
   type="image/png" 
   href="http://graffitialphabet.org/letter-e/green-graffiti-alphabet-e.jpg">
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,prodotto.ProductBean"%>
<meta charset="ISO-8859-1">
<title>E-COLLECTIBLES</title>
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
<section id="main">
<%@ include file="main1.jsp" %>
<%@ include file="main2.jsp" %>
</section>
<%@ include file="footer.jsp" %>	
</body>
</html>