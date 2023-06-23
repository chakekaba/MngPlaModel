package base.logic;

import base.model.JavaBeansModel;
import base.model.MdlCommonData;

/**
 * ロジック処理インターフェース
 * @author kk-ma
 *
 */
public interface ServerLogic {

	public void execute(
			JavaBeansModel inputData,
			JavaBeansModel outputData,
			MdlCommonData comData);
}
