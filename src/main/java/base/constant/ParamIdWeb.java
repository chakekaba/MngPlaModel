package base.constant;

/**
 * 属性/ID用定数クラス
 * @author kohei kajiki
 *
 */
public class ParamIdWeb {
	
	/** 共通データ保持クラス **/
	public static final String COM_DATA = "comData";

	/** ログイン画面 **/
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
	
	/** メイン画面 **/
	public static class View00000 {
		
		/** サーブレットパス **/
		public static final String FORWARD_PATH = "/View00000";
		
		/** 画面フォーム/リダイレクト用パス **/
		public static final String FORM_PATH = "/MngPlaModel/View00000";

		/** 画面ファイルパス **/
		public static final String PAGE_SRC = "/WEB-INF/View/View00000/View00000.jsp";
	}
	
	/** 塗料一覧画面 **/
	public static class View02000 {
		
		/** サーブレットパス **/
		public static final String FORWARD_PATH = "/View02000";
		
		/** 画面フォーム/リダイレクト用パス **/
		public static final String FORM_PATH = "/MngPlaModel/View02000";

		/** 画面ファイルパス **/
		public static final String PAGE_SRC = "/WEB-INF/View/View02000/View02000.jsp";
		
		/** CSSファイルパス **/
		public static final String CSS_SRC = "/WEB-INF/View/View02000/View02000.css";
		
		/** 塗料名 **/
		public static final String COLOR_NM = "colornm";
		
		/** ブランドID **/
		public static final String BRAND_ID = "brandid";
		
		/** プラモデルID **/
		public static final String PLMDL_ID = "plmdlid";
		
		/** 検索実行フラグ(0:false, 1:true) **/
		public static final String SEARCH_EXE_FLG = "searchExeFlg";
		
		/** 入力データ **/
		public static final String INDATA = "View02000In";

		/** 出力データ **/
		public static final String OUTDATA = "View02000Out";
	}
	
	/** 塗料登録画面 **/
	public static class View02010 {
		
		/** サーブレットパス **/
		public static final String FORWARD_PATH = "/View02010";
		
		/** 画面フォーム/リダイレクト用パス **/
		public static final String FORM_PATH = "/MngPlaModel/View02010";

		/** 画面ファイルパス **/
		public static final String PAGE_SRC = "/WEB-INF/View/View02010/View02010.jsp";
		
		/** ブランドID **/
		public static final String BRAND_ID = "brandid";
		
		/** カラーコード **/
		public static final String COLOR_CODE = "colorcode";
		
		/** カラー名 **/
		public static final String COLOR_NAME = "colornm";
		
		/** 所持(0:未所持, 1:所持) **/
		public static final String POSESSION = "posession";
		
		/** 選択肢表示(0:非表示, 1:表示) **/
		public static final String SEL_VISIBLE = "selvisible";
		
		/** 近似塗料ID **/
		public static final String APPAINTID = "appaintid";
		
		/** 処理パターン **/
		public static final String LOGIC_PTN = "logicPtn";
		
		/** 入力データ **/
		public static final String INDATA = "View02010In";

		/** 出力データ **/
		public static final String OUTDATA = "View02010Out";
		
		/** GETメソッド **/
		public static final String GET = "GET";

		/** POSTメソッド **/
		public static final String POST = "POST";
	}

	/** 塗料登録確認画面 **/
	public static class View02020 {
		
		/** サーブレットパス **/
		public static final String FORWARD_PATH = "/View02020";
		
		/** 画面フォーム/リダイレクト用パス **/
		public static final String FORM_PATH = "/MngPlaModel/View02020";

		/** 画面ファイルパス **/
		public static final String PAGE_SRC = "/WEB-INF/View/View02020/View02020.jsp";
		
		/** 拡張パス **/
		public static final String EXT_PATH = "/register";
		
		/** ブランドID **/
		public static final String BRAND_ID = "brandid";
		
		/** カラーコード **/
		public static final String COLOR_CODE = "colorcode";
		
		/** カラー名 **/
		public static final String COLOR_NAME = "colornm";
		
		/** 所持(0:未所持, 1:所持) **/
		public static final String POSESSION = "posession";
		
		/** 選択肢表示(0:非表示, 1:表示) **/
		public static final String SEL_VISIBLE = "selvisible";
		
		/** 近似塗料ID **/
		public static final String APPAINTID = "appaintid";
		
		/** 処理パターン **/
		public static final String LOGIC_PTN = "logicPtn";
		
		/** 入力データ **/
		public static final String INDATA = "View02020In";

		/** 出力データ **/
		public static final String OUTDATA = "View02020Out";
	}
}
