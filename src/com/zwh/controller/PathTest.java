package com.zwh.controller;

public class PathTest {

	
	
	public static void main(String[] args) {
		
		//普通java类中获取workspace中项目的根目录
//		String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
//		System.out.println(t+"-------------tttttttt----------------------");
//		int num=t.indexOf(".metadata");
//		System.out.println(num+"-------------num----------------------");
//		String path=t.substring(1,num).replace('/', '\\')+"Java-20190701-servlet-001\\WebContent\\file";
//		System.out.println(path+"-------------path----------------------");
		
		
		
		
//		1、获取sts-3.9.2.RELEASE（也就是官方为springBoot工程特制的一个插件）的路径
		String a1=System.getProperty("user.dir");
		System.out.println(a1+"-------------a1----------------");//打印结果为：    D:\zhangweihai\workspace-sts-3.9.2.RELEASE\Java-20190701-servlet-001-------------a1----------------
		
//		2、获取当前的classpath路径:这里需要注意的是各个路径的差别，在使用过程中不需要究其根理，只需要应用，但是如果有时间的话，查其本源还是很有必要的，因为这些可以帮我们拓展知识面
		String a2=PathTest.class.getResource("").toString();
		String a3=PathTest.class.getResource("/").toString();
		String a4=PathTest.class.getClassLoader().getResource("").toString();
		String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		
		System.out.println(a2+"-------------a2----------------");//打印结果为：   file:/D:/zhangweihai/workspace-sts-3.9.2.RELEASE/Java-20190701-servlet-001/build/classes/com/zwh/controller/-------------a2----------------
		System.out.println(a3+"-------------a3----------------");//打印结果为：   file:/D:/zhangweihai/workspace-sts-3.9.2.RELEASE/Java-20190701-servlet-001/build/classes/-------------a3----------------
		System.out.println(a4+"-------------a4----------------");//打印结果为：   file:/D:/zhangweihai/workspace-sts-3.9.2.RELEASE/Java-20190701-servlet-001/build/classes/-------------a4----------------
		System.out.println(t+"---------------t----------------");//打印结果为：   /D:/zhangweihai/workspace-sts-3.9.2.RELEASE/Java-20190701-servlet-001/build/classes/-------------t----------------
		
		
//		3、获取文件的绝对路径
//		如果要获取WebContent目录下的文件绝对路径怎么办？可以用下面的方法
		String t2=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
		 int num=t2.indexOf("build");
		 System.out.println(num);
		 String path=t2.substring(1,num).replace('/', '\\')+"WebContent\\static\\file";
		 System.out.println(path+"----------path--------------");//打印结果为：   D:\zhangweihai\workspace-sts-3.9.2.RELEASE\Java-20190701-servlet-001\WebContent\static\file----------path--------------

		
	}
	
	
	
}
