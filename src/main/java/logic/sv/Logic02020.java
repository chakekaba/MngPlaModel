package logic.sv;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import base.constant.ParamIdWeb;
import base.constant.ResultConstant;
import base.constant.ValueConstant;
import base.logic.CheckUtil;
import base.logic.DbConnection;
import base.logic.ExceptionLogic;
import base.logic.ServerLogic;
import base.model.MdlCommonData;
import logic.sql.SQL0005_SelBrandData;
import logic.sql.SQL0006_SelPaintData;
import logic.sql.SQL1001_CntPaintView;
import logic.sql.SQL1002_CntBrand;
import logic.sql.SQL2001_InsPaintList;
import logic.sql.model.SQL0005In;
import logic.sql.model.SQL0005Out;
import logic.sql.model.SQL0006In;
import logic.sql.model.SQL0006Out;
import logic.sql.model.SQL1001In;
import logic.sql.model.SQL1002In;
import logic.sql.model.SQL1XXXCntOut;
import logic.sql.model.SQL2001In;
import logic.sql.model.SQL2001Out;
import logic.sv.model.MdlLogic02020In;
import logic.sv.model.MdlLogic02020Out;

public class Logic02020 extends ServerLogic {

	/** 入力データ **/
	protected MdlLogic02020In inputData = new MdlLogic02020In();

	/** 出力データ **/
	protected MdlLogic02020Out outputData = new MdlLogic02020Out();

	/** データベース接続クラス **/
	protected DbConnection dbconn = null;

	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(Logic02020.class.getName());
	
	/** ロジックID **/
	protected final String logicId = "Logic02020";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, MdlCommonData comData) {

		logger.setLevel(Level.INFO);
		logger.log(Level.INFO, logicId + ":開始");
		
		super.execute(request, response, comData);
		
		logger.log(Level.INFO, logicId + ":終了");
	}

	@Override
	protected void exeNormal(HttpServletRequest request, HttpServletResponse response, MdlCommonData comData)
			throws Exception {

		logger.setLevel(Level.INFO);

		// DB接続
		dbconn = new DbConnection();
		dbconn.connect();

		getInputData(request, response, comData);

		checkInputData(comData);

		// 処理パターンが"/register"
		// かつ、処理結果が"正常"の場合
		if (ParamIdWeb.View02020.EXT_PATH.equals(inputData.getLogicPtn())
				&& ResultConstant.NORMAL.equals(comData.getResult())) {

			// 塗料一覧登録SQL実行
			doSql_SQL2001(comData);
			
		} else if (ResultConstant.NORMAL.equals(comData.getResult())) {
			// 処理結果が"正常"の場合
			
			// ブランド情報取得SQL実行
			doSql_SQL0005(comData);
			
			if (ResultConstant.NORMAL.equals(comData.getResult())
					&& CheckUtil.requiredCheck(inputData.getAppaintid())) {
				
				// 近似塗料情報取得SQL実行
				doSql_SQL0006(comData);
				
			}
		}
		
		if (ResultConstant.NORMAL.equals(comData.getResult())) {
		}

		editSetOutputData(request, response, comData);

		// DBクローズ処理
		Boolean result = ResultConstant.NORMAL.equals(comData.getResult());
		dbconn.close(result);
		
		if (ParamIdWeb.View02020.EXT_PATH.equals(inputData.getLogicPtn())
				&& ResultConstant.NORMAL.equals(comData.getResult())) {
			// 処理パターンが"/register"
			// かつ、処理結果が"正常"の場合
			String msg = "塗料登録処理が正常に完了しました。";
			comData.setErrorData(logger, Level.INFO, null, msg);
		}

	}

	@Override
	protected void getInputData(HttpServletRequest request, HttpServletResponse response, MdlCommonData comData)
			throws Exception {

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
		inputData.setLogicPtn((String)request.getAttribute(ParamIdWeb.View02010.LOGIC_PTN));
	}

	/**
	 *
	 */
	@Override
	protected void checkInputData(MdlCommonData comData) throws Exception {
		
		// SQL1001実行準備
		SQL1001_CntPaintView sql1001 = new SQL1001_CntPaintView();
		SQL1001In sqlin;
		SQL1XXXCntOut sqlout;
		
		/** ブランドID **/
		// 必須チェック
		String brandid = inputData.getBrandid();
		boolean brandidExist = CheckUtil.requiredCheck(brandid);
		if (!brandidExist) {
			String msg = "必須チェックエラー：ブランドID";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}

		// 設定可能値チェック
		// ブランドIDが入力されている場合
		if (brandidExist) {
			
			SQL1002_CntBrand sql1002 = new SQL1002_CntBrand();
			SQL1002In sql1002In = new SQL1002In();
			sqlout = new SQL1XXXCntOut();
			
			sql1002In.setSqlNo(SQL1002_CntBrand.SQL_NO1);
			sql1002In.setBrandid(inputData.getBrandid());
			
			sql1002.execute(dbconn, comData, sql1002In, sqlout);
			
			// 設定可能値でない場合
			if (sqlout.getRowCnt() != 1) {
				String msg = "設定可能値チェックエラー：ブランドID";
				comData.setResult(ResultConstant.LOGIC_ERROR);
				comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
			}
		}

		/** カラーコード **/
		// 必須チェック
		String colorcode = inputData.getColorcode();
		boolean colorcodeExist = CheckUtil.requiredCheck(colorcode);
		if (!colorcodeExist) {
			String msg = "必須チェックエラー：カラーコード";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}

		// 文字列長チェック
		// 必須チェックOKの場合チェック実行
		if (colorcodeExist) {
			
			int maxLength = 6;
			
			if (!CheckUtil.lengthCheck(colorcode, maxLength)) {
				String msg = "文字列長チェックエラー：カラーコード";
				comData.setResult(ResultConstant.LOGIC_ERROR);
				comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
			}
		}

		// ブランドID・カラーコード重複チェック
		// ブランドID・カラーコードがどちらも必須チェックOKの場合チェック実行
		if (brandidExist && colorcodeExist) {

			sqlin = new SQL1001In();
			sqlout = new SQL1XXXCntOut();

			sqlin.setPaintid(brandid + colorcode);
			sqlin.setSelvisible("%%");

			sql1001.execute(dbconn, comData, sqlin, sqlout);
			
			// 検索件数
			int cnt = sqlout.getRowCnt();

			// 重複データが存在する場合
			if (cnt != 0) {
				String msg = "重複チェックエラー：ブランドID・カラーコード";
				comData.setResult(ResultConstant.LOGIC_ERROR);
				comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
			}
		}
		
		/** カラー名 **/
		// 必須チェック
		String colorname = inputData.getColornm();
		boolean colornameExist = CheckUtil.requiredCheck(colorname);
		if (!colornameExist) {
			String msg = "必須チェックエラー：カラー名";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}

		// 文字列長チェック
		// 必須チェックOKの場合チェック実行
		if (colornameExist) {
			
			int maxLength = 20;
			
			if (!CheckUtil.lengthCheck(colorname, maxLength)) {
				String msg = "文字列長チェックエラー：カラー名";
				comData.setResult(ResultConstant.LOGIC_ERROR);
				comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
			}
		}

		/** 所持 **/
		// 必須チェック
		String posession = inputData.getPosession();
		boolean posessionExist = CheckUtil.requiredCheck(posession);
		if (!posessionExist) {
			String msg = "必須チェックエラー：所持";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}

		// 設定可能値チェック
		// 必須チェックOKの場合チェック実行
		if (posessionExist) {
			// 設定可能値でない場合
			if (!ValueConstant.isProperPosession(posession)) {
				String msg = String.format("設定可能値チェックエラー：所持{%s}", posession);
				comData.setResult(ResultConstant.LOGIC_ERROR);
				comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
			}
		}

		/** 選択肢表示 **/
		// 必須チェック
		String selVisible = inputData.getSelvisible();
		boolean selVisibleExist = CheckUtil.requiredCheck(selVisible);
		if (!selVisibleExist) {
			String msg = "必須チェックエラー：選択肢表示";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}

		// 設定可能値チェック
		// 必須チェックOKの場合チェック実行
		if (selVisibleExist) {
			// 設定可能値でない場合
			if (!ValueConstant.isProperSelValue(selVisible)) {
				String msg = String.format("設定可能値チェックエラー：選択肢表示{%s}", selVisible);
				comData.setResult(ResultConstant.LOGIC_ERROR);
				comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
			}
		}

		/** 近似塗料ID **/
		// 設定値存在チェック
		String appaintid = inputData.getAppaintid();
		// appaintidが設定されている場合存在チェック実行
		if (CheckUtil.requiredCheck(appaintid)) {
			
			sqlin = new SQL1001In();
			sqlout = new SQL1XXXCntOut();

			// 
			sqlin.setPaintid(appaintid);
			sqlin.setSelvisible("%%");

			sql1001.execute(dbconn, comData, sqlin, sqlout);

			// 検索件数
			int cnt = sqlout.getRowCnt();

			// 検索対象の"塗料ID"が存在しない場合
			if (cnt != 1) {
				String msg = "設定値存在チェックエラー：近似塗料ID（存在しない塗料です。）";
				comData.setResult(ResultConstant.LOGIC_ERROR);
				comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
			} else {

				sqlin = new SQL1001In();
				sqlout = new SQL1XXXCntOut();

				sqlin.setPaintid(appaintid);
				sqlin.setSelvisible("1");
				
				sql1001.execute(dbconn, comData, sqlin, sqlout);
				
				cnt = sqlout.getRowCnt();

				// 未所持の塗料の場合
				if (cnt != 1) {
					String msg = "設定可能値チェックエラー：近似塗料ID（近似塗料として設定出来ない塗料です。）";
					comData.setResult(ResultConstant.LOGIC_ERROR);
					comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
				}
			}
		}

		/** 処理パターン **/
		String logicPtn = inputData.getLogicPtn();
		if (logicPtn != null
				&& !(ParamIdWeb.View02020.EXT_PATH.equals(logicPtn))
				) {
			// 設定可能値でない場合
			String msg = "設定可能値チェックエラー：処理パターン";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}
	}

	/**
	 * ブランド情報取得SQL実行
	 * @param comData
	 */
	protected void doSql_SQL0005(MdlCommonData comData) {
		
		SQL0005_SelBrandData sql0005 = new SQL0005_SelBrandData();
		SQL0005In sqlin = new SQL0005In();
		SQL0005Out sqlout = new SQL0005Out();
		
		// 入力値設定
		// ブランドID
		sqlin.setBrandid(inputData.getBrandid());
		
		// SQL0005実行
		sql0005.execute(dbconn, comData, sqlin, sqlout);
		
		// 実行結果が"正常"以外の場合
		if (!ResultConstant.NORMAL.equals(comData.getResult())) {
			// ロールバック実行
			try {
				dbconn.roolback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {

			// 処理結果が"正常"の場合
			// 出力データ設定
			outputData.setBrandData(sqlout.getBrandData());
		}
		
		
	}
	
	/**
	 * 近似塗料情報取得SQL実行
	 * @param comData
	 */
	protected void doSql_SQL0006(MdlCommonData comData) {
		
		SQL0006_SelPaintData sql0006 = new SQL0006_SelPaintData();
		SQL0006In sqlin = new SQL0006In();
		SQL0006Out sqlout = new SQL0006Out();
		
		// 入力値設定
		// 塗料ID
		sqlin.setPaintid(inputData.getAppaintid());
		
		// SQL0006実行
		sql0006.execute(dbconn, comData, sqlin, sqlout);

		// 実行結果が"正常"以外の場合
		if (!ResultConstant.NORMAL.equals(comData.getResult())) {
			
			// ロールバック実行
			try {
				dbconn.roolback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			
			// 処理結果が"正常"の場合
			// 出力データ設定
			outputData.setAppaintData(sqlout.getPaintData());
		}
		
	}
	
	/**
	 * 塗料一覧登録SQL実行
	 * @param comData
	 */
	protected void doSql_SQL2001(MdlCommonData comData) {
		SQL2001_InsPaintList sql2001 = new SQL2001_InsPaintList();
		SQL2001In sqlin = new SQL2001In();
		SQL2001Out sqlout = new SQL2001Out();

		// 入力値設定
		// ブランドID
		sqlin.setBrandid(inputData.getBrandid());

		// カラーコード
		sqlin.setColorcode(inputData.getColorcode());

		// カラー名
		sqlin.setColornm(inputData.getColornm());

		// 所持
		sqlin.setPosession(inputData.getPosession());

		// 選択肢表示
		sqlin.setSelvisible(inputData.getSelvisible());

		// 近似塗料ID
		sqlin.setAppaintid(inputData.getAppaintid());

		// SQL2001実行
		sql2001.execute(dbconn, comData, sqlin, sqlout);

		// 実行結果が"正常"以外の場合
		if (!ResultConstant.NORMAL.equals(comData.getResult())) {
			// ロールバック実行
			try {
				dbconn.roolback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void editSetOutputData(
			HttpServletRequest request,
			HttpServletResponse response,
			MdlCommonData comData)
			throws Exception {

		// ブランドID
		outputData.setBrandid(inputData.getBrandid());
		
		// カラーコード
		outputData.setColorcode(inputData.getColorcode());
		
		// カラー名
		outputData.setColornm(inputData.getColornm());
		
		// 所持
		String posession = inputData.getPosession();
		outputData.setPosession(posession);
		
		// 所持（表示）
		outputData.setPosessionDisp(ValueConstant.POSESSION_MAP.get(posession));
		
		// 選択肢表示
		String selvisible = inputData.getSelvisible();
		outputData.setSelvisible(selvisible);
		
		// 選択肢表示（表示）
		outputData.setSelvisibleDisp(ValueConstant.SEL_VISIBLE_MAP.get(selvisible));
		
		// 近似塗料ID
		outputData.setAppaintid(inputData.getAppaintid());

		// 処理パターンが"/register"
		// もしくは、処理結果が"正常"以外の場合
		if (ParamIdWeb.View02020.EXT_PATH.equals(inputData.getLogicPtn())
				|| !ResultConstant.NORMAL.equals(comData.getResult())) {

			// セッションスコープに出力データを設定
			HttpSession session = request.getSession();
			session.setAttribute(ParamIdWeb.View02020.OUTDATA, outputData);
		} else {

			// リクエストスコープに出力データを設定
			request.setAttribute(ParamIdWeb.View02020.OUTDATA, outputData);
		}
	}

	@Override
	protected void exeErr(HttpServletRequest request, HttpServletResponse response, MdlCommonData comData,
			Exception e) {

		String msg = "塗料登録処理で想定外のエラーが発生";
		comData.setResult(ResultConstant.LOGIC_ERROR);
		comData.setErrorData(logger, Level.SEVERE, e, msg);

		try {
			dbconn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
