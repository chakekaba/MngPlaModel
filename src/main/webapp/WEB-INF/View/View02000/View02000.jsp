<%@page import="logic.sv.model.MdlLogic02000Out"%>
<%@page import="base.constant.ParamIdWeb"%>
<%@page import="base.model.MdlCommonData"%>
<%@page import="java.util.List"%>
<%@page import="logic.sql.model.SQL0001Out"%>
<%@page import="logic.sql.model.SQL0002Out"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
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
			<h2>塗料一覧</h2>
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
<form action="<%= ParamIdWeb.View02000.FORM_PATH %>" method="post" class="mb-3 border-bottom">
	<!-- 塗料名（部分一致） -->
	<div class="mb-3 w-50">
		<label for="paintnm" class="form-label">塗料名（部分一致）</label>
		<input type="text" class="form-control" name="<%= ParamIdWeb.View02000.COLOR_NM %>"
			id="paintnm" value="${param['colornm']}">
	</div>
	<!-- ブランド -->
	<div class="mb-3 w-50">
		<label for="brandnm" class="form-label">ブランド</label>
		<select class="form-control" name="<%= ParamIdWeb.View02000.BRAND_ID %>" id="brandnm">
			<option />
			<c:forEach var="brand" items="${View02000Out.brandList}">
				<c:choose>
					<c:when test="${param['brandid'] == brand.brandid}">
						<option value="${brand.brandid}" selected>${brand.brandnm}</option>
					</c:when>
					<c:otherwise>
						<option value="${brand.brandid}">${brand.brandnm}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>
	<!-- プラモデル -->
	<div class="mb-3 w-50">
		<label for="plmdl" class="form-label">プラモデル</label>
		<select class="form-control" name="<%= ParamIdWeb.View02000.PLMDL_ID %>" id="plmdl">
			<option />
			<c:forEach var="plmdl" items="${View02000Out.plmdlList}">
				<c:choose>
					<c:when test="${param['plmdlid'] == plmdl.plmdlid}">
						<option value="${plmdl.plmdlid}" selected>${plmdl.plmdlnm}</option>
					</c:when>
					<c:otherwise>
						<option value="${plmdl.plmdlid}">${plmdl.plmdlnm}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>
	<input type="hidden" name="<%= ParamIdWeb.View02000.SEARCH_EXE_FLG %>" value="1">
	<button type="submit" class="btn btn-primary mb-3">検索</button>
</form>
<!-- 検索一覧テーブル -->
<div class="mb-3">
	<table class="table table-sm table-striped table-hover table-bordered">
		<thead class="table-secondary">
			<tr>
				<th>ブランド名</th>
				<th>カラーコード</th>
				<th>カラー名</th>
				<th>所持</th>
				<th>近似カラーコード</th>
				<th>近似カラー名</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${View02000Out.resultList}">
			<tr>
				<td>${result.brandnm}</td>
				<td>${result.colorcode}</td>
				<td>${result.colornm}</td>
				<td>${result.posession}</td>
				<td>${result.apcolorcode}</td>
				<td>${result.apcolornm}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="mb-3">
	<a href="<%= ParamIdWeb.View00000.FORM_PATH %>">メイン画面</a>
</div>
${ comData.clearErr() }
</div>
</body>
</html>