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
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void connect() throws SQLException, ClassNotFoundException {
		
		// ドライバーの初期化
		// 本処理を実行しない、かつtomcat側のクラスパスにjdbcが設定されていない場合、
		// java.sql.SQLException: No suitable driver found for jdbc:postgresql://localhost:5432/mywork
		// というエラーが発生
		Class.forName(DbConnConstant.DRIVER);
		
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
	 * データベース接続切断 - トランザクション操作あり
	 * @throws SQLException
	 */
	public void close(boolean isCommit) throws SQLException {
		
		if (conn != null) {
			
			// トランザクション操作
			if (isCommit == true) {
				
				// コミット
				this.commit();
			} else {
				
				// ロールバック
				this.roolback();
			}
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
