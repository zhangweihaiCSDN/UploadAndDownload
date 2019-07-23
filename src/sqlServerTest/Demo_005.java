package sqlServerTest;
/**
 * 使用工具类OperationDB中的方法操作数据库，进行增删改查操作：
 * 目标：添加一条数据，删除一条数据，修改一条数据，查询一条数据，查询所有数据
 * @author Administrator
 *
 */

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sqlServerTest.entity.StudentEntity;
import sqlServerTest.util.OperationDB;

public class Demo_005 {
//	StudentEntity student = new StudentEntity("天天向上003", "20", "男");
	OperationDB operationDB = new OperationDB("Test_001");
	
	public static void main(String[] args) {
		Demo_005 demo = new Demo_005();
//		demo.queryDatas("student", "*", "");//查询所有学生信息
//		demo.queryDatas("student", "*", "name='天天向上003'");//通过name查询一条学生信息
//		demo.queryDatas("student", "name, age, sex", "name='天天向上001'");//通过name查询一条学生信息
//		demo.queryDatas("student", "*", "sex='男'");//查询性别为男的学生信息
//		demo.insertData("student", "'天天向上003', '20', '女'", "name='天天向上003'");//添加一名学生信息
//		demo.deleteData("student", "name='天天向上003'");//删除一名学生信息
//		demo.updateData("student", "age='30'", "name='天天向上003'");//删除一名学生信息
		BigDecimal total = new BigDecimal("99");
		System.out.println(total);
	}
	
	
	
	
	//查询所有学生信息
	public void queryDatas(String TableName, String InsertValue, String WhereStr) {
		//创建一个list集合存储所有的学生信息
		List<StudentEntity> studentList = new ArrayList<StudentEntity>();
		Boolean linkDB = operationDB.linkDB();
		if (linkDB) {
			ResultSet queryData = operationDB.queryData(TableName, InsertValue, WhereStr);
			try {
				while (queryData.next()) {
					//创建一个学生对象存储学生信息
					StudentEntity student = new StudentEntity();
					student.setName(queryData.getString(1));
					student.setAge(queryData.getString(2));
					student.setSex(queryData.getString(3));
					studentList.add(student);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				operationDB.closeDB();
			}
			System.out.println(studentList);
		} else {
			System.out.println("查询数据失败，数据库连接异常，请核对数据库名称");
		}
	}
	
	//添加一条学生信息
	public void insertData(String TableName, String InsertValue, String WhereStr) {
		Boolean linkDB = operationDB.linkDB();
		if (linkDB) {
			boolean insertData = operationDB.insertData(TableName, InsertValue, WhereStr);
			if (insertData) {
				System.out.println(operationDB.TempInfo);
			} else {
				System.out.println(operationDB.TempInfo);
			}
		} else {
			System.out.println("查询数据失败，数据库连接异常，请核对数据库名称");
		}
	}
	
	//删除一条学生信息
	public void deleteData(String TableName, String WhereStr) {
		Boolean linkDB = operationDB.linkDB();
		if (linkDB) {
			boolean deleteData = operationDB.deleteData(TableName, WhereStr);
			if (deleteData) {
				System.out.println(operationDB.TempInfo);
			} else {
				System.out.println(operationDB.TempInfo);
			}
		} else {
			System.out.println("查询数据失败，数据库连接异常，请核对数据库名称");
		}
	}
	
	//修改一名学生信息
	public void updateData(String TableName, String UpdateValue, String WhereStr) {
		Boolean linkDB = operationDB.linkDB();
		if (linkDB) {
			boolean deleteData = operationDB.updateData(TableName, UpdateValue, WhereStr);
			if (deleteData) {
				System.out.println(operationDB.TempInfo);
			} else {
				System.out.println(operationDB.TempInfo);
			}
		} else {
			System.out.println("查询数据失败，数据库连接异常，请核对数据库名称");
		}
	}
	
	
}
