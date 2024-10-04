<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/fragments/head.jsp" %>
<body>
	
	<%@ include file="/WEB-INF/fragments/navNoUser.html" %>
	
	<div class="my-4 text-center">
		<form method="post">
			<c:if test="${!empty listeCodesErreur}">
				<div class="alert alert-danger" role="alert">
				  <strong>Erreur!</strong>
				  <ul>
				  	<c:forEach var="code" items="${listeCodesErreur}">
				  		<li>${LecteurMessage.getMessageErreur(code)}</li>
				  	</c:forEach>
				  </ul>
				</div>
			</c:if>
	        <label for="login">Login:</label>
			<input type="text" id="login" name="login" value="${rememberedUser}" required><br><br>
	
	        
	        <label for="mdp">Mot de passe:</label>
	        <input type="password" id="mdp" name="mdp" required><br><br>
	        
	        <input type="submit" value="Se connecter">
	        <label for="rememberMe">Se souvenir de moi :</label>
			<input type="checkbox" name="rememberMe" id="rememberMe">
	    </form>
	    <br>
   		<button onclick="window.location.href='${pageContext.request.contextPath}/Inscription'">Créer un compte</button>
    </div>
    
    <%@ include file="/WEB-INF/fragments/footer.html" %>
</body>
</html>