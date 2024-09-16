<%@page import="java.time.LocalDate"%>
<%@page import="data.ExpensesDto"%>
<%@page import="data.CategoriesDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Objects"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>データ編集</title>
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
		// 最後に送信されたデータを取得
		String id = request.getParameter("id");
	    String name = request.getParameter("name");
	    String date = request.getParameter("date");
	    String price = request.getParameter("price");
	    String memo = request.getParameter("memo");
	    String categoryId = request.getParameter("category_id");
	    // NULLは空文字に置き換え
	    id = Objects.toString(id, "");
	    name = Objects.toString(name, "");
	    date = Objects.toString(date, "");
	    price = Objects.toString(price, "");
	    memo = Objects.toString(memo, "");
	    categoryId = Objects.toString(categoryId, "");
	    // 経費データリストを取得
	    ArrayList<ExpensesDto> expensesDataList = (ArrayList<ExpensesDto>) request.getAttribute("expensesDataList");
	    if (expensesDataList != null && !expensesDataList.isEmpty()) {
	    	// 先頭の要素を取得
	    	ExpensesDto expensesData = expensesDataList.get(0);
	    	// 編集対象のデータを各変数に設定
	    	id = Integer.toString(expensesData.getId());
	    	name = expensesData.getName();
	    	date = expensesData.getDate().toString();
	    	price = Integer.toString(expensesData.getPrice());
	    	memo = expensesData.getMemo();
	    	categoryId = Integer.toString(expensesData.getCategory_id());
	    }
		%>
		<article class="registration">
			<h1>データ編集</h1>
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
	        <form action="<%=request.getContextPath()%>/update?id=<%= id %>" method="post" class="registration-form">
	        	<div>
	        		<label for="name">購入品名</label>
	        		<input type="text" id="name" name="name" maxlength="50" value="<%= name %>" required>
	        		<label for="price">値段</label>
	        		<input type="number" id="price" name="price" min="0" max="100000000" value="<%= price %>" required>
	        		<label for="date">日付</label>
	        		<input type="date" id="date" name="date" value="<%= date %>" required>
	        		<label for="memo">メモ</label>
	        		<input type="text" id="memo" name="memo" maxlength="50" value="<%= memo %>">
	        		<label for="category_id">カテゴリー</label>
	        		<select id="category_id" name="category_id" required>
	        			<option disabled selected value>カテゴリーを選択してください</option>
	        			<%
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
	        	<button type="submit" class="submit-btn" name="submit" value="update">更新</button>
	        </form>
		</article>
	</main>
</body>
</html>