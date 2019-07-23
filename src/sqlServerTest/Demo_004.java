package sqlServerTest;
/**
 * 给数据库Test_001添加数据
 * @author Administrator
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo_004 {

	//驱动路径
    public static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //数据库地址
    public static final String DBURL = "jdbc:sqlserver://localhost:1434;DatabaseName=Test_001";
    //数据库登录用户名
    public static final String DBUSER = "sa";
    //数据库用户密码
    public static final String DBPASSWORD = "123";
    //数据库连接
    public static Connection conn = null;
    //用于执行SQL语句
    public static Statement stmt = null;
    //SQL语句
    public static final String SQLSTR = "insert into student values('王思','21','女')";

	public static void main(String[] args) {
		try {
			Class.forName(DBDRIVER);
			System.out.println("成功连接");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			stmt = conn.createStatement();//实例化statement
			int executeUpdate = stmt.executeUpdate(SQLSTR);
			System.out.println(executeUpdate);//在控制台输出更新操作的状态，添加成功的话输出为：  1，添加失败的话
			System.out.println("成功添加!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
