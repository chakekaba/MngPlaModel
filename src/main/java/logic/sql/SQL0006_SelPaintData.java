package logic.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.model.MdlCommonData;
import logic.sql.model.SQL0006In;
import logic.sql.model.SQL0006Out;

/**
 * 塗料情報取得SQL
 * @author kk-ma
 *
 */
public class SQL0006_SelPaintData {
	
	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(SQL0006_SelPaintData.class.getName());

	/** SQL文**/
	protected static final String sql = 
			"select\n"
			+ "  paintview.paintid,\n"
			+ "  coalesce(brand.brandnm || '  ', '') ||\n"
			+ "  coalesce(ptype.producttypenm || '  ', '') ||\n"
			+ "  coalesce(paintview.colorcode || '  ', '') ||\n"
			+ "  coalesce(paintview.colornm, '')\n"
			+ "  as paintdata\n"
			+ "from\n"
			+ "  paintview\n"
			+ "left join mst0000_brand brand on\n"
			+ "  brand.brandid = paintview.brandid\n"
			+ "left join mst0001_company company on\n"
			+ "  company.compid = brand.compid\n"
			+ "left join mst0002_producttype ptype on\n"
			+ "  ptype.producttypeid = brand.producttypeid\n"
			+ "where\n"
			+ "  paintview.paintid = ?";
	
	/** SQLid **/
	protected static final String sqlId = "SQL0006";
	
	/** PreparedStatement オブジェクト **/
	protected PreparedStatement pstmt = null;
	
	/** SQL結果リスト **/
	protected ResultSet rSet = null;
	
	/**
	 * コンストラクタ
	 */
	public SQL0006_SelPaintData() {
	}
	
	/**
	 * 動作確認テスト
	 * @param args
	 */
	public static void main(String[] args) {
		DbConnection conn = new DbConnection();
		MdlCommonData comData = new MdlCommonData();
		
		SQL0006_SelPaintData sql0006 = new SQL0006_SelPaintData();
		SQL0006In inData = new SQL0006In();
		SQL0006Out outData = new SQL0006Out();
		
		inData.setPaintid("amX-11");
		
		try {
			conn.connect();
			sql0006.execute(conn, comData, inData, outData);
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			System.out.println(outData.getPaintid());
			System.out.println(outData.getPaintData());
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
			SQL0006In inData,
			SQL0006Out outData) {
		
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
			SQL0006In inData) throws SQLException {

		pstmt = conn.getStatement(sql);
		
		// 塗料ID
		pstmt.setString(1, inData.getPaintid());

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
	protected void editOut(SQL0006Out outData) throws SQLException {

		rSet.next();
		
		// 塗料ID
		outData.setPaintid(rSet.getString(1));
		
		// 塗料情報
		outData.setPaintData(rSet.getString(2));

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
