package logic.sql.model;

import base.model.JavaBeansModel;

/** 検索条件用プラモデルリストOutDM **/
public class SQL0002Out extends JavaBeansModel {

	/** プラモデルID **/
	private String plmdlid;
	
	/** プラモデル名 **/
	private String plmdlnm;

	// getter/setter -----------------------------------------
	/**
	 * @return plmdlid プラモデルID
	 */
	public String getPlmdlid() {
		return plmdlid;
	}

	/**
	 * @param plmdlid プラモデルID
	 */
	public void setPlmdlid(String plmdlid) {
		this.plmdlid = plmdlid;
	}

	/**
	 * @return plmdlnm プラモデル名
	 */
	public String getPlmdlnm() {
		return plmdlnm;
	}

	/**
	 * @param plmdlnm プラモデル名
	 */
	public void setPlmdlnm(String plmdlnm) {
		this.plmdlnm = plmdlnm;
	}
	// getter/setter end -----------------------------------------
}
