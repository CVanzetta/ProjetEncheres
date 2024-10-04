<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/fragments/head.jsp" %>

<body>

	<%@ include file="/WEB-INF/fragments/nav.html" %>
	
	<c:if
		test="${ !empty sessionScope.identifiant && !empty sessionScope.userLoggedIn }">
	<div class="container">
		<p>User : ${ sessionScope.identifiant }</p>
		
      <h1>Liste des Utilisateurs inscrits</h1>
	  <hr>	 
	   <table class="table table-condensed">
	        <tr>
	            <th>Pseudo</th>
	            <th>Nom</th>
	            <th>Prénom</th>
	            <th>Email</th>
	            <th>Téléphone</th>
	            <th>Rue</th>
	            <th>Code Postal</th>
	            <th>Ville</th>
	        </tr>
	        
	        <c:forEach var="users" items="${usersList}">
	            <tr>
	                <td>${users.pseudo}</td>
	                <td>${users.nom}</td>
	                <td>${users.prenom}</td>
	                <td>${users.email}</td>
	                <td>${users.telephone}</td>
	                <td>${users.rue}</td>
	                <td>${users.code_postal}</td>
	                <td>${users.ville}</td>
	            </tr>
	        </c:forEach>
	    </table>
	    <br>
	  <p>Rentrer le pseudonyme de l'utilisateur dont vous souhaitez afficher spécifiquement le profil !</p>  
	  <form method="post">
			<label for="utilisateur">Utilisateur :&nbsp;</label>
			<input type="text" id="utilisateur" name="utilisateur" required/>
			<input type="submit" value="Rechercher" />
		</form>
	</div>
	</c:if>
	
	<c:if
		test="${empty sessionScope.identifiant && empty sessionScope.userLoggedIn }">
		<div class="my-4 text-center">
			<h1>Liste des Utilisateurs inscrits</h1>
			<p>Vous devez vous connecter pour avoir accès au contenu de cette page.</p>
		</div>
	</c:if>

	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>