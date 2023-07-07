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
import logic.sv.Logic02000;
import logic.sv.model.MdlLogic02000In;
import logic.sv.model.MdlLogic02000Out;

/**
 * Servlet implementation class Ctrl02000
 */
@WebServlet("/View02000")
public class Ctrl02000 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(Ctrl02020.class.getName());

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
		
		// コントローラID
		final String ctrlId = "Ctrl02000/get";
		
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
		
		// サーバ処理
		Logic02000 logic02000 = new Logic02000();
		MdlLogic02000In inputData = new MdlLogic02000In();
		MdlLogic02000Out outputData = new MdlLogic02000Out();
		
		// 塗料名
		inputData.setColornm(request.getParameter(ParamIdWeb.View02000.COLOR_NM));
		
		// ブランドID
		inputData.setBrandid(request.getParameter(ParamIdWeb.View02000.BRAND_ID));
		
		// プラモデルID
		inputData.setPlmdlid(request.getParameter(ParamIdWeb.View02000.PLMDL_ID));
		
		// 検索実行フラグ
		inputData.setSearchExeFlg("0"); // 検索実行せず

		// ロジック処理実行
		logic02000.execute(inputData, outputData, comData);
		
		// 出力データをリクエストスコープに設定
		request.setAttribute(ParamIdWeb.View02000.OUTDATA, outputData);
		
		// 遷移先画面判定
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			
			// メイン画面へのフォワード遷移実行
			dispatcher = request.getRequestDispatcher(ParamIdWeb.View02000.PAGE_SRC);
			
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
		final String ctrlId = "Ctrl02000/post";
		
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

		// サーバ処理
		Logic02000 logic02000 = new Logic02000();
		MdlLogic02000In inputData = new MdlLogic02000In();
		MdlLogic02000Out outputData = new MdlLogic02000Out();
		
		// 塗料名
		inputData.setColornm(request.getParameter(ParamIdWeb.View02000.COLOR_NM));
		
		// ブランドID
		inputData.setBrandid(request.getParameter(ParamIdWeb.View02000.BRAND_ID));
		
		// プラモデルID
		inputData.setPlmdlid(request.getParameter(ParamIdWeb.View02000.PLMDL_ID));
		
		// 検索実行フラグ
		inputData.setSearchExeFlg("1"); // 検索実行

		// ロジック処理実行
		logic02000.execute(inputData, outputData, comData);
		
		// 出力データをリクエストスコープに設定
		request.setAttribute(ParamIdWeb.View02000.OUTDATA, outputData);
		
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
			
			// 塗料一覧画面へのフォワード遷移実行
			dispatcher = request.getRequestDispatcher(ParamIdWeb.View02000.PAGE_SRC);
			
			dispatcher.forward(request, response);

		} else {
			
			// 遷移元画面用servletにリダイレクト遷移で戻る
			response.sendRedirect(uriLogicMdl.getFullURI());
		}
		
		logger.log(Level.INFO, ctrlId + ":終了");

	}
}
