<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/fragments/head.jsp" %>

<body>

	<%@ include file="/WEB-INF/fragments/nav.html" %>

	<header>
		<%-- message confirmation ajout nouvel article  --%>
		<c:if test="${sessionScope.insertionReussie}">
			<br>
			<div class="alert alert-success" role="alert">
				L'article a été ajouté avec succès !
			</div>
			<br>
			<%-- réinitialisation l'attribut --%>
			<%
			session.removeAttribute("insertionReussie");
			%>
		</c:if>
		<div class="my-4 text-center">
			<h1>Liste des enchères</h1>
		</div>
	</header>


	<div class="container">
		<div class="card">
			<div class="card-body">
				<form action="/encheres" method="get" class="form-inline">
					<div class="row">
						<div class="col">
							<label for="nomArticleFiltre">Nom de l'article :</label> <input
								type="text" class="form-control" name="nomArticleFiltre"
								id="nomArticleFiltre">
						</div>
						<div class="col">
							<label for="categorieFiltre">Catégorie :</label> <select
								class="form-control" name="idCategorieFiltre"
								id="categorieFiltre">
								<option value="">Toutes les catégories</option>
								<option value="1">Informatique</option>
								<option value="2">Ameublement</option>
								<option value="3">Vêtement</option>
								<option value="4">Sport & Loisirs</option>
							</select>
						</div>
						<div class="col">
							<br>
							<button onclick="window.location.href='${pageContext.request.contextPath}/encheres'" type="submit" class="btn btn-primary btn-block">Rechercher</button>
						</div>
					</div>
				</form>
				<br>
				<c:if
					test="${ !empty sessionScope.identifiant && !empty sessionScope.userLoggedIn }">
					<div class="container">
						<div class="row justify-content-start">
							<div class="col-4">
								<input type="radio" name="filtres" id="achats" value="achats"
									onclick="onclickAchats()"> <label for="encheresencours">Achats</label><br>

								<input type="checkbox" name="encheresouvertes"
									id="encheresouvertes"> <label for="encheresencours">Enchères
									ouvertes</label><br> <input type="checkbox" name="encheresencours"
									id="encheresencours"> <label for="encheresencours"
									style="white-space: nowrap;">Mes enchères en cours</label><br>
								<input type="checkbox" name="encheresremportees"
									id="encheresremportees"> <label
									for="encheresremportees" style="white-space: nowrap;">Mes
									enchères remportées</label>
							</div>
							<div class="col-4">
								<input type="radio" name="filtres" id="ventes" value="ventes"
									onclick="onclickVentes()"> <label for="ventes">Mes
									ventes</label><br> <input type="checkbox" name="ventesencours"
									id="ventesencours"> <label for="ventesencours">Mes
									ventes en cours</label><br> <input type="checkbox"
									name="ventesnondebutees" id="ventesnondebutees"> <label
									for="ventesnondebutees">Ventes non débutées</label><br> <input
									type="checkbox" name="ventesterminees" id="ventesterminees">
								<label for="ventesterminees">Ventes terminéees</label>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	<br>

	<div class="container">
		<c:forEach var="article" items="${articles}">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">${article.nomArticle}</h5>
					<p class="card-text">Prix initial : ${article.prixInitial} points</p>
					<p class="card-text">Fin de l'enchère: ${article.dateFin}</p>
				</div>
			</div>
			<br>
		</c:forEach>
	</div>



	<script type="application/javascript">
		
		
		
    function onclickAchats(){
        let ventesencours = document.getElementById("ventesencours");
        ventesencours.checked = false;
        ventesencours.disabled = true;
        let ventesnondebutees = document.getElementById("ventesnondebutees");
        ventesnondebutees.checked = false;
        ventesnondebutees.disabled = true;
        let ventesterminees = document.getElementById("ventesterminees");
        ventesterminees.checked = false;
        ventesterminees.disabled = true;
        
        let encheresouvertes = document.getElementById("encheresouvertes");
        encheresouvertes.disabled = false;
        let encheresencours = document.getElementById("encheresencours");
        encheresencours.disabled = false;
        let encheresremportees = document.getElementById("encheresremportees");
        encheresremportees.disabled = false;
    }
    function onclickVentes(){
        let encheresouvertes = document.getElementById("encheresouvertes");
        encheresouvertes.checked = false;
        encheresouvertes.disabled = true;
        let encheresencours = document.getElementById("encheresencours");
        encheresencours.checked = false;
        encheresencours.disabled = true;
        let encheresremportees = document.getElementById("encheresremportees");
        encheresremportees.checked = false;
        encheresremportees.disabled = true;
        
        let ventesencours = document.getElementById("ventesencours");
        ventesencours.disabled = false;
        let ventesnondebutees = document.getElementById("ventesnondebutees");
        ventesnondebutees.disabled = false;
        let ventesterminees = document.getElementById("ventesterminees");
        ventesterminees.disabled = false;
    }
	
	
	</script>


	<%@ include file="/WEB-INF/fragments/footer.html" %>
</body>
</html>