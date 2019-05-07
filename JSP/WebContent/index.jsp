
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ecodeup</title>
</head>
<body>
	<c:out value="Bienvenido a ecodeup, el blog de Java y Java Web"></c:out><br>
	<c:out value="esto es un mensaje"></c:out><br>
	<c:out value="Hola mundo con JSTL"></c:out><br>
	<jsp:useBean id="fecha" class="java.util.Date" scope="page" />
	<c:set var="variable" value="${fecha}"></c:set>
	<c:out value="FECHA ACTUAL:"></c:out>
	<c:out value="${variable}"></c:out>
	<c:set var="contador" value="${7}"></c:set><br>
	<c:out value="CONTADOR:"></c:out>
	<c:out value="${ contador}"></c:out><br>
	
	<c:out value="${header['User-Agent']}"></c:out>
</body>
</html>