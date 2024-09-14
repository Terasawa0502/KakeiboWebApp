package data;

// カテゴリーデータ受け渡し用DTOクラス
public class CategoriesDto {
	/*
	 * フィールド
	 */
	private int category_id = 0; //カテゴリーID
	private String category_name = ""; //カテゴリー名
	private String category_color = ""; //カテゴリーカラー
	
	/*
	 * ゲッター・セッター
	 */
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_color() {
		return category_color;
	}

	public void setCategory_color(String category_color) {
		this.category_color = category_color;
	}
	
}


