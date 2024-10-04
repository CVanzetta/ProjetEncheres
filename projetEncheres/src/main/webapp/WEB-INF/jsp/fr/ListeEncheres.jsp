<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.projetencheres.bo.Enchere"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<input type="text" name="filtre"
				placeholder="Rechercher par nom d'article"> <select
				name="categorie">
				<option value="" disabled selected>Choisir une catégorie</option>
				<option value="informatique">Informatique</option>
				<option value="ameublement">Ameublement</option>
				<option value="vetement">Vêtement</option>
				<option value="sportLoisirs">Sport & Loisirs</option>
			</select>
			<button type="submit">Rechercher</button>
		</form>

		<!-- Afficher des filtres pour les utilisateurs connectés -->
		<c:if test="${sessionScope.userId != null}">
			<h2>Filtres :</h2>
			<div>
				<label> <input type="checkbox" name="achats" value="achats">
					Achats
				</label> <label> <input type="checkbox" name="ventes" value="ventes">
					Ventes
				</label>
			</div>
			<div id="achatsFilters">
				<h3>Achats :</h3>
				<label> <input type="checkbox" name="enchereOuverte"
					value="enchereOuverte"> Enchères ouvertes
				</label> <label> <input type="checkbox" name="mesEncheresEnCours"
					value="mesEncheresEnCours"> Mes enchères en cours
				</label> <label> <input type="checkbox" name="mesEncheresRemportees"
					value="mesEncheresRemportees"> Mes enchères remportées
				</label>
			</div>
			<div id="ventesFilters">
				<h3>Ventes :</h3>
				<label> <input type="checkbox" name="mesVentesEnCours"
					value="mesVentesEnCours"> Mes ventes en cours
				</label> <label> <input type="checkbox" name="ventesNonDebutees"
					value="ventesNonDebutees"> Ventes non débutées
				</label> <label> <input type="checkbox" name="ventesTerminees"
					value="ventesTerminees"> Ventes terminées
				</label>
			</div>
		</c:if>
	</div>
	<table>
		<tr>
			<th>Nom de l'article</th>
			<th>Prix actuel</th>
			<th>Date de fin</th>
		</tr>
		<c:forEach var="enchere" items="${encheres}">
			<tr>
				<td>${enchere.nomArticle}</td>
				<td>${enchere.montantEnchere}</td>
				<td>${enchere.dateEnchere}</td>
			</tr>
		</c:forEach>
		<%@ include file="/WEB-INF/fragments/footer.html"%>
</body>
</html>

