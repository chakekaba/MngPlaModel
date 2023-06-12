package logic.sql.model;

import base.model.JavaBeansModel;

/**
 * ブランド情報取得SQL_InDM
 * @author kk-ma
 *
 */
public class SQL0005In extends JavaBeansModel {
	
	/** ブランドID **/
	private String brandid;

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
	
	// getter/setter end -----------------------------------------
}
