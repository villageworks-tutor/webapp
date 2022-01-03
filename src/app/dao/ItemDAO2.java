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

	/**
	 * 商品の全件を取得する。
	 * @return List<ItemBean> 商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> findAll() throws DAOException {
		if (this.conn == null) {
			// 同じインスタンスでSQLを複数回実行する場合、connはnullとなっているので、再接続する。
			this.conn = this.getConnection();
		}

		// SQL実行関連オブジェクトを初期化
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
	 * 指定された価格以下の商品を取得する。
	 * @param upperPrice 指定された価格の上限値
	 * @return List<ItemBean> 商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> findByPrice(int upperPrice) throws DAOException {
		if (this.conn == null) {
			// 同じインスタンスでSQLを複数回実行する場合、connはnullとなっているので、再接続する。
			this.conn = this.getConnection();
		}

		// SQL実行関連オブジェクトの初期化
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 実行するSQLの設定
		String sql = "SELECT * FROM item WHERE price <= ?";
		try {
			// SQL実行オブジェクトを取得
			pstmt = this.conn.prepareStatement(sql);
			// プレースホルダにパラメータを設定
			pstmt.setInt(1, upperPrice);
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
	 * 価格で並べ替える。
	 * @param isAscending 価格の低い順の場合はtrue、それ以外はfalse
	 * @return List<ItemBean> 商品リスト
	 * @throws DAOException
	 */
	public List<ItemBean> sortPrice(boolean isAscending) throws DAOException {
		if (this.conn == null) {
			// 同じインスタンスでSQLを複数回実行する場合、connはnullとなっているので、再接続する。
			this.conn = this.getConnection();
		}

		// SQL実行関連オブジェクトの初期化
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 実行するSQLの初期化
		String sql = "SELECT * FROM item ORDER BY price ";
		// SQL実行オブジェクトの取得
		if (isAscending) {
			sql += "ASC";
		} else {
			sql += "DESC";
		}
		try {
			// SQL実行オブジェクトを取得
			pstmt = this.conn.prepareStatement(sql);
			// SQLの実行と結果セットの取得
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

	public int addItem(String name, int price) throws DAOException {
		if (this.conn == null) {
			// 同じインスタンスでSQLを複数回実行する場合、connはnullとなっているので、再接続する。
			this.conn = this.getConnection();
		}

		// SQL実行関連オブジェクトの初期化
		PreparedStatement pstmt = null;

		// 実行するSQLを設定
		String sql = "INSERT INTO item (name, price) VALUES (?, ?)";
		try {
			// SQL実行オブジェクトを取得
			pstmt = this.conn.prepareStatement(sql);
			// プレースホルダにパラメータを設定
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			// SQLの実行
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの追加に失敗しました。");
		} finally {
			try {
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
