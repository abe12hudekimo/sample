package sql操作;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectAllStudent {

	public static void main(String[] args) {

		final String URL = "jdbc:mysql://localhost/sampledb";
		final String USER = "root";
		final String PASS = "pass";
		String sql = "SELECT * FROM student";

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			//①DBに接続
			con = DriverManager.getConnection(URL, USER, PASS);
			//②ステートメントを生成
			stmt = con.createStatement();
			//③SQLを実行
			rs = stmt.executeQuery(sql);
			//④検索結果の処理
			System.out.println("+-------+-------+-------+");
			System.out.println("| no    | name  | score |");
			System.out.println("+-------+-------+-------+");
			while (rs.next()) {
				System.out.print("| " + rs.getInt("no") + "\t");
				System.out.print("| " + rs.getString("name") + "\t");
				System.out.println("| " + rs.getInt("score") + "\t|");
			}
			System.out.println("+-------+-------+-------+");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//⑤リソースを解放
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}