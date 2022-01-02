package app.bean;

import java.io.Serializable;

/**
 * itemテーブルの1レコードを管理するBean
 * @author tutor
 *
 */
public class ItemBean implements Serializable {

	/**
	 * クラスフィールド
	 */
	private int code;			// 商品番号
	private String name;	// 商品名
	private int price;		// 価格

	/**
	 * コンストラクタ
	 * @param code	商品番号
	 * @param name	商品名
	 * @param price	価格
	 */
	public ItemBean(int code, String name, int price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	/**
	 * デフォルトコンストラクタ
	 */
	public ItemBean() {

	}

	/**
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code 設定する商品番号
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 設定する商品名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price 設定する価格
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	// テキストでは未実装であるが、テストケースとで必要となるため宣言しておく。
	@Override
	public String toString() {
		return "ItemBean [code=" + code + ", name=" + name + ", price=" + price + "]";
	}

}
