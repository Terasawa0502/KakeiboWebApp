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
import util.ForwardLibrary;

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
		// TopPage用経費データリストのインスタンス生成
        ArrayList<ExpensesDto> topExpensesDataList = new ArrayList<>();
		// 経費データリストのインスタンス生成
		ArrayList<ExpensesDto> expensesDataList = new ArrayList<>();
		// 経費データ操作用DAOのインスタンス生成
		ExpensesDao expensesData = new ExpensesDao();
		try {
			// TopPage用経費データの一覧を取得(今月分のデータ)
			topExpensesDataList = expensesData.selectTopPage();
			// 経費データの一覧を取得(ID指定・検索なし)
			expensesDataList = expensesData.select(0, keyword, order);
			// 並べ替え・キーワード指定なし
			if (order.equals("") && keyword.equals("")) {
				// TopPage用経費データがない場合は全データ取得
				if (topExpensesDataList.isEmpty()) {
					if (expensesDataList.isEmpty()) {
						// 経費データリストが空だった場合は失敗メッセージを保存
						req.setAttribute(Constants.FAILURE_MESSAGE, Constants.NODATA_EXPENSES_MESSAGE);
					} else {
						// 経費データリストが空じゃない場合は経費データリストを保存
						req.setAttribute(Constants.EXPENSES_DATA_LIST, expensesDataList);
						// TopPage用経費のデータリストが取得できなかったFLAG(NO)
						req.setAttribute(Constants.FLAG, Constants.FLAG_NO);
					}
				} else {
					// TopPage用経費データリストが空じゃない場合は経費データリストを保存
					req.setAttribute(Constants.EXPENSES_DATA_LIST, topExpensesDataList);
					// TopPage用経費のデータリストが取得できたFLAG(YES)
					req.setAttribute(Constants.FLAG, Constants.FLAG_YES);
				}
			} else {
				if (expensesDataList.isEmpty()) {
					// 経費データリストが空だった場合は失敗メッセージを保存
					req.setAttribute(Constants.FAILURE_MESSAGE, Constants.NODATA_EXPENSES_MESSAGE);
				} else {
					// 経費データリストが空じゃない場合は経費データリストを保存
					req.setAttribute(Constants.EXPENSES_DATA_LIST, expensesDataList);
					// TopPage用経費のデータリストが取得できなかったFLAG(NO)
					req.setAttribute(Constants.FLAG, Constants.FLAG_NO);
				}
			}
		} catch (Exception e) {
			// データベース処理の例外発生時
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.DB_EXCEPTION_MESSAGE);
		}
		// 一覧画面にフォワードで画面遷移
		ForwardLibrary.pageForward(Constants.LIST_PAGE_JSP, req, resp);
	}
	
	// POSTメソッドのリクエスト受信時に実行されるメソッド
    // ※InsertServletのdoPost()メソッドから遷移した場合のみ呼び出される
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// リクエスト・レスポンスの設定
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        // Servletからの成功メッセージ取得
        String successMessage = (String) req.getAttribute(Constants.SUCCESS_MESSAGE);
        if (successMessage != null && !successMessage.isEmpty()) {
            // 商品一覧ページのJSPへ成功メッセージを受け渡すために再設定
            req.setAttribute(Constants.SUCCESS_MESSAGE, successMessage);
        }
        // doGet()メソッドと同様のデータ取得処理を行う
        doGet(req, resp);
	}
}
