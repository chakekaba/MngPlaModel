package logic.sv.logicLogin;

import base.model.JavaBeansModel;

public class MdlLogicLoginIn extends JavaBeansModel {

	/** ユーザ名 **/
	private String userName;

	/** パスワード **/
	private String password;

	public MdlLogicLoginIn() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public MdlLogicLoginIn(String userName, String password) {

		this.userName = userName;

		this.password = password;
	}

	// getter
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}


}
