package logic.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.model.MdlCommonData;
import logic.sql.model.SQL0002In;
import logic.sql.model.SQL0002Out;

/** 検索条件用プラモデルリスト取得SQL **/
public class SQL0002_SelPlmdlList {

	/** SQL文**/
	protected static final String sql = "select\r\n"
			+ "plmdlid,\r\n"
			+ "plmdlnm\r\n"
			+ "from tbl0200_plmdl\r\n"
			+ "order by plmdlid";
//			+ "from tbl0200_plmd";
	
	/** SQLid **/
	protected static final String sqlId = "SQL0002";
	
	/** PreparedStatement オブジェクト **/
	protected PreparedStatement pstmt = null;
	
	/** SQL結果リスト **/
	protected ResultSet rSet = null;
	
	
	/**
	 * コンストラクタ
	 */
	public SQL0002_SelPlmdlList() {
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
			SQL0002In inData,
			List<SQL0002Out> outData) {
		
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
			SQL0002In inData) throws SQLException {
		pstmt = conn.getStatement(sql);
	}

	/**
	 * SQL実行処理
	 * @throws SQLException
	 */
	private void executeSQL() throws SQLException {
		
		rSet = pstmt.executeQuery();
	}

	/**
	 * 出力データ編集処理
	 * @param outData
	 */
	private void editOut(List<SQL0002Out> outData) throws SQLException {

		// 行データの定義
		SQL0002Out rowData;

		while (rSet.next()) {
			
			rowData = new SQL0002Out();
			
			// プラモデルID
			rowData.setPlmdlid(rSet.getString(1));
			
			// プラモデル名
			rowData.setPlmdlnm(rSet.getString(2));
			
			// 出力データリストに行データを設定
			outData.add(rowData);
		}
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
			if (rSet != null) {
				rSet.close();
			}
		} catch (SQLException e) {
			// 例外発生を記録
			String errMsg = sqlId + ":リソースのクローズ処理時に例外発生（異常終了とはしない）";

			// 実行結果は更新せず
			comData.setErrorData(e, errMsg);
		}
	}

}
