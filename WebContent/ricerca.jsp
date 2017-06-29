<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,prodotto.ProductBean"%>
    <% Collection<?> products = (Collection<?>) request.getAttribute("prodotti");
       String enter= (String) session.getAttribute("enter");%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati ricerca</title>
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
<section id="ricerca">
<div class="ricerca">
<br>
 <%if (products.size()!=0){ %>
 <%		
            int i=0;
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
					i++;
		%>
		<div class="offerteDiv" >
		<div align=center><img alt="IMAGE NOT FOUND" src="<%=bean.getFoto()%>" width="60" height="80"  ></div><br>
		&nbsp &nbsp<a href="product2?action=prodotto&id=<%=bean.getCodice()%>" ><%=bean.getNome().toUpperCase() %>	  </a> &nbsp &nbsp
		<div align=center><strong><%=bean.getPrezzo() %>&euro;</strong> </div>
		</div>
<%} } }
 else{%>
 <br>
 <div align=center><h1>Nessun Prodotto trovato</h1></div>
 <%} %>		
 </section>
 </div>
 <%@ include file="footer.jsp" %>	
 
</body>
</html>