package logic.sql.model;

import base.model.JavaBeansModel;

/** 塗料一覧リストInDM **/
public class SQL0003In extends JavaBeansModel {

	/** 塗料名 **/
	private String colornm;

	/** ブランドID **/
	private String brandid;

	/** プラモデルID **/
	private String plmdlid;
	
	// getter/setter -----------------------------------------
	/**
	 * @return colornm
	 */
	public String getColornm() {
		return colornm;
	}

	/**
	 * @param colornm セットする colornm
	 */
	public void setColornm(String colornm) {
		this.colornm = colornm;
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

	/**
	 * @return plmdlid
	 */
	public String getPlmdlid() {
		return plmdlid;
	}

	/**
	 * @param plmdlid セットする plmdlid
	 */
	public void setPlmdlid(String plmdlid) {
		this.plmdlid = plmdlid;
	}
	// getter/setter end -----------------------------------------
}
