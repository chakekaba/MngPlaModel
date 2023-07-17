package logic.sv.model;

import base.model.JavaBeansModel;

public class MdlLogic02010In extends JavaBeansModel {

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
	
	/** 処理パターン **/
	private String logicPtn;

	/**
	 * コンストラクタ（フィールド初期化）
	 */
	public MdlLogic02010In() {
		this.init();
	}
	
	/**
	 * コンストラクタ（フィールド設定）
	 * @param brandid
	 * @param colorcode
	 * @param colornm
	 * @param posession
	 * @param selvisible
	 * @param appaintid
	 * @param logicPtn
	 */
	public MdlLogic02010In(
			String brandid,
			String colorcode,
			String colornm,
			String posession,
			String selvisible,
			String appaintid,
			String logicPtn) {
		super();
		this.brandid = brandid;
		this.colorcode = colorcode;
		this.colornm = colornm;
		this.posession = posession;
		this.selvisible = selvisible;
		this.appaintid = appaintid;
		this.logicPtn = logicPtn;
	}

	/**
	 * フィールド初期化処理
	 */
	public void init() {
		brandid = null;
		colorcode = null;
		colornm = null;
		posession = null;
		selvisible = null;
		appaintid = null;
		logicPtn = null;
	}

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

	/**
	 * @return logicPtn
	 */
	public String getLogicPtn() {
		return logicPtn;
	}

	/**
	 * @param logicPtn セットする logicPtn
	 */
	public void setLogicPtn(String logicPtn) {
		this.logicPtn = logicPtn;
	}
	// getter/setter end -----------------------------------------
}
