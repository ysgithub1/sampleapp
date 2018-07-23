<!DOCTYPE html>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%> 
<html >
<!-- 	<head> -->
<!-- 		<meta charset="utf-8"> -->

		<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
		<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
		<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>


		<!-- bootstrap datepicker -->
		${script}
		<script src="<c:url value="/resources/bootstrap/js/bootstrap-datepicker.js" />"></script>
		<script src="<c:url value="/resources/bootstrap/locales/bootstrap-datepicker.ja.min.js" />"></script>

		<script>
		$(function() {
			//date picker
			$("#dp").datepicker({
				format: 'yyyymmdd',
				langage: 'ja'
			});

		})
		</script>
	<p>${head}</p>
<!-- 		<title>表示2</title> -->
<!-- 	</head> -->
	<body>
		<p>${containerstart}</p>
		<p>${titleheader}</p>
		<p>${menu}</p>
		<p>${contentsstart}</p>
		


		<div style="display:inline-flex">
<!-- 		<div class="form-container"> -->
<!-- 			<div class="form-a-container"> -->
			<f:form  modelAttribute="Display2Form" action="order" method="post">
				<label>開始日付：</label><input id="deliveryDateFrom" name="deliveryDateFrom" maxlength="8" type="text"></input>
				～ 
				<label>終了日付：</label><input id="deliveryDateTo" name="deliveryDateTo" maxlength="8" type="text"></input>
 				<f:errors path="deliveryDateFrom" element="div" cssStyle="color:red" />
  				<f:errors path="deliveryDateTo" element="div" cssStyle="color:red" />
				<br/>
 				<input type="submit" name="orderGet" value="注文表示">
				<input type="submit" name="orderAdjustment" value="注文調整">

			</f:form>
<!-- 			</div> -->
		</div>
<!-- 			<div class="form-b-container"> -->
<%-- 				<f:form name="orderAdjustment" action="orderAdjustment" method="post"> --%>
<!-- 					<input type="submit" name="orderAdjustment" value="注文調整"> -->
<%-- 				</f:form> --%>
<!-- 			</div> -->
  		
 		
 		<p>${orderlist}</p>
 

<!-- 		<button type="button" class="btn btn-primary" data-toggle="collapse"  -->
<!-- 						data-target="#collapseExample" aria-expanded="false"  -->
<!-- 						aria-controls="collapseExample"> -->
<!--     		クリックしてね（buttonタグ) -->
<!-- 		</button> -->
<!-- 		<div class="collapse" id="collapseExample"> -->
<!--     		<div class="border p-3"> -->
<!--        		 表示されました！ -->
<!--     		</div> -->
<!-- 				<table class="table"> -->
<!-- 				    <thead> -->
<!-- 				        <tr> -->
<!-- 				            <th>#</th> -->
<!-- 				            <th>First Name</th> -->
<!-- 				            <th>Last Name</th> -->
<!-- 				            <th>Username</th> -->
<!-- 				        </tr> -->
<!-- 				    </thead> -->
<!-- 				    <tbody> -->
<!-- 				        <tr> -->
<!-- 				            <th>1</th> -->
<!-- 				            <td>Hanako</td> -->
<!-- 				            <td>Qita</td> -->
<!-- 				            <td>@Hanaq</td> -->
<!-- 				        </tr> -->
<!-- 				        <tr> -->
<!-- 				            <th>2</th> -->
<!-- 				            <td>Taro</td> -->
<!-- 				            <td>Qita</td> -->
<!-- 				            <td>@TaroQ</td> -->
<!-- 				        </tr> -->
<!-- 				        <tr> -->
<!-- 				            <th>3</th> -->
<!-- 				            <td>john</td> -->
<!-- 				            <td>Qita</td> -->
<!-- 				            <td>@johnQ</td> -->
<!-- 				        </tr> -->
<!-- 						<tr> -->
<!-- 				            <th>4</th> -->
<!-- 				            <td>stela</td> -->
<!-- 				            <td>Qita</td> -->
<!-- 				            <td>@stelaQ</td> -->
<!-- 				        </tr>				     -->
<!-- 					</tbody> -->
<!-- 				</table> -->
<!-- 		</div> -->
		
		<p>${contentsend}</p>
		<p>${containerend}</p>
		
		
<%-- 	 	ログインユーザー：<c:out value="${sessionScope.session2}" /><br> --%>


<!-- 		<div class="input-group date" data-provide="datepicker"> -->
<!--    			<input type="text" class="form-control"> -->
<!--    			<div class="input-group-addon"> -->
<!--        			<span class="glyphicon glyphicon-th"></span> -->
<!--     		</div> -->
<!-- 		</div> -->
	</body>
</html>
