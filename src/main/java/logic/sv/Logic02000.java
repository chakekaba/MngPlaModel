package logic.sv;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.constant.ParamIdWeb;
import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.logic.ServerLogic;
import base.model.MdlCommonData;
import logic.sql.SQL0001_SelBrandList;
import logic.sql.SQL0002_SelPlmdlList;
import logic.sql.SQL0003_SelPaintList;
import logic.sql.model.SQL0001In;
import logic.sql.model.SQL0001Out;
import logic.sql.model.SQL0002In;
import logic.sql.model.SQL0002Out;
import logic.sql.model.SQL0003In;
import logic.sql.model.SQL0003Out;
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

	/**
	 * 正常系処理
	 */
	@Override
	protected void exeNormal(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData
		) throws Exception {
		
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
		
		// リクエストパラメータに設定
		request.setAttribute(ParamIdWeb.View02000.INDATA, inputData);
	}
	
	@Override
	protected void checkInputData(
			MdlCommonData comData) throws Exception {
		/** 省略（暫定）**/
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
		
		sql0001.execute(dbconn, comData, sqlin, sqlout);
		
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
		
		// 出力データにプラモデルリストを設定
		outputData.setPlmdlList(sqlout);
	}

	/**
	 * 塗料一覧リスト取得SQL実行
	 * @param comData
	 */
	protected void doSql_SQL0003(
			MdlCommonData comData) {
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
		
		// 出力データにプラモデルリストを設定
		outputData.setResultList(sqlout);
	}
	
	@Override
	protected void editSetOutputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData) throws Exception {
		
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
		comData.setErrorData(e, msg);
	}

}
