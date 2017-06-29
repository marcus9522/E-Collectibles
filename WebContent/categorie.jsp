<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% String cat = request.getParameter("cat");%>
    
<section id="categorie">

    <button><h1>CATEGORIE</h1></button>
            <p><a href="product2?action=categoria&cat=Actionfigure">Action Figure</a></p>
            <p><a href="product2?action=categoria&cat=Cartedagioco">Carte da gioco</a></p>
            <p><a href="product2?action=categoria&cat=Fumetti">Fumetti</a></p>
            <p><a href="product2?action=categoria&cat=Maglie">Maglie</a></p>
            <p><a href="product2?action=categoria&cat=Gadget">Gadget</a></p>
</section>