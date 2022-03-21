package model;

import base.constant.ResultConstant;
import base.model.JavaBeansModel;

/**
 * 実行結果モデル
 * @author kohei kajiki
 *
 */
public class MdlResult extends JavaBeansModel {

	/** 実行結果 **/
	private String result;

	/** エラーメッセージ **/
	private String errMsg;

	/**
	 * コンストラクタ
	 */
	public MdlResult() {
		init();
	}

	/**
	 * 初期化
	 */
	public void init() {
		result = ResultConstant.NORMAL;
		errMsg = "";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		if (this.result.equals(ResultConstant.NORMAL)) {
			this.result = result;
		}
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {

		if (!this.errMsg.equals("")) {
			// 既にエラーメッセージが設定されている場合
			// 改行を設定
			this.errMsg = this.errMsg + "\n";

//			System.out.println("開発向け:例外が重複して発生しています。");
		}

		this.errMsg = this.errMsg + errMsg;
	}

	public void setError(String result, String errMsg) {
		setResult(result);
		setErrMsg(errMsg);
	}
}
