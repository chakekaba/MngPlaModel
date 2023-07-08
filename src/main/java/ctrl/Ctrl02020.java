package ctrl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ParamIdWeb;
import base.constant.ResultConstant;
import base.constant.StringEncode;
import base.model.MdlCommonData;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.sv.Logic02020;
import logic.sv.model.MdlLogic02020In;
import logic.sv.model.MdlLogic02020Out;

/**
 * Servlet implementation class Ctrl02020
 */
@WebServlet("/View02020/*")
public class Ctrl02020 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(Ctrl02020.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ctrl02020() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// コントローラID
		final String ctrlId = "Ctrl02020/post";
		
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
		
		// サーバ処理
		Logic02020 logic02020 = new Logic02020();
		MdlLogic02020In inputData = new MdlLogic02020In();
		MdlLogic02020Out outputData = new MdlLogic02020Out();
		
		// ブランドID
		inputData.setBrandid(request.getParameter(ParamIdWeb.View02010.BRAND_ID));

		// カラーコード
		inputData.setColorcode(request.getParameter(ParamIdWeb.View02010.COLOR_CODE));

		// カラー名
		inputData.setColornm(request.getParameter(ParamIdWeb.View02010.COLOR_NAME));

		// 所持
		inputData.setPosession(request.getParameter(ParamIdWeb.View02010.POSESSION));

		// 選択肢表示
		inputData.setSelvisible(request.getParameter(ParamIdWeb.View02010.SEL_VISIBLE));

		// 近似塗料ID
		inputData.setAppaintid(request.getParameter(ParamIdWeb.View02010.APPAINTID));

		// 処理パターン
		String logicPtn = request.getPathInfo();
		inputData.setLogicPtn(logicPtn);
		
		// サーバ処理実行
		logic02020.execute(inputData, outputData, comData);
		
		// 遷移先画面判定
		// 拡張パスが"/register
		// または、処理結果が"正常"以外の場合
		if (ParamIdWeb.View02020.EXT_PATH.equals(logicPtn)
				|| !ResultConstant.NORMAL.equals(comData.getResult())) {
			
			// セッションスコープに出力データを設定
			session.setAttribute(ParamIdWeb.View02020.OUTDATA, outputData);

			// 塗料登録画面servletへリダイレクト遷移
			response.sendRedirect(ParamIdWeb.View02010.FORM_PATH);
		} else {
			
			// リクエストスコープに出力データを設定
			request.setAttribute(ParamIdWeb.View02020.OUTDATA, outputData);

			// 塗料登録確認画面へフォワード遷移
			dispatcher = request.getRequestDispatcher(ParamIdWeb.View02020.PAGE_SRC);
			dispatcher.forward(request, response);
		}
		
		logger.log(Level.INFO, ctrlId + ":終了");

	}
}
