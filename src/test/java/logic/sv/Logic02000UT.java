package logic.sv;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
class Logic02000UT {

	// 使用インスタンス呼出
	public MdlCommonData comData;
	public Logic02000 logic02000;
	public MdlLogic02000In inputData;
	public MdlLogic02000Out outputData;
	Method method;

	@BeforeAll
	public void doBeforeAllTest() {
		logic02000 = new Logic02000();
	}
	
	@BeforeEach
	public void doBeforeEachTest() {
		comData = new MdlCommonData();
	}
	
	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("checkInputDataメソッド")
	class checkInputDataTest {
		
		@BeforeAll
		public void doBeforeAllTest() throws Exception {
			
			// checkInputDataメソッド取得
			method = Logic02000.class.getDeclaredMethod(
					"checkInputData",
					MdlLogic02000In.class,
					MdlCommonData.class);
			
			method.setAccessible(true);

		}
				
		/**
		 * @param colornm
		 * @param brandid
		 * @param plmdlid
		 * @param searchExeFlg
		 * @param msg
		 * @throws Exception
		 */
		@ParameterizedTest
		@MethodSource("dataTestInvalid")
		@DisplayName("入力値エラーが1件のみ発生する場合")
		public void testInvalid(
				String colornm,
				String brandid,
				String plmdlid,
				boolean searchExeFlg,
				String msg) throws Exception {
			
			// 入力データ
			inputData = new MdlLogic02000In(colornm, brandid, plmdlid, searchExeFlg);
			
			// checkInputDataメソッド実行
			method.invoke(logic02000, inputData, comData);
			
			// 実行結果判定
			assertEquals(ResultConstant.LOGIC_ERROR, comData.getResult());

			// エラー情報件数
			assertEquals(1, comData.getErrorCnt());

			// メッセージ情報判定
			MdlCommonData.ErrorData errorData = comData.getErrorDataList().get(0);
			assertEquals(msg, errorData.getMessage());
			assertTrue(errorData.isWARNING());
		}
		
		/**
		 * testInvalidメソッド用テストデータ提供
		 * @return
		 */
		static Stream<Arguments> dataTestInvalid() {
			return Stream.of(
//					Arguments.arguments("テスト", "am", "0001", true, "(エラーメッセージ)"), // 正常時サンプル
					// 塗料名
					Arguments.arguments("123456789012345678901", "am", "0001", true, "文字列長チェックエラー：カラーコード"),
					// ブランドID
					Arguments.arguments("12345678901234567890", "xx", "0001", true, "(エラーメッセージ)")
					// プラモデルID_省略
			);
		}
		
		@ParameterizedTest
		@MethodSource("dataTestValid")
		@DisplayName("正常な入力値データの場合")
		public void testValid(
				String colornm,
				String brandid,
				String plmdlid,
				boolean searchExeFlg) throws Exception {
			
			inputData = new MdlLogic02000In(colornm, brandid, plmdlid, searchExeFlg);
			
			// checkInputDataメソッド実行
			method.invoke(logic02000, inputData, comData);
			
			// 実行結果判定
			assertEquals(ResultConstant.NORMAL, comData.getResult());

			// エラー情報件数
			assertEquals(0, comData.getErrorCnt());

		}
		
		/**
		 * testValidメソッド用テストデータ提供
		 * @return
		 */
		static Stream<Arguments> dataTestValid() {
			return Stream.of(
					Arguments.arguments("12345678901234567890", "am", "0001", true), // 正常データ
					Arguments.arguments(null, null, null, true),
					Arguments.arguments("", "", "", true),
					Arguments.arguments(" ", " ", " ", true),
					Arguments.arguments("123456789012345678901", "xx", "xxxx", false) // 検索実行フラグfalseで他データが異常の場合
			);
		}
	}

	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	@DisplayName("editSetOutputDataメソッド")
	class editSetOutputDataTest {
		
		@BeforeAll
		public void doBeforeAllTest() throws Exception {
			
			// editSetOutputData
			method = Logic02000.class.getDeclaredMethod(
					"editSetOutputData",
					MdlLogic02000In.class,
					MdlLogic02000Out.class,
					MdlCommonData.class);
			
		}
		
		@BeforeEach
		public void doBeforeEachTest() {
			outputData = new MdlLogic02000Out();
		}
		
		@Test
		@DisplayName("検索実行フラグ：true の場合")
		public void flgTrue() throws Exception {
			String colornm = "_colornm";
			String brandid = "_brandid";
			String plmdlid = "_plmdlid";
			boolean searchExeFlg = true;
			
			inputData = new MdlLogic02000In(colornm, brandid, plmdlid, searchExeFlg);
			
			// editSetOutputDataメソッド実行
			method.invoke(logic02000, inputData, outputData, comData);
			
			// 実行結果判定
			assertEquals(ResultConstant.NORMAL, comData.getResult());
			
			// 設定値判定
			assertEquals(colornm, outputData.getColornm());
			assertEquals(brandid, outputData.getBrandid());
			assertEquals(plmdlid, outputData.getPlmdlid());
		}

		@Test
		@DisplayName("検索実行フラグ：false の場合")
		public void flgFalse() throws Exception {
			String colornm = "_colornm";
			String brandid = "_brandid";
			String plmdlid = "_plmdlid";
			boolean searchExeFlg = false;
			
			String expected = null;
			
			inputData = new MdlLogic02000In(colornm, brandid, plmdlid, searchExeFlg);
			
			// editSetOutputDataメソッド実行
			method.invoke(logic02000, inputData, outputData, comData);
			
			// 実行結果判定
			assertEquals(ResultConstant.NORMAL, comData.getResult());
			
			// 設定値判定
			assertEquals(expected, outputData.getColornm());
			assertEquals(expected, outputData.getBrandid());
			assertEquals(expected, outputData.getPlmdlid());

		}
	}
}
