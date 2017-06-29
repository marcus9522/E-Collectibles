<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,ordine.OrdineBean"%>
<!DOCTYPE html >
<%Collection<?> ordini = (Collection<?>) request.getAttribute("ordini");
  OrdineBean ordine = (OrdineBean) request.getAttribute("ordine");
  String done = (String) request.getAttribute("done");%>
   <%String enter= (String) session.getAttribute("enter");
        if((enter==null)||(enter.equals("user")))
        {
          response.sendRedirect("login.jsp");
        } %>
 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<title>Gestione Ordini</title>
</head>
<body>
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
<section id="ordini">
<%if(ordini!=null){ %>
<script src="JAVASCRIPT/alert.js"></script>
<select onChange="info3(this.value)">
<option> Scegli un ordine </option>
  <%Iterator<?> it = ordini.iterator();
     while(it.hasNext()){
     OrdineBean bean = (OrdineBean) it.next();%>
     	
  <option value="<%=bean.getCod_ordine()%>"> <%=bean.getCod_ordine()%></option>
  <%} } %>
  </select>
 <%if(ordine!=null){ %> 
<div class="ordine" align=center> 
 <form action="ordine?action=update" id="ordine" method="post" >
	     <input name="mod_pagamento" type="text"  required value="<%=ordine.getMod_pagamento() %>" hidden="true" >
	     <input name="corriere" type="text" required value="<%=ordine.getCorriere() %>" hidden="true" >
	     <input name="email" type="text" maxlength="40" required value="<%=ordine.getEmail() %>" hidden="true" ><br>
	     <input name="importo_tot" type="number"  required value="<%=ordine.getImporto_tot() %>"  hidden="true" ><br>
	     <input name="data_ordine" type="date"  required value="<%=ordine.getData_ord() %>"  hidden="true" ><br>	     
	     <input id="cod" name="cod_ordine" type="number" value="<%=ordine.getCod_ordine()%>" hidden="true" >
	     <label for="tracciamento">TRACCIAMENTO:</label><br>
	     <input name="tracciamento" maxlength="20" type="text" required  value="<%=ordine.getTracciamento()%>" ><br>
	     <label for="data_partenza">DATA PARTENZA:</label><br>
	     <input name="data_partenza" type="date" min="<%=ordine.getData_ord()%>" max=<%=new java.sql.Date(System.currentTimeMillis()) %> required value="<%=ordine.getData_partenza()%>" ><br>
         <label for="stato">STATO:</label><br>
	     <select name="stato" selected="<%=ordine.getStato() %>">
	     <option value="In elaborazione">In elaborazione</option>
	     <option value="In preparazione">In preparazione</option>
	     <option value="In spedizione">In spedizione</option>
	     <option value="Spedito">Spedito</option>
	     <option value="In transito">In transito</option>
	     <option value="In consegna">In consegna</option>
	     <option value="Consegnato">Consegnato</option>
	     <option value="In giacenza">In giacenza</option>
	     </select>
	     <br>
	     <br>
	     <input id="ord" type="submit" value="Aggiorna Ordine">
	     </form>
	     </div> 
<% }%>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>       