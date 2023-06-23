package base.logic;



import base.model.MdlCommonData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ロジック処理抽象クラス
 * @author kohei kajiki
 *
 */
public class ServerLogic_old {
	
	/**
	 * コントローラから呼び出されるメイン処理
	 * @param request
	 * @param response
	 * @param comData
	 */
	public void execute(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData
		) {
		try {
			
			exeNormal(request, response, comData);
		}catch (Exception e) {
			
			exeErr(request, response, comData, e);
		}
	};
	
	protected void exeNormal(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData
		) throws Exception {
	};
	
	
	protected void exeErr(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData,
			Exception e
		) {
	};
	
	/**
	 * requestから入力パラメータ取得、Inクラスに設定
	 * @param request
	 * @param comData
	 */
	protected void getInputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData
		)  throws Exception {
		
	};
	
	/**
	 * 入力パラメータチェック
	 * @param comData
	 */
	protected void checkInputData(
			MdlCommonData comData
		)  throws Exception {
		
	};
	
	/**
	 * Outクラスに出力パラメータ設定、requestにOutクラス設定
	 * @param request
	 * @param response
	 * @param comData
	 */
	protected void editSetOutputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData
		)  throws Exception {
		
	};
}
