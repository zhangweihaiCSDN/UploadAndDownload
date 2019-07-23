package sqlServerTest;
/**
 * 连接数据库名为：”Test_001”的数据库，数据库登录用户名：'sa'，密码：'123'的数据库
 * 程序运行结果不为空则证明数据库连接正确
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo_002 {

	public static void main(String[] args) {
		Connection con = null;
		try {
			// 加载数据库驱动类
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("数据库驱动加载成功");
			
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1434;DatabaseName=Test_001", "sa", "123");//这里使用端口号1434和1433都能够连接到数据库，但是建议使用1433
//			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1434;DatabaseName=Test_001", "sa", "123");//这里使用端口号1434和1433都能够连接到数据库，但是建议使用1433
			System.out.println(con);/*如果连接成功，这里在控制台打印连接ID和用户的连接ID，如下:
										使用1433端口：ConnectionID:1 ClientConnectionId: 01d7f415-e4e7-4e4a-a000-c57947df3416
										使用1434端口：ConnectionID:1 ClientConnectionId: ba32fe45-1f97-471a-9270-4d589302b98a*/
			System.out.println("数据库连接成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			con.close();//关闭连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
}
