package base.logic.model;

import base.model.JavaBeansModel;

public class URILogicModel extends JavaBeansModel {
	
	/** 全URI **/
	private String fullURI;
	
	/** サーブレットパス **/
	private String servletPath;

	// -- 全URI ---------------------------
	/** getter - 全URI **/
	public String getFullURI() {
		return fullURI;
	}

	/** setter - 全URI **/
	public void setFullURI(String fullURI) {
		this.fullURI = fullURI;
	}

	// -- サーブレットパス ---------------------------
	/** getter - サーブレットパス **/
	public String getServletPath() {
		return servletPath;
	}

	/** setter - サーブレットパス **/
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	
}
