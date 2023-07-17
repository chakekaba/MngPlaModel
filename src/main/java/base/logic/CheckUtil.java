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
	public static boolean requiredCheck(String str) {
		return !(str == null || str.trim().isEmpty());
	}
	
	/**
	 * 文字列長チェック
	 * （チェック対象が Null でないかの判定は事前に別個で行うものとする）
	 * @param str
	 * @param maxLen
	 * @return
	 */
	public static boolean lengthCheck(String str, int maxLen) {
		return str.length() <= maxLen;
	}
	
	// 数値型チェック
	// 数値範囲チェック
	// 正規表現パターンチェック
}
