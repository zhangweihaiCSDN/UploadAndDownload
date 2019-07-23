package sqlServerTest;
/**
 * 测试SQL server能否正常加载
 * @author Administrator
 *
 */

public class Demo_001 {

	
	//驱动路径
    private static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //程序入口函数
    public static void main(String[] args) {
        
    	
    	
    	
    	try {
    		//方式一：测试SQL server加载
            //加载驱动程序
            Class.forName(DBDRIVER);//将外部设置的数据库driver作为参数代入到forName（ ）方法中去
            System.out.println("12334");//加载成功打印消息        12334
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
	
}
