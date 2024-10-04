<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/fragments/head.jsp" %>

<body>

	<%@ include file="/WEB-INF/fragments/nav.html" %>

	<header>
		<div class="my-4 text-center">
			<h1>Liste des enchères</h1>
		</div>
	</header>

	<div class="container">
		<form>
			<input type="text" id="filtrer" name="filtrer" placeholder="Je recherche..." ><br><br>
			<select name="select" id="select">
				<option value="" disabled selected>Choisir une catégorie</option>
				<option value="informatique">Informatique</option>
				<option value="ameublemen">Ameublement</option>
				<option value="vêtement">Vêtement</option>
				<option value="sportLoisirs">Sport & Loisirs</option>
			</select><br><br>
			<button type="submit">Rechercher</button>
			
		</form>
	</div>
	
	
	<br>

	<c:if test="${ !empty sessionScope.identifiant && !empty sessionScope.userLoggedIn }">
        <p><strong>Pseudo:</strong> <c:out value="${user.pseudo}" /></p>
        <p><strong>ID:</strong> <c:out value="${ID}" /></p>
	
		
	</c:if>


		
    


	<%@ include file="/WEB-INF/fragments/footer.html" %>
</body>
</html>