package logic.sv;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ResultConstant;
import base.logic.CheckUtil;
import base.logic.DbConnection;
import base.logic.ExceptionLogic;
import base.model.MdlCommonData;
import logic.sql.SQL0001_SelBrandList;
import logic.sql.SQL0002_SelPlmdlList;
import logic.sql.SQL0003_SelPaintList;
import logic.sql.SQL1002_CntBrand;
import logic.sql.model.SQL0001In;
import logic.sql.model.SQL0001Out;
import logic.sql.model.SQL0002In;
import logic.sql.model.SQL0002Out;
import logic.sql.model.SQL0003In;
import logic.sql.model.SQL0003Out;
import logic.sql.model.SQL1002In;
import logic.sql.model.SQL1XXXCntOut;
import logic.sv.model.MdlLogic02000In;
import logic.sv.model.MdlLogic02000Out;

public class Logic02000 {
	
	/** ロジック処理内データ **/
	// 不要
	
	/** データベース接続クラス **/
	protected DbConnection dbconn = null;

	/** 処理ID **/
	protected String logicId = "Logic02000";

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(Logic02000.class.getName());
	
	/** コンストラクタ **/
	public Logic02000() {
	}

	/**
	 * メイン実行処理
	 * @param inputData
	 * @param outputData
	 * @param comData
	 */
	public void execute(
			MdlLogic02000In inputData,
			MdlLogic02000Out outputData,
			MdlCommonData comData) {
		
		logger.setLevel(Level.INFO);
		logger.log(Level.INFO, logicId + ":開始");

		try {
			// DB接続
			dbconn = new DbConnection();
			dbconn.connect();

			checkInputData(inputData, comData);
			
			doSql_SQL0001(inputData, outputData, comData);
			
			doSql_SQL0002(inputData, outputData, comData);
			
			// 検索実行フラグ = true:検索実行 の場合
			if (inputData.getSearchExeFlg()) {
				
				doSql_SQL0003(inputData, outputData, comData);
			}
			
			editSetOutputData(inputData, outputData, comData);
			
		} catch (Exception e) {
			String msg = "塗料一覧で想定外のエラーが発生";
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
	 * 入力値チェック処理
	 * @param inputData
	 * @param comData
	 */
	protected void checkInputData(
			MdlLogic02000In inputData,
			MdlCommonData comData) {
		
		// 検索実行フラグが true:検索実行 の場合
		if (inputData.getSearchExeFlg()) {
			
			/** 塗料名 **/
			String colornm = inputData.getColornm();
			
			// 文字列長チェック
			int maxLenColornm = 20;
			
			if (!CheckUtil.lengthCheck(colornm, maxLenColornm)) {
				String msg = "文字列長チェックエラー：カラーコード";
				comData.setResult(ResultConstant.LOGIC_ERROR);
				comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
			}
			
			/** ブランドID **/
			String brandid = inputData.getBrandid();
			
			// 設定可能値チェック
			// ブランドIDが入力されている場合
			if (CheckUtil.requiredCheck(brandid)) {
				
				SQL1002_CntBrand sql1002 = new SQL1002_CntBrand();
				SQL1002In sql1002In = new SQL1002In();
				SQL1XXXCntOut sqlout = new SQL1XXXCntOut();
				
				sql1002In.setSqlNo(SQL1002_CntBrand.SQL_NO1);
				sql1002In.setBrandid(inputData.getBrandid());
				
				sql1002.execute(dbconn, comData, sql1002In, sqlout);
				
				// 設定可能値でない場合
				if (sqlout.getRowCnt() != 1) {
					String msg = "設定可能値チェックエラー：ブランドID";
					comData.setResult(ResultConstant.LOGIC_ERROR);
					comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
				}
			}
			
			/** プラモデルID **/
			String plmdlid = inputData.getPlmdlid();
			
			// 設定可能値チェック
			// プラモデルIDが入力されている場合
			if (CheckUtil.requiredCheck(plmdlid)) {
				/** 省略（暫定）**/
			}
		}
	}
	
	/**
	 * 検索条件用ブランドリスト取得SQL実行
	 * @param inputData
	 * @param outputData
	 * @param comData
	 * @throws Exception
	 */
	protected void doSql_SQL0001(
			MdlLogic02000In inputData,
			MdlLogic02000Out outputData,
			MdlCommonData comData) throws Exception {
		
		SQL0001_SelBrandList sql0001 = new SQL0001_SelBrandList();
		SQL0001In sqlin = new SQL0001In();
		List<SQL0001Out> sqlout = new ArrayList<SQL0001Out>();
		
		sqlin.setSqlNo(SQL0001_SelBrandList.SQL_NO1);
		
		sql0001.execute(dbconn, comData, sqlin, sqlout);
		
		// 例外発生の場合DBロールバック実行
		if (!comData.getResult().equals(ResultConstant.NORMAL)) {
			dbconn.roolback();
		}
		
		// 出力データにブランドリストを設定
		outputData.setBrandList(sqlout);
	}
	
	/**
	 * 検索条件用プラモデルリスト取得SQL実行
	 * @param inputData
	 * @param outputData
	 * @param comData
	 * @throws Exception
	 */
	protected void doSql_SQL0002(
			MdlLogic02000In inputData,
			MdlLogic02000Out outputData,
			MdlCommonData comData) throws Exception {
		
		SQL0002_SelPlmdlList sql0002 = new SQL0002_SelPlmdlList();
		SQL0002In sqlin = new SQL0002In();
		List<SQL0002Out> sqlout = new ArrayList<SQL0002Out>();
		
		sql0002.execute(dbconn, comData, sqlin, sqlout);
		
		// 例外発生の場合DBロールバック実行
		if (!comData.getResult().equals(ResultConstant.NORMAL)) {
			dbconn.roolback();
		}
		
		// 出力データにプラモデルリストを設定
		outputData.setPlmdlList(sqlout);
	}

	/**
	 * 塗料一覧リスト取得SQL実行
	 * @param inputData
	 * @param outputData
	 * @param comData
	 * @throws Exception
	 */
	protected void doSql_SQL0003(
			MdlLogic02000In inputData,
			MdlLogic02000Out outputData,
			MdlCommonData comData) throws Exception {
		SQL0003_SelPaintList sql0003 = new SQL0003_SelPaintList();
		SQL0003In sqlin = new SQL0003In();
		List<SQL0003Out> sqlout = new ArrayList<SQL0003Out>();
		
		// 塗料名
		String colornm = "%" + inputData.getColornm() + "%";;
				
		sqlin.setColornm(colornm);
		
		// ブランドID
		String brandid;
		
		if (inputData.getBrandid().equals("")) {
			// 全選択
			brandid = "%%";
		}else {
			// 一致条件
			brandid = inputData.getBrandid();
		}
		
		sqlin.setBrandid(brandid);
		
		// プラモデルID
		String plmdlid;
		
		if (inputData.getPlmdlid().equals("")) {
			// 全選択
			plmdlid = "%%";
		}else {
			// 一致条件
			plmdlid = inputData.getPlmdlid();
		}
		
		sqlin.setPlmdlid(plmdlid);

		// SQL実行
		sql0003.execute(dbconn, comData, sqlin, sqlout);
		
		// 例外発生の場合DBロールバック実行
		if (!comData.getResult().equals(ResultConstant.NORMAL)) {
			dbconn.roolback();
		}
		
		// 出力データにプラモデルリストを設定
		outputData.setResultList(sqlout);
	}
	
	/**
	 * 出力データ編集処理
	 * @param inputData
	 * @param outputData
	 * @param comData
	 */
	protected void editSetOutputData(
			MdlLogic02000In inputData,
			MdlLogic02000Out outputData,
			MdlCommonData comData) {
		
		// 塗料名
		outputData.setColornm(inputData.getColornm());
		
		// ブランドID
		outputData.setBrandid(inputData.getBrandid());
		
		// プラモデルID
		outputData.setPlmdlid(inputData.getPlmdlid());
		
	}

}
