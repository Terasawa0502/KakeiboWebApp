package util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servletの画面遷移用メソッドを定義するクラス
public class ForwardLibrary {
	
	// フォワード処理
	public static void pageForward(String screenURL, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(screenURL).forward(req, resp);
	}
	
}