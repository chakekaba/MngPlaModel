package ctrl.ctrlLogin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.constant.ResultConstant;
import base.constant.WebPath;
import logic.sv.logicLogin.LogicLogin;
import logic.sv.logicLogin.MdlLogicLoginIn;
import logic.sv.logicLogin.MdlLogicLoginOut;
import model.ExecResult;

/**
 * Servlet implementation class LogicLogin
 */
@WebServlet("/CtrlLogin")
public class CtrlLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字エンコーディング設定
		request.setCharacterEncoding("UTF-8");

		// パラメータ取得
		// ユーザ名
		String userName = request.getParameter("user");

		// パスワード
		String password = request.getParameter("pass");


		// 入出力モデル設定
		MdlLogicLoginIn mdlIn = new MdlLogicLoginIn(userName, password);

		MdlLogicLoginOut mdlOut = new MdlLogicLoginOut();

		ExecResult result = new ExecResult();


		// ログイン処理実行
		LogicLogin logicLogin = new LogicLogin();

		logicLogin.execute(mdlIn, mdlOut, result);

		// ログイン処理実行結果判定
		if (ResultConstant.NORMAL.equals(result.getResult())) {
			// 正常の場合、メイン画面にフォワード遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher(WebPath.VIEW00000);

			dispatcher.forward(request, response);
		} else {
			// 異常
		}
	}

}
