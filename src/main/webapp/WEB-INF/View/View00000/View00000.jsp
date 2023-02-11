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
</head>
<body>
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
</body>
</html>