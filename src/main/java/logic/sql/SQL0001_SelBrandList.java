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
import logic.sql.model.SQL0001In;
import logic.sql.model.SQL0001Out;

/** 検索条件用ブランドリスト取得SQL **/
public class SQL0001_SelBrandList {
	
	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(SQL0000_connectionCheck.class.getName());

	/** SQL文0_全対象 **/
	protected final String sqlNo0 = 
			"select\r\n"
			+ "  brandid,\r\n"
			+ "  coalesce(brand.brandnm || ' ', '') ||\r\n"
			+ "  '(' ||\r\n"
			+ "  coalesce(company.compnm || ' ', '') ||\r\n"
			+ "  '/' ||\r\n"
			+ "  coalesce(ptype.abbr || ' ', '') ||\r\n"
			+ "  ')'\r\n"
			+ "  as brandData\r\n"
			+ "from mst0000_brand brand\r\n"
			+ "left join mst0001_company company on\r\n"
			+ "  company.compid = brand.compid\r\n"
			+ "left join mst0002_producttype ptype on\r\n"
			+ "  ptype.producttypeid = brand.producttypeid\r\n"
			+ "order by brand.brandid";
	
	/** SQL番号0_全対象 **/
	public static final int SQL_NO0 = 0;

	/** SQL文1_種別:塗料 **/
	protected final String sqlNo1 =
			"select\r\n"
			+ "  brandid,\r\n"
			+ "  coalesce(brand.brandnm || ' ', '') ||\r\n"
			+ "  '(' ||\r\n"
			+ "  coalesce(company.compnm || ' ', '') ||\r\n"
			+ "  '/' ||\r\n"
			+ "  coalesce(ptype.abbr || ' ', '') ||\r\n"
			+ "  ')'\r\n"
			+ "  as brandData\r\n"
			+ "from mst0000_brand brand\r\n"
			+ "left join mst0001_company company on\r\n"
			+ "  company.compid = brand.compid\r\n"
			+ "left join mst0002_producttype ptype on\r\n"
			+ "  ptype.producttypeid = brand.producttypeid\r\n"
			+ "where\r\n"
			+ "  brand.producttypeid in ('lcr','acl','ena')\r\n"
			+ "order by brand.brandid";
	
	/** SQL番号1_種別:塗料 **/
	public static final int SQL_NO1 = 1;
	
	/** SQL格納リスト **/
	protected final String[] sqlList = {
			sqlNo0,
			sqlNo1
	};

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
	 * 動作確認テスト用
	 * @param args
	 */
	public static void main(String[] args) {
		
		DbConnection conn = new DbConnection();
		MdlCommonData comData = new MdlCommonData();
		
		SQL0001_SelBrandList sql0001 = new SQL0001_SelBrandList();
		SQL0001In sqlin = new SQL0001In();
		List<SQL0001Out> sqloutList = new ArrayList<SQL0001Out>();
		
		sqlin.setSqlNo(SQL_NO0);
//		sqlin.setSqlNo(SQL_NO1);
		
		try {
			conn.connect();
			sql0001.execute(conn, comData, sqlin, sqloutList);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			
			System.out.println("行数：" + sqloutList.size());
			
			for (SQL0001Out sqlout: sqloutList) {

				System.out.println("-----------------\r\n");
				System.out.println(sqlout.getBrandid());
				System.out.println(sqlout.getBrandData());
			}
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
			SQL0001In inData,
			List<SQL0001Out> outData) {
		
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
			SQL0001In inData) throws SQLException {
		
		String sql = sqlList[inData.getSqlNo()];
		
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
			rowData.setBrandData(rSet.getString(2));
			
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
