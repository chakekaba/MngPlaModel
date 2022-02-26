package logic.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.logic.ExecuteSQL;
import base.model.JavaBeansModel;
import model.DummyMdl;
import model.ExecResult;

public class SQL0001workDetail extends ExecuteSQL {

	public static void main(String[] args) {

		// SQL実行準備
		DbConnection conn = new DbConnection();
		SQL0001workDetail sql0001 = new SQL0001workDetail();
		DummyMdl in = new DummyMdl();
		DummyMdl out = new DummyMdl();
		ExecResult result = new ExecResult();

		try {
			conn.connect();

			if (ResultConstant.NORMAL.equals(result.getResult())) {
				sql0001.execute(conn, in, out, result);
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result.setError(ResultConstant.LOGIC_ERROR, "DB接続でエラー");
		}

		if (!ResultConstant.NORMAL.equals(result.getResult())) {
			System.out.println(result.getErrMsg());
		}
	}


	protected void init(){
		super.sql = "select paintcode, color from tbl0001_paint where selvisible = ?";

		super.sqlId = "SQL0001";
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
