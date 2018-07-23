<!DOCTYPE html>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%> 
<html >
		${script}

	<p>${head}</p>
<!-- 		<title>表示2</title> -->
<!-- 	</head> -->
	<body>
		<p>${containerstart}</p>
		<p>${titleheader}</p>
		<p>${menu}</p>
		<p>${contentsstart}</p>

			<form:select path="state">
   			<form:options items="${states}" />
			</form:select>

		
 		<p>${resultlist}</p>
		<p>${contentsend}</p>
		<p>${containerend}</p>
		
		

	</body>
</html>