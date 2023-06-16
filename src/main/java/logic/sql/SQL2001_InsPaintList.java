package logic.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.model.MdlCommonData;
import logic.sql.model.SQL0003In;
import logic.sql.model.SQL0003Out;
import logic.sql.model.SQL2001In;
import logic.sql.model.SQL2001Out;

/**
 * 塗料一覧登録SQL
 * @author kk-ma
 *
 */
public class SQL2001_InsPaintList {

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(SQL2001_InsPaintList.class.getName());

/** SQL文**/
	protected static final String sql = 
			"insert into tbl0000_paint(\n"
			+ "    brandid,\n"
			+ "    colorcode,\n"
			+ "    colornm,\n"
			+ "    posession,\n"
			+ "    selvisible,\n"
			+ "    appaintid\n"
			+ ") values (\n"
			+ "    ?,\n"
			+ "    ?,\n"
			+ "    ?,\n"
			+ "    ?,\n"
			+ "    ?,\n"
			+ "    ?\n"
			+ ")";

	/** SQLid **/
	protected static final String sqlId = "SQL2001";
	
	/** PreparedStatement オブジェクト **/
	protected PreparedStatement pstmt = null;
	
	/** SQL結果リスト **/
//	protected ResultSet rSet = null;

	/**
	 * コンストラクタ
	 */
	public SQL2001_InsPaintList() {
	}
	
	/**
	 * 動作確認
	 * @param args
	 */
	public static void main(String[] args) {
		DbConnection conn = new DbConnection();
		MdlCommonData comData = new MdlCommonData();
		SQL2001_InsPaintList sql2001 = new SQL2001_InsPaintList();
		SQL2001In inData = new SQL2001In();
		SQL2001Out outData = new SQL2001Out();
		
		SQL0003_SelPaintList sql0003 = new SQL0003_SelPaintList();
		SQL0003In selInData = new SQL0003In();
		List<SQL0003Out> selOutDataList = new ArrayList<SQL0003Out>();
		
		
		
		String brandid = "mc";
		String colorcode = "Ccode";
		String colornm = "テスト登録";
		String appaintid = "amX-10";
		inData.setBrandid(brandid);
		inData.setColorcode(colorcode);
		inData.setColornm(colornm);
		inData.setPosession("1");
		inData.setSelvisible("1");
		inData.setAppaintid(appaintid);
		
		selInData.setBrandid("%%");
		selInData.setColornm("%" + colornm + "%");
//		selInData.setColornm("%%");
		
		
		
		try {
			conn.connect();
			sql2001.execute(conn, comData, inData, outData);
			
			sql0003.execute(conn, comData, selInData, selOutDataList);
			
			
			conn.close(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(selOutDataList.size());
		for (SQL0003Out selOutData: selOutDataList) {
			
			System.out.println(selOutData.getBrandnm());
			System.out.println(selOutData.getColorcode());
			System.out.println(selOutData.getColornm());
			System.out.println(selOutData.getPosession());
			System.out.println(selOutData.getApcolorcode());
			System.out.println(selOutData.getApcolornm());
			System.out.println("----------------------\r\n");
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
			SQL2001In inData,
			SQL2001Out outData) {
		
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
	private void editStatement(
			DbConnection conn,
			SQL2001In inData) throws SQLException {
		
		pstmt = conn.getStatement(sql);
		
		// ブランドID
		pstmt.setString(1, inData.getBrandid());
		
		// カラーコード
		pstmt.setString(2, inData.getColorcode());
		
		// カラー名
		pstmt.setString(3, inData.getColornm());
		
		// 所持
		pstmt.setString(4, inData.getPosession());
		
		// 選択肢表示
		pstmt.setString(5, inData.getSelvisible());
		
		// 近似塗料ID
		pstmt.setString(6, inData.getAppaintid());
	}

	/**
	 * SQL実行処理
	 * @throws SQLException
	 */
	private void executeSQL() throws SQLException {
		
		pstmt.executeUpdate();
	}

	
	/**
	 * 出力データ編集処理
	 * @param outData
	 * @throws SQLException
	 */
	private void editOut(SQL2001Out outData) throws SQLException {
		
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
//			if (rSet != null) {
//				rSet.close();
//			}
		} catch (SQLException e) {
			// 例外発生を記録
			String errMsg = sqlId + ":リソースのクローズ処理時に例外発生（異常終了とはしない）";

			// 実行結果は更新せず
			comData.setErrorData(logger, Level.INFO, e, errMsg);
		}
	}
}
