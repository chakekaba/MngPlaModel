<%@page import="base.model.MdlCommonData"%>
<%@page import="base.constant.ParamIdWeb"%>
<%@page import="java.util.List"%>
<%@page import="java.util.logging.Level"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ViewLogin_ログイン画面 -->
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
	<div class="row mb-3">
		<div class="col">
			<h2>ログイン</h2>
		</div>
	</div>
	<c:if test="${not empty comData.errorDataList }">
		<!-- エラーメッセージ表示 -->
		<div class="row mb-3">
			<ul>
				<c:forEach var="errorData" items="${ comData.errorDataList }">
					<c:if test="${ errorData.isINFO() }">
						<li class="alert alert-info"><c:out value="${ errorData.message }" /></li>
					</c:if>
					<c:if test="${ errorData.isWARNING() }">
						<li class="alert alert-warning"><c:out value="${ errorData.message }" /></li>
					</c:if>
					<c:if test="${ errorData.isSEVERE() }">
						<li class="alert alert-danger"><c:out value="${ errorData.message }" /></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<div class="row">
		<form action="/MngPlaModel/View00000" method="post">
			<div class="mb-3 w-50">
				<label for="user" class="form-label">ユーザ：</label>
				<input type="text" id="user" name="<%=ParamIdWeb.ViewLogin.USER %>" class="form-control">
			</div>
			<div class="mb-3 w-50">
				<label for="pass" class="form-label">パスワード：</label>
				<input type="password" id="pass" name="<%=ParamIdWeb.ViewLogin.PASS %>" class="form-control">
			</div>
			<button type="submit" class="btn btn-primary mb-3">ログイン</button>
		</form>
	</div>
</div>
${ comData.clearErr() }
</body>
</html>