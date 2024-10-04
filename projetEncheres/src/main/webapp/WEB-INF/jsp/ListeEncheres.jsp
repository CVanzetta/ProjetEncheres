<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.bo.Article"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/fragments/head.jsp"%>
<body>
	<header>
		<%@ include file="/WEB-INF/fragments/nav.html"%>
		<h1>Liste des Enchères en Cours</h1>
	</header>
	<div>
		<form action="ServletListeEnchere" method="get">
			<input type="text" name="nomArticle"
				placeholder="Rechercher par nom d'article"> <select
				name="categorie">
				<option value="" disabled selected>Choisir une catégorie</option>
				<option value="informatique">Informatique</option>
				<option value="ameublement">Ameublement</option>
				<option value="vetement">Vêtement</option>
				<option value="sportLoisirs">Sport & Loisirs</option>
			</select>
			<button type="submit">Rechercher</button>
			<% if (request.getAttribute("aucunResultat") != null) { %>
    <p><%= request.getAttribute("aucunResultat") %></p>
<% } %>
		</form>
	</div>
	<table>
		<thead>
			<tr>
				<th>No Article</th>
				<th>Nom Article</th>
				<th>Description</th>
				<th>Date Debut</th>
				<th>Date Fin</th>
				<th>Prix Initial</th>
				<th>Prix Vente</th>
				<th>No Utilisateur</th>
				<th>No Categorie</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${encheres}" var="article">
				<tr>
					<td>${article.noArticle}</td>
					<td>${article.nomArticle}</td>
					<td>${article.description}</td>
					<td>${article.dateDebut}</td>
					<td>${article.dateFin}</td>
					<td>${article.prixInitial}</td>
					<td>${article.prixVente}</td>
					<td>${article.noUtilisateur}</td>
					<td>${article.noCategorie}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>

