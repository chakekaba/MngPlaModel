package logic.sv.model;

import base.model.JavaBeansModel;

public class MdlLogic02000In extends JavaBeansModel {
	
	/** 塗料名 **/
	private String colornm;
	
	/** ブランドID **/
	private String brandid;
	
	/** プラモデルID **/
	private String plmdlid;
	
	/** 検索実行フラグ **/
	private boolean searchExeFlg;
	
	
	/**
	 * コンストラクタ（フィールド初期化）
	 */
	public MdlLogic02000In(){
		this.init();
	}
	
	
	
	/**
	 * コンストラクタ（フィールド設定）
	 * @param colornm
	 * @param brandid
	 * @param plmdlid
	 * @param searchExeFlg
	 */
	public MdlLogic02000In(
			String colornm,
			String brandid,
			String plmdlid,
			boolean searchExeFlg) {
		super();
		this.colornm = colornm;
		this.brandid = brandid;
		this.plmdlid = plmdlid;
		this.searchExeFlg = searchExeFlg;
	}



	/**
	 * フィールド初期化処理
	 */
	public void init() {
		colornm = null;
		brandid = null;
		plmdlid = null;
		searchExeFlg = false; // デフォルトはfalse
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
	 * @return searchExeFlg
	 */
	public boolean getSearchExeFlg() {
		return searchExeFlg;
	}

	/**
	 * @param searchExeFlg セットする searchExeFlg
	 */
	public void setSearchExeFlg(boolean searchExeFlg) {
		this.searchExeFlg = searchExeFlg;
	}
}
