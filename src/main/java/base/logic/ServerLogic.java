package base.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.model.MdlCommonData;

/**
 * ロジック処理抽象クラス
 * @author kohei kajiki
 *
 */
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
	
	/**
	 * requestから入力パラメータ取得、Inクラスに設定
	 * @param request
	 * @param comData
	 */
	abstract protected void getInputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData
		);
	
	/**
	 * 入力パラメータチェック
	 * @param comData
	 */
	abstract protected void checkInputData(
			MdlCommonData comData
		);
	
	/**
	 * Outクラスに出力パラメータ設定、requestにOutクラス設定
	 * @param request
	 * @param response
	 * @param comData
	 */
	abstract protected void editSetOutputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData
		);
}
