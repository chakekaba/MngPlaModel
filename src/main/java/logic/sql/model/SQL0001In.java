package logic.sql.model;

import base.model.JavaBeansModel;

/** 検索条件用ブランドリストInDM **/
public class SQL0001In extends JavaBeansModel {

	/** sql番号 **/
	private int sqlNo;
	
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
	// getter/setter end -----------------------------------------
}
