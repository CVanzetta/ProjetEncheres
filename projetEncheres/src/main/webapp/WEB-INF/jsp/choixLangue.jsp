<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<%@ include file="/WEB-INF/fragments/head.jsp" %>

<body>

<nav  class="navbar bg-secondary" data-bs-theme="dark">
		<div class="container-fluid">
			<span class="navbar-brand mb-0 h1"><a href="${pageContext.request.contextPath}/encheres"><img src="${pageContext.request.contextPath}/resources/logo.png" alt="Logo" class="img-fluid rounded-circle" style="max-width: 100px; max-height: 80px;"></a>			
			ENI-ENCHÈRES</span>
			<div class="d-flex justify-content-start">
			</div>
		</div>
	</nav>

	<header>
		<div class="my-4 text-center">
			<h1>Choix langue / Choice language</h1>
		</div>
	</header>

	<div class="container d-flex justify-content-center">
		<span class="navbar-brand mb-0 h1"><a href="${pageContext.request.contextPath}/encheres"><img src="${pageContext.request.contextPath}/resources/fr.png" alt="FR" class="img-fluid" style="max-width: 100px; max-height: 80px;"></a>			
			Français</span>
		<span class="navbar-brand mb-0 h1"><a href="${pageContext.request.contextPath}/encheres"><img src="${pageContext.request.contextPath}/resources/en.png" alt="EN" class="img-fluid" style="max-width: 100px; max-height: 80px;"></a>			
			English</span>
	</div>
	
	
	<br>

	<%@ include file="/WEB-INF/fragments/footer.html" %>
</body>
</html>