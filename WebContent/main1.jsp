<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,prodotto.ProductBean"%>
    
   <% Collection<?> products = (Collection<?>) request.getAttribute("prodotti");%>
    
<div class="main1">
    <button><h1>OFFERTE</h1></button><br>
     <p></p>
    <%		
            int i=0;
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()&& i<6) {
					ProductBean bean = (ProductBean) it.next();
					
		%>
		<%if(bean.getOfferta()==1){ i++;%>
		<div class="offerteDiv" >
		<div align=center><img alt="IMAGE NOT FOUND" src="<%=bean.getFoto()%>" width="60" height="80"  ></div><br>
		&nbsp &nbsp<a href="product2?action=prodotto&id=<%=bean.getCodice()%>" ><%=bean.getNome().toUpperCase() %>	  </a> &nbsp &nbsp
		<div align=center><strong><%=bean.getPrezzo() %>&euro;		</strong> </div>
		</div>

			<%} 
				} 
				}%>			
</div>