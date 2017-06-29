<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,prodotto.ProductBean,prodotto.Cart,java.sql.Date"%>
<% String email = (String) request.getAttribute("email"); 
   Cart cart = (Cart) request.getSession().getAttribute("cart");
   String done= (String) request.getAttribute("done");
   String nome = (String) request.getAttribute("name");
 %>
   <%String enter= (String) session.getAttribute("enter");
        if((enter==null)||(enter.equals("admin")))
        {
          response.sendRedirect("login.jsp");
        } %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<title>Carrello</title>
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
<section id="carrello">
  	<%if((cart!=null)&&(cart.getProducts().size()>0)){ %>
	     <div class=carrello align=center>
	     <br>
	     <h2>Carrello</h2>
	     <table>
	     <tr>
	        <th>Foto</th>
	        <th>Nome</th>
	        <th>Prezzo</th>
	        <th></th>
	     </tr>
	     <% double tot = 0;
	 	        List<ProductBean> prodcart=cart.getProducts();
	        for(ProductBean beancart:prodcart){
	        	
	     %>
	   
	     <tr>
	        <td>
	        <a href ="http://localhost:8080/e-collectibles/product2?action=prodotto&id=<%=beancart.getCodice()%>">
	        <img alt="IMAGE NOT FOUND"  src="<%=beancart.getFoto()%> " width="40" height="40"> </a></td>
	        <td><%=beancart.getNome().toUpperCase()%> </td>
	        <td><%=beancart.getPrezzo()%>&euro;</td>
	        <% tot+=beancart.getPrezzo();%>
	        <td><a href="cart?action=deleteC&id=<%=beancart.getCodice() %>"><img alt="IMAGE NOT FOUND" src="FOTO/x.png " width="20" height="20"> </a></td>
	     </tr>
	     <%  }%>        
	     </table>
	     </div>
	     <form action="ordine?action=insert" id="ordine" method="post" >
	     <div align=center><p>Totale: <%=tot %>&euro;</p><br>
	     
	     <small>Seleziona metodo di pagamento: </small>
	     <select id="selectc" name="mod_pagamento">
	     <option value="Paypal">Paypal</option>
	     <option value="Carta di credito">Carta di credito</option>
	     <option value="Bonifico bancario">Bonifico Bancario</option>
	     <option value="Contrassegno">Contrassegno</option>
	     </select>
	     <small>Seleziona corriere: </small>
	     <select id="selectc" name="corriere">
	     <option value="BRT">Bartolini</option>
	     <option value="GLS">GLS</option>
	     <option value="SDA">SDA</option>
	     <option value="TNT">TNT</option>
	     <option value="DHL">DHL</option>
	     <option value="UPS">UPS</option>
	     <option value="Poste Italiane">Poste Italiane</option>
	     </select>
	     <input name="email" type="text" maxlength="40" required value="<%=email %>" hidden="true" ><br>
	     <input name="importo_tot" type="number"  required value="<%=tot %>"  hidden="true" ><br>
	     <input id="cod" name="cod_ordine" type="number" value="<%= Math.round(Math.floor(Math.random() * 900000))%>" required hidden="true" >
	     
	     <input type="submit" value="Effettua Ordine">
	     </form>
	     </div>
	     </section>
<%done=request.getParameter("done"); %>
<%if(done!=null){ 
if(done.equalsIgnoreCase("no")){ %>
<%nome=request.getParameter("name"); %>
<script>
myalert("Il prodotto <%=nome %> non è disponibile nelle quantità richieste");
</script>
<%} } } 
  	else { %>
  	<br>
	<div align=center><h1 id="nrec">Carrello vuoto!</h1>
	<% } %>
	     </div>
<%@ include file="footer.jsp" %>	    

</body>
</html>