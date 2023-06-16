package ctrl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import base.model.MdlCommonData;
import logic.sv.Logic02010;
import logic.sv.model.MdlLogic02020Out;

/**
 * Servlet implementation class Ctrl02010
 */
@WebServlet("/View02010")
public class Ctrl02010 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(Ctrl02010.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ctrl02010() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// コントローラID
		final String ctrlId = "Ctrl02010/get";
		
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
			
			return;
		}
		
		// Logic02020出力データ存在確認
		MdlLogic02020Out mdlLogic02020Out = (MdlLogic02020Out)session.getAttribute(ParamIdWeb.View02020.OUTDATA);
		boolean mdlLogic02020OutExist = mdlLogic02020Out != null;
		
		// リクエストスコープに処理パターンを設定
		String logicPtn = ParamIdWeb.View02010.GET;
		request.setAttribute(ParamIdWeb.View02010.LOGIC_PTN, logicPtn);
		
		// サーバ処理
		Logic02010 logic02010 = new Logic02010();
		logic02010.execute(request, response, comData);
		
		// 遷移先画面判定
		if (ResultConstant.NORMAL.equals(comData.getResult())
				|| mdlLogic02020OutExist) {
			
			// 塗料登録画面へフォワード遷移
			dispatcher = request.getRequestDispatcher(ParamIdWeb.View02010.PAGE_SRC);
			dispatcher.forward(request, response);
		} else {
			
			// 塗料一覧画面servletへリダイレクト遷移
			response.sendRedirect(ParamIdWeb.View02000.FORM_PATH);
		}

		logger.log(Level.INFO, ctrlId + ":終了");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// コントローラID
		final String ctrlId = "Ctrl02010/post";
		
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
			
			return;
		}
		
		// リクエストスコープに処理パターン設定
		String logicPtn = ParamIdWeb.View02010.POST;
		request.setAttribute(ParamIdWeb.View02010.LOGIC_PTN, logicPtn);
		
		// サーバ処理
		Logic02010 logic02010 = new Logic02010();
		logic02010.execute(request, response, comData);

		// 塗料登録画面へフォワード遷移
		dispatcher = request.getRequestDispatcher(ParamIdWeb.View02010.PAGE_SRC);
		dispatcher.forward(request, response);

		logger.log(Level.INFO, ctrlId + ":終了");

	}

}
