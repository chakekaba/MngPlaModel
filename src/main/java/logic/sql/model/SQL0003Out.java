package logic.sql.model;

import base.model.JavaBeansModel;

/** 塗料一覧リストOutDM **/
public class SQL0003Out extends JavaBeansModel {

	/** ブランド名 **/
	private String brandnm;

	/** カラーコード **/
	private String colorcode;

	/** カラー名 **/
	private String colornm;

	/** 所持 **/
	private String posession;

	/** 近似カラーコード **/
	private String apcolorcode;

	/** 近似カラー名 **/
	private String apcolornm;

	// getter/setter -----------------------------------------
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
	 * @return brandnm
	 */
	public String getBrandnm() {
		return brandnm;
	}

	/**
	 * @param brandnm セットする brandnm
	 */
	public void setBrandnm(String brandnm) {
		this.brandnm = brandnm;
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
	 * @return apcolorcode
	 */
	public String getApcolorcode() {
		return apcolorcode;
	}

	/**
	 * @param apcolorcode セットする apcolorcode
	 */
	public void setApcolorcode(String apcolorcode) {
		this.apcolorcode = apcolorcode;
	}

	/**
	 * @return apcolornm
	 */
	public String getApcolornm() {
		return apcolornm;
	}

	/**
	 * @param apcolornm セットする apcolornm
	 */
	public void setApcolornm(String apcolornm) {
		this.apcolornm = apcolornm;
	}
	// getter/setter end -----------------------------------------
}
