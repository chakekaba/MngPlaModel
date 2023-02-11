package logic.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.model.MdlCommonData;
import logic.sql.model.SQL0001In;
import logic.sql.model.SQL0001Out;

/** 検索条件用ブランドリスト取得SQL **/
public class SQL0001_SelBrandList {
	
	/** SQL文**/
	protected static final String sql = 
			"select\r\n"
			+ "  brandid,\r\n"
			+ "  brandnm\r\n"
			+ "from mst0000_brand\r\n"
			+ "order by brandid";
	
	/** SQLid **/
	protected static final String sqlId = "SQL0001";
	
	/** PreparedStatement オブジェクト **/
	protected PreparedStatement pstmt = null;
	
	/** SQL結果リスト **/
	protected ResultSet rSet = null;
	
	/**
	 * コンストラクタ
	 */
	public SQL0001_SelBrandList() {
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
			SQL0001In inData,
			List<SQL0001Out> outData) {
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
			SQL0001In inData) throws SQLException {
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
	private void editOut(List<SQL0001Out> outData) throws SQLException {

		// 行データの定義
		SQL0001Out rowData;

		while (rSet.next()) {
			
			rowData = new SQL0001Out();
			
			// ブランドID
			rowData.setBrandid(rSet.getString(1));
			
			// ブランド名
			rowData.setBrandnm(rSet.getString(2));
			
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
