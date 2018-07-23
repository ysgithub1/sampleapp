<!DOCTYPE html>
  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- 非同期 -->
<%@ page import="component.Console" %>
<% Console.println("Called complete.jsp"); %>
<% Console.println(request.getDispatcherType()); %>


<html>
 <head>
 		<link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
 		<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
 
 <meta charset="utf-8">
 <title>ログイン</title>
 </head>
 <body>
 	<form:form modelAttribute="loginForm" action="login" method="post">


 		<div>
 		ユーザID：<input type="text" id="userId" name="userId">
 		<form:errors path="userId" element="div" cssStyle="color:red" />
 		</div>
 		<div>
 		
 		パスワード：<input type="password" id="loginPassword" name="loginPassword">
 		<form:errors path="loginPassword" element="div" cssStyle="color:red" />
 		</div>
 		<div>
 		<input type="submit" value="ログイン">
 		</div>
 	</form:form>
 	

 	<form:form  action="loading" method="post">

 		<input type="submit" id="indicator" onclick="Start_Ajax()" value="インジケータ表示">
 	</form:form>

  <!--しばらくお待ち下さい メッセージ-->
 	<div id="Loading" style="display:none;">
        <img src="/sampleapp/resources/img/loading.gif" class="loadimg">
        <p class="loadmsg">
          処理中...<br>
          しばらくお待ち下さい。
        </p>
  </div>
  
	  <form:form action="property" method="get" class="form-control">
         <input type="submit" value="設定ファイルを読み込みます。" />
         <p>${message}</p>
	 </form:form>
	 
	 
	 
	<form:form action="upload/finish" method="POST" enctype="multipart/form-data" >
	<table class="data-col">
	 <tr>
	  <th>ファイル<br></th>
	  <td><input type="file" name="file"/></td>
	 </tr>
	 
	</table>
	<br>
	 <input type="submit" value="送信" />
	</form:form>
	 
	 
	<spring:url value="/upload" var="url" htmlEscape="true" />
	<form:form name="up" action="${url}" method="POST"
		enctype="multipart/form-data">

		<input type="file" name="file">
		<br>
		<input type="submit" value="送信">
	</form:form>
	${msg}
 	
 	
	  <form:form action="excel" method="get" class="form-control">
         <input type="submit" value="エクセルファイルを読み込みます。" />
         <p>${excel}</p>
	 </form:form>

 	
	<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
  	<script>
  	$(function() {  //以下コントローラーに値を送る処理です
      $("#lesson").change(function() {
          $.ajax({  //これを使ってajax通信を行っている？
              type : "POST",
              url : "user", // リクエストURL
              dataType : "text",
              data : "lesson=" + $("#lesson").val(),//送る値
              success: function(){ //通信に成功したときに表示されるアラートです
                  alert("ok");
                  console.log(lesson);
              },
              error: function(){
                  alert("値はコンソール参照");
                  console.log(lesson);
              }

          	});
      	});
  	});
  
  
  
  /* 実行ボタン押下時の関数 */
  function Start_Ajax(){
// 	document.getElementById("Loading").style.display = "block";  //ﾒｯｾｰｼﾞのDIV要素をｽﾀｲﾙｼｰﾄのdisplayﾌﾟﾛﾊﾟﾃｨで非表示から表示へ変更します
//     document.getElementById("Loading").style.top = 150
// 	document.getElementById("Loading").style.left = (document.body.clientWidth - 300) / 2; //ﾒｯｾｰｼﾞのDIV要素を画面中央に配置します

// 	var reqUrl="/loading";
// 	new Ajax.Request(reqUrl, {method :"post",onComplete:setShnData});  //Ajaxでﾘｸｴｽﾄを開始します
	    dispLoading("処理中...");

  }

  //Ajaxのコールバック関数*/
  function setShnData(httpObj){
  	document.getElementById("Loading").style.display = "none";  //ﾒｯｾｰｼﾞのDIV要素をｽﾀｲﾙｼｰﾄのdisplayﾌﾟﾛﾊﾟﾃｨで表示から非表示へ変更します
  }
  
  
//   最初
  /* ------------------------------
  Loading イメージ表示関数
  引数： msg 画面に表示する文言
  ------------------------------ */
 function dispLoading(msg){
   // 引数なし（メッセージなし）を許容
   if( msg == undefined ){
     msg = "";
   }
   // 画面表示メッセージ
   var dispMsg = "<div class='loadingMsg'>" + msg + "</div>";
   // ローディング画像が表示されていない場合のみ出力
   if($("#loading").length == 0){
     $("body").append("<div id='loading'>" + dispMsg + "</div>");
   }
 }
  
 /* ------------------------------
  Loading イメージ削除関数
  ------------------------------ */
 function removeLoading(){
   $("#loading").remove();
 }
//  次
 /* ------------------------------
 非同期処理の組み込みイメージ
 ------------------------------ */
$(function () {
  $("#proc_button").click( function() {
 
    // 処理前に Loading 画像を表示
    dispLoading("処理中...");
 
    // 非同期処理
    $.ajax({
      url : "サーバーサイドの処理を行うURL",
      type:"GET",
      dataType:"json"
    })
    // 通信成功時
    .done( function(data) {
      showMsg("成功しました");
    })
    // 通信失敗時
    .fail( function(data) {
      showMsg("失敗しました");
    })
    // 処理終了時
    .always( function(data) {
      // Lading 画像を消す
      removeLoading();
    });
  });
});
 
 

  </script>

 </body>
</html>