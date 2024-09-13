<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
<title>家計簿アプリ</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" ></link>
<%-- Google Fontsの読み込み --%>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
    href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP&display=swap"
    rel="stylesheet">
</head>

<body>
	<header>
		<nav>
			<a href="<%= request.getContextPath() %>/">家計簿アプリ</a>
		</nav>
	</header>
	<main>
		<article class="home">
			<h1>家計簿アプリ</h1>
			<p>ServletとJSPで作成したWebアプリ</p>
			<a href="<%= request.getContextPath() %>/list" class="btn">データ一覧を取得する</a>
		</article>
	</main>
    <footer>
    	<p class="copyright">&copy; 家計簿アプリ All rights reserved.</p>
    </footer>
</body>
</html>