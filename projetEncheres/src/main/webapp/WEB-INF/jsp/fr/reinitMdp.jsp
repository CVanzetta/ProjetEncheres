<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html >
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reinitialisation mot de passe</title>
    <link rel="stylesheet" href="/css/style.css" >
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.min.js"></script>
</head>
<body>
   <%@ include file="/WEB-INF/fragments/head.jsp" %>  
    <div align="center">
        <h2>Reinitialisation de votre mot de passe</h2>
        <p>
        Veuillez saisir votre email de connexion, nous vous enverrons un nouveau mot de passe aléatoire dans votre boîte de réception :
        </p>
         
        <form id="resetForm" action="${pageContext.request.contextPath}/ReinitMdp" method="post">
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email" id="email" size="20"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button type="submit">Envoyez-moi un nouveau mot de passe</button>
                    </td>
                </tr>    
            </table>
        </form>
    </div>
     
     <%@ include file="/WEB-INF/fragments/footer.html" %>
    
</body>
</html>