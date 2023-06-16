package base.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import base.constant.ResultConstant;

/**
 * サーバ処理の共通データ保持クラス
 * @author kohei kajiki
 *
 */
public class MdlCommonData extends JavaBeansModel {
	
	/** 実行結果 **/
	private String result;
	
	/** エラー情報クラスリスト **/
	private List<ErrorData> errorDataList = new ArrayList<ErrorData>();
	
	/** エラー情報クラス **/
	public class ErrorData {
		
		/** ログレベル **/
		private Level logLevel;
		
		/** 発生例外 **/
		private Exception exception;
		
		/** メッセージ **/
		private String message;

		// -- ログレベル ---------------------------
		/**
		 * @return logLevel
		 */
		public Level getLogLevel() {
			return logLevel;
		}

		/**
		 * @param logLevel セットする logLevel
		 */
		public void setLogLevel(Level logLevel) {
			this.logLevel = logLevel;
		}

		// -- 発生例外 ---------------------------
		public Exception getException() {
			return exception;
		}

		public void setException(Exception exception) {
			this.exception = exception;
		}

		// -- メッセージ ---------------------------
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		public boolean isINFO() {
			return this.logLevel.equals(Level.INFO);
		}
		
		public boolean isWARNING() {
			return this.logLevel.equals(Level.WARNING);
		}
		public boolean isSEVERE() {
			return this.logLevel.equals(Level.SEVERE);
		}
	}

	/** コンストラクタ **/
	public MdlCommonData() {
		init();
	}
	
	// -- 実行結果 ---------------------------
	
	/**
	 * getter - 実行結果
	 * @return 実行結果
	 */
	public String getResult() {
		return result;
	}

	/**
	 * setter - 実行結果
	 * @param result 実行結果
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	// -- エラー情報クラスリスト ---------------------------
	/**
	 * @return errorDataList
	 */
	public List<ErrorData> getErrorDataList() {
		return errorDataList;
	}

	// -- 検出エラー件数 ---------------------------
	public int getErrorCnt() {
		return this.errorDataList.size();
	}
	
	
	/**
	 * 初期化処理
	 */
	public void init() {
		
		result = ResultConstant.NORMAL;
		
		errorDataList.clear();
		
	}
	
	/**
	 * エラー情報クリア処理
	 */
	public void clearErr() {
		
		result = ResultConstant.NORMAL;
		
		errorDataList.clear();
		
	}

//	/**
//	 * エラー情報格納処理
//	 * @param exception
//	 * @param message
//	 */
//	public void setErrorData(Exception exception, String message) {
//		
//		// エラー情報クラスにエラー情報を設定
//		ErrorData errorData = new ErrorData();
//		
//		errorData.setException(exception);
//		errorData.setMessage(message);
//		
//		// エラー情報クラスリストに格納
//		errorDataList.add(errorData);
//	}
	
	
	/**
	 * エラー情報格納処理
	 * @param logger 出力先ロガー
	 * @param level エラーレベル
	 * @param exception 発生例外
	 * @param message 表示メッセージ
	 */
	public void setErrorData(Logger logger, Level level, Exception exception, String message) {
		
		// メッセージ出力
		if (logger != null) {
			logger.log(level, message);
		}
		
		// エラー情報出力
		if (exception != null) {
			exception.printStackTrace();
		}
		
		// エラー情報格納
		ErrorData errorData = new ErrorData();
		
		errorData.setException(exception);
		errorData.setLogLevel(level);
		errorData.setMessage(message);
		
		// エラー情報クラスリストに格納
		errorDataList.add(errorData);
	}

//	/**
//	 * エラーログ出力（暫定）
//	 */
//	public void showErrorLog() {
//		
//		for (ErrorData errorData: errorDataList) {
//			
//			String message = errorData.getMessage();
//			
//			Exception e = errorData.getException();
//			
//			System.out.println(message);
//			
//			e.printStackTrace();
//		}
//	}
}
