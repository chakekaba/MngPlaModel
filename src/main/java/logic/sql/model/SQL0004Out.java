package logic.sql.model;

import base.model.JavaBeansModel;

/**
 * 近似塗料リストOutDM
 * @author kk-ma
 *
 */
public class SQL0004Out extends JavaBeansModel {
	
	/** 塗料ID **/
	private String paintid;
	
	/** 塗料情報 **/
	private String paintdata;

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
	 * @return paintdata
	 */
	public String getPaintdata() {
		return paintdata;
	}

	/**
	 * @param paintdata セットする paintdata
	 */
	public void setPaintdata(String paintdata) {
		this.paintdata = paintdata;
	}
	
	// getter/setter end -----------------------------------------
}
