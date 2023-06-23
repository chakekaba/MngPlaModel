package ctrl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ParamIdWeb;
import base.constant.ResultConstant;
import base.constant.StringEncode;
import base.logic.URILogic;
import base.logic.model.URILogicModel;
import base.model.MdlCommonData;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.sv.LogicLogin;
import logic.sv.model.MdlLogicLoginIn;
import logic.sv.model.MdlLogicLoginOut;

/**
 * Servlet implementation class Ctrl00000
 */
@WebServlet("/View00000")
public class Ctrl00000 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(Ctrl00000.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// コントローラID
		final String ctrlId = "Ctrl00000/get";
		
		// ロガー開始
		logger.setLevel(Level.INFO);
		logger.log(Level.INFO, ctrlId + ":開始");

		// 文字エンコーディング設定:UTF-8
		request.setCharacterEncoding(StringEncode.UTF8);
		
		// セッションスコープ取得
		HttpSession session = request.getSession();
		
		// 共通データ保持クラスをセッションスコープから取得
		MdlCommonData comData = (MdlCommonData)session.getAttribute(ParamIdWeb.COM_DATA);
				
		// リクエストディスパッチャ
		RequestDispatcher dispatcher = null;
		
		// 共通データ保持クラスを取得できない場合
		if (comData == null) {
			
			// ログイン画面に遷移
			response.sendRedirect(ParamIdWeb.ViewLogin.FORM_PATH);
			logger.log(Level.INFO, String.format("%s:終了_redirect '%s'", ctrlId, ParamIdWeb.ViewLogin.FORM_PATH));
			
			return;
		}
		
		// 遷移処理でエラー発生→リダイレクトで遷移元に戻ってきた場合
		if (!ResultConstant.NORMAL.equals(comData.getResult())) {
			
			dispatcher = request.getRequestDispatcher(ParamIdWeb.View00000.PAGE_SRC);
			
			dispatcher.forward(request, response);
			logger.log(Level.INFO, ctrlId + ":終了_エラー時中断");

			return;
		}

		// 遷移元画面URI取得
		URILogic uriLogic = new URILogic();
		URILogicModel uriLogicMdl = new URILogicModel();
		
		uriLogic.getReferrerViewData(request, uriLogicMdl);
//	------------------------
//	------------------------

		// 共通データ保持クラスをセッションスコープに設定
		session.setAttribute(ParamIdWeb.COM_DATA, comData);
		
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			
			// メイン画面へのフォワード遷移実行
			dispatcher = request.getRequestDispatcher(ParamIdWeb.View00000.PAGE_SRC);
			
			dispatcher.forward(request, response);

		} else {
			
			// 遷移元画面用servletにリダイレクト遷移で戻る
			response.sendRedirect(uriLogicMdl.getFullURI());
		}
		
		logger.log(Level.INFO, ctrlId + ":終了");

	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// コントローラID
		final String ctrlId = "Ctrl00000/post";
		
		// ロガー開始
		logger.setLevel(Level.INFO);
		logger.log(Level.INFO, ctrlId + ":開始");

		// 文字エンコーディング設定:UTF-8
		request.setCharacterEncoding(StringEncode.UTF8);
		
		// セッションスコープ取得
		HttpSession session = request.getSession();
		
		// 共通データ保持クラスをセッションスコープから取得
		MdlCommonData comData = (MdlCommonData)session.getAttribute(ParamIdWeb.COM_DATA);
		
		// リクエストディスパッチャ
		RequestDispatcher dispatcher = null;
		
		// 共通データ保持クラスを取得できない場合
		if (comData == null) {
			
			// ログイン画面に遷移
			response.sendRedirect(ParamIdWeb.ViewLogin.FORM_PATH);
			logger.log(Level.INFO, String.format("%s:終了_redirect '%s'", ctrlId, ParamIdWeb.ViewLogin.FORM_PATH));
			
			return;
		}

		// 遷移元画面URI取得
		URILogic uriLogic = new URILogic();
		URILogicModel uriLogicMdl = new URILogicModel();
		
		uriLogic.getReferrerViewData(request, uriLogicMdl);
		
		String viewIdFr = uriLogicMdl.getServletPath();

//		// 遷移元の画面ID取得
//		// 一旦省略
//		String viewIdFr = "遷移元画面ID";
//
//		// 遷移元別のサーバ処理を実行
//		switch (viewIdFr) {
//			case "xxxxx":
//				break;
//
//			case "yyyyy":
//				break;
//		}
				
		// ロジック処理（仮）
		LogicLogin logicLogin = new LogicLogin();
		MdlLogicLoginIn inputData = new MdlLogicLoginIn();
		MdlLogicLoginOut outputData = new MdlLogicLoginOut();
		
		// ユーザ名
		inputData.setUserName(request.getParameter(ParamIdWeb.ViewLogin.USER));
		
		// パスワード
		inputData.setPassword(request.getParameter(ParamIdWeb.ViewLogin.PASS));
		
		// ロジック処理実行
		logicLogin.execute(inputData, outputData, comData);
		
		
		// 共通データ保持クラスをセッションスコープに設定
		session.setAttribute(ParamIdWeb.COM_DATA, comData);
		
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			
			// メイン画面へのフォワード遷移実行
			dispatcher = request.getRequestDispatcher(ParamIdWeb.View00000.PAGE_SRC);
			
			dispatcher.forward(request, response);

		} else {
			
			// 遷移元画面用servletにリダイレクト遷移で戻る
			response.sendRedirect(uriLogicMdl.getFullURI());
		}
		
		logger.log(Level.INFO, ctrlId + ":終了");

	}

}
