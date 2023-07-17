package base.logic;

import java.sql.SQLException;
import java.util.List;

import base.model.JavaBeansModel;
import base.model.MdlCommonData;

/**
 * （暫定）実際には継承元としては使用せず、SQL実行クラスの自動生成のみに使用（暫定）
 * JavaBeansModelクラスで定義している引数は、個々のSQL用データモデルクラスに差し替え
 * @author kk-ma
 *
 */
public abstract class SqlLogicAbstract {

	public void execute(
			DbConnection conn,
			MdlCommonData comData,
			JavaBeansModel inData,
			JavaBeansModel outData) {
	}
	
	public void execute(
			DbConnection conn,
			MdlCommonData comData,
			JavaBeansModel inData,
			List<JavaBeansModel> outDataList) {
	}
	
	protected abstract void editStatement(DbConnection conn, JavaBeansModel inData) throws SQLException;
	
	protected abstract void executeSQL() throws SQLException;
	
	protected void editOut(JavaBeansModel outData) throws SQLException  {}
	
	protected void editOut(List<JavaBeansModel> outDataList) throws SQLException  {}
	
	protected abstract void close(MdlCommonData comData);

}
