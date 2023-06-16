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
			<h2>塗料登録</h2>
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
	<div class="border mb-3">
		<!-- 登録データフォーム -->
		<form action="<%= ParamIdWeb.View02020.FORM_PATH %>" method="post" class="m-3 ">
			<!-- ブランド -->
			<div class="mb-3 w-50">
				<label for="brandid" class="form-label">ブランド</label>
				<select class="form-control" id="brandid" name="<%= ParamIdWeb.View02010.BRAND_ID %>">
					<option hidden></option>
					<c:forEach var="brand" items="${View02010Out.brandList}">
						<c:choose>
							<c:when test="${View02010Out.brandid == brand.brandid}">
								<option value="${brand.brandid}" selected>${brand.brandData}</option>
							</c:when>
							<c:otherwise>
								<option value="${brand.brandid}">${brand.brandData}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<!-- カラーコード -->
			<div class="mb-3 w-50">
				<label for="colorcode" class="form-label">カラーコード</label>
				<input type="text" class="form-control" id="colorcode" name="<%= ParamIdWeb.View02010.COLOR_CODE %>" value="${ View02010Out.colorcode }">
			</div>
			<!-- カラー名 -->
			<div class="mb-3 w-50">
				<label for="colornm" class="form-label">カラー名</label>
				<input type="text" class="form-control" id="colornm" name="<%= ParamIdWeb.View02010.COLOR_NAME %>" value="${ View02010Out.colornm }">
			</div>
			<!-- 所持 -->
			<div class="mb-3 w-50">
				<label for="posession" class="form-label">所持状況</label>
				<select class="form-control" id="posession" name="<%= ParamIdWeb.View02010.POSESSION %>">
					<c:forEach var="entryPosession" items="${ View02010Out.posessionMap }">
						<c:choose>
							<c:when test="${ entryPosession.key == View02010Out.posession }">
								<option value="${ entryPosession.key }" selected>${ entryPosession.value }</option>
							</c:when>
							<c:otherwise>
								<option value="${ entryPosession.key }">${ entryPosession.value }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<!-- 選択肢表示 -->
			<div class="mb-3 w-50">
				<label for="selvisible" class="form-label">選択肢表示</label>
				<select class="form-control" id="selvisible" name="<%= ParamIdWeb.View02010.SEL_VISIBLE %>">
					<c:forEach var="entrySelvisible" items="${ View02010Out.selvisibleMap }">
						<c:choose>
							<c:when test="${ entrySelvisible.key == View02010Out.selvisible }">
								<option value="${ entrySelvisible.key }" selected>${ entrySelvisible.value }</option>
							</c:when>
							<c:otherwise>
								<option value="${ entrySelvisible.key }">${ entrySelvisible.value }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<!-- 近似色 -->
			<div class="mb-3 w-50">
				<label for="appaintid" class="form-label">近似カラー</label>
				<select class="form-control" id="appaintid" name="<%= ParamIdWeb.View02010.APPAINTID %>">
					<option></option>
					<c:forEach var="appaint" items="${ View02010Out.appaintList }">
						<c:choose>
							<c:when test="${ appaint.paintid == View02010Out.appaintid }">
								<option value="${ appaint.paintid }" selected>${ appaint.paintdata }</option>
							</c:when>
							<c:otherwise>
								<option value="${ appaint.paintid }">${ appaint.paintdata }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="mb-3">
				<button type="submit" class="btn btn-primary">登録</button>
			</div>
		</form>
	</div>
	<div>
		<!-- 塗料一覧画面遷移ボタン -->
		<a href="<%= ParamIdWeb.View02000.FORM_PATH %>" class="btn btn-link border" role="button">塗料一覧画面</a>
	</div>
	${ comData.clearErr() }
</div>
</body>
</html>