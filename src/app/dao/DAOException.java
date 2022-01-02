package app.dao;

/**
 * DAOで発生する例外クラス
 * @author tutor
 */
public class DAOException extends Exception {
	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 */
	public DAOException(String message) {
		super(message);
	}
}
