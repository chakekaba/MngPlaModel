package logic.sv.model;

import base.model.JavaBeansModel;

public class MdlLogicLoginIn extends JavaBeansModel {

	/** ユーザ名 **/
	private String userName;

	/** パスワード **/
	private String password;

	public MdlLogicLoginIn() {
	}

	// -- ユーザ名 ---------------------------
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// -- パスワード ---------------------------
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
