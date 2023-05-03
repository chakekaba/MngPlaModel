package logic.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.model.MdlCommonData;
import logic.sql.model.SQL1002In;
import logic.sql.model.SQL1XXXCntOut;


/**
 * 塗料一覧行数カウント用SQL2（近似塗料ID）
 * @author kk-ma
 */
public class SQL1002_CntPaintView_2 {
	
	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(SQL1002_CntPaintView_2.class.getName());

	/** SQL文**/
	protected static final String sql =
			"select\n"
			+ "    count(*)\n"
			+ "from paintView\n"
			+ "where\n"
			+ "    paintid = ?\n";

	/** SQLid **/
	protected static final String sqlId = "SQL1002";
	
	/** PreparedStatement オブジェクト **/
	protected PreparedStatement pstmt = null;
	
	/** SQL結果リスト **/
	protected ResultSet rSet = null;
	
	/**
	 * コンストラクタ
	 */
	public SQL1002_CntPaintView_2() {
	}
	
	/**
	 * 動作確認
	 * @param args
	 */
	public static void main(String[] args) {
		DbConnection conn = new DbConnection();
		MdlCommonData comData = new MdlCommonData();
		SQL1002_CntPaintView_2 sql1002 = new SQL1002_CntPaintView_2();
		SQL1002In inData = new SQL1002In();
		SQL1XXXCntOut outData = new SQL1XXXCntOut();

//		inData.setPaintid("amX-10");
		inData.setPaintid("xxxxxx");
		
		try {
			conn.connect();
			sql1002.execute(conn, comData, inData, outData);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("行数：" + outData.getRowCnt());

	}
	
	/**
	 * メイン実行処理
	 * @param conn
	 * @param comData
	 * @param inData
	 * @param outData
	 */
	public void execute(
			DbConnection conn,
			MdlCommonData comData,
			SQL1002In inData,
			SQL1XXXCntOut outData) {
		
		logger.setLevel(Level.INFO);
		
		try {
			editStatement(conn, inData);
			
			executeSQL();
			
			editOut(outData);
			
		} catch (Exception e) {
			String errMsg = sqlId + ":SQL実行時に例外発生";
			
			comData.setResult(ResultConstant.SQL_ERROR);
			comData.setErrorData(logger, Level.WARNING, e, errMsg);
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
			SQL1002In inData) throws SQLException {
		
		pstmt = conn.getStatement(sql);
		
		// 近似塗料ID
		pstmt.setString(1, inData.getPaintid());
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
	 * @throws SQLException
	 */
	private void editOut(SQL1XXXCntOut outData) throws SQLException {

		ResultSetMetaData metaData = rSet.getMetaData();
		
		// ResultSetの取得行が1行以外の場合
		if (metaData.getColumnCount() != 1) {
			throw new SQLException();
		}
		

		// 行数取得
		rSet.next();
		outData.setRowCnt(rSet.getInt(1));
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
			comData.setErrorData(logger, Level.INFO, e, errMsg);
		}
	}

}
