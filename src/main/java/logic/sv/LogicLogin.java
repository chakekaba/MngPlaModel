package logic.sv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.logic.ExceptionLogic;
import base.logic.ServerLogic;
import base.model.MdlCommonData;
import logic.sv.model.MdlLogicLoginIn;
import logic.sv.model.MdlLogicLoginOut;

public class LogicLogin extends ServerLogic {

	/** 入力データ **/
	protected MdlLogicLoginIn inputData = new MdlLogicLoginIn();
	
	/** 出力データ **/
	protected MdlLogicLoginOut outputData = new MdlLogicLoginOut();
	
	/** ロジック処理内データ **/
	// 不要
	
	/** データベース接続クラス **/
	protected DbConnection dbconn = null;
	
	/** コンストラクタ **/
	public LogicLogin() {
	}
	
	
	@Override
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData) {
		
		getInputData(request, response, comData);
		
		checkInputData(comData);
		
		editSetOutputData(request, response, comData);
	}


	@Override
	protected void getInputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData) {
		
		// ユーザ名
		inputData.setUserName(request.getParameter("user"));
		
		// パスワード
		inputData.setUserName(request.getParameter("password"));

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
			comData.setErrorData(new ExceptionLogic(), msg);
		}
		
		// -- ユーザ名 -----------
		String password = inputData.getPassword();
		
		// 必須チェック
		// 暫定
		if (password == null || password.equals("")) {
			String msg = "必須チェックエラー：パスワード";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(new ExceptionLogic(), msg);
		}

	}


	@Override
	protected void editSetOutputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData) {
		
	}

}
