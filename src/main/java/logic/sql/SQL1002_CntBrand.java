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
 * 塗料IDカウント用SQL（検索対象：ブランドID）
 * @author kk-ma
 *
 */
public class SQL1002_CntBrand {
	
	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(SQL1002_CntBrand.class.getName());

	/** SQL文1_全対象 **/
	protected final String sqlNo0 =
			"select\n"
			+ "    count(*)\n"
			+ "from\n"
			+ "    mst0000_brand\n"
			+ "where\n"
			+ "    brandid = ?";

	/** SQL番号0_全対象 **/
	public static final int SQL_NO0 = 0;

	/** SQL文1_種別:塗料 **/
	protected final String sqlNo1 =
			"select\n"
			+ "    count(*)\n"
			+ "from\n"
			+ "    mst0000_brand\n"
			+ "where\n"
			+ "    brandid = ?\n"
			+ "    and producttypeid in ('lcr','acl','ena')";
	
	/** SQL番号1_種別:塗料 **/
	public static final int SQL_NO1 = 1;
	
	protected final String[] sqlList = {
			sqlNo0,
			sqlNo1
	};

	/** SQLid **/
	protected static final String sqlId = "SQL1002";
	
	/** PreparedStatement オブジェクト **/
	protected PreparedStatement pstmt = null;
	
	/** SQL結果リスト **/
	protected ResultSet rSet = null;
	
	/**
	 * コンストラクタ
	 */
	public SQL1002_CntBrand() {
	}
	
	/**
	 * 動作確認テスト
	 * @param args
	 */
	public static void main(String[] args) {
		
		DbConnection conn = new DbConnection();
		MdlCommonData comData = new MdlCommonData();
		
		SQL1002In sqlin = new SQL1002In();
		SQL1XXXCntOut sqlout = new SQL1XXXCntOut();
		SQL1002_CntBrand sql1002 = new SQL1002_CntBrand();
		
//		sqlin.setSqlNo(SQL_NO0);
		sqlin.setSqlNo(SQL_NO1);
//		sqlin.setBrandid("p1");
		sqlin.setBrandid("mc");
		
		try {
			conn.connect();
			sql1002.execute(conn, comData, sqlin, sqlout);
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			
			System.out.println("行数：" + sqlout.getRowCnt());
		}
		
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
	protected void editStatement(
			DbConnection conn,
			SQL1002In inData) throws SQLException {

		String sql = sqlList[inData.getSqlNo()];
		
		pstmt = conn.getStatement(sql);
		
		pstmt.setString(1, inData.getBrandid());
	}

	/**
	 * SQL実行処理
	 * @throws SQLException
	 */
	protected void executeSQL() throws SQLException {
		
		rSet = pstmt.executeQuery();
	}

	/**
	 * 出力データ編集処理
	 * @param outData
	 * @throws SQLException
	 */
	protected void editOut(SQL1XXXCntOut outData) throws SQLException {

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
	protected void close(MdlCommonData comData) {
		
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
