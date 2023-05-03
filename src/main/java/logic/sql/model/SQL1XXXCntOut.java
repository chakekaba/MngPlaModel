package logic.sql.model;

import base.model.JavaBeansModel;


/**
 * @author kk-ma
 * 行数カウント用SQLOutDM
 */
public class SQL1XXXCntOut extends JavaBeansModel {
	
	/** 行カウント数 **/
	private int rowCnt;

	// getter/setter -----------------------------------------
	/**
	 * @return rowCnt
	 */
	public int getRowCnt() {
		return rowCnt;
	}

	/**
	 * @param rowCnt セットする rowCnt
	 */
	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}
	
	// getter/setter end -----------------------------------------
}
