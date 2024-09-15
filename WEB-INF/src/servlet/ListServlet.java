package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ExpensesDao;
import data.ExpensesDto;
import util.Constants;

// 経費データ一覧ページ用Servlet
public class ListServlet extends HttpServlet {
	// GETメソッドのリクエスト受信時に実行されるメソッド
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // リクエスト・レスポンスの設定
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		// JSPからのリクエストデータ取得
		String order = req.getParameter("order");
		order = Objects.toString(order, "");
		String keyword = req.getParameter("keyword");
        keyword = Objects.toString(keyword, "");
		
		// 経費データリストのインスタンスを生成
		ArrayList<ExpensesDto> expensesDataList = new ArrayList<>();
		// 経費データ操作用DAOのインスタンス生成
		ExpensesDao expensesData = new ExpensesDao();
		try {
			// 経費データの一覧を取得(ID指定・検索なし)
			expensesDataList = expensesData.select(0, keyword, order);
			if (expensesDataList.isEmpty()) {
				// 経費データリストが空だった場合は失敗メッセージを保存
				req.setAttribute(Constants.FAILURE_MESSAGE, Constants.NODATA_EXPENSES_MESSAGE);
			} else {
				// 経費データリストが空じゃない場合は経費データリストを保存
				req.setAttribute(Constants.EXPENSES_DATA_LIST, expensesDataList);
			}
		} catch (Exception e) {
			// データベース処理の例外発生時
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.DB_EXCEPTION_MESSAGE);
		}
		// フォワードによる画面遷移
		req.getRequestDispatcher(Constants.LIST_PAGE_JSP).forward(req, resp);
	}
}
