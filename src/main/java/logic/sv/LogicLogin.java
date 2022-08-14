package logic.sv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.logic.DbConnection;
import base.logic.ServerLogic;
import base.model.MdlCommonData;
import logic.sv.model.MdlLogicLoginIn;
import logic.sv.model.MdlLogicLoginOut;

public class LogicLogin extends ServerLogic {

	/** 入力データ **/
	protected MdlLogicLoginIn inputData = new MdlLogicLoginIn();
	
	/** 出力データ **/
	protected MdlLogicLoginOut outputData = new MdlLogicLoginOut();
	
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
		
		getInputData(request, response, comData, inputData);
		
		checkInputData(comData, inputData);
		
		editSetOutputData(request, response, comData, inputData, outputData);
	}


	protected void getInputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData,
			MdlLogicLoginIn inputData) {

		// ユーザ名
		inputData.setUserName(request.getParameter("user"));
		
		// パスワード
		inputData.setUserName(request.getParameter("password"));
	}


	protected void checkInputData(
			MdlCommonData comData,
			MdlLogicLoginIn inputData) {
		
		// -- ユーザ名 -----------
//		String userName = inputData.getUserName();
		
		// 必須チェック
		
	}


	protected void editSetOutputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData,
			MdlLogicLoginIn inputData,
			MdlLogicLoginOut outputData) {
	}

}
