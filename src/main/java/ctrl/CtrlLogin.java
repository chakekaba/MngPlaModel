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
import base.constant.StringEncode;
import base.constant.WebPath;
import base.model.MdlCommonData;

/**
 * Servlet implementation class CtrlLogin
 */
@WebServlet("/ViewLogin")
public class CtrlLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

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

		// 共通データ保持クラスを取得できない場合
		if (comData == null) {
			
			comData = new MdlCommonData();
			
			// 共通データ保持クラスをセッションスコープに設定
			session.setAttribute(ParamIdWeb.COM_DATA, comData);
		}
		
		
		
		// ログイン画面に遷移
		RequestDispatcher  dispatcher= request.getRequestDispatcher(WebPath.VIEWLOGIN);
		
		dispatcher.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
