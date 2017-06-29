<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,prodotto.ProductBean,ordine.OrdineBean"%>
<%Collection<?> prodotti = (Collection<?>) request.getAttribute("prodotti");
  Collection<?> ordini = (Collection<?>) request.getAttribute("ordini");
  String done = (String) request.getAttribute("done");%>
   <%String enter= (String) session.getAttribute("enter");
        if((enter==null)||(enter.equals("admin")))
        {
          response.sendRedirect("login.jsp");
        } %>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<title>I miei acquisti</title>
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
<section id="acquisti">
<%if((prodotti!=null)&&(prodotti.size()!=0)){ %>
  <%Iterator<?> it = prodotti.iterator();
  Iterator<?> it2 = ordini.iterator();
     while((it.hasNext())&&(it2.hasNext())){
     ProductBean bean = (ProductBean) it.next();
     OrdineBean bean2 = (OrdineBean) it2.next();%>
     <div class="offerteDiv2">
     <fieldset>
     <a href ="http://localhost:8080/e-collectibles/product2?action=prodotto&id=<%=bean.getCodice()%>">
     <div align="center"><img alt="IMAGE NOT FOUND" src="<%=bean.getFoto()%>" width="60" height="80"></div></a>
     <p>Nome Prodotto = <%=bean.getNome().toUpperCase() %> </p>
     <p> Codice Ordine = <%=bean2.getCod_ordine() %> </p>
     <p>Data Acquisto = <%= bean2.getData_ord() %> </p>
     <p>Prezzo Pagato = <%=bean.getPrezzo() %>&euro;</p>
     <p>Stato = <%= bean2.getStato() %> </p>
     <p>Data partenza = <%=bean2.getData_partenza() %></p>
     <p>Tracciamento = <%= bean2.getTracciamento() %> </p>
     </div> 
     </fieldset>
 <%} }
else {%>	
<div align=center><h1 id="nrec">NESSUN PRODOTTO ACQUISTATO!</h1></div>
<%} %>
 
</section> 
</body>
</html>