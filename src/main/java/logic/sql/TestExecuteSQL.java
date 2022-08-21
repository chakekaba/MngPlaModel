package logic.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.constant.ResultConstant;
import base.logic.DbConnection;
import base.logic.ExecuteSQL;
import base.model.JavaBeansModel;
import base.model.MdlCommonData;

public class TestExecuteSQL extends ExecuteSQL {

	public static void main(String[] args) {

		// SQL実行準備
		DbConnection conn = new DbConnection();
		TestExecuteSQL sql0001 = new TestExecuteSQL();
		TestExecuteSQLIn in = new TestExecuteSQLIn();
		TestExecuteSQLOut out = new TestExecuteSQLOut();
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

	
	private static class TestExecuteSQLIn extends JavaBeansModel{
		private String selVisible;

		public String getSelVisible() {
			return selVisible;
		}

		public void setSelVisible(String selVisible) {
			this.selVisible = selVisible;
		}
		
	}
	
	private static class TestExecuteSQLOut extends JavaBeansModel{
		
		private String paintcode;
		
		private String colorname;

		public String getPaintcode() {
			return paintcode;
		}

		public void setPaintcode(String paintcode) {
			this.paintcode = paintcode;
		}

		public String getColorname() {
			return colorname;
		}

		public void setColorname(String colorname) {
			this.colorname = colorname;
		}
		
		
	}
	
	protected void init(){
		super.sql = "select paintcode, color from tbl0001_paint where selvisible = ?";

		super.sqlId = "SQLTest";
	}

	protected void editStatement(PreparedStatement pStmt, JavaBeansModel In) throws SQLException {
//		pStmt.setString(1, "1");
	}

	protected void editOut(ResultSet rSet, JavaBeansModel out) throws SQLException {

		// 実行結果の取り出し
//		while (rSet.next()) {
//			System.out.println("塗料コード:" + rSet.getString(1));
//			System.out.println("カラー名:" + rSet.getString(2));
//			System.out.println();
//		}
	}
}
