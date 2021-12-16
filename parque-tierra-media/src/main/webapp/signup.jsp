<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<jsp:include page="partials/head.jsp" />

	<h3>Registro de usuarie</h3>
	<form method="post" action="signup">
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="nombre">Nombre</label> <input type="text"
					class="form-control" id="nombre" name="nombre">
			</div>

			<div class="form-group col-md-6">
				<label for="inputPassword4">Password</label> <input type="password"
					class="form-control" id="inputPassword4" name="contraseña">
			</div>


			<div class="form-group col-md-2">
				<label for="presupuesto">Presupuesto disponible</label> <input
					type="number" class="form-control" id="presupuesto"
					name="presupuesto">
			</div>


			<div class="form-group col-md-2">
				<label for="tiempo">Tiempo estimado disponible</label> <input
					type="number" class="form-control" id="tiempo" name="tiempo">
			</div>


		</div>

		<div class="form-group col-md-4">
			<label for="preferencia"> Durante mi visita a la tierra
					media, me gustaría vivir una experiencia de tipo..</label> 
					<select	id="preferencia" class="form-control" name="preferencia">
				<option selected>AVENTURA</option>
				<option>DEGUSTACION</option>
				<option>PAISAJE</option>

			</select>
		</div>

	
		<button type="submit" class="btn btn-primary">Registrarse</button>
	</form>
</body>
</html>
