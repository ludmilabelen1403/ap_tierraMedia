<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${flash != null}">
				<div class="alert alert-danger">
					<p>
						<c:out value="${flash}" />
					</p>
				</div>
			</c:if>

<form method="post" action="login">

<label for="username">Usuario</label>
<input id="username" type="text" name="username"></input>

<label for="password">Contraseña</label>
<input id="password" type="password" name="password"></input>

<input type="submit" value="ingresar"></input>
</form>

</body>
</html>



