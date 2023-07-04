package logic.sv;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import base.constant.ResultConstant;
import base.model.MdlCommonData;
import logic.sv.model.MdlLogicLoginIn;
import logic.sv.model.MdlLogicLoginOut;

class LogicLoginUT {

	// 使用インスタンス呼出
	public MdlCommonData comData;
	public LogicLogin logicLogin;
	public MdlLogicLoginIn inputData;
	public MdlLogicLoginOut outputData;
	Method method;

	@BeforeEach
	public void createNewInstance() {
		comData = new MdlCommonData();
		logicLogin = new LogicLogin();
	}

	@Nested
	@DisplayName("checkInputDataメソッド")
	class checkInputDataTest {

		@BeforeEach
		public void defMdlData () throws Exception {
			inputData = new MdlLogicLoginIn();
			outputData = new MdlLogicLoginOut();

			// checkInputDataメソッド取得
			method = LogicLogin.class.getDeclaredMethod(
					"checkInputData",
					MdlLogicLoginIn.class,
					MdlCommonData.class);

			method.setAccessible(true);
		}

		/**
		 * .@MethodSourceの使用例
		 * @param name
		 * @param pass
		 * @param msg
		 * @throws Exception
		 */
		@ParameterizedTest
		@MethodSource("dataTestInvalid")
		@DisplayName("入力値エラーが1件のみ発生する場合")
		public void testInvalid(
				String name,
				String pass,
				String msg) throws Exception {

			// 入力データ設定
			inputData.setUserName(name);
			inputData.setPassword(pass);

			method.invoke(logicLogin, inputData, comData);

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
					Arguments.arguments(null, "_pass", "必須チェックエラー：ユーザ名"),
					Arguments.arguments("", "_pass", "必須チェックエラー：ユーザ名"),
					Arguments.arguments(" ", "_pass", "必須チェックエラー：ユーザ名"),
					Arguments.arguments("_name", null, "必須チェックエラー：パスワード"),
					Arguments.arguments("_name", "", "必須チェックエラー：パスワード"),
					Arguments.arguments("_name", " ", "必須チェックエラー：パスワード")
			);
		}

		/**
		 * .@CsvSourceの使用例
		 * @param name
		 * @param pass
		 */
		@ParameterizedTest
		@CsvSource({
			"'a','b'",
			"'sssssssss', 'xxxxxxxxxx'"
		})
		@DisplayName("正常な入力値データの場合")
		public void testValid(String name, String pass) {

			// 入力データ設定
			inputData.setUserName(name);
			inputData.setPassword(pass);

			// 実行結果判定
			assertEquals(ResultConstant.NORMAL, comData.getResult());

			// エラー情報件数
			assertEquals(0, comData.getErrorCnt());
		}

		/** エラーが複数発生するパターンは LogicLoginIT で実施 **/
	}

	@Nested
	@DisplayName("doSql_SQL0000メソッド")
	class doSql_SQL0000Test {

		@BeforeEach
		public void defMdlData() throws Exception{

			// doSql_SQL0000メソッド取得
			method = LogicLogin.class.getDeclaredMethod(
					"doSql_SQL0000",
					MdlCommonData.class);

			method.setAccessible(true);
		}


	}
}
