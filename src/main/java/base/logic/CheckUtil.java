package base.logic;

/**
 * 値判定処理クラス
 * @author kk-ma
 *
 */
public class CheckUtil {

	/**
	 * コンストラクタ 
	 */
	public CheckUtil() {
	}
	
	/**
	 * 必須チェック
	 * @param str
	 * @return
	 */
	public boolean requiredCheck(String str) {
		return !(str == null || str.trim().isEmpty());
	}
	
	/**
	 * 文字列長チェック
	 * @param str
	 * @param maxLen
	 * @return
	 */
	public boolean lengthCheck(String str, int maxLen) {
		return str.length() <= maxLen;
	}
	
	// 数値型チェック
	// 数値範囲チェック
	// 正規表現パターンチェック
}
