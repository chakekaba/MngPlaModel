package base.logic;

import base.model.JavaBeansModel;
import base.model.MdlCommonData;

/**
 * ロジック処理抽象クラス
 * @author kk-ma
 *
 */
public abstract class ServerLogicAbstract implements ServerLogic {

	@Override
	public abstract void execute(
			JavaBeansModel inputData,
			JavaBeansModel outputData,
			MdlCommonData comData);

	protected abstract void exeErr(
			MdlCommonData comData,
			Exception e);
	
	protected abstract void exeFinal(MdlCommonData comData);
	
	protected abstract void init(
			JavaBeansModel inputData,
			JavaBeansModel outputData,
			MdlCommonData comData);
	
	protected abstract void checkInputData(MdlCommonData comData);
	
	protected abstract void editSetOutputData(MdlCommonData comData);
}
