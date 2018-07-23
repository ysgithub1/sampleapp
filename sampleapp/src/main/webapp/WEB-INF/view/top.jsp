<!DOCTYPE html>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%> 
<html>
	<head>
 		<meta charset="utf-8">
			<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
			<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
			<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
 		<title>トップ</title>
 	</head>
 <body>	

	<p>${containerstart}</p>
	<p>${menu}</p>
	<p>${contentsstart}</p>
      表示したいコンテンツを記載	
	<p>${contentsend}</p>
	<p>${containerend}</p>
<!-- ようこそ -->
<%--  	ようこそ<c:out value="${loginName}" />さん --%>

<%-- 	HttpSession: <c:out value="${sessionScope.session1}" /><br>
	WebRequest: <c:out value="${sessionScope.session2}" /><br>
 --%>
 <!-- 	<div class="container"> -->
<!-- 		<div class="row"> -->
<!-- 			<div class="col-md-4">　 -->
<%-- 				<f:form name="display1" action="display1" method="get"> --%>
<!-- 					<a href="javascript:document.display1.submit();">表示1</a> -->
<%-- 			 	</f:form> --%>
<!-- 				<br> -->
<%-- 				<f:form name="display2" action="display2" method="get"> --%>
<!-- 					<a href="javascript:document.display2.submit();">表示2</a> -->
<%-- 			 	</f:form> --%>
<!-- 				<br> -->
<%-- 				<f:form name="logout" action="logout" method="get"> --%>
<!-- 					<a href="javascript:document.logout.submit();">ログアウト</a> -->
<%-- 			 	</f:form> --%>
<!-- 				<br> -->
<!-- 			</div> -->
				
<!-- 				<div class="col-md-8"> -->
<%-- 		 			ようこそ<c:out value="${userName}" />さん --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
 





 </body>
</html>