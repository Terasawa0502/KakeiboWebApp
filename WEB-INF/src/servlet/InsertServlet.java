package servlet;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

// データ登録機能用Servlet
public class InsertServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		
		// フォームから送られたデータを取得
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String date = req.getParameter("date");
		String category_id = req.getParameter("category_id");
		String memo = req.getParameter("memo");
		// データ型を変換
		int iPrice, iCategoryId;
		LocalDate lDate;
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			// 数値型カラムのデータはString型→int型へ
			iPrice = Integer.parseInt(price);
			iCategoryId = Integer.parseInt(category_id);
			// 日付型カラムのデータはString型→date型へ
			lDate = LocalDate.parse(date, fmt);
		// 変換処理失敗時は失敗メッセージをリクエストスコープに保存して登録画面へ遷移	
		} catch (NumberFormatException e) {
			// 数値変換処理失敗
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.PRICE_COVRESION_MESSAGE);
			ForwardLibrary.pageForward(Constants.REGISTER_PAGE_JSP, req, resp);
			return;
		} catch (DateTimeException e) {
			// 日付変換処理失敗
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.DATE_COVRESION_MESSAGE);
			ForwardLibrary.pageForward(Constants.REGISTER_PAGE_JSP, req, resp);
			return;
		}
		// 購入品名が正しく取得できている（NULLや空文字でない）かチェック
		// チェック結果がNGなら登録画面へ遷移
		if (name == null || name.isEmpty()) {
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.NAME_MISS_MESSAGE);
			ForwardLibrary.pageForward(Constants.REGISTER_PAGE_JSP, req, resp);
			return;
		}
		/*
		 * データ追加処理
		 */
		// 経費データDTOインスタンスを生成して各カラムのデータをセット
		ExpensesDto data = new ExpensesDto();
		data.setName(name);
		data.setPrice(iPrice);
		data.setDate(lDate);
		data.setCategory_id(iCategoryId);
		data.setMemo(memo);
		// 経費データ操作用DAOクラスのインスタンスを生成
		ExpensesDao expensesDao = new ExpensesDao();
		// 経費データを追加
		int rowCnt = expensesDao.insert(data);
		if (rowCnt > 0) {
			// 一覧画面にフォワードで画面遷移
			req.setAttribute(Constants.SUCCESS_MESSAGE, rowCnt + Constants.REGISTER_DATA_MESSAGE);
			ForwardLibrary.pageForward(Constants.LIST_SERVLET_PAGE, req, resp);
		} else {
			// データベース処理の例外発生時
			req.setAttribute(Constants.FAILURE_MESSAGE, Constants.DB_EXCEPTION_MESSAGE);
			ForwardLibrary.pageForward(Constants.REGISTER_PAGE_JSP, req, resp);
		}
		
	}
}
