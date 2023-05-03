package logic.sql.model;

import base.model.JavaBeansModel;

/**
 * @author kk-ma
 * 塗料一覧行数カウント用SQL2_OutDM
 */
public class SQL1002In extends JavaBeansModel {
	
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
