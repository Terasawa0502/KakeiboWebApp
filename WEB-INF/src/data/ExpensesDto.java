package data;

import java.time.LocalDate;

//経費データ受け渡し用DTOクラス
public class ExpensesDto {
	/*
	 * フィールド
	 */
	private int id = 0; //ID
	private String name = ""; //名前
	private int price = 0; //値段
	private LocalDate date = LocalDate.now(); //購入日
	private int category_id = 0; //カテゴリーID
	private String memo = ""; //買い物メモ
	
	/*
	 * ゲッター・セッター
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}