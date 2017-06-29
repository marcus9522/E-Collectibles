<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     
  <% String name = (String) request.getAttribute("name");%>

<div class="search" align=center>
<form action="product2?action=search" id="search" method="post">
<input type="text" name="name" required  placeholder="Cerca..."> 
<input id="cerca" type="submit" value="CERCA">
<br>
</form>
</div>
