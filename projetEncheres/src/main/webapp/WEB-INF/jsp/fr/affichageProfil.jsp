<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/fragments/head.jsp" %>
<body>

	<%@ include file="/WEB-INF/fragments/nav.html" %>
	
	<h1 class="my-4 text-center">Vous avez choisi l'utilisateur suivant :</h1>
	
	<div class="d-flex justify-content-center">
		<div class="card">
			<div class="card-body">
				<c:if test="${not empty utilisateur}">
					<p>
						<strong>Pseudo:</strong>
						<c:out value="${utilisateur.pseudo}" />
					</p>
					<p>
						<strong>Nom:</strong>
						<c:out value="${utilisateur.nom}" />
					</p>
					<p>
						<strong>Prénom:</strong>
						<c:out value="${utilisateur.prenom}" />
					</p>
					<p>
						<strong>Email:</strong>
						<c:out value="${utilisateur.email}" />
					</p>
					<p>
						<strong>Téléphone:</strong>
						<c:out value="${utilisateur.telephone}" />
					</p>
					<p>
						<strong>Rue:</strong>
						<c:out value="${utilisateur.rue}" />
					</p>
					<p>
						<strong>Code Postal:</strong>
						<c:out value="${utilisateur.code_postal}" />
					</p>
					<p>
						<strong>Ville:</strong>
						<c:out value="${utilisateur.ville}" />
					</p>
				</c:if>
			</div>
		</div>
	</div>
	<c:if test="${empty utilisateur}">
		<p>
			<strong>Ce pseudo n'existe pas</strong>
		</p>
	</c:if>
	
	<div class="my-4 text-center">
	<a href="${pageContext.request.contextPath}/encheres">Retour</a>
	</div>
    
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
</body>
</html>