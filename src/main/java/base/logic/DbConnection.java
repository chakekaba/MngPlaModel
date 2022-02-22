package base.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.constant.DbConnConstant;

public class DbConnection {

	private Connection conn = null;
	private PreparedStatement pStmt = null;
	private ResultSet resultSet = null;

	/**
	 * メインメソッド　試験用
	 * @param args
	 */
	public static void main(String[] args) {
		String sqlSelect = "select paintcode, color from tbl0001_paint";
		String sqlInsert =
				  "insert into tbl0001_paint (paintcode, distributor, brand, color, painttype, possession, visible) "
				+ "values ('c608', 'gsiクレオス', 'Mr.カラー', '暗灰色2705(N4) 海上自衛隊護衛艦色', '0', '1', '1')";

		DbConnection conn = new DbConnection();

		try {
			conn.connect();
			ResultSet rSet = conn.executeR(sqlSelect);

			while (rSet.next()) {
				System.out.println("塗料コード:" + rSet.getString(1));
				System.out.println("カラー名:" + rSet.getString(2));
				System.out.println();
			}

			System.out.println("--------------------------------------------");

			// レコード追加
			conn.executeCUD(sqlInsert);

			rSet = conn.executeR(sqlSelect);

			while (rSet.next()) {
				System.out.println("塗料コード:" + rSet.getString(1));
				System.out.println("カラー名:" + rSet.getString(2));
				System.out.println();
			}

			System.out.println("--------------------------------------------");

			// ロールバック実行
			conn.roolback();

			rSet = conn.executeR(sqlSelect);

			while (rSet.next()) {
				System.out.println("塗料コード:" + rSet.getString(1));
				System.out.println("カラー名:" + rSet.getString(2));
				System.out.println();
			}

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

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
		}
	}

	/**
	 * ロールバック実行
	 * @throws SQLException
	 */
	public void roolback() throws SQLException {
		if (conn != null) {
			conn.rollback();
		}
	}

	/**
	 * データベース接続切断
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}
		if (pStmt != null) {
			pStmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * select文実行
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeR(String sql) throws SQLException {
		pStmt = conn.prepareStatement(sql);

		// SQL実行
		resultSet = pStmt.executeQuery();

		return resultSet;
	}

	/**
	 * insert/update/delete文実行
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public void executeCUD(String sql) throws SQLException {

		pStmt = conn.prepareStatement(sql);

		// SQL実行
		pStmt.execute();
	}
}
