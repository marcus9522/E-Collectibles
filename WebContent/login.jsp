<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<% String done = (String) request.getAttribute("done");
   String email= (String) request.getAttribute("email");
   String password = (String) request.getAttribute("password");
   String redirect = (String) request.getAttribute("redirect");
   String enter= (String) session.getAttribute("enter");
   %>
<title>Login Form</title>
</head>
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
<section id="login">
<form action="utente2?azione=login" method="post">
	<div align=center>
	<br>

	<h2>Login Form</h2>
	<label for="email">E-mail&nbsp&nbsp&nbsp&nbsp&nbsp</label>
	<input id="email" type="text" name="email" id="email" placeholder="Inserisci email" required>
	<br>
	<label for="password">Password &nbsp</label>
	<input id="email" type="password" name="password" id="password" placeholder="Inserisci password" required>
	<br>
	<br>
	<input id="log" type="submit" value="Login"/>
	<input id="log" type="reset"  value="Reset"/>
</form>
</div>
<%done=request.getParameter("done"); %>
<%if(done!=null){ 
if(done.equalsIgnoreCase("no")){%>
<script>
myalert("Email e/o password errati")
</script>
<%} } %>
</section>
<%@ include file="footer.jsp" %>	
</body>
</html>