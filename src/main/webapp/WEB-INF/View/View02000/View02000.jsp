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
<!-- <link rel="stylesheet" href="/MngPlaModel/WEB-INF/View/View02000/View02000.css"> -->
<link rel="stylesheet" href="/MngPlaModel/ViewSample/css/View02000Sample.css">
</head>
<body>
<h1>塗料一覧</h1>
<form action="<%= ParamIdWeb.View02000.FORM_PATH %>" method="post">
	<div>
		<!-- 塗料名（部分一致） -->
		<label for="paintnm">塗料名（部分一致）</label>
		<input type="text" name="<%= ParamIdWeb.View02000.COLOR_NM %>"
			id="paintnm" value="${param['colornm']}">
		<br>
		<label for="brandnm">ブランド名</label>
		<select name="<%= ParamIdWeb.View02000.BRAND_ID %>" id="brandnm">
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
		<br>
		<label for="plmdl">プラモデル</label>
		<select name="<%= ParamIdWeb.View02000.PLMDL_ID %>" id="plmdl">
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
		<input type="hidden" name="<%= ParamIdWeb.View02000.SEARCH_EXE_FLG %>" value="1">
	</div>
	<button type="submit">検索</button>
</form>
<!-- 検索一覧テーブル -->
<div class="searchTable">
	<table>
		<thead>
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
<a href="<%= ParamIdWeb.View00000.FORM_PATH %>">メイン画面</a>
<%
MdlCommonData comData = (MdlCommonData)session.getAttribute(ParamIdWeb.COM_DATA);
// エラー情報クリア
comData.clearErr();
%>
</body>
</html>