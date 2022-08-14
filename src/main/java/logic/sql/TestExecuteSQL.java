package logic.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.logic.ExecuteSQL;
import base.model.JavaBeansModel;
import model.MdlCommonData;
import model.MdlDummy;

public class TestExecuteSQL extends ExecuteSQL {

	public static void main(String[] args) {

		// SQL実行準備
		DbConnection conn = new DbConnection();
		TestExecuteSQL sql0001 = new TestExecuteSQL();
		MdlDummy in = new MdlDummy();
		MdlDummy out = new MdlDummy();
		MdlCommonData comData = new MdlCommonData();

		try {
			conn.connect();

			if (ResultConstant.NORMAL.equals(comData.getResult())) {
				sql0001.execute(conn, comData, in, out);
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			comData.setErrorData(e, "DB接続でエラー");
		}

	}


	protected void init(){
		super.sql = "select paintcode, color from tbl0001_paint where selvisible = ?";

		super.sqlId = "SQLTest";
	}

	protected void editStatement(PreparedStatement pStmt, JavaBeansModel In) throws SQLException {
		pStmt.setString(1, "1");
	}

	protected void editOut(ResultSet rSet, JavaBeansModel out) throws SQLException {

		// 実行結果の取り出し
		while (rSet.next()) {
			System.out.println("塗料コード:" + rSet.getString(1));
			System.out.println("カラー名:" + rSet.getString(2));
			System.out.println();
		}
	}
}
