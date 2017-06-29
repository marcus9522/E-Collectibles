<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,prodotto.ProductBean"%>
<!DOCTYPE html >
<%Collection<?> products = (Collection<?>) request.getAttribute("prodotti2");
  ProductBean prodotto = (ProductBean) request.getAttribute("prodotto2");
  String done = (String) request.getAttribute("done");%>
   <%String enter= (String) session.getAttribute("enter");
        if((enter==null)||(enter.equals("user")))
        {
          response.sendRedirect("login.jsp");
        } %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="CSS/index.css">

<title>Gestione Prodotto</title>
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
<section id="prodotti">
<%if(products!=null){ %>
<script src="JAVASCRIPT/alert.js"></script>
<select onChange="info(this.value)">
<option> Scegli un prodotto </option>
  <%Iterator<?> it = products.iterator();
     while(it.hasNext()){
     ProductBean bean = (ProductBean) it.next();%>
     	
  <option value="<%=bean.getCodice()%>"> <%=bean.getNome()%></option>
  
<%} } %>
</select>
<%if(prodotto!=null){ %>
<div class="prodotto" align=center> 
<form action="product2?action=update" id="gestioneprodotto" method="post" >
		<input name="codice" type="number" hidden="true"  value=<%=prodotto.getCodice()%>  ><br>
<label for="nome">NOME:</label><br> 
		<input name="nome" type="text" maxlength="30" required value="<%=prodotto.getNome() %>"  ><br>
<label for="prezzo">PREZZO:</label><br> 
		<input name="prezzo" type="number" required value=<%=prodotto.getPrezzo()%>><br>
<label for="categoria">CATEGORIA:</label><br> 
		<input name="categoria" type="text" maxlength="20" required value="<%=prodotto.getCategoria()%>" > <br>
<label for="descrizione">DESCRIZIONE:</label><br> 
		<input name="descrizione" type="text" required value="<%=prodotto.getDescrizione()%>" ><br>
<label for="quantita">QUANTITA:</label><br> 
		<input name="quantita" type="number" required min=1 value=<%=prodotto.getQuantita()%> ><br>
  <label for="offerta">OFFERTA: </label><br>
  <input type="radio" name="offerta" value="1" required> SI<br>
  <input type="radio" name="offerta" value="0" required> NO<br>
  <label for="foto">FOTO:</label><br> 
		<input name="foto" id="url" type="text" value="<%=prodotto.getFoto()%>" ><br>
   <input id="prod" type="submit" value="Invia">
  </form>
  <a href="product2?action=delete&cod=<%= prodotto.getCodice() %>"><button>Elimina Prodotto</button></a>	 
</div>
<%} %>

<%done=request.getParameter("done"); %>
<%if(done!=null){ 
if(done.equalsIgnoreCase("updated")){ %>
<script>
myalert("Prodotto Aggiornato");
</script><%}%>
<% if(done.equalsIgnoreCase("eliminated")){ %>
<script>
myalert("Prodotto Eliminato");
</script><%} } %>
</section>
<%@ include file="footer.jsp" %>	
</body>
</html>