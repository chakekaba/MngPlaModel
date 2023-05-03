package logic.sql.model;

import base.model.JavaBeansModel;

/**
 * @author kk-ma
 * 塗料一覧行数カウント用SQL1_OutDM
 */
public class SQL1001In extends JavaBeansModel {
	
	/** ブランドID **/
	private String brandid;
	
	/** カラーコード **/
	private String colorcode;

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
	 * @return colorcode
	 */
	public String getColorcode() {
		return colorcode;
	}

	/**
	 * @param colorcode セットする colorcode
	 */
	public void setColorcode(String colorcode) {
		this.colorcode = colorcode;
	}
	// getter/setter end -----------------------------------------
}
