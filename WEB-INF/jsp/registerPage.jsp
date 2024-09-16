<%@page import="java.util.Objects"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="data.CategoriesDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>データ登録</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<%-- Google Fontsの読み込み --%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP&display=swap" rel="stylesheet">
</head>
<body>
	<header>
		<nav>
			<a href="<%=request.getContextPath()%>/">家計簿アプリ</a>
		</nav>
	</header>
	<main>
		<%
		// 最後に送信されたデータ(購入品名、値段、メモ)を取得
	    String name = request.getParameter("name");
	    String price = request.getParameter("price");
	    String memo = request.getParameter("memo");
	    // NULLは空文字に置き換え
	    name = Objects.toString(name, "");
	    price = Objects.toString(price, "");
	    memo = Objects.toString(memo, "");
		%>
		<article class="registration">
			<h1>データ登録</h1>
			 <%
             // 失敗メッセージがあれば表示
             String failureMessage = (String) request.getAttribute("failureMessage");
             if( failureMessage != null && !failureMessage.isEmpty() ) {
                 out.println("<p class='failure'>" + failureMessage + "</p>");
             }
             %>
	        <div class="back">
	            <a href="<%=request.getContextPath()%>/list" class="btn">&lt; 戻る</a>
	        </div>
	        <form action="<%=request.getContextPath()%>/insert" method="post" class="registration-form">
	        	<div>
	        		<label for="name">購入品名</label>
	        		<input type="text" id="name" name="name" maxlength="50" value="<%= name %>" required>
	        		<label for="price">値段</label>
	        		<input type="number" id="price" name="price" min="0" max="100000000" value="<%= price %>" required>
	        		<label for="date">日付</label>
	        		<%
	        		// 最後に送信されたデータ(日付)を取得
	        		String date = request.getParameter("date");
	        		// NULLは空文字に置き換え
	        		date = Objects.toString(date, "");
	        		// 当日の日付を取得
        			LocalDate today = LocalDate.now();
	        		date = Objects.toString(date, "");
        			// 日付を文字列に変換("yyyy-MM-dd")
        			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        			String todayDate = today.format(fmt);
        			// dateの値がnullまたは空の場合は当日の日付を設定/それ以外は以外はリクエストパラメータから取得
        			String dateValue = date.isEmpty() ? todayDate : date;
	        		%>
	        		<input type="date" id="date" name="date" value="<%= dateValue %>" required>
	        		<label for="memo">メモ</label>
	        		<input type="text" id="memo" name="memo" maxlength="50" value="<%= memo %>">
	        		<label for="category_id">カテゴリー</label>
	        		<select id="category_id" name="category_id" required>
	        			<option disabled selected value>カテゴリーを選択してください</option>
	        			<%
	        			// 最後に送信されたデータ(カテゴリーID)を取得
		        		String categoryId = request.getParameter("category_id");
		        		// NULLは空文字に置き換え
		        		categoryId = Objects.toString(categoryId, "");
	        			// カテゴリーデータリストを取得
	        			ArrayList<CategoriesDto> categoriesDataList = (ArrayList<CategoriesDto>) request.getAttribute("categoriesDataList");
	        			if (categoriesDataList != null) {
	        				for (CategoriesDto categoriesData : categoriesDataList) {
	        					// カテゴリーIDをint型をStringに変換
	        					String tempCategoryId = Integer.toString(categoriesData.getCategory_id());
	        					// 直前のカテゴリー名と一致した場合は選択状態にする
	        					String selected = Objects.equals(categoryId, tempCategoryId) ? "selected" : "";
	        					out.println("<option value='" + categoriesData.getCategory_id() + "' " + selected + ">"
	        							+ categoriesData.getCategory_name() + "</option>");
	        				}
	        			}
	        			%>
	        		</select>
	        	</div>
	        	<button type="submit" class="submit-btn" name="submit" value="insert">登録</button>
	        </form>
		</article>
	</main>
	<footer>
        <p class="copyright">&copy; 家計簿アプリ All rights reserved.</p>
    </footer>
</body>
</html>