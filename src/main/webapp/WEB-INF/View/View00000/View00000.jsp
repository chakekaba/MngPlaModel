<%@page import="base.constant.ParamIdWeb"%>
<%@page import="base.model.MdlCommonData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- View00000_メイン画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プラモデル管理アプリ</title>
<!-- BootstrapのCSS読み込み -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<!-- jQuery読み込み -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのJS読み込み -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
<h2>メイン</h2>
<ul>
<li><a href="/MngPlaModel/WEB-INF/View/View01000/View01000.jsp">作業一覧</a></li>
<li><a href="/MngPlaModel/View02000">塗料一覧</a></li>
</ul>
<%
MdlCommonData comData = (MdlCommonData)session.getAttribute(ParamIdWeb.COM_DATA);
// エラー情報クリア
comData.clearErr();
%>
</div>
</body>
</html>