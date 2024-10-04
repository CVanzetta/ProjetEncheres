<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/fragments/head.jsp" %>
<body>

	<%@ include file="/WEB-INF/fragments/navNoUser.html" %>
	
	<div class="my-4 text-center">
	    <h1 class="text-secondary">Inscription</h1>
	    
	    <form action="Inscription" method="post">
	    
	        <label for="pseudo">Pseudo :</label>
	        <input type="text" id="pseudo" name="pseudo" pattern="^[a-zA-Z0-9]+$" required><br><br>
	        
	        <label for="nom">Nom :</label>
	        <input type="text" id="nom" name="nom" required><br><br>
	        
	        <label for="prenom">Prénom :</label>
	        <input type="text" id="prenom" name="prenom" required><br><br>
	        
	        <label for="email">Email :</label>
	        <input type="email" id="email" name="email" required><br><br>
	        
	        <label for="telephone">Téléphone :</label>
	        <input type="text" id="telephone" name="telephone"><br><br>
	
	        <label for="codePostal">Code Postal :</label>
	        <input type="text" id="codePostal" name="codePostal" required pattern="^\d{5}$"><br><br>
	
	        <label for="ville">Ville :</label>
	        <input type="text" id="ville" name="ville" required><br><br>
	
	        <label for="rue">Rue :</label>
	        <input type="text" id="rue" name="rue" required><br><br>
	
	        <label for="motDePasse">Mot de passe :</label>
	        <input type="password" id="motDePasse" name="motDePasse" required><br><br>
	
	        <label for="confirmationMotDePasse">Confirmer le mot de passe :</label>
	        <input type="password" id="confirmationMotDePasse" name="confirmationMotDePasse" required><br><br>
	
	        <input type="submit" value="Créer"  class="btn btn-secondary">
	        <input type="button" value="Annuler" onclick="window.location.href='${pageContext.request.contextPath}/encheres'"  class="btn btn-secondary">
	            
	        <%-- Affichage des erreurs --%>
	        <c:if test="${not empty erreurs}">
	            <div class="erreur">
	                <c:forEach var="erreur" items="${erreurs}">
	                    ${erreur}
	                </c:forEach>
	            </div>v 
	        </c:if>
	        <% String inscriptionErreur = (String) request.getAttribute("inscriptionErreur"); %>
	<% if (inscriptionErreur != null) { %>
	    <p><%= inscriptionErreur %></p>
	<% } %>
	        
	        <%-- Fin de l'affichage des erreurs --%>
	    </form>
	</div>
	<%@ include file="/WEB-INF/fragments/footer.html" %>
	
</body>
</html>
