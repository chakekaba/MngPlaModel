package logic.sv;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ParamIdWeb;
import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.model.MdlCommonData;
import logic.sql.SQL0001_SelBrandList;
import logic.sql.SQL0004_SelPaintPulldown;
import logic.sql.model.SQL0001In;
import logic.sql.model.SQL0001Out;
import logic.sql.model.SQL0004In;
import logic.sql.model.SQL0004Out;
import logic.sv.model.MdlLogic02010In;
import logic.sv.model.MdlLogic02010Out;

public class Logic02010 {
	
	/** データベース接続クラス **/
	protected DbConnection dbconn = null;

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(Logic02010.class.getName());
	
	/** ロジックID **/
	protected final String logicId = "Logic02010";
	
	/** コンストラクタ **/
	public Logic02010() {
	}

	/**
	 * メイン実行処理
	 * @param inputData
	 * @param outputData
	 * @param comData
	 */
	public void execute(
			MdlLogic02010In inputData,
			MdlLogic02010Out outputData,
			MdlCommonData comData) {

		logger.setLevel(Level.INFO);
		logger.log(Level.INFO, logicId + ":開始");
		
		try {
			// DB接続
			dbconn = new DbConnection();
			dbconn.connect();

			doSql_SQL0001(inputData, outputData, comData);
			
			doSql_SQL0004(inputData, outputData, comData);
			
			editSetOutputData(inputData, outputData, comData);
			
		} catch (Exception e) {
			String msg = "塗料登録画面で想定外のエラーが発生";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.SEVERE, e, msg);
		} finally {
			
			// DB切断
			try {
				if (dbconn != null) {
					dbconn.close();
				}
			} catch (SQLException e) {
				// 何も処理しない
			}

		}
		
		logger.log(Level.INFO, logicId + ":終了");
	}

	/**
	 * ブランドリスト取得SQL実行
	 * @param comData
	 * @throws Exception
	 */
	protected void doSql_SQL0001(
			MdlLogic02010In inputData,
			MdlLogic02010Out outputData,
			MdlCommonData comData) {
		
		SQL0001_SelBrandList sql0001 = new SQL0001_SelBrandList();
		SQL0001In sqlin = new SQL0001In();
		List<SQL0001Out> sqlout = new ArrayList<SQL0001Out>();
		
		sqlin.setSqlNo(SQL0001_SelBrandList.SQL_NO1);
		
		sql0001.execute(dbconn, comData, sqlin, sqlout);
		
		// 例外発生の場合DBロールバック実行
		if (!comData.getResult().equals(ResultConstant.NORMAL)) {
			// ロールバック実行
			try {
				dbconn.roolback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 出力データにブランドリストを設定
		outputData.setBrandList(sqlout);
	}

	/**
	 * 近似塗料リスト取得SQL実行
	 * @param comData
	 * @throws Exception
	 */
	protected void doSql_SQL0004(
			MdlLogic02010In inputData,
			MdlLogic02010Out outputData,
			MdlCommonData comData) {
		
		SQL0004_SelPaintPulldown sql0004 = new SQL0004_SelPaintPulldown();
		SQL0004In sqlin = new SQL0004In();
		List<SQL0004Out> sqlout = new ArrayList<SQL0004Out>();
		
		// SQL0004実行
		sql0004.execute(dbconn, comData, sqlin, sqlout);
		
		// 例外発生の場合DBロールバック実行
		if (!comData.getResult().equals(ResultConstant.NORMAL)) {
			// ロールバック実行
			try {
				dbconn.roolback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 実行結果リスト設定
		outputData.setAppaintList(sqlout);
	}
	
	protected void editSetOutputData(
			MdlLogic02010In inputData,
			MdlLogic02010Out outputData,
			MdlCommonData comData) {
		
		// 処理パターンが'POST'（登録差し戻し）の場合
		if (ParamIdWeb.View02010.POST.equals(inputData.getLogicPtn())) {
			
			// ブランドID
			outputData.setBrandid(inputData.getBrandid());
			
			// カラーコード
			outputData.setColorcode(inputData.getColorcode());
			
			// カラー名
			outputData.setColornm(inputData.getColornm());
			
			// 所持
			outputData.setPosession(inputData.getPosession());
			
			// 選択肢表示
			outputData.setSelvisible(inputData.getSelvisible());
			
			// 近似塗料ID
			outputData.setAppaintid(inputData.getAppaintid());
		}
		
	}
	
}
