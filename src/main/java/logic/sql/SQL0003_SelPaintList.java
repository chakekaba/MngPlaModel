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
import logic.sql.model.SQL0003In;
import logic.sql.model.SQL0003Out;


/**
 * 塗料一覧リスト取得SQL
 * @author kohei kajiki
 *
 */
public class SQL0003_SelPaintList {

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(SQL0000_connectionCheck.class.getName());

	/** SQL文**/
	protected static final String sql = 
			"select\r\n"
			+ "    brand.brandnm,\r\n"
			+ "    paint.colorcode,\r\n"
			+ "    paint.colornm,\r\n"
			+ "    case paint.posession\r\n"
			+ "        when '0' then '未所持'\r\n"
			+ "        else '所持'\r\n"
			+ "    end as posession,\r\n"
			+ "    COALESCE(appaint.colorcode, ' '),\r\n"
			+ "    COALESCE(appaint.colornm, ' ')\r\n"
			+ "from paintview paint\r\n"
			+ "left join mst0000_brand brand\r\n"
			+ "    on brand.brandid = paint.brandid\r\n"
			+ "left join paintview appaint\r\n"
			+ "    on appaint.paintid = paint.appaintid\r\n"
			+ "where 1 = 1\r\n"
			+ "    and paint.colornm like ?\r\n"
			+ "    and brand.brandid like ?\r\n"
//			+ "  and \r\n"
			+ "order by\r\n"
			+ "    brand.brandnm,\r\n"
			+ "    paint.ccode_str,\r\n"
			+ "    paint.ccode_num";
	
	/** SQLid **/
	protected static final String sqlId = "SQL0003";
	
	/** PreparedStatement オブジェクト **/
	protected PreparedStatement pstmt = null;
	
	/** SQL結果リスト **/
	protected ResultSet rSet = null;

	/**
	 * コンストラクタ
	 */
	public SQL0003_SelPaintList() {
	}
	
	
	/**
	 * 単体テスト
	 * @param args
	 */
	public static void main(String[] args) {
		DbConnection conn = new DbConnection();
		MdlCommonData comData = new MdlCommonData();
		SQL0003_SelPaintList sql0003 = new SQL0003_SelPaintList();
		SQL0003In inData = new SQL0003In();
		List<SQL0003Out> outDataList = new ArrayList<SQL0003Out>();
		
		inData.setBrandid("%%");
//		inData.setBrandid("%mc%");
		inData.setColornm("%%");
//		inData.setColornm("%グレ%");
		
		try {
			conn.connect();
			sql0003.execute(conn, comData, inData, outDataList);
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		for (SQL0003Out outData: outDataList) {
			
			System.out.println(outData.getBrandnm());
			System.out.println(outData.getColorcode());
			System.out.println(outData.getColornm());
			System.out.println(outData.getPosession());
			System.out.println(outData.getApcolorcode());
			System.out.println(outData.getApcolornm());
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
			SQL0003In inData,
			List<SQL0003Out> outData) {
		
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
			SQL0003In inData) throws SQLException {
		pstmt = conn.getStatement(sql);
		
		// 塗料名
		pstmt.setString(1, inData.getColornm());
		
		// ブランドID
		pstmt.setString(2, inData.getBrandid());
		
//		// プラモデルID
//		pstmt.setString(3, inData.getPlmdlid());
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
	private void editOut(List<SQL0003Out> outData) throws SQLException {

		// 行データの定義
		SQL0003Out rowData;

		while (rSet.next()) {
			
			rowData = new SQL0003Out();
			
			// ブランド名
			rowData.setBrandnm(rSet.getString(1));
			
			// カラーコード
			rowData.setColorcode(rSet.getString(2));
			
			// カラー名
			rowData.setColornm(rSet.getString(3));
			
			// 所持
			rowData.setPosession(rSet.getString(4));
			
			// 近似カラーコード
			rowData.setApcolorcode(rSet.getString(5));
			
			// 近似カラー名
			rowData.setApcolornm(rSet.getString(6));

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
