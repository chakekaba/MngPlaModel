package base.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kk-ma
 * DBデータに依存しないパラメータの設定可能値の定義クラス
 */
public class ValueConstant {
	
	/** 所持map **/
	public static final Map<String, String> POSESSION_MAP;
	static {
		Map<String, String> posessionMap = new HashMap<>();
		
		// 設定可能値定義
		posessionMap.put("0", "未所持");
		posessionMap.put("1", "所持");
		
		POSESSION_MAP = Collections.unmodifiableMap(posessionMap);
	}
	
	/**
	 * 設定可能値チェック：所持
	 * @param posession
	 * @return
	 */
	public static final boolean isProperPosession(String posession) {
		// 引数posessionが設定可能値であるか判定
		return POSESSION_MAP.containsKey(posession);
	}
	
	/** 選択肢表示map **/
	public static final Map<String, String> SEL_VISIBLE_MAP;
	static {
		Map<String, String> selvisibleMap = new HashMap<>();
		
		// 設定可能値定義
		selvisibleMap.put("0", "非表示");
		selvisibleMap.put("1", "表示");
		
		SEL_VISIBLE_MAP = Collections.unmodifiableMap(selvisibleMap);
	}
	
	public static final boolean isProperSelValue(String selValue) {
		// 引数selValueが設定可能値であるか判定
		return SEL_VISIBLE_MAP.containsKey(selValue);
	}

}
