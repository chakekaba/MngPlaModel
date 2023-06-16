package logic.sql.model;

import base.model.JavaBeansModel;

/**
 * 塗料情報データ取得OutDM
 * @author kk-ma
 *
 */
public class SQL0006Out extends JavaBeansModel {

	/** 塗料ID **/
	private String paintid;

	/** 塗料情報 **/
	private String paintData;

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
	 * @return paintData
	 */
	public String getPaintData() {
		return paintData;
	}

	/**
	 * @param paintData セットする paintData
	 */
	public void setPaintData(String paintData) {
		this.paintData = paintData;
	}

	// getter/setter end -----------------------------------------
}
