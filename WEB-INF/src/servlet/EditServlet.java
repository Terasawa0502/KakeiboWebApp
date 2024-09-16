package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.CategoriesDao;
import data.CategoriesDto;
import data.ExpensesDao;
import data.ExpensesDto;
import util.Constants;
import util.ForwardLibrary;

//経費データ編集ページ用Servlet
public class EditServlet extends HttpServlet {
	// GETメソッドのリクエスト受信時に実行されるメソッド
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // リクエスト・レスポンスの設定
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		// JSPからのリクエストデータ取得
		String id = req.getParameter("id");
		// NULLは空文字に置き換え
		id = Objects.toString(id);
		//データ型を変換
		int Iid;
		try {
			//IDをString型→int型に変換
			Iid = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			// 数値変換処理失敗
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.ID_COVRESION_MESSAGE);
			ForwardLibrary.pageForward(Constants.LIST_PAGE_JSP, req, resp);
			return;
		}
		// 経費データリストのインスタンス生成
		ArrayList<ExpensesDto> expensesDataList = new ArrayList<>();
		ArrayList<CategoriesDto> categoriesDataList = new ArrayList<>();
		// 経費データ・カテゴリーデータ操作用DAOクラスのインスタンスを生成
		ExpensesDao expensesData = new ExpensesDao();
		CategoriesDao categoriesData = new CategoriesDao();
		try {
			// 経費データの一覧を取得(ID指定あり)
			expensesDataList = expensesData.select(Iid, "", "");
			// カテゴリーデータの一覧を取得(ID指定なし)
			categoriesDataList = categoriesData.select(0);
		} catch (Exception e) {
			// データベース処理の例外発生時
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.DB_EXCEPTION_MESSAGE);
			ForwardLibrary.pageForward(Constants.LIST_PAGE_JSP, req, resp);
			return;
		}
		/*
		 * JSPに送信するデータの設定
		 */
		if (expensesDataList.isEmpty()) {
			// 経費データリストが空だった場合は失敗メッセージを保存
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.NODATA_EXPENSES_MESSAGE);
		} else {
			// 経費データリストが空じゃない場合は経費データリストを保存
			req.setAttribute(Constants.EXPENSES_DATA_LIST, expensesDataList);
		}
		if (categoriesDataList.isEmpty()) {
			// カテゴリーデータリストが空だった場合
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.NODATA_CATEGORIES_MESSAGE);
		} else {
			// カテゴリーデータリストが空じゃない場合はカテゴリーデータリストを保存
			req.setAttribute(Constants.CATEGORIES_DATA_LIST, categoriesDataList);
		}
		// 編集画面にフォワードで画面遷移
		ForwardLibrary.pageForward(Constants.EDIT_PAGE_JSP, req, resp);
	}
}
