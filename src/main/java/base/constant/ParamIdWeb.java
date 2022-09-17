package base.constant;

/**
 * 属性/ID用定数クラス
 * @author kohei kajiki
 *
 */
public class ParamIdWeb {
	
	/** 共通データ保持クラス **/
	public static final String COM_DATA = "comData";

	public static class ViewLogin {
		
		/** フォワードパス **/
		public static final String FORWARD_PATH = "/ViewLogin";
		
		/** 画面フォーム/リダイレクト用パス **/
		public static final String FORM_PATH = "/MngPlaModel/ViewLogin";
		
		/** 画面ファイルパス **/
		public static final String PAGE_SRC = "/ViewLogin/ViewLogin.jsp";
		
		/** ユーザ **/
		public static final String USER = "user";
		
		/** パスワード **/
		public static final String PASS = "pass";
		
	}
	
	public static class View00000 {
		
		/** サーブレットパス **/
		public static final String FORWARD_PATH = "/View00000";
		
		/** 画面フォーム/リダイレクト用パス **/
		public static final String FORM_PATH = "/MngPlaModel/View00000";

		/** 画面ファイルパス **/
		public static final String PAGE_SRC = "/WEB-INF/View/View00000/View00000.jsp";
	}
}
