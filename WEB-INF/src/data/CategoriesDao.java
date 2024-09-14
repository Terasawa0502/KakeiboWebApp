package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.Constants;

// カテゴリーデータ操作用DAOクラス
public class CategoriesDao {

	// カテゴリーデータの読み取り
	public ArrayList<CategoriesDto> select() throws SQLException {
		// 取得したデータを格納するためのリスト
		ArrayList<CategoriesDto> categoriesDataList = new ArrayList<>();
		// SELECT文のフォーマット
		String sql = "SELECT * FROM categories";
		// データベースへの接続・SQL文の送信準備
		try(Connection connection = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
			Statement statement = connection.createStatement()) 
		{
			// SQL文を実行
			ResultSet resultSet = statement.executeQuery(sql);
			// SQLクエリの実行結果を抽出
			while (resultSet.next()) {
				// DTOのインスタンスにデータをセット
				CategoriesDto categoriesData = new CategoriesDto();
				categoriesData.setCategory_name(resultSet.getString("category_name"));
				categoriesData.setCategory_color(resultSet.getString("category_color"));
				// リストにデータを追加
				categoriesDataList.add(categoriesData);
			}
		}
		// カテゴリーデータリストを返す
		return categoriesDataList;
	}
}
