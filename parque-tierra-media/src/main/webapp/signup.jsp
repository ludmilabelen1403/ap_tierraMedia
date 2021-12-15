<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>
<jsp:include page="partial/head.jsp"/>

<h3>Registro de usuario</h3>
<form method="post" action="signup">
  <div class="form-row">
  <div class="form-group col-md-6">
      <label for="nombre">Nombre</label>
      <input type="text" class="form-control" id="nombre" name="nombre">
    </div>
   
    <div class="form-group col-md-6">
      <label for="inputPassword4">Password</label>
      <input type="password" class="form-control" id="inputPassword4" name="contraseña">
    </div>
  
  
  
    <div class="form-group col-md-4">
      <label for="provincia">Provincia</label>
      <select id="provincia" class="form-control" name="provincia">
        <option selected>Tierra Del Fuego</option>
        <option>Buenos Aires</option>
        <option>Santa Cruz</option>
        <option>Rio Negro</option>
        <option>La Pampa</option>
      </select>
    </div>
    <div class="form-row">
    <div class="form-group col-md-6">
      <label for="ciudad">Ciudad</label>
      <input type="text" class="form-control" id="ciudad" name="ciudad">
    </div>
    </div>
  <div class="form-group">
    <label for="domicilio">Domicilio</label>
    <input type="text" class="form-control" id="domicilio" placeholder="ejemplo: callle 133bis y avenida 44" name="domicilio">
  </div>
    <div class="form-group col-md-2">
      <label for="presupuesto">Presupuesto disponilbe</label>
      <input type="number" class="form-control" id="presupuesto" name="presupuesto">
    </div>
        <div class="form-group col-md-2">
      <label for="tiempo">Tiempo estimado disponible</label>
      <input type="number" class="form-control" id="tiempo" name="tiempo">
    </div>
  </div>
  
  </div>
       <div class="form-group col-md-4">
      <label for="preferencia"><p>Durante mi visita a la tierra media, me gustaría vivir una experiencia de tipo...</p></label>
      <select id="preferencia" class="form-control" name="preferencia">
        <option selected>AVENTURA</option>
        <option>DEGUSTACION</option>
        <option>PAISAJE</option>
        
      </select>
    </div>

<!-- <div> -->
<!--   <input type="radio" id="aventura" name="preferencia" value="1" -->
<!--          checked> -->
<!--   <label for="aventura">Aventura</label> -->
<!-- </div> -->

<!-- <div> -->
<!--   <input type="radio" id="paisaje" name="preferencia" value="2"> -->
<!--   <label for="paisaje">Paisaje</label> -->
<!-- </div> -->

<!-- <div> -->
<!--   <input type="radio" id="degustacion" name="preferencia" value="3"> -->
<!--   <label for="degustacion">Degustacion</label> -->
<!-- </div> -->
  </div>
  <button type="submit" class="btn btn-primary">Registrarse</button>
</form>
</body>
</html>
