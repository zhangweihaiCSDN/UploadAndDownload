package sqlServerTest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 操作数据库的类,连接SQLserver数据库,以及对数据库的增删改查操作
 * @author Administrator
 *
 */
public class OperationDB {

	//驱动路径
    private static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //数据库地址
    private String DBURL = "jdbc:sqlserver://localhost:1434;DataBaseName=";
    //数据库登录用户名
    private static final String DBUSER = "sa";
    //数据库用户密码
    private static final String DBPASSWORD = "123";
    //数据库连接
    public Connection conn = null;
    //执行SQL语句的接口
    public Statement stmt = null;
    //要执行的SQL语句
    public String SQLStr = null;
    //数据容器
    public ResultSet rs = null;
    //提示信息
    public String TempInfo = "";
	
    //有参的构造方法
	public OperationDB(String DBName) {
		super();
		this.DBURL += DBName;
	}

	/**
	 * 连接数据库：成功返回true，失败返回false
	 * @return
	 */
	public Boolean linkDB() {
		try {
			Class.forName(DBDRIVER);//连接数据库
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);//创建连接
			stmt = conn.createStatement();//实例化声明
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				stmt.close();
				conn.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return false;
		}
	}
    
	/**
	 * 关闭数据库连接：成功返回true，失败返回false
	 * @return
	 */
    public Boolean closeDB() {
		try {
			stmt.close();
			conn.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
    
    /**
     * 添加数据
     * @param TableName 要操作的数据库表名
     * @param InsertValue 要增加的数据
     * @param WhereStr 查询表中是否已存在要新增的信息
     * @return 成功返回true,失败返回false
     */
    public boolean insertData(String TableName, String InsertValue, String WhereStr){
        this.SQLStr = "insert into " + TableName + " values (" + InsertValue + ")";
        try {
            if(stmt.executeQuery("select * from " + TableName + " where " + WhereStr).next()){
                TempInfo = "该记录已存在!";
                this.closeDB();
                return false;
            }else{
                stmt.executeUpdate(SQLStr);
                this.closeDB();
                TempInfo = "添加记录成功!";
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.closeDB();
            return false;
        }

    }

    /**
     * 删除数据
     * @param TableName 要操作的数据库表名
     * @param WhereStr 删除哪一条记录
     * @return 成功返回true,失败返回false
     */
    public boolean deleteData(String TableName, String WhereStr){
        this.SQLStr = "delete from " + TableName + " where " + WhereStr;
        try {
            if(stmt.executeUpdate(SQLStr) != 0){
                this.closeDB();
                TempInfo = "删除数据成功!";
                return true;
            }else{
                this.closeDB();
                TempInfo = "数据不存在!";
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.closeDB();
            return false;
        }
    }

    /**
     * 修改数据
     * @param TableName  要操作的数据库表名
     * @param UpdateValue  要修改的属性
     * @param WhereStr 要修改哪一条记录
     * @return 成功返回true,失败返回false
     */
    public boolean updateData (String TableName, String UpdateValue, String WhereStr){
        this.SQLStr = "update " + TableName + " set " + UpdateValue + " where " + WhereStr;
        try {
            if(stmt.executeUpdate(SQLStr) != 0){
                this.closeDB();
                TempInfo = "修改数据成功!";
                return true;
            }else{
                this.closeDB();
                TempInfo = "数据不存在!";
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.closeDB();
            return false;
        }
    }
    
    /**
     * 查询数据,该方法没有关闭数据库连接,因为不能关闭RS结果集
     * @param TableName 要操作的数据库表名
     * @param QueryValue 需要查询的数据
     * @param WhereStr 查询哪一条记录
     * @return 返回一个ResultSet集合
     */
    public ResultSet queryData(String TableName, String QueryValue, String WhereStr){
        if(WhereStr == ""){
            this.SQLStr = "select "+ QueryValue + " from " + TableName;
        }else{
            this.SQLStr = "select "+ QueryValue + " from " + TableName + " where " + WhereStr;
        }
        try {
            rs = stmt.executeQuery(SQLStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
