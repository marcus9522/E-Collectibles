<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,prodotto.ProductBean,prodotto.Cart"%>
<% String email = (String) session.getAttribute("email");
    Collection<?> products = (Collection<?>) request.getAttribute("prodotti");
 %>
   <%String enter= (String) session.getAttribute("enter");
        if((enter==null)||(enter.equals("admin")))
        {
          response.sendRedirect("login.jsp");
        } %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<title>Lista Desideri</title>
</head>
<body >
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
<section id="lista">
<%if(products.size()!=0){ %>
	     <div class=listadesideri align=center>
	     <br>
	     <h2>Lista Desideri</h2>
	     <table>
	     <tr>
	        <th>Foto</th>
	        <th>Nome</th>
	        <th>Prezzo</th>
	        <th></th>
	     </tr>
	     <tr>
            <%if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
		%> 
		   <td>
		    <a href ="http://localhost:8080/e-collectibles/product2?action=prodotto&id=<%=bean.getCodice()%>">
		   <img alt="IMAGE NOT FOUND" src="<%=bean.getFoto()%> " width="40" height="40"></a></td>
	        <td><%=bean.getNome().toUpperCase()%> </td>
	        <td><%=bean.getPrezzo()%>&euro;</td>
	        <td><a href="lista?azione=deletel&id=<%=bean.getCodice()%>&email=<%=email%>"><img alt="IMAGE NOT FOUND" src="FOTO/x.png " width="20" height="20"></a></td>
	     </tr>
	     <%  }%>        
	     </table>
	     </div>

	<% }%> 
	<% } else{ %>
	<br>
	<div align=center><h1 id="nrec">Lista Desideri vuota!</h1></div>
	<%} %>
	</section>
	<%@ include file="footer.jsp" %>	
	
</body>
</html>