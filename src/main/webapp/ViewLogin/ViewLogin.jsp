<%@page import="base.constant.ParamIdWeb"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- ViewLogin_ログイン画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プラモデル管理アプリ</title>
</head>
<body>
<h2>ログイン</h2>
<form action="/MngPlaModel/View00000" method="post">
<label for="user">ユーザ：</label>
<input type="text" id="user" name="<%=ParamIdWeb.ViewLogin.USER%>"><br>
<label for="pass">パスワード：</label>
<input type="password" id="pass" name="<%=ParamIdWeb.ViewLogin.PASS%>"><br>
<button type="submit">ログイン</button>
</form>
</body>
</html>