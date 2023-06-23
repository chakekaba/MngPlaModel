package logic.sv;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ParamIdWeb;
import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.logic.ExceptionLogic;
import base.logic.ServerLogic_old;
import base.model.MdlCommonData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.sql.SQL0000_connectionCheck;
import logic.sql.model.SQL0000In;
import logic.sql.model.SQL0000Out;
import logic.sv.model.MdlLogicLoginIn;
import logic.sv.model.MdlLogicLoginOut;

public class LogicLogin extends ServerLogic_old {

	/** 入力データ **/
	protected MdlLogicLoginIn inputData = new MdlLogicLoginIn();
	
	/** 出力データ **/
	protected MdlLogicLoginOut outputData = new MdlLogicLoginOut();
	
	/** ロジック処理内データ **/
	// 不要
	
	/** データベース接続クラス **/
	protected DbConnection dbconn = null;
	
	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(LogicLogin.class.getName());
	
	/** コンストラクタ **/
	public LogicLogin() {
	}
	
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
		
		getInputData(request, response, comData);
		
		checkInputData(comData);
		
		doSql_SQL0000(comData);
		
		editSetOutputData(request, response, comData);

	}
	
	@Override
	protected void getInputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData) {
		
		// ユーザ名
		inputData.setUserName(request.getParameter(ParamIdWeb.ViewLogin.USER));
		
		// パスワード
		inputData.setPassword(request.getParameter(ParamIdWeb.ViewLogin.PASS));

	}


	@Override
	protected void checkInputData(
			MdlCommonData comData) {
		// -- ユーザ名 -----------
		String userName = inputData.getUserName();
	
		// 必須チェック
		// 暫定
		if (userName == null || userName.equals("")) {
			String msg = "必須チェックエラー：ユーザ名";
			comData.setResult(ResultConstant.LOGIC_ERROR);
//			comData.setErrorData(new ExceptionLogic(), msg);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}
		
		// -- ユーザ名 -----------
		String password = inputData.getPassword();
		
		// 必須チェック
		// 暫定
		if (password == null || password.equals("")) {
			String msg = "必須チェックエラー：パスワード";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}

	}

	/**
	 * 
	 * @param comData
	 */
	protected void doSql_SQL0000(MdlCommonData comData) {
		
		SQL0000_connectionCheck sql0000 = new SQL0000_connectionCheck();
		SQL0000In sqlin = new SQL0000In();
		SQL0000Out sqlout = new SQL0000Out();
		

		dbconn = new DbConnection();
		
		try {
			dbconn.connect();
			
			sql0000.execute(dbconn, comData, sqlin, sqlout);
			
			
		} catch (Exception e) {
			String errMsg = "DB接続時にエラー発生";
			
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.SEVERE, e, errMsg);
		} finally {
			try {
				if (dbconn != null) {
					dbconn.close();
				}
			} catch (SQLException e) {
				// 何も処理しない
			}
		}
	}
	
	@Override
	protected void editSetOutputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData) {
		
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
		
		String msg = "ログイン処理で想定外のエラーが発生";
		comData.setResult(ResultConstant.LOGIC_ERROR);
		comData.setErrorData(logger, Level.SEVERE, e, msg);
	};
}
