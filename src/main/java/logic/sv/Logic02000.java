package logic.sv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.constant.ParamIdWeb;
import base.constant.ResultConstant;
import base.logic.CheckUtil;
import base.logic.DbConnection;
import base.logic.ExceptionLogic;
import base.logic.ServerLogic;
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

public class Logic02000 extends ServerLogic {

	/** 入力データ **/
	protected MdlLogic02000In inputData = new MdlLogic02000In();
	
	/** 出力データ **/
	protected MdlLogic02000Out outputData = new MdlLogic02000Out();
	
	/** ロジック処理内データ **/
	// 不要
	
	/** データベース接続クラス **/
	protected DbConnection dbconn = null;

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(Logic02000.class.getName());

	/**
	 * 正常系処理
	 */
	@Override
	protected void exeNormal(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData
		) throws Exception {
		
		logger.setLevel(Level.INFO);
		
		// DB接続
		dbconn = new DbConnection();
		dbconn.connect();
		
		getInputData(request, response, comData);
		
		checkInputData(comData);
		
		doSql_SQL0001(comData);
		
		doSql_SQL0002(comData);
		
		// 検索実行フラグ = "1" の場合
		if ("1".equals(inputData.getSearchExeFlg())) {
			
			doSql_SQL0003(comData);
		}
		
		editSetOutputData(request, response, comData);
		
		// DB切断
		dbconn.close();
	}
	
	@Override
	protected void getInputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData) throws Exception {
		
		/** 以下リクエストパラメータ取り出し **/
		// 塗料名
		inputData.setColornm(request.getParameter(ParamIdWeb.View02000.COLOR_NM));
		
		// ブランドID
		inputData.setBrandid(request.getParameter(ParamIdWeb.View02000.BRAND_ID));
		
		// プラモデルID
		inputData.setPlmdlid(request.getParameter(ParamIdWeb.View02000.PLMDL_ID));
		
		// 検索実行フラグ
		String searchExeFlg = request.getParameter(ParamIdWeb.View02000.SEARCH_EXE_FLG);

		// 取得した検索実行フラグが null の場合
		if (searchExeFlg == null) {
			searchExeFlg = "0";
		}
		
		inputData.setSearchExeFlg(searchExeFlg);
		
	}
	
	@Override
	protected void checkInputData(
			MdlCommonData comData) throws Exception {
		
		/** 検索実行フラグ **/
		String searchExeFlg = inputData.getSearchExeFlg();
		
		final String[] searchExeFlgList = {"0", "1"};
		
		// 必須チェック
		if (!CheckUtil.requiredCheck(searchExeFlg)) {
			String msg = "必須チェックエラー：検索実行フラグ";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}
		
		// 設定可能値チェック
		if (!Arrays.asList(searchExeFlgList).contains(searchExeFlg)) {
			String msg = "設定可能値チェックエラー：検索実行フラグ";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}
		
		// 検索実行フラグが "1":検索実行 の場合
		if ("1".equals(searchExeFlg)) {
			
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
	 * @param comData
	 * @throws Exception
	 */
	protected void doSql_SQL0001(
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
	 * @param comData
	 * @throws Exception
	 */
	protected void doSql_SQL0002(
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
	 * @param comData
	 */
	protected void doSql_SQL0003(
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
	
	@Override
	protected void editSetOutputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData) throws Exception {
		
		// 塗料名
		outputData.setColornm(inputData.getColornm());
		
		// ブランドID
		outputData.setBrandid(inputData.getBrandid());
		
		// プラモデルID
		outputData.setPlmdlid(inputData.getPlmdlid());
		
		request.setAttribute(ParamIdWeb.View02000.OUTDATA, outputData);
	}

	/**
	 * 想定外のエラー発生時の処理
	 */
	@Override
	protected void exeErr(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData,
			Exception e
		) {
		
		String msg = "塗料一覧で想定外のエラーが発生";
		comData.setResult(ResultConstant.LOGIC_ERROR);
		comData.setErrorData(logger, Level.SEVERE, e, msg);
	}

}
