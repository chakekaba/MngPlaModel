<%@page import="base.constant.ParamIdWeb"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h2>塗料登録確認</h2>
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
	<!-- ブランド -->
	<div class="row mb-2">
		<div class="col-md-2">ブランド：</div>
		<div class="col-md">${ View02020Out.brandData }</div>
	</div>
	<!-- カラーコード -->
	<div class="row mb-2">
		<div class="col-md-2">カラーコード：</div>
		<div class="col-md">${ View02020Out.colorcode }</div>
	</div>
	<!-- カラー名 -->
	<div class="row mb-2">
		<div class="col-md-2">カラー名：</div>
		<div class="col-md">${ View02020Out.colornm }</div>
	</div>
	<!-- 所持状況 -->
	<div class="row mb-2">
		<div class="col-md-2">所持状況：</div>
		<div class="col-md">${ View02020Out.posessionDisp }</div>
	</div>
	<!-- 選択肢表示 -->
	<div class="row mb-2">
		<div class="col-md-2">選択肢表示：</div>
		<div class="col-md">${ View02020Out.selvisibleDisp }</div>
	</div>
	<!-- 近似カラー -->
	<div class="row mb-2">
		<div class="col-md-2">近似カラー：</div>
		<div class="col-md">${ View02020Out.appaintData }</div>
	</div>
	<div class="row mb-3">
		<div class="col-1">
			<!-- 登録 -->
			<form action="<%= ParamIdWeb.View02020.FORM_PATH + ParamIdWeb.View02020.EXT_PATH %>" method="post">
				<input type="hidden" name="<%= ParamIdWeb.View02020.BRAND_ID %>" value="${ View02020Out.brandid }">
				<input type="hidden" name="<%= ParamIdWeb.View02020.COLOR_CODE %>" value="${ View02020Out.colorcode }">
				<input type="hidden" name="<%= ParamIdWeb.View02020.COLOR_NAME %>" value="${ View02020Out.colornm }">
				<input type="hidden" name="<%= ParamIdWeb.View02020.POSESSION %>" value="${ View02020Out.posession }">
				<input type="hidden" name="<%= ParamIdWeb.View02020.SEL_VISIBLE %>" value="${ View02020Out.selvisible }">
				<input type="hidden" name="<%= ParamIdWeb.View02020.APPAINTID %>" value="${ View02020Out.appaintid }">
				<button type="submit" class="btn btn-primary">登録</button>
			</form>
		</div>
		<div class="col-1">
			<!-- 戻る -->
			<form action="<%= ParamIdWeb.View02010.FORM_PATH %>" method="post">
				<input type="hidden" name="<%= ParamIdWeb.View02010.BRAND_ID %>" value="${ View02020Out.brandid }">
				<input type="hidden" name="<%= ParamIdWeb.View02010.COLOR_CODE %>" value="${ View02020Out.colorcode }">
				<input type="hidden" name="<%= ParamIdWeb.View02010.COLOR_NAME %>" value="${ View02020Out.colornm }">
				<input type="hidden" name="<%= ParamIdWeb.View02010.POSESSION %>" value="${ View02020Out.posession }">
				<input type="hidden" name="<%= ParamIdWeb.View02010.SEL_VISIBLE %>" value="${ View02020Out.selvisible }">
				<input type="hidden" name="<%= ParamIdWeb.View02010.APPAINTID %>" value="${ View02020Out.appaintid }">
				<button type="submit" class="btn btn-primary">戻る</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>