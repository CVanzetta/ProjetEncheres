<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/fragments/head.jsp" %>
<body>

	<%@ include file="/WEB-INF/fragments/nav.html" %>
	
	<c:if test="${not empty user}">
	<br>
		<div class="d-flex justify-content-center">
			<div class="card">
  				<div class="card-body">
	<form action="ModifierProfil" method="post" accept-charset="UTF-8">
	<h5 class="card-title text-secondary">Modifier profil de  ${user.pseudo}</h5>
        <label for="nom"><strong>Nom :</strong></label>
        <input type="text" id="nom" name="nom" value="${user.nom}"><br>
        <label for="prenom"><strong>Prenom :</strong></label>
        <input type="text" id="prenom" name="prenom" value="${user.prenom}"><br>
        <label for="email"><strong>Email :</strong></label>
        <input type="text" id="email" name="email" value="${user.email}"><br>
        <label for="tel"><strong>Téléphone :</strong></label>
        <input type="text" id="tel" name="tel" value="${user.telephone}"><br>
        <label for="rue"><strong>Rue :</strong></label>
        <input type="text" id="rue" name="rue" value="${user.rue}"><br>
        <label for="cp"><strong>Code Postal :</strong></label>
        <input type="text" id="cp" name="cp" value="${user.code_postal}"><br>
        <label for="ville"><strong>Ville :</strong></label>
        <input type="text" id="ville" name="ville" value="${user.ville}"><br>
        <input type="submit" class="btn btn-secondary" value="Confirmer">
        </form>
        </div>
	   		</div>
	   	</div>
    </c:if>
    
<%@ include file="/WEB-INF/fragments/footer.html" %>
</body>
</html>