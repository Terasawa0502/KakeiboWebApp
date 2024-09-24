<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Objects"%>
<%@page import="data.CategoriesDao"%>
<%@page import="data.CategoriesDto"%>
<%@page import="data.ExpensesDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page contentType="text/html; charset=UTF-8"%>

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
        <%
            // 最後に送信されたデータを取得
            String keyword = request.getParameter("keyword");
            keyword = Objects.toString(keyword, ""); // NULLは空文字に置き換え
            String order = request.getParameter("order");
            order = Objects.toString(order, ""); // NULLは空文字に置き換え
        %>

        <canvas id="expenseChart" width="300" height="300" style="max-width: 100%; height: auto;"></canvas>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
            const labels = [];
            const data = [];

            <% 
            // 経費データリストを取得
            ArrayList<ExpensesDto> expensesDataList = (ArrayList<ExpensesDto>) request.getAttribute("expensesDataList");
            // 円グラフ用Mapを定義
            Map<String, Double> categoryTotals = new HashMap<>();
            if (expensesDataList != null) {
                CategoriesDao categoriesData = new CategoriesDao();
                for (ExpensesDto expensesData : expensesDataList) {
                    int id = expensesData.getCategory_id();
                    ArrayList<CategoriesDto> categoriesDataList = categoriesData.select(id);
                    String categoryName = !categoriesDataList.isEmpty() ? categoriesDataList.get(0).getCategory_name() : "未分類";
                    categoryTotals.put(categoryName, categoryTotals.getOrDefault(categoryName, 0.0) + expensesData.getPrice());
                }
            }
            %>

            <% for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) { %>
                labels.push("<%= entry.getKey() %>");
                data.push(<%= entry.getValue() %>);
            <% } %>

            // 円グラフを表示する
            const ctx = document.getElementById('expenseChart').getContext('2d');
            const expenseChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: labels,
                    datasets: [{
                        label: '支出',
                        data: data,
                        backgroundColor: [
                            '#FF6F61',     // 食費 (オレンジ)
                            '#6FBF92',     // 水道・光熱費 (緑)
                            '#3F729B',     // 住宅 (青)
                            '#F8C471',     // 交通費 (薄黄)
                            '#D77C9B',     // 趣味・娯楽 (ピンク)
                            '#4B4B4B'      // その他 (ダークグレー)
                        ],
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'カテゴリーごとの支出'
                        }
                    }
                }
            });
        </script>

        <article class="products">
            <%
            // 成功メッセージがあれば表示
            String successMessage = (String) request.getAttribute("successMessage");
            if (successMessage != null && !successMessage.isEmpty()) {
                out.println("<p class='success'>" + successMessage + "</p>");
            }
            // 失敗メッセージがあれば表示
            String failureMessage = (String) request.getAttribute("failureMessage");
            if (failureMessage != null && !failureMessage.isEmpty()) {
                out.println("<p class='failure'>" + failureMessage + "</p>");
            }
            // FLAGを取得してリストメッセージを決定
            String flag = (String) request.getAttribute("flag");
            String listMessage = "データ一覧";
            if (flag != null && !flag.isEmpty()) {
                listMessage = (flag.equals("YES")) ? "今月のデータ一覧" : "データ一覧";
            }
            %>
            <h1><%= listMessage %></h1>
            <div class="products-ui">
                <div>
                    <a href="<%=request.getContextPath()%>/list?order=desc&keyword=<%=keyword%>">
                        <img src="images/desc.png" alt="降順に並び替え" class="sort-img">
                    </a>
                    <a href="<%=request.getContextPath()%>/list?order=asc&keyword=<%=keyword%>">
                        <img src="images/asc.png" alt="昇順に並び替え" class="sort-img">
                    </a>
                    <form action="<%=request.getContextPath()%>/list" method="get" class="search-form">
                        <input type="hidden" name="order" value="<%=order%>"> 
                        <input type="text" class="search-box" placeholder="購入品で検索" name="keyword" value="<%=keyword%>">
                    </form>
                </div>
                <a href="<%= request.getContextPath() %>/register" class="btn">データ登録</a>
            </div>
            <table class="products-table">
                <tr>
                    <th class="hidden-id">ID</th>
                    <th>購入品</th>
                    <th>値段</th>
                    <th>カテゴリー</th>
                    <th>日付</th>
                    <th>メモ</th>
                    <th>編集</th>
                    <th>削除</th>
                </tr>
                <%
                    // 経費データリストを取得
                    if (expensesDataList != null) {
                        // カテゴリーDAOのインスタンスを生成
                        CategoriesDao categoriesDao = new CategoriesDao();
                        for (ExpensesDto expensesData : expensesDataList) {
                            // カテゴリーID・カテゴリー名用の変数を定義
                            int categoryId = expensesData.getCategory_id();
                            // カテゴリーデータの取得
                            ArrayList<CategoriesDto> expensesCategoriesDataList = categoriesDao.select(categoryId);
                            String expensesCategoryName = !expensesCategoriesDataList.isEmpty() ? expensesCategoriesDataList.get(0).getCategory_name() : "未分類";
                            out.println("<tr>");
                            out.println("<td class='hidden-id'>" + expensesData.getId() + "</td>");
                            out.println("<td>" + expensesData.getName() + "</td>");
                            out.println("<td>" + expensesData.getPrice() + "</td>");
                            out.println("<td>" + expensesCategoryName + "</td>");
                            out.println("<td>" + expensesData.getDate() + "</td>");
                            out.println("<td>" + expensesData.getMemo() + "</td>");
                            out.println("<td><a href='" + request.getContextPath() + "/edit?id=" + expensesData.getId() + "'><img src='images/edit.png' alt='編集' class='edit-icon'></a></td>");
                            out.println("<td><a href='" + request.getContextPath() + "/delete?id=" + expensesData.getId() + "' onclick=\"return confirm('この操作は元に戻せません。購入品名:" + expensesData.getName() + "を本当に削除しますか？')\"><img src='images/delete.png' alt='削除' class='delete-icon'></a></td>");
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