package logic.sql.model;

import base.model.JavaBeansModel;

/**
 * @author kk-ma
 * 塗料一覧登録SQLInDM
 */
public class SQL2001In extends JavaBeansModel {
	
	/** ブランドID **/
	private String brandid;
	
	/** カラーコード **/
	private String colorcode;

	/** カラー名 **/
	private String colornm;
	
	/** 所持 **/
	private String posession;
	
	/** 選択肢表示 **/
	private String selvisible;
	
	/** 近似塗料ID **/
	private String appaintid;

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
	 * @return posession
	 */
	public String getPosession() {
		return posession;
	}

	/**
	 * @param posession セットする posession
	 */
	public void setPosession(String posession) {
		this.posession = posession;
	}

	/**
	 * @return selvisible
	 */
	public String getSelvisible() {
		return selvisible;
	}

	/**
	 * @param selvisible セットする selvisible
	 */
	public void setSelvisible(String selvisible) {
		this.selvisible = selvisible;
	}

	/**
	 * @return appaintid
	 */
	public String getAppaintid() {
		return appaintid;
	}

	/**
	 * @param appaintid セットする appaintid
	 */
	public void setAppaintid(String appaintid) {
		this.appaintid = appaintid;
	}	
	// getter/setter end -----------------------------------------

}
