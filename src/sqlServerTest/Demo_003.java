package sqlServerTest;
/**
 * 查询数据库”Test_001”中”student”表的数据。
 * @author Administrator
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.sg.prism.web.NGWebView;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import sqlServerTest.entity.StudentEntity;

public class Demo_003 {

	//驱动路径
	private static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//数据库地址
	private static final String DBURL = "jdbc:sqlserver://localhost:1433;DataBaseName=Test_001";
	//数据库登录用户名
	private static final String DBUSER = "sa";
	//数据库用户密码
	private static final String DBPASSWORD = "123";
	//数据库连接
	public static Connection conn = null;
	//数据库操作
	public static Statement stat = null;
	//数据库查询结果集
	public static ResultSet rs = null;
	
	
	
	public static void main(String[] args) {
		//结果集存放集合
		List<StudentEntity> students = new ArrayList<>();
		
		try {
			//加载驱动程序
			Class.forName(DBDRIVER);
			//连接数据库
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			//实例化Statement对象
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from student");
			while (rs.next()) {
				//创建一个学生对象
				StudentEntity student = new StudentEntity();
				student.setName(rs.getString(1));
				student.setAge(rs.getString(2));
				student.setSex(rs.getString(3));
				students.add(student);
			}
			stat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(students);/*在控制台打印所有学生信息，结果如下：
										[StudentEntity [name=天天向上002, age=20, sex=男], StudentEntity [name=天天向上002, age=20, sex=男]]*/
	}
	
}
