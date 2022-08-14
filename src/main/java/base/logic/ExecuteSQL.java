package base.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.constant.ResultConstant;
import base.model.JavaBeansModel;
import model.MdlCommonData;

/**
 * SQL実行用の基底クラス<br>
 * 各SQL実行クラスは本クラスを呼び出す
 * @author kohei kajiki
 *
 */
abstract public class ExecuteSQL {

	/** SQL文**/
	protected String sql = "";

	/** SQLid **/
	protected String sqlId = "";

	private final String SELECT = "select";

	/**
	 * メインメソッド　試験用
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}


	/**
	 * SQL実行メイン処理
	 * @param in
	 * @param out
	 * @param result
	 * @throws SQLException
	 */
	public void execute(DbConnection conn, MdlCommonData comData, JavaBeansModel in, JavaBeansModel out) {

		PreparedStatement pStmt = null;

		ResultSet rSet = null;

		// 初期化
		init();

		if (sql.length() == 0 || sql == null) {
			String errMsg = sqlId + ":SQL文が設定されていません";

			comData.setResult(ResultConstant.SQL_ERROR);
			comData.setErrorData(new ExceptionLogic(), errMsg);
		}

		if (ResultConstant.NORMAL.equals(comData.getResult())) {

			// 処理結果:正常の場合
			try {
				pStmt = conn.getStatement(sql);

				// SQL文のパラメータ編集
				editStatement(pStmt, in);

				if (isSelectSQL()) {
					// select文の場合
					rSet = executeR(pStmt);

					// 結果セットの編集
					editOut(rSet, out);
				} else {
					
					// select以外の文の場合
					executeCUD(pStmt);
				}
			} catch (SQLException e) {
//				e.printStackTrace();
				// 例外発生を記録
				String errMsg = sqlId + ":SQL実行時に例外発生";
				
				comData.setResult(ResultConstant.SQL_ERROR);
				comData.setErrorData(e, errMsg);
				
			} finally {
				try {
					// リソースのクローズ
					if (rSet != null) {
						rSet.close();
					}
					if (pStmt != null) {
						pStmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * SQLがselect文かどうか判定
	 * @return
	 * true:select<br>
	 * false:select以外
	 */
	private boolean isSelectSQL()	{

		// 空白を除いた先頭文字を取得
		String head = sql.trim().substring(0, SELECT.length()).toLowerCase();

		// 先頭文字が"select"であるか判定
		return head.equals(SELECT);
	}

	/**
	 * select文実行
	 * @param pStmt
	 * @return
	 * @throws SQLException
	 */
	private ResultSet executeR(PreparedStatement pStmt) throws SQLException {

		// SQL実行
		ResultSet rSet = pStmt.executeQuery();

		return rSet;
	}

	/**
	 * insert/update/delete文実行
	 * @param pStmt
	 * @throws SQLException
	 */
	private void executeCUD(PreparedStatement pStmt) throws SQLException {

		// SQL実行
		pStmt.execute();
	}

	// ---- 以下は子クラスでオーバーライドするメソッド -----------------------
	/**
	 * 初期化処理<br>
	 * sql文,sqlIdを設定
	 */
	abstract protected void init();

	/**
	 * PreparedStatementの編集<br>
	 * ※ パラメータ無しのSQLの場合はオーバーライドをせず、処理なしのメソッドとする。
	 * @param pStmt
	 * @param In
	 */
	abstract protected void editStatement(PreparedStatement pStmt, JavaBeansModel In) throws SQLException;

	/**
	 * select文の出力結果を編集
	 * @param rSet
	 * @param out
	 * @throws SQLException
	 */
	abstract protected void editOut(ResultSet rSet, JavaBeansModel out) throws SQLException;
}
