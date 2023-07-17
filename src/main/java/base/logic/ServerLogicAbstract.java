package base.logic;

import base.model.JavaBeansModel;
import base.model.MdlCommonData;

/**
 * ロジック処理抽象クラス
 * （暫定）実際には継承元としては使用せず、Logicクラスの自動生成のみに使用（暫定）
 * JavaBeansModelクラスで定義している引数は、個々のSQL用データモデルクラスに差し替え
 * @author kk-ma
 *
 */
public abstract class ServerLogicAbstract {

	public abstract void execute(
			JavaBeansModel inputData,
			JavaBeansModel outputData,
			MdlCommonData comData);

	protected abstract void init(
			JavaBeansModel inputData,
			JavaBeansModel outputData,
			MdlCommonData comData);

	protected abstract void checkInputData(
			JavaBeansModel inputData,
			MdlCommonData comData);

	protected abstract void editSetOutputData(
			JavaBeansModel inputData,
			JavaBeansModel outputData,
			MdlCommonData comData);
}
