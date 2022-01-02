package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.bean.ItemBean;

/**
 * itemテーブルにアクセスするDAOクラス
 * @author tutor
 */
public class ItemDAO2 {

	/**
	 * クラスフィールド
	 */
	private Connection conn;	// データベース接続オブジェクト

	/**
	 * コンストラクタ
	 * @throws DAOException
	 */
	public ItemDAO2() throws DAOException {
		this.conn = this.getConnection();
	}

	public List<ItemBean> findAll() throws DAOException {
		if (this.conn == null) {
			// 同じインスタンスでSQLを複数回実行する場合、connはnullとなっているので、再接続する。
			this.conn = this.getConnection();
		}

		// SQL関連オブジェクトを初期化
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 実行するSQLを設定
			String sql = "SELECT * FROM item";
			// SQL実行オブジェクトを取得
			pstmt = this.conn.prepareStatement(sql);
			// SQLを実行し結果セットを取得
			rs = pstmt.executeQuery();
			// 結果セットから商品リストを作成
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				list.add(new ItemBean(code, name, price));
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				this.close();
			} catch (SQLException e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	/**
	 * データベースへの接続を切断する。
	 * @throws SQLException
	 */
	private void close() throws SQLException {
		if (this.conn != null) {
			this.conn.close();
			this.conn = null;
		}
	}

	/**
	 * データベースに接続する（データベース接続オブジェクトを取得する）。
	 * @return Connection データベース接続オブジェクト
	 * @throws DAOException
	 * ※ スコープがテキストでの宣言と同じではなくpublicとして宣言しているがテストケースで必要となるために変更した。
	 * TODO:データベース接続情報は定数にする（補充題）。
	 */
	public Connection getConnection() throws DAOException {
		// データベース接続情報
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String password = "himitu";
		try {
			// JDBCドライバの登録
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DAOException("接続に失敗しました。");
		}
	}
}
