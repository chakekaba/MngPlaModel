package logic.sv;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import base.constant.ResultConstant;
import base.model.MdlCommonData;
import logic.sv.model.MdlLogic02000In;
import logic.sv.model.MdlLogic02000Out;

@TestInstance(Lifecycle.PER_CLASS)
class Logic02000IT {

	// 使用インスタンス呼出
	public MdlCommonData comData;
	public Logic02000 logic02000;
	public MdlLogic02000In inputData;
	public MdlLogic02000Out outputData;

	@BeforeAll
	public void doBeforeAllTest() {
		logic02000 = new Logic02000();
	}
	
	@BeforeEach
	public void doBeforeEachTest() {
		comData = new MdlCommonData();
		outputData = new MdlLogic02000Out();
	}
	
	@ParameterizedTest
	@MethodSource("dataSuccess")
	@DisplayName("正常終了")
	public void success(
			String colornm,
			String brandid,
			String plmdlid,
			boolean searchExeFlg) {
		
		// 入力データ設定
		inputData = new MdlLogic02000In(colornm, brandid, plmdlid, searchExeFlg);
		
		// Logic02000実行
		logic02000.execute(inputData, outputData, comData);
		
		// 実行結果判定
		assertEquals(ResultConstant.NORMAL, comData.getResult());
		
		// エラー情報件数
		assertEquals(0, comData.getErrorCnt());
	}
	
	/**
	 * successメソッド用テストデータ提供
	 * @return
	 */
	static Stream<Arguments> dataSuccess() {
		return Stream.of(
				Arguments.arguments("12345678901234567890", "am", "0001", true), // 正常データ
				Arguments.arguments(null, null, null, true),
				Arguments.arguments("", "", "", true),
				Arguments.arguments(" ", " ", " ", true),
				Arguments.arguments("123456789012345678901", "xx", "xxxx", false) // 検索実行フラグfalseで他データが異常の場合
		);
	}
	
	@ParameterizedTest
	@MethodSource("dataFailInvalidData")
	@DisplayName("異常終了")
	public void failInvalidData(
			String colornm,
			String brandid,
			String plmdlid,
			boolean searchExeFlg,
			int errorCnt) {
		
		// 入力データ設定
		inputData = new MdlLogic02000In(colornm, brandid, plmdlid, searchExeFlg);
		
		// Logic02000実行
		logic02000.execute(inputData, outputData, comData);

		// 実行結果判定
		assertEquals(ResultConstant.LOGIC_ERROR, comData.getResult());
		
		// エラー情報件数
		assertEquals(errorCnt, comData.getErrorCnt());
	}

	static Stream<Arguments> dataFailInvalidData() {
		return Stream.of(
				Arguments.arguments("123456789012345678901", "xx", "xxxx", true, 2)
		);
	}

}
