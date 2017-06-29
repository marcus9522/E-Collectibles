<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,prodotto.ProductBean,recensione.RecensioneBean"%>
    
    <% ProductBean product = (ProductBean) request.getAttribute("prodotto");
    Collection<?> recensioni = (Collection<?>) request.getAttribute("recensioni");
    Integer valutazione = (Integer) request.getAttribute("valutazione");
    String testo = (String) request.getAttribute("testo");
    String email = (String) session.getAttribute("email");
    String done = (String) request.getAttribute("done");
    String insert = (String) request.getAttribute("insert");
    Integer cod_prod= (Integer) request.getAttribute("cod_prod");
    String enter= (String) session.getAttribute("enter");%>    
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=product.getNome().toUpperCase()%></title>
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
<%if (product!= null) { %>
<section id=prodotti>
    <br>
	<div class=dettagli align=center> <img alt="IMAGE NOT FOUND" src="<%=product.getFoto()%>" width="200" height="200" >
	<p> Nome: <%=product.getNome().toUpperCase() %></p>
	                   <p> Prezzo: <%=product.getPrezzo() %> EURO </p>
	                   <p> Pezzi disponibili: <%=product.getQuantita() %> </p>
	                   <%if (product.getOfferta()==1){ %>
	                   <p> <img alt="IMAGE NOT FOUND" src="FOTO/offerta.gif" width="40" height="40"></p>  
	                   <%} %>
	<%if((enter==null)||(enter.equals("user"))){%>
	<a href="cart?action=addC&id=<%=product.getCodice()%>"><button onclick="myalert2(<%=enter%>)">Aggiungi al carrello</button></a>
	<%if(enter==null){%>
	<a href=""><button onclick="myalert3(<%=enter%>)">Aggiungi alla tua Lista Desideri</button></a>
	<%}%>
	<% if(enter!=null){ %>
	<a href="lista?azione=addl&email=<%=email%>&cod_prod=<%=product.getCodice()%>"><button>Aggiungi alla tua Lista Desideri</button></a>
	<%} }%>
	<br><br>
	<div align=left class="descrizione">Descrizione: <%=product.getDescrizione() %></div><br>
	</div>
	<div align=left class="recensioni"><%if (recensioni.size()!=0) { %>
	<table>
		<tr>
			<th>Utente</th>
			<th>Valutazione</th>
			<th>Testo</th>
		</tr>
	<%Iterator<?> it = recensioni.iterator(); 
	  while (it.hasNext()) {
					RecensioneBean bean = (RecensioneBean) it.next();
		%>
	
		<tr>
			<td id="utente"><%=bean.getEmail()%></td>
			<td><%=bean.getValutazione()%></td>
			<td><%=bean.getTesto()%></td>
		</tr>
			
<%}
	  }
	  else {%>
	  <div align=center><h1 id="nrec">Nessuna Recensione disponibile</h1></div>
	  <%} %>
	  </div>
	  </table>
	  <div align=center><h3>Inserisci una recensione</h3>
	  <form action="recensioni?azione=inserisci" name="saverecensione" method="post" >	
	  <label for="Valutazione">VALUTAZIONE:</label><br> 
	  <input name="valutazione" type="number" min="0" max="5" required ><br>
      <label for="Testo">TESTO:</label><br> 
	  <textarea id="testo" name="testo"  maxlength="70" rows="4" cols="60" required placeholder="Inserisci Testo" ></textarea><br>
      <input name="cod_prod" type="number" hidden="true" value=<%=product.getCodice() %>>
      <input name="email" type="text" hidden="true" value=<%=email %>>
      <input name="enter" type="text" hidden="true" value=<%=enter %>><br>
       <input id="rec" type="submit" value="Invia">
       </form>
       <%done=request.getParameter("done"); %>
<%if(done!=null){ 
if(done.equalsIgnoreCase("no")){ %>
<script>
myalert("Per inserire una recensione bisogna essere loggati come utenti non amministratori");
</script>
<%}%>
<% if(done.equalsIgnoreCase("repeat")){ %>
<script>
myalert("Mi dispiace ma hai già inserito una recensione per questo prodotto");
</script>
<%} } %>


<%insert=request.getParameter("insert"); %>
<%if(insert!=null){ 
if(insert.equalsIgnoreCase("no")){ %>
<script>
myalert("Hai già aggiunto questo prodotto alla lista desideri");
</script>
<%}%>
<% if(insert.equalsIgnoreCase("yes")){ %>
<script>
myalert("Prodotto aggiunto alla lista desideri");
</script>
<%} } }%>
 	</div>
</section>
</body>
</html>