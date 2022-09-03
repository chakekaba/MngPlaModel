package logic.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.model.MdlCommonData;
import logic.sql.model.SQL0000In;
import logic.sql.model.SQL0000Out;

public class SQL0000_connectionCheck {
	
	/** SQL文**/
	protected static final String sql = "select 1";
	
	/** SQLid **/
	protected static final String sqlId = "SQL0000";
	
	/** PreparedStatement オブジェクト **/
	protected PreparedStatement pstmt = null;
	
//	/** SQL結果リスト **/
//	SQL0000では不要
//	protected ResultSet rSet = null;
	
	/** コンストラクタ **/
	public SQL0000_connectionCheck() {
	}
	
	/**
	 * メイン処理
	 * @param conn
	 * @param comData
	 * @param inData
	 * @param outData
	 */
	public void execute(
			DbConnection conn,
			MdlCommonData comData,
			SQL0000In inData,
			SQL0000Out outData) {
		
		try {
			
			editStatement(conn, inData);
			
			executeSQL();
			
			editOut(outData);
			
		} catch (Exception e) {
			String errMsg = sqlId + ":SQL実行時に例外発生";
			
			comData.setResult(ResultConstant.SQL_ERROR);
			comData.setErrorData(e, errMsg);
		} finally {
			close(comData);
		}
	}
	
	/**
	 * Statement編集処理
	 * @param conn
	 * @param inData
	 * @throws SQLException
	 */
	private void editStatement(
			DbConnection conn,
			SQL0000In inData) throws SQLException {
		pstmt = conn.getStatement(sql);
	}
	
	/**
	 * SQL実行処理
	 * @throws SQLException
	 */
	private void executeSQL() throws SQLException {
		
		pstmt.executeQuery();
	}
	
	/**
	 * 出力データ編集処理
	 * @param outData
	 */
	private void editOut(SQL0000Out outData) {
	}
	
	/**
	 * SQL終了処理
	 * @param comData
	 */
	private void close(MdlCommonData comData) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			// 例外発生を記録
			String errMsg = sqlId + ":リソースのクローズ処理時に例外発生（異常終了とはしない）";

			// 実行結果は更新せず
			comData.setErrorData(e, errMsg);
		}
	}
}
