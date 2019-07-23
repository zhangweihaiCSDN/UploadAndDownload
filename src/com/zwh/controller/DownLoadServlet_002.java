package com.zwh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet("/downLoadServlet_002")
public class DownLoadServlet_002 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DownLoadServlet_002() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到要下载的文件名
        String fileName = request.getParameter("filename");
        //fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
//        String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");//这里的路径获取的是tomcat里的路径，工程中一旦工程关闭，重新发布时，tomcat里的文件就不见了，这就使得数据库的目录和服务器上的实际文件不一致
        String fileSaveRootPath="Java-20190701-servlet-001/WebContent/static/file/upload";//这里的路径是sts程序所在的文件夹，里面对应的存有WebContent文件夹，这个具体的原理不太清楚，但是，可以使用，
        
        //        处理文件名
         String realname = fileName.substring(fileName.indexOf("_")+1);
        //通过文件名找出文件的所在目录
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path+File.separator+fileName);
        //如果文件不存在
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        
         //设置响应头，控制浏览器下载该文件
         response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
         
         //读取要下载的文件，保存到文件输入流
         FileInputStream fis = new FileInputStream(path + File.separator + fileName);
         //输入通道
         FileChannel readChannel = fis.getChannel();
         //创建输出流
         ServletOutputStream fos = response.getOutputStream();
         
         // 128 KB buffer
		 int bufferSize = 131072;
         //设置缓存区 8x128 KB = 1024KB byte buffer
		 ByteBuffer buffer = ByteBuffer.allocateDirect(1048576);
		 byte[] barray = new byte[bufferSize];
		 int nRead, nGet;
		 try {
			 while ((nRead = readChannel.read(buffer)) != -1) {
				 System.out.println("文件的nRead为：======="+nRead);
				 if (nRead == 0)
					 continue;
				 buffer.position(0);
				 buffer.limit(nRead);
				 while (buffer.hasRemaining()) {
					 nGet = Math.min(buffer.remaining(), bufferSize);
					 // read bytes from disk
					 buffer.get(barray, 0, nGet);
					 // write bytes to output
					 fos.write(barray);
				 }
				 buffer.clear();
			 }
		  } catch (IOException e) {
		    	e.printStackTrace();
		  } finally {
			  buffer.clear();
			  readChannel.close();
			  fis.close();
			  fos.close();
		  }
		 
		 
		 
		 
		 
         
         
         //--------------------------------------------------------------------------------------------------
         
//         //读取要下载的文件，保存到文件输入流
//         FileInputStream fis = new FileInputStream(path + File.separator + fileName);
//         //创建输出流
//         OutputStream fos = response.getOutputStream();
//         //ServletOutputStream fos = response.getOutputStream();
//         
//         //设置缓存区
//         ByteBuffer buffer = ByteBuffer.allocate(1024);
//         //输入通道
//         FileChannel readChannel = fis.getChannel();
//         //输出通道
//         FileChannel writeChannel = ((FileOutputStream) fos).getChannel();
//         while(true){
//             buffer.clear();
//             int len = readChannel.read(buffer);//读入数据
//             if(len < 0){
//                 break;//传输结束
//             }
//             buffer.flip();
//             writeChannel.write(buffer);//写入数据
//         }
//         //关闭输入流
//         fis.close();
//         //关闭输出流
//         fos.close();
         
         //--------------------------------------------------------------------------------------------------
         
         
         
         
         
	}

	
	
	
	
	//通过文件名和存储上传文件根目录找出要下载的文件的所在路径
    public String findFileSavePathByFileName(String fileName,String fileSaveRootPath){
        int hashcode = fileName.hashCode();
        int dir1 = hashcode&0xf;
        int dir2 = (hashcode&0xf0)>>4;
        String dir = fileSaveRootPath + "\\" + dir1 + "\\" + dir2;
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }
}
