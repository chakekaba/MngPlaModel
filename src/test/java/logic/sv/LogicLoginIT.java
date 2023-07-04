package logic.sv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.constant.ResultConstant;
import base.model.MdlCommonData;
import logic.sv.model.MdlLogicLoginIn;
import logic.sv.model.MdlLogicLoginOut;

class LogicLoginIT {

	// 使用インスタンス呼出
	public MdlCommonData comData;
	public LogicLogin logicLogin;
	public MdlLogicLoginIn inputData;
	public MdlLogicLoginOut outputData;

	@BeforeEach
	public void createNewInstance() {
		comData = new MdlCommonData();
		logicLogin = new LogicLogin();
		inputData = new MdlLogicLoginIn();
		outputData = new MdlLogicLoginOut();
	}

	@Test
	@DisplayName("正常終了")
	public void success() {
		inputData.setUserName("user");
		inputData.setPassword("pass");

		logicLogin.execute(inputData, outputData, comData);

		// 実行結果判定
		assertEquals(ResultConstant.NORMAL, comData.getResult());

		// エラー情報件数
		assertEquals(0, comData.getErrorCnt());
	}

	@Test
	@DisplayName("DB停止状態で実行、3件エラーが発生する場合")
	public void failDbconn() {
		inputData.setUserName(null);
		inputData.setPassword(null);

		logicLogin.execute(inputData, outputData, comData);

		// 実行結果判定
		assertEquals(ResultConstant.LOGIC_ERROR, comData.getResult());

		// エラー情報件数
		assertEquals(3, comData.getErrorCnt());

		// メッセージ情報判定
		MdlCommonData.ErrorData errorData = comData.getErrorDataList().get(2);
		assertEquals("DB接続時にエラー発生", errorData.getMessage());
		assertTrue(errorData.isSEVERE());
	}

}
