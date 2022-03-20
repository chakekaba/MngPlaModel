<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- View01000_作業一覧画面 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プラモデル管理アプリ</title>
</head>
<body>
<h2>作業一覧</h2>

<!-- 検索フォーム -->
<form action="search" method="post" onsubmit="return false;">
	<div>
		表示モード<br>
		<input type="radio" name="searchType" id="all" value="all" checked>
		<label for="all">全作業</label>
		<input type="radio" name="searchType" id="extract" value="extract" disabled>
		<label for=extract>対象作業のみ抽出</label>
	</div>
	<div>
		対象作業<br>
		<!-- 後々ツリーメニューに置き換えたい -->
 		<select name="work_1">
			<option value="--">--</option>
			<option value="XF-25">塗装_ライトシーグレイ</option>
			<option value="glue01">接着_ゼリー瞬間接着剤</option>
			<option value="glue02">接着_低粘度瞬間接着剤</option>
			<option value="glue03">接着_プラパーツ</option>
		</select>
 	</div>
	<button type="submit">検索</button>
	<button type="button">リセット</button><!-- javascriptで実装する。 -->
</form>

<!-- 検索一覧テーブル -->
<div class="searchTable">

	<!-- 作業モード選択 -->
	<p>
		作業モード<br>
		<input type="radio" name="workMode" id="search" value="search" checked>
		<!-- onclick="変更時に編集ボタンの有効・無効を切り替えるJS処理" -->
		<label for="search">検索</label>
		<input type="radio" name="workMode" id="work" value="work" disabled>
		<!-- onclick="変更時に編集ボタンの有効・無効を切り替えるJS処理" -->
		<label for="work">プラモ作成中</label>
		<input type="radio" name="workMode" id="edit" value="edit" disabled>
		<!-- onclick="変更時に編集ボタンの有効・無効を切り替えるJS処理" -->
		<label for="edit">編集</label>
	</p>

	<!-- 新規作業作成 -->
	<button type="button" onclick="location.href=
		'/MngPlaModel/WEB-INF/View/View01010/View01010.jsp?workId=new'">新規</button>
	<table>
		<tr>
			<th>No</th>
			<th>プラモデル</th>
			<th>場所</th>
			<th>画像</th>
			<th>編集</th>
			<th>作業内容</th>
			<th>優先度</th>
			<th>進捗率</th>
			<th>チェック欄</th>
		</tr>
	</table>
</div>

<!-- メインメニュー遷移ボタン -->
<a href="/MngPlaModel/WEB-INF/View/View00000/View00000.jsp">メインメニューへ戻る</a>
</body>
</html>