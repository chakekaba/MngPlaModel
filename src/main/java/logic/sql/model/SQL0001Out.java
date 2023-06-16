package logic.sql.model;

import base.model.JavaBeansModel;

/** 検索条件用ブランドリストOutDM **/
public class SQL0001Out extends JavaBeansModel {

	/** ブランドID **/
	private String brandid;
	
	/** ブランド情報 **/
	private String brandData;

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
	 * @return brandData ブランド情報
	 */
	public String getBrandData() {
		return brandData;
	}

	/**
	 * @param brandData ブランド情報
	 */
	public void setBrandData(String brandData) {
		this.brandData = brandData;
	}
	// getter/setter end -----------------------------------------
}
