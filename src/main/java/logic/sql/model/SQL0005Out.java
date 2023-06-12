package logic.sql.model;

import base.model.JavaBeansModel;

/**
 * ブランド情報取得SQL_OutDM
 * @author kk-ma
 *
 */
public class SQL0005Out extends JavaBeansModel {

	/** ブランドID **/
	private String brandid;
	
	/** ブランド情報 **/
	private String brandData;

	// getter/setter -----------------------------------------

	/**
	 * @return brandid
	 */
	public String getBrandid() {
		return brandid;
	}

	/**
	 * @param brandid セットする brandid
	 */
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	/**
	 * @return brandData
	 */
	public String getBrandData() {
		return brandData;
	}

	/**
	 * @param brandData セットする brandData
	 */
	public void setBrandData(String brandData) {
		this.brandData = brandData;
	}

	// getter/setter end -----------------------------------------
}
