package util;

// 共通定数を定義するクラス
public class Constants {

	/*
	 * DB接続情報
	 */
	public static final String URL = "jdbc:mariadb://localhost:8889/java_db_app_kakeibo"; //URL
	public static final String USER = "root"; //ユーザ名
	public static final String PASSWORD = "root"; //パスワード
	
	/*
	 * エラーメッセージ
	 */
	// 経費データ関連
	public static final String DE001 = "経費データ読み取り失敗：";
	public static final String DE002 = "経費データ追加失敗：";
	
}
