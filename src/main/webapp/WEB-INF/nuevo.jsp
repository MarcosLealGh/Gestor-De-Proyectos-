<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nuevo Proyecto</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Nuevo Proyecto</h1>
		<form:form action="/crear" method="post" modelAttribute="proyecto">
			<div>
				<form:label path="titulo">Titulo del Proyecto</form:label>
				<form:input path="titulo" class="form-control" />
				<form:errors path="titulo" class="text-danger" />
			</div>
			
			<div>
				<form:label path="descripcion">Descripción del Proyecto</form:label>
				<form:textarea path="descripcion" class="form-control" />
				<form:errors path="descripcion" class="text-danger" />
			</div>
			
			<div>
				<form:label path="fecha">Fecha de Entrega</form:label>
				<form:input type="date" path="fecha" class="form-control" />
				<form:errors path="fecha" class="text-danger" />
			</div>
			
			<!--Quien creo el proyecto -->
			<form:hidden path="lider" value="${usuarioEnSesion.id}"/>
			<!--Quien creo el proyecto -->
			<input type="submit" class="btn btn-success" value="Guardar" />
		</form:form>
	
	
	
	</div>

</body>
</html>