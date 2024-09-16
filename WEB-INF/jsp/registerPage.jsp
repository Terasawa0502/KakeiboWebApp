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
		<article class="registration">
			<h1>データ登録</h1>
	        <div class="back">
	            <a href="<%=request.getContextPath()%>/list" class="btn">&lt; 戻る</a>
	        </div>
	        <form action="<%=request.getContextPath()%>/insert" method="post" class="registration-form">
	        	<div>
	        		<label for="name">購入品名</label>
	        		<input type="text" id="name" name="name" maxlength="50" required>
	        		<label for="price">値段</label>
	        		<input type="number" id="price" name="price" min="0" max="100000000" required>
	        		<label for="date">日付</label>
	        		<%
	        		// 当日の日付を取得
        			LocalDate today = LocalDate.now();
        			// 日付を文字列に変換("yyyy-MM-dd")
        			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        			String todayDate = today.format(fmt);
	        		%>
	        		<input type="date" id="date" name="date" value="<%= todayDate %>" required>
	        		<label for="memo">メモ</label>
	        		<input type="text" id="memo" name="memo" maxlength="50">
	        		<label for="category_name">カテゴリー</label>
	        		<select id="category_name" name="category_name" required>
	        			<option disabled selected value>カテゴリーを選択してください</option>
	        			<%
	        			// カテゴリーデータリストを取得
	        			ArrayList<CategoriesDto> categoriesDataList = (ArrayList<CategoriesDto>) request.getAttribute("categoriesDataList");
	        			if (categoriesDataList != null) {
	        				for (CategoriesDto CategoriesData : categoriesDataList) {
	        					out.println("<option value='" + CategoriesData.getCategory_name() + "'>"
	        							+ CategoriesData.getCategory_name() + "</option>");
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