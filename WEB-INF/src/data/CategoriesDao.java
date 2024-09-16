package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Constants;

// カテゴリーデータ操作用DAOクラス
public class CategoriesDao {

	// カテゴリーデータの読み取り
	public ArrayList<CategoriesDto> select(int categoryId) {
		// 取得したデータを格納するためのリスト
		ArrayList<CategoriesDto> categoriesDataList = new ArrayList<>();
		// SELECT文のフォーマット
		String sql = "SELECT * FROM categories ";
		// 引数に合わせてSELECT文に条件を追加
		if (categoryId > 0) {
			// IDに指定があった場合(データ一覧表示時)
			sql += "WHERE category_id = ?";
		}
		// 文末にセミコロンを追加
		sql += ";";
		
		// データベースへの接続・SQL文の送信準備
		try(Connection connection = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
			PreparedStatement statement = connection.prepareStatement(sql)) 
		{
			// ?があれば置き換え
			if (categoryId > 0) {
				statement.setInt(1, categoryId);
			}
			// SQL文を実行
			ResultSet resultSet = statement.executeQuery();
			// SQLクエリの実行結果を抽出
			while (resultSet.next()) {
				// DTOのインスタンスにデータをセット
				CategoriesDto categoriesData = new CategoriesDto();
				categoriesData.setCategory_id(resultSet.getInt("category_id"));
				categoriesData.setCategory_name(resultSet.getString("category_name"));
				categoriesData.setCategory_color(resultSet.getString("category_color"));
				// リストにデータを追加
				categoriesDataList.add(categoriesData);
			}
		} catch (SQLException e) {
			System.out.println(Constants.DE005 + e.getMessage());
		}
		// カテゴリーデータリストを返す
		return categoriesDataList;
	}
}
