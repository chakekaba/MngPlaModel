package base.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MdlCommonData;

public abstract class ServerLogic {
	
	/**
	 * コントローラから呼び出されるメイン処理
	 * @param request
	 * @param response
	 * @param comData
	 */
	abstract public void execute(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData
		);
	
//	/**
//	 * requestから入力パラメータ取得、Inクラスに設定
//	 * @param request
//	 * @param comData
//	 * @param inputData
//	 */
//	abstract protected void getInputData(
//			HttpServletRequest request,
//			HttpServletResponse response,
//			MdlCommonData comData,
//			JavaBeansModel inputData
//		);
	
//	/**
//	 * 入力パラメータチェック
//	 * @param comData
//	 * @param inputData
//	 */
//	abstract protected void checkInputData(
//			MdlCommonData comData,
//			JavaBeansModel inputData
//		);
	
//	/**
//	 * Outクラスに出力パラメータ設定、requestにOutクラス設定
//	 * @param request
//	 * @param response
//	 * @param comData
//	 * @param outputData
//	 */
//	abstract protected void editSetOutputData(
//			HttpServletRequest request,
//			HttpServletResponse response,
//			MdlCommonData comData,
//			JavaBeansModel inputData,
//			JavaBeansModel outputData
//		);
}
