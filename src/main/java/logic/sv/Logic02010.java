package logic.sv;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.constant.ParamIdWeb;
import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.logic.ServerLogic;
import base.model.MdlCommonData;
import logic.sql.SQL0001_SelBrandList;
import logic.sql.SQL0004_SelPaintPulldown;
import logic.sql.model.SQL0001In;
import logic.sql.model.SQL0001Out;
import logic.sql.model.SQL0004In;
import logic.sql.model.SQL0004Out;
import logic.sv.model.MdlLogic02010In;
import logic.sv.model.MdlLogic02010Out;

public class Logic02010 extends ServerLogic {
	
	/** 入力データ **/
	protected MdlLogic02010In inputData = new MdlLogic02010In();
	
	/** 出力データ **/
	protected MdlLogic02010Out outputData = new MdlLogic02010Out();
	
	/** データベース接続クラス **/
	protected DbConnection dbconn = null;

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(Logic02010.class.getName());
	
	/** ロジックID **/
	protected final String logicId = "Logic02010";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, MdlCommonData comData) {

		logger.setLevel(Level.INFO);
		logger.log(Level.INFO, logicId + ":開始");
		
		super.execute(request, response, comData);
		
		logger.log(Level.INFO, logicId + ":終了");
	}

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
		
		doSql_SQL0001(comData);
		
		doSql_SQL0004(comData);
		
		editSetOutputData(request, response, comData);
		
		dbconn.close();
	}
	
	@Override
	protected void getInputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData) throws Exception {
		
		// ブランドID
		inputData.setBrandid(request.getParameter(ParamIdWeb.View02010.BRAND_ID));
		
		// カラーコード
		inputData.setColorcode(request.getParameter(ParamIdWeb.View02010.COLOR_CODE));
		
		// カラー名
		inputData.setColornm(request.getParameter(ParamIdWeb.View02010.COLOR_NAME));
		
		// 所持
		inputData.setPosession(request.getParameter(ParamIdWeb.View02010.POSESSION));
		
		// 選択肢表示
		inputData.setSelvisible(request.getParameter(ParamIdWeb.View02010.SEL_VISIBLE));
		
		// 近似塗料ID
		inputData.setAppaintid(request.getParameter(ParamIdWeb.View02010.APPAINTID));
		
		// 処理パターン
		inputData.setLogicPtn((String)request.getAttribute(ParamIdWeb.View02010.LOGIC_PTN));
	}

	/**
	 * ブランドリスト取得SQL実行
	 * @param comData
	 * @throws Exception
	 */
	protected void doSql_SQL0001(
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
	
	@Override
	protected void editSetOutputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData) throws Exception {
		
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
		
		request.setAttribute(ParamIdWeb.View02010.OUTDATA, outputData);
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
		String msg = "塗料登録画面で想定外のエラーが発生";
		comData.setResult(ResultConstant.LOGIC_ERROR);
		comData.setErrorData(logger, Level.SEVERE, e, msg);

	}
}
