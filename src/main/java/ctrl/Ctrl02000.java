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
import base.logic.URILogic;
import base.logic.model.URILogicModel;
import base.model.MdlCommonData;
import logic.sv.Logic02000;

/**
 * Servlet implementation class Ctrl02000
 */
@WebServlet("/View02000")
public class Ctrl02000 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ctrl02000() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			
			return;
		}
		
		// 遷移元画面URI取得
		URILogic uriLogic = new URILogic();
		URILogicModel uriLogicMdl = new URILogicModel();
		
		uriLogic.getReferrerViewData(request, uriLogicMdl);
		
		// サーバ処理（仮）
		Logic02000 logic02000 = new Logic02000();
		logic02000.execute(request, response, comData);
		
		// 遷移先画面判定
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			
			// メイン画面へのフォワード遷移実行
			dispatcher = request.getRequestDispatcher(ParamIdWeb.View02000.PAGE_SRC);
			
			dispatcher.forward(request, response);

		} else {
			// 遷移元画面用servletにリダイレクト遷移で戻る
			response.sendRedirect(uriLogicMdl.getFullURI());
		}
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
			response.sendRedirect(ParamIdWeb.ViewLogin.FORM_PATH);
			
			return;
		}
		
		
		// 遷移元画面URI取得
		URILogic uriLogic = new URILogic();
		URILogicModel uriLogicMdl = new URILogicModel();
		
		uriLogic.getReferrerViewData(request, uriLogicMdl);
		
		String viewIdFr = uriLogicMdl.getServletPath();

		// 以下編集中 ------------------
		// サーバ処理（仮）
		Logic02000 logic02000 = new Logic02000();
		
		logic02000.execute(request, response, comData);
		
		// 共通データ保持クラスをセッションスコープに設定
		session.setAttribute(ParamIdWeb.COM_DATA, comData);

		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			
			// 塗料一覧画面へのフォワード遷移実行
			dispatcher = request.getRequestDispatcher(ParamIdWeb.View02000.PAGE_SRC);
			
			dispatcher.forward(request, response);

		} else {
			
			// 遷移元画面用servletにリダイレクト遷移で戻る
			response.sendRedirect(uriLogicMdl.getFullURI());
		}

	}
}
