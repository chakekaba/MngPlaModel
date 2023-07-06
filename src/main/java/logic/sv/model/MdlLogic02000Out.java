
package logic.sv.model;

import java.util.ArrayList;
import java.util.List;

import base.model.JavaBeansModel;
import logic.sql.model.SQL0001Out;
import logic.sql.model.SQL0002Out;
import logic.sql.model.SQL0003Out;

public class MdlLogic02000Out extends JavaBeansModel {

	/** 塗料名 **/
	private String colornm;
	
	/** ブランドID **/
	private String brandid;
	
	/** プラモデルID **/
	private String plmdlid;
	
	/** ブランドリスト **/
	private List<SQL0001Out> brandList = new ArrayList<SQL0001Out>();
	
	/** プラモデルリスト **/
	private List<SQL0002Out> plmdlList = new ArrayList<SQL0002Out>();
	
	/** 検索結果リスト **/
	private List<SQL0003Out> resultList = new ArrayList<SQL0003Out>();

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
	 * @return plmdlList
	 */
	public List<SQL0002Out> getPlmdlList() {
		return plmdlList;
	}

	/**
	 * @param plmdlList セットする plmdlList
	 */
	public void setPlmdlList(List<SQL0002Out> plmdlList) {
		this.plmdlList = plmdlList;
	}

	/**
	 * @return resultList
	 */
	public List<SQL0003Out> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<SQL0003Out> resultList) {
		this.resultList = resultList;
	}
	
	
}
