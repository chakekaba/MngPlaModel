package ctrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.constant.ResultConstant;
import base.constant.StringEncode;
import base.constant.WebPath;
import base.model.MdlCommonData;
import logic.sv.LogicLogin;

/**
 * Servlet implementation class Ctrl00000
 */
@WebServlet("/View00000/*")
public class Ctrl00000 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 文字エンコーディング設定:UTF-8
		request.setCharacterEncoding(StringEncode.UTF8);
		
		MdlCommonData comData = new MdlCommonData();
		
		
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
		
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			
			// メイン画面へのフォワード遷移実行
			RequestDispatcher dispatcher = request.getRequestDispatcher(WebPath.VIEW00000);
			
			dispatcher.forward(request, response);

		} else {
			
			comData.showErrorLog();
			
			// 遷移元画面にフォワード遷移で戻る（ブラウザ表示URLの問題は後回し）
			RequestDispatcher dispatcher = request.getRequestDispatcher(WebPath.VIEWLOGIN);
			
			dispatcher.forward(request, response);

		}
		
	}

}
