package logic.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.model.MdlCommonData;
import logic.sql.model.SQL0004In;
import logic.sql.model.SQL0004Out;

/**
 * プルダウンメニュー用近似塗料リスト取得SQL
 * @author kk-ma
 *
 */
public class SQL0004_SelPaintPulldown {

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(SQL0004_SelPaintPulldown.class.getName());

	/** SQL文**/
	protected static final String sql = 
			"select\n"
			+ "    paintview.paintid,\n"
			+ "    coalesce(brand.brandnm || '  ', '') ||\n"
			+ "    coalesce(ptype.producttypenm || '  ', '') ||\n"
			+ "    coalesce(paintview.colorcode || '  ', '') ||\n"
			+ "    coalesce(paintview.colornm, '')\n"
			+ "    as paintdata\n"
			+ "from\n"
			+ "    paintview\n"
			+ "left join mst0000_brand brand on\n"
			+ "    brand.brandid = paintview.brandid\n"
			+ "left join mst0001_company company on\n"
			+ "    company.compid = brand.compid\n"
			+ "left join mst0002_producttype ptype on\n"
			+ "    ptype.producttypeid = brand.producttypeid\n"
			+ "where\n"
			+ "    paintview.selvisible = '1'\n"
			+ "order by \n"
			+ "    paintview.paintid";
	
	/** SQLid **/
	protected static final String sqlId = "SQL0004";
	
	/** PreparedStatement オブジェクト **/
	protected PreparedStatement pstmt = null;
	
	/** SQL結果リスト **/
	protected ResultSet rSet = null;

	/**
	 * コンストラクタ
	 */
	public SQL0004_SelPaintPulldown() {
		
	}
	
	/**
	 * 単体テスト
	 * @param args
	 */
	public static void main(String[] args) {
		
		DbConnection conn = new DbConnection();
		MdlCommonData comData = new MdlCommonData();
		SQL0004_SelPaintPulldown sql0004 = new SQL0004_SelPaintPulldown();
		SQL0004In inData = new SQL0004In();
		List<SQL0004Out> outDataList = new ArrayList<SQL0004Out>();
		
		try {
			conn.connect();
			sql0004.execute(conn, comData, inData, outDataList);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("行数：" + outDataList.size());
		System.out.println("----------------------\r\n");
		
		for (SQL0004Out outData: outDataList) {
			System.out.println(outData.getPaintid());
			System.out.println(outData.getPaintdata());
			System.out.println("----------------------\r\n");
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
			SQL0004In inData,
			List<SQL0004Out> outData) {
		
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
			SQL0004In inData) throws SQLException {
		
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
	 * @throws SQLException
	 */
	private void editOut(List<SQL0004Out> outData) throws SQLException {
		
		// 行データの定義
		SQL0004Out rowData;
		
		while (rSet.next()) {
			
			rowData = new SQL0004Out();
			
			// 塗料ID
			rowData.setPaintid(rSet.getString(1));
			
			// 塗料情報
			rowData.setPaintdata(rSet.getString(2));
			
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
			comData.setErrorData(logger, Level.INFO, e, errMsg);
		}
	}

}
