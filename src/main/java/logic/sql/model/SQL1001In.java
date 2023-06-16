package logic.sql.model;

import base.model.JavaBeansModel;

/**
 * @author kk-ma
 * 塗料一覧行数カウント用SQL_InDM
 */
public class SQL1001In extends JavaBeansModel {
	
	/** 塗料ID **/
	private String paintid;
	
	/** 選択肢表示 **/
	private String selvisible;

	// getter/setter -----------------------------------------
	/**
	 * @return paintid
	 */
	public String getPaintid() {
		return paintid;
	}

	/**
	 * @param paintid セットする paintid
	 */
	public void setPaintid(String paintid) {
		this.paintid = paintid;
	}	

	/**
	 * @return selvisible
	 */
	public String getSelvisible() {
		return selvisible;
	}

	/**
	 * @param selvisible セットする selvisible
	 */
	public void setSelvisible(String selvisible) {
		this.selvisible = selvisible;
	}
	// getter/setter end -----------------------------------------
}
