package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.CategoriesDao;
import data.CategoriesDto;
import util.Constants;
import util.ForwardLibrary;

//経費データ登録ページ用Servlet
public class RegisterServlet extends HttpServlet {
	// GETメソッドのリクエスト受信時に実行されるメソッド
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // リクエスト・レスポンスの設定
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		// カテゴリーデータリストのインスタンス生成
		ArrayList<CategoriesDto> categoriesDataList = new ArrayList<>();
		// カテゴリーデータ操作用DAOのインスタンス生成
		CategoriesDao categoriesData = new CategoriesDao(); 
		try {
			// カテゴリーデータの一覧を取得(ID指定なし)
			categoriesDataList = categoriesData.select(0);
			if (categoriesDataList.isEmpty()) {
				// カテゴリーデータリストが空だった場合
				req.setAttribute(Constants.FAILURE_MESSAGE, Constants.NODATA_CATEGORIES_MESSAGE);
			} else {
				// カテゴリーデータリストが空じゃない場合はカテゴリーデータリストを保存
				req.setAttribute(Constants.CATEGORIES_DATA_LIST, categoriesDataList);
			}
		} catch (Exception e) {
			// データベース処理の例外発生時
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.DB_EXCEPTION_MESSAGE);
		}
		// 登録画面にフォワードで画面遷移
		ForwardLibrary.pageForward(Constants.REGISTER_PAGE_JSP, req, resp);
	}

}
