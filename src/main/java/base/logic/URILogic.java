package base.logic;



import base.logic.model.URILogicModel;
import jakarta.servlet.http.HttpServletRequest;

public class URILogic {

	/**  **/
	protected static final String HEAD_REFERRER = "Referer";
	
	protected static final String ORIGIN_PATH = "http://localhost:8080";
	
	public String getRefererViewId(HttpServletRequest request) {
		String refererViewId = "";

//		refererViewId = request.getHeader("Referer");

		refererViewId = request.getHeader("Origin");
		
		return refererViewId;
	}
	
	public void getReferrerViewData(HttpServletRequest request, URILogicModel uriLogicModel) {
		
		// 遷移元画面のURIを取得
		String referrerViewURI = request.getHeader(HEAD_REFERRER);
		
		// コンテキストパスを取得
		String contextPath = request.getContextPath();
		
		// サーブレットパス以下を取得
		int idxStartServletPath = ORIGIN_PATH.length() + contextPath.length();
		
		String servletPath = referrerViewURI.substring(idxStartServletPath);
		
		// モデルクラスに取得した値を設定
		uriLogicModel.setFullURI(referrerViewURI);
		
		uriLogicModel.setServletPath(servletPath);
	}
}
