package logic.sql.model;

import base.model.JavaBeansModel;

/** 検索条件用ブランドリストOutDM **/
public class SQL0001Out extends JavaBeansModel {

	/** ブランドID **/
	private String brandid;
	
	/** ブランド名 **/
	private String brandnm;

	// getter/setter -----------------------------------------
	/**
	 * @return brandid ブランドID
	 */
	public String getBrandid() {
		return brandid;
	}

	/**
	 * @param brandid ブランドID
	 */
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	/**
	 * @return brandnm ブランド名
	 */
	public String getBrandnm() {
		return brandnm;
	}

	/**
	 * @param brandnm ブランド名
	 */
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
	}
	// getter/setter end -----------------------------------------
}
