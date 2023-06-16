package logic.sv.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import base.constant.ValueConstant;
import base.model.JavaBeansModel;
import logic.sql.model.SQL0001Out;
import logic.sql.model.SQL0004Out;

public class MdlLogic02010Out extends JavaBeansModel {
	
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

	/** ブランドリスト **/
	private List<SQL0001Out> brandList = new ArrayList<SQL0001Out>();

	/** 所持 **/
	private Map posessionMap = ValueConstant.POSESSION_MAP;
	
	/** 選択肢表示 **/
	private Map selvisibleMap = ValueConstant.SEL_VISIBLE_MAP;

	/** 近似塗料リスト **/
	private List<SQL0004Out> appaintList = new ArrayList<SQL0004Out>();
	

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

	/**
	 * @return brandList
	 */
	public List<SQL0001Out> getBrandList() {
		return brandList;
	}

	/**
	 * @param brandList セットする brandList
	 */
	public void setBrandList(List<SQL0001Out> brandList) {
		this.brandList = brandList;
	}

	/**
	 * @return posessionMap
	 */
	public Map<String, String> getPosessionMap() {
		return posessionMap;
	}

	/**
	 * @return selvisibleMap
	 */
	public Map<String, String> getSelvisibleMap() {
		return selvisibleMap;
	}

	/**
	 * @return appaintList
	 */
	public List<SQL0004Out> getAppaintList() {
		return appaintList;
	}

	/**
	 * @param appaintList セットする appaintList
	 */
	public void setAppaintList(List<SQL0004Out> appaintList) {
		this.appaintList = appaintList;
	}
}
