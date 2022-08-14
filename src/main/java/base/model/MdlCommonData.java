package base.model;

import java.util.ArrayList;
import java.util.List;

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
	private class ErrorData {
		
		/** 発生例外 **/
		private Exception exception;
		
		/** メッセージ **/
		private String message;

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
	}

	/** コンストラクタ **/
	public MdlCommonData() {
		init();
	}
	
	// -- 実行結果 ---------------------------
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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
		
	}

	/**
	 * エラー情報格納処理
	 * @param exception
	 * @param message
	 */
	public void setErrorData(Exception exception, String message) {
		
		// エラー情報クラスにエラー情報を設定
		ErrorData errorData = new ErrorData();
		
		errorData.setException(exception);
		errorData.setMessage(message);
		
		// エラー情報クラスリストに格納
		errorDataList.add(errorData);
	}
}
