<%@page import="base.constant.ParamIdWeb"%>
<%@page import="base.model.MdlCommonData"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
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
	<div class="row mb-3">
		<div class="col">
			<h2>メイン</h2>
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
<ul>
<li><a href="/MngPlaModel/WEB-INF/View/View01000/View01000.jsp">作業一覧</a></li>
<li><a href="/MngPlaModel/View02000">塗料一覧</a></li>
</ul>
${ comData.clearErr() }
</div>
</body>
</html>