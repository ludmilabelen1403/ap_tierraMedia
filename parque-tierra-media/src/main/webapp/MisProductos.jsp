<%@page import="model.Atraccion"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="org.apache.taglibs.standard.tag.el.core.ForEachTag"%>
 <%@ page import = "java.io.*"%>
 <%@ page import = "java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mis productos adquiridos</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville:ital@1&display=swap" rel="stylesheet">
<link href="styleProductos.css" rel="stylesheet" type="text/css">
</head>
<body>
<img alt="" src="https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/media/image/2019/09/10-parques-atracciones-espectaculares-mundo-puedes-visitar_10.jpg">
<h1>Mis productos</h1>
<p class= "titulo1"> mis atracciones </p>
<p class= "atracciones"> ${nombre_atraccion}</p>
<br>
<br>
<br>
<p class="titulo2">mis promociones</p>
<p class="promociones">${promociones}</p>

<br>
<a href="index.jsp"> <button> volver </button></a>
</body>
</html>