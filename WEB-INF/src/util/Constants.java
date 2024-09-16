package util;

// 共通定数を定義するクラス
public class Constants {

	/*
	 * DB接続情報
	 */
	public static final String URL = "jdbc:mariadb://localhost:8889/java_db_app_kakeibo";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	
	/*
	 * 経費データ関連メッセージ
	 */
	// エラーメッセージ
	public static final String DE001 = "経費データ読み取り失敗：";
	public static final String DE002 = "経費データ追加失敗：";
	public static final String DE003 = "経費データ更新失敗：";
	public static final String DE004 = "経費データ削除失敗：";
	
	/*
	 * カテゴリーデータ関連メッセージ
	 */
	public static final String DE005 = "カテゴリーデータ読み取り失敗：";
	
	/*
	 * Servlet関連メッセージ
	 */
	// 共通エラーメッセージ
	public static final String NODATA_EXPENSES_MESSAGE = "該当するデータが見つかりませんでした。";
	public static final String NODATA_CATEGORIES_MESSAGE = "カテゴリーデータが1件も登録されていません。";
	public static final String DB_EXCEPTION_MESSAGE = "データベース処理エラーが発生しました。システム管理者に確認してください。";
	public static final String PRICE_COVRESION_MESSAGE = "数値の入力形式に誤りがあります。有効な数値を入力してください。";
	public static final String DATE_COVRESION_MESSAGE = "日付の変換処理失敗が発生しました。有効な日付を入力してください。";
	public static final String NAME_MISS_MESSAGE = "有効な購入品名を入力してください。";
	public static final String REGISTER_DATA_MESSAGE = "件のデータを登録しました。";
	//  共通部品
	public static final String FAILURE_MESSAGE = "failureMessage";
	public static final String SUCCESS_MESSAGE = "successMessage";
	public static final String EXPENSES_DATA_LIST = "expensesDataList";
	public static final String CATEGORIES_DATA_LIST = "categoriesDataList";
	public static final String LIST_SERVLET_PAGE = "/list";
	public static final String LIST_PAGE_JSP = "/WEB-INF/jsp/listPage.jsp";
	public static final String REGISTER_PAGE_JSP = "/WEB-INF/jsp/registerPage.jsp";
	
}
