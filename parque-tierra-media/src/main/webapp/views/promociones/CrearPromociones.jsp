<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Promociones</title>
</head>
<body>
	<jsp:useBean id="servicio" class="service.CreatePromocionesService" />

	<main>

		<h1>Cree su promoción aquí.</h1>

		<form method="post" action="">

			<div class="input-group mb-3">
				<button class="btn btn-outline-secondary" type="submit">seleccionar</button>
				<select class="form-select" id="inputGroupSelect03"
					aria-label="Example select with button addon">
					<option selected>Parque</option>
					<option value="1">Aventura</option>
					<option value="2">Degustación</option>
					<option value="3">Paisaje</option>
				</select>
			</div>

		</form>

		<div class="input-group mb-3">
			<label class="input-group-text" for="inputGroupSelect01">Options</label>
			<select class="form-select" id="inputGroupSelect01">
				<option selected>Atracción</option>
				<c:forEach items="${atrccs}" var="atrcc">
					<option><c:out value="${atrcc.nombre}"></c:out></option>
					
					
					</c:forEach>
			</select>
		</div>

		<div class="input-group mb-3">
			<select class="form-select" id="inputGroupSelect02">
				<option selected>Choose...</option>
				<option value="1">One</option>
				<option value="2">Two</option>

				<option value="3">Three</option>
			</select> <label class="input-group-text" for="inputGroupSelect02">Options</label>
		</div>


		<div class="input-group">
			<select class="form-select" id="inputGroupSelect04"
				aria-label="Example select with button addon">
				<option selected>Choose...</option>
				<option value="1">One</option>
				<option value="2">Two</option>
				<option value="3">Three</option>
			</select>
			<button class="btn btn-outline-secondary" type="button">Button</button>
		</div>
		
		<c:forEach items="${atrccs}" var="attraction">
					<tr>
						<td><strong><c:out value="${attraction.name}"></c:out></strong>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Cras pretium eros urna. Sed quis erat congue, bibendum tortor
								malesuada, iaculis diam. Ut ut imperdiet sapien.</p></td>
	</c:forEach>
	</main>

</body>
</html>