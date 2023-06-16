package logic.sql.model;

import base.model.JavaBeansModel;

/**
 * 塗料情報取得SQL_InDM
 * @author kk-ma
 *
 */
public class SQL1002In extends JavaBeansModel {

	/** sql番号 **/
	private int sqlNo;
	
	/** ブランドID **/
	private String brandid;

	// getter/setter -----------------------------------------
	/**
	 * @return sqlNo
	 */
	public int getSqlNo() {
		return sqlNo;
	}

	/**
	 * @param sqlNo セットする sqlNo
	 */
	public void setSqlNo(int sqlNo) {
		this.sqlNo = sqlNo;
	}

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
