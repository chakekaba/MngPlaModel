package logic.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.model.MdlCommonData;
import logic.sql.model.SQL0005In;
import logic.sql.model.SQL0005Out;

/**
 * ブランド情報取得SQL
 * @author kk-ma
 *
 */
public class SQL0005_SelBrandData {
	
	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(SQL0005_SelBrandData.class.getName());

	/** SQL文**/
	protected static final String sql = 
			"select\n"
			+ "    brand.brandid,\n"
			+ "    coalesce(brand.brandnm || '  ', '') ||\n"
			+ "    '(' ||\n"
			+ "    coalesce(company.compnm || '  ', '') ||\n"
			+ "    '/' ||\n"
			+ "    coalesce(ptype.abbr || '  ', '') ||\n"
			+ "    ')'\n"
			+ "    as brandData\n"
			+ "from\n"
			+ "    mst0000_brand brand\n"
			+ "left join mst0001_company company on\n"
			+ "    company.compid = brand.compid\n"
			+ "left join mst0002_producttype ptype on\n"
			+ "    ptype.producttypeid = brand.producttypeid\n"
			+ "where\n"
			+ "    brand.brandid = ?";
	
	/** SQLid **/
	protected static final String sqlId = "SQL0005";
	
	/** PreparedStatement オブジェクト **/
	protected PreparedStatement pstmt = null;
	
	/** SQL結果リスト **/
	protected ResultSet rSet = null;
	
	/**
	 * コンストラクタ
	 */
	public SQL0005_SelBrandData() {
	}
	
	/**
	 * 動作確認テスト
	 * @param args
	 */
	public static void main(String[] args) {
		DbConnection conn = new DbConnection();
		MdlCommonData comData = new MdlCommonData();
		
		SQL0005_SelBrandData sql0005 = new SQL0005_SelBrandData();
		SQL0005In inData = new SQL0005In();
		SQL0005Out outData = new SQL0005Out();
		
		inData.setBrandid("am");
		
		try {
			conn.connect();
			sql0005.execute(conn, comData, inData, outData);
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			System.out.println(outData.getBrandid());
			System.out.println(outData.getBrandData());
		}
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
			SQL0005In inData,
			SQL0005Out outData) {
		
		logger.setLevel(Level.INFO);
		logger.log(Level.INFO, sqlId + ":開始");
		

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

		logger.log(Level.INFO, sqlId + ":終了");
	}

	/**
	 * Statement編集処理
	 * @param conn
	 * @param inData
	 * @throws SQLException
	 */
	protected void editStatement(
			DbConnection conn,
			SQL0005In inData) throws SQLException {

		pstmt = conn.getStatement(sql);
		
		// ブランドID
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
	protected void editOut(SQL0005Out outData) throws SQLException {

		rSet.next();
		
		// ブランドID
		outData.setBrandid(rSet.getString(1));
		
		// ブランド情報
		outData.setBrandData(rSet.getString(2));

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
