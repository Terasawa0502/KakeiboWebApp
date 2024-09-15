<%@page import="data.CategoriesDao"%>
<%@page import="data.CategoriesDto"%>
<%@page import="data.ExpensesDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<!DOCTYPE html>
<html lang="ja">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
<title>データ一覧</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"></link>
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
		<article class="products">
			<h1>データ一覧</h1>
			<%
			// 失敗メッセージがあれば表示
			String failureMessage = (String) request.getAttribute("failureMessage");
			if (failureMessage != null && !failureMessage.isEmpty()) {
				out.println("<p class='failure'>" + failureMessage + "</p>");
			}
			%>
			<table class="products-table">
				<tr>
					<th class="hidden-id">ID</th>
					<th>購入品</th>
					<th>値段</th>
					<th>カテゴリー</th>
					<th>日付</th>
					<th>メモ</th>
				</tr>
				<%
				ArrayList<ExpensesDto> expensesDataList = (ArrayList<ExpensesDto>) request.getAttribute("expensesDataList");
				if (expensesDataList != null) {
					// カテゴリーDAOのインスタンスを生成
					CategoriesDao categoriesData = new CategoriesDao();
					for (ExpensesDto expensesData : expensesDataList) {
						// カテゴリーID・カテゴリー名用の変数を定義
						int id = expensesData.getCategory_id();
						String categoryName = "";
						// カテゴリーデータの取得
						ArrayList<CategoriesDto> categoriesDataList = categoriesData.select(id);
						if (!categoriesDataList.isEmpty()) {
							// カテゴリー名を取得して代入
							categoryName = categoriesDataList.get(0).getCategory_name();
						}
						out.println("<tr>");
						out.println("<td class='hidden-id'>" + expensesData.getId() + "</td>");
						out.println("<td>" + expensesData.getName() + "</td>");
						out.println("<td>" + expensesData.getPrice() + "</td>");
						out.println("<td>" + categoryName + "</td>");
						out.println("<td>" + expensesData.getDate() + "</td>");
						out.println("<td>" + expensesData.getMemo() + "</td>");
						out.println("</tr>");
					}
				}
				%>
			</table>
		</article>
	</main>
	<footer>
        <p class="copyright">&copy; 家計簿アプリ All rights reserved.</p>
    </footer>
</body>
</html>