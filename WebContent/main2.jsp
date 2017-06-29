<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,prodotto.ProductBean"%>
    
   <% Collection<?> products2 = (Collection<?>) request.getAttribute("prodotti");%>
<div class="main2">
    <button><h1>ALTRI PRODOTTI</h1></button><br>
    <p></p>
		    <%		
            int j=0;
			if (products2 != null && products2.size() != 0) {
				Iterator<?> it = products2.iterator();
				while (it.hasNext()&& j<6) {
					ProductBean bean = (ProductBean) it.next();
		%>
		<%if(bean.getOfferta()==0){j++; %>
		<div class="offerteDiv" >
		<div align=center><img alt="IMAGE NOT FOUND" src="<%=bean.getFoto()%>" width="60" height="80" ></div><br>
		&nbsp &nbsp<a href="product2?action=prodotto&id=<%=bean.getCodice()%>" ><%=bean.getNome().toUpperCase() %>	  </a> &nbsp &nbsp
		<div align=center><strong><%=bean.getPrezzo() %>&euro;  		</strong> </div>
		</div>

			<%} 
				} 
				}%>	
</div>