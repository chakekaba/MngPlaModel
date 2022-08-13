package base.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import base.constant.DbConnConstant;

/**
 * データベース接続クラス
 * @author kohei kajiki
 *
 */
public class DbConnection {

	private Connection conn = null;

	/**
	 * データベース接続
	 * @throws Exception
	 */
	public void connect() throws SQLException {
		// DB接続
		conn = DriverManager.getConnection(
				DbConnConstant.URL, DbConnConstant.USER, DbConnConstant.PASSWORD
			);

		// オートコミットOFF
		conn.setAutoCommit(false);
	}

	/**
	 * コミット実行
	 * @throws SQLException
	 */
	public void commit() throws SQLException {
		if (conn != null) {
			conn.commit();
			System.out.println("コミット実行");
		}
	}

	/**
	 * ロールバック実行
	 * @throws SQLException
	 */
	public void roolback() throws SQLException {
		if (conn != null) {
			conn.rollback();
			System.out.println("ロールバック実行");
		}
	}

	/**
	 * データベース接続切断
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * getter - PreparedStatement
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement getStatement(String sql) throws SQLException {
		PreparedStatement pStmt = conn.prepareStatement(sql);
		return pStmt;
	}
}
