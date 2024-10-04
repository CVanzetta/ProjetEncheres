<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/fragments/head.jsp" %>

<body>

	<%@ include file="/WEB-INF/fragments/nav.html" %>
	<c:if test="${not empty erreurs}">
		<div class="alert alert-danger">
			<ul>
				<c:forEach var="erreur" items="${erreurs}">
					<li>${erreur}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty requestScope.listeCodesErreur}">
        <div class="alert alert-danger">
            ${requestScope.listeCodesErreur}
        </div>
    </c:if>

	
	<c:if
		test="${ !empty sessionScope.identifiant && !empty sessionScope.userLoggedIn }">
	<div class="my-4 text-center">
	    <h1 class="text-secondary">Nouvelle vente</h1>
	</div>
	<div class="d-flex justify-content-center">
		
		<form action=NouvelleVente method="post" class="well form-horizontal">
			<div class="saisie">
				<label for="nomarticle">Article :</label>
	       		<input type="text" id="nomarticle" name="nomarticle" required>	
			</div>	
			<br>
			<div class="saisie">
				<label for="description" class="description-texte">Description : </label>
				<textarea rows="5" cols="30" id="description" name="description" required><%=request.getParameter("description")!=null?request.getParameter("description"):""%></textarea>
			</div>
			<br>
			<div class="saisie">
				<label for="categorie">Catégorie : </label>
				<select name="categorie" id="categorie" required>
					<option value="" disabled selected>Choisir une catégorie</option>
					<option value="1">Informatique</option>
					<option value="2">Ameublement</option>
					<option value="3">Vêtement</option>
					<option value="4">Sport & Loisirs</option>
				</select>
			</div>
			<br>
			<div class="saisie">
				<label for="miseaprix">Mise à prix : </label> 
				<input type="number" name="miseaprix" id="miseaprix" min="0" required>
			</div>
			<br>
			<div class="saisie">
				<label for="datedebut">Début de l'enchère :</label>
				<input type="date" name="datedebut" id="datedebut" required value="<%=request.getParameter("datedebut")%>"/>
			</div>
			<br>
			<div class="saisie">
				<label for="datefin">Fin de l'enchère :</label>
				<input type="date" name="datefin" id="datefin" required value="<%=request.getParameter("datefin")%>"/>
			</div>
			<br>
			<div class="card">
  				<div class="card-body">
  					<h5 class="card-title text-secondary">Retrait</h5>
					<label for="rue">Rue :</label>
		       		<input type="text" id="rue" name="rue" value="${user.rue}"><br><br>
					<label for="codepostal">Code postal :</label>
		       		<input type="text" id="codepostal" name="codepostal" value="${user.code_postal}"><br><br>
					<label for="ville">Ville :</label>
		       		<input type="text" id="ville" name="ville" value="${user.ville}"><br>
            	</div>
            </div>
			<br>
			
			<div class="d-flex justify-content-evenly">
				<input type="submit" value="Enregistrer" class="btn btn-secondary"/>
				<input type="button"  class="btn btn-secondary" value="Annuler" onclick="window.location.href='${pageContext.request.contextPath}/encheres'">
			</div>
			<br>
		</form>
		
	</div>
	</c:if>
	<c:if
		test="${empty sessionScope.identifiant && empty sessionScope.userLoggedIn }">
		<div class="d-flex justify-content-center align-items-center vh-100">
			<div class="text-center">
				<h1 class="text-secondary">Nouvelle vente</h1>
				<p>Vous devez vous connecter pour avoir accès au contenu de
					cette page.</p>
			</div>
		</div>
	</c:if>

	<%@ include file="/WEB-INF/fragments/footer.html" %>
</body>
</html>