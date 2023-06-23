package logic.sv;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.logic.ExceptionLogic;
import base.logic.ServerLogicAbstract;
import base.model.JavaBeansModel;
import base.model.MdlCommonData;
import logic.sql.SQL0000_connectionCheck;
import logic.sql.model.SQL0000In;
import logic.sql.model.SQL0000Out;
import logic.sv.model.MdlLogicLoginIn;
import logic.sv.model.MdlLogicLoginOut;

public class LogicLogin extends ServerLogicAbstract {

	/** 入力データ **/
	protected MdlLogicLoginIn inputData_ = null;
	
	/** 出力データ **/
	protected MdlLogicLoginOut outputData_ = null;
	
	/** ロジック処理内データ **/
	// 不要
	
	/** データベース接続クラス **/
	protected DbConnection dbconn = null;
	
	/** 処理ID **/
	protected String logicId = "LogicLogin";
	
	/** ロガーインスタンス **/
	Logger logger = Logger.getLogger(LogicLogin.class.getName());
	
	/** コンストラクタ **/
	public LogicLogin() {
	}

	@Override
	public void execute(
			JavaBeansModel inputData,
			JavaBeansModel outputData,
			MdlCommonData comData) {

		logger.setLevel(Level.INFO);
		logger.log(Level.INFO, logicId + ":開始");
		
		try {
			
			init(inputData, outputData, comData);
			
			checkInputData(comData);
			
			doSql_SQL0000(comData);
			
			editSetOutputData(comData);

		} catch (Exception e) {
			
			exeErr(comData, e);
		} finally {
			
			exeFinal(comData);
		}
		
		logger.log(Level.INFO, logicId + ":終了");
		
	}

	@Override
	protected void init(
			JavaBeansModel inputData,
			JavaBeansModel outputData,
			MdlCommonData comData) {
		
		// 入力・出力データクラスキャスト
		this.inputData_ = (MdlLogicLoginIn)inputData;
		this.outputData_ = (MdlLogicLoginOut)outputData;
		
	}

	@Override
	protected void checkInputData(
			MdlCommonData comData) {
		// -- ユーザ名 -----------
		String userName = this.inputData_.getUserName();
	
		// 必須チェック
		// 暫定
		if (userName == null || userName.equals("")) {
			String msg = "必須チェックエラー：ユーザ名";
			comData.setResult(ResultConstant.LOGIC_ERROR);
//			comData.setErrorData(new ExceptionLogic(), msg);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}
		
		// -- ユーザ名 -----------
		String password = this.inputData_.getPassword();
		
		// 必須チェック
		// 暫定
		if (password == null || password.equals("")) {
			String msg = "必須チェックエラー：パスワード";
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.WARNING, new ExceptionLogic(), msg);
		}

	}

	/**
	 * 
	 * @param comData
	 */
	protected void doSql_SQL0000(MdlCommonData comData) {
		
		SQL0000_connectionCheck sql0000 = new SQL0000_connectionCheck();
		SQL0000In sqlin = new SQL0000In();
		SQL0000Out sqlout = new SQL0000Out();
		

		dbconn = new DbConnection();
		
		try {
			dbconn.connect();
			
			sql0000.execute(dbconn, comData, sqlin, sqlout);
			
			
		} catch (Exception e) {
			String errMsg = "DB接続時にエラー発生";
			
			comData.setResult(ResultConstant.LOGIC_ERROR);
			comData.setErrorData(logger, Level.SEVERE, e, errMsg);
		} finally {
			try {
				if (dbconn != null) {
					dbconn.close();
				}
			} catch (SQLException e) {
				// 何も処理しない
			}
		}
	}

	@Override
	protected void editSetOutputData(MdlCommonData comData) {
		// TODO 自動生成されたメソッド・スタブ
		
	};
	
	@Override
	protected void exeErr(MdlCommonData comData, Exception e) {
		
		String msg = "ログイン処理で想定外のエラーが発生";
		comData.setResult(ResultConstant.LOGIC_ERROR);
		comData.setErrorData(logger, Level.SEVERE, e, msg);
	}

	@Override
	protected void exeFinal(MdlCommonData comData) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
