package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import util.Constants;


// 経費データ操作用DAOクラス
public class ExpensesDao {

	//経費データの読み取り
	public ArrayList<ExpensesDto> select(int id, String keyword, String order) {
		// 取得したデータを格納するためのリスト
		ArrayList<ExpensesDto> expensesDataList = new ArrayList<>();
		
		// 文字列がNULLだった場合は空文字に置き換え
		keyword = Objects.toString(keyword, "");
		order = Objects.toString(order, "");
		
		// SELECT用のSQL
		String sql = "SELECT * FROM expenses ";
		
		// 引数に合わせてSELECT文に条件を追加
		if (id > 0) {
			// IDが指定があった場合(データ編集時)
			sql += "WHERE id = ?;";
		} else {
			// 名前で検索キーワードがあった場合(データ検索時)
			if (!keyword.isEmpty()) {
				sql += "WHERE name LIKE ?";
			}
			// 並べ替え方向に合わせてORDER BY句を追加
			// desc:降順 asc・指定なし:昇順
			sql += (order.equals("desc")) ? "ORDER BY date DESC" : "ORDER BY date ASC";
			// 文末にセミコロンを追加
			sql += ";";
		}
		// データベースへの接続・SQL文の送信準備
		try (Connection connection = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql))
		{
			// ?があれば置き換え
			if (id > 0) {
				// IDが指定されていた場合置き換え
				statement.setInt(1, id);
			} else if (!keyword.isEmpty()) {
				// キーワードが指定されていた場合置き換え
				statement.setString(1, "%" + keyword + "%");
			}
			// SQL文を実行
			ResultSet resultSet = statement.executeQuery();
			
			// SQLクエリの実行結果を抽出
			while (resultSet.next()) {
				// DTOのインスタンスに各カラムのデータをセット
				ExpensesDto expensesData = new ExpensesDto();
				expensesData.setId(resultSet.getInt("id"));
				expensesData.setName(resultSet.getString("name"));
				expensesData.setPrice(resultSet.getInt("price"));
				expensesData.setCategory_id(resultSet.getInt("category_id"));
				expensesData.setMemo(resultSet.getString("memo"));
				
				// Date型→LocalDate型へ変換
				Date sqlDate = resultSet.getDate("date");
				if (sqlDate != null) {
					LocalDate localDate = sqlDate.toLocalDate();
					expensesData.setDate(localDate);
				}
				// リストにデータを追加
				expensesDataList.add(expensesData);
			}
		} catch (SQLException e) {
			System.out.println(Constants.DE001 + e.getMessage());
		}
		// 経費データリストを返す
		return expensesDataList;
	}
	
	// 経費データの追加
	public int insert(ExpensesDto data) {
		// 更新レコード数
		int rowCnt = 0;
		// INSERT文のフォーマット
		String sql = "INSERT INTO expenses(name, price, date, category_id, memo) VALUES(?, ?, ?, ?, ?);";
		
		// データベースへの接続・SQL文の送信準備
		try (Connection connection = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql))
		{
			// SQL文の?を更新するデータで置き換える
			statement.setString(1, data.getName());
			statement.setInt(2, data.getPrice());
			statement.setObject(3, data.getDate());
			statement.setInt(4, data.getCategory_id());
			statement.setString(5, data.getMemo());
			// SQL文を実行
			rowCnt = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(Constants.DE002 + e.getMessage());
		}
		// 更新レコード数を返す
		return rowCnt;
	}
	
}
