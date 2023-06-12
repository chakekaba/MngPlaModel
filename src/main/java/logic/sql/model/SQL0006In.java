package logic.sql.model;

import base.model.JavaBeansModel;

/**
 * 塗料情報データ取得InDM
 * @author kk-ma
 *
 */
public class SQL0006In extends JavaBeansModel {

	/** 塗料ID **/
	private String paintid;

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
	
	// getter/setter end -----------------------------------------

}
