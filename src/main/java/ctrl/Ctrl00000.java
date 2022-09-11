package ctrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import base.constant.ParamIdWeb;
import base.constant.ResultConstant;
import base.constant.StringEncode;
import base.constant.WebPath;
import base.logic.URILogic;
import base.logic.model.URILogicModel;
import base.model.MdlCommonData;
import logic.sv.LogicLogin;

/**
 * Servlet implementation class Ctrl00000
 */
@WebServlet("/View00000")
public class Ctrl00000 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			dispatcher = request.getRequestDispatcher("/ViewLogin");
			
			dispatcher.forward(request, response);
			
			return;
		}

//		// 連携元servletでエラーが発生していた場合
//		if (ResultConstant.NORMAL.equals(comData.getResult())) {
//			
//			dispatcher = request.getRequestDispatcher("/ViewLogin");
//		}
		
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
				
		// サーバ処理（仮）
		LogicLogin logicLogin = new LogicLogin();
		
		logicLogin.execute(request, response, comData);
		
		
		// 共通データ保持クラスをセッションスコープに設定
		session.setAttribute(ParamIdWeb.COM_DATA, comData);
		
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			
			// メイン画面へのフォワード遷移実行
			dispatcher = request.getRequestDispatcher(WebPath.VIEW00000);
			
			dispatcher.forward(request, response);

		} else {
			
			comData.showErrorLog();
			
			// 遷移元画面用servletにリダイレクト遷移で戻る
			response.sendRedirect(uriLogicMdl.getFullURI());
		}
		
	}

}
