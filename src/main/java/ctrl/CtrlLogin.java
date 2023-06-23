package ctrl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ParamIdWeb;
import base.constant.StringEncode;
import base.model.MdlCommonData;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CtrlLogin
 */
@WebServlet("/ViewLogin")
public class CtrlLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(CtrlLogin.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// コントローラID
		final String ctrlId = "CtrlLogin";
		
		// ロガー開始
		logger.setLevel(Level.INFO);
		logger.log(Level.INFO, ctrlId + ":開始");

		// 文字エンコーディング設定:UTF-8
		request.setCharacterEncoding(StringEncode.UTF8);

		// セッションスコープ取得
		HttpSession session = request.getSession();
		
		// 共通データ保持クラスをセッションスコープから取得
		MdlCommonData comData = (MdlCommonData)session.getAttribute(ParamIdWeb.COM_DATA);

		// 共通データ保持クラスを取得できない場合
		if (comData == null) {
			
			comData = new MdlCommonData();
			
			// 共通データ保持クラスをセッションスコープに設定
			session.setAttribute(ParamIdWeb.COM_DATA, comData);
		}
		
		
		
		// ログイン画面に遷移
		RequestDispatcher  dispatcher= request.getRequestDispatcher(ParamIdWeb.ViewLogin.PAGE_SRC);
		
		dispatcher.forward(request, response);
		
		logger.log(Level.INFO, ctrlId + ":終了");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
