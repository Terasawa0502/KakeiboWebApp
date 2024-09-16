package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ExpensesDao;
import util.Constants;
import util.ForwardLibrary;

// データの削除機能用Servlet
public class DeleteServlet extends HttpServlet {
    // GETメソッドのリクエスト受信時に実行されるメソッド
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエスト・レスポンスの設定
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		// フォームから送られたデータを取得
		String id = req.getParameter("id");
		// データ型を変換
		int iId;
		try {
			// 数値型カラムのデータはString型→int型へ
			iId = Integer.parseInt(id);
		// 変換処理失敗時は失敗メッセージをリクエストスコープに保存して登録画面へ遷移	
		} catch (NumberFormatException e) {
			// 数値変換処理失敗
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.NUMBER_COVRESION_MESSAGE);
			ForwardLibrary.pageForward(Constants.EDIT_PAGE_JSP, req, resp);
			return;
		}
		/*
		 * データ削除処理
		 */
		// 経費データ操作用DAOクラスのインスタンスを生成
		ExpensesDao expensesDao = new ExpensesDao();
		// 経費データを削除
		int rowCnt = expensesDao.delete(iId);
		if (rowCnt > 0) {
			// 削除成功時
			req.setAttribute(Constants.SUCCESS_MESSAGE, rowCnt + Constants.DELETE_DATA_MESSAGE);
		} else {
			// 削除失敗時
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.DB_EXCEPTION_MESSAGE);
		}
		// 一覧画面にフォワードで画面遷移
		ForwardLibrary.pageForward(Constants.LIST_SERVLET_PAGE, req, resp);
	}
}
