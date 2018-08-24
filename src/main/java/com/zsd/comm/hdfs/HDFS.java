package com.zsd.comm.hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
 




import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HDFS(分布式文件存储系统操作类)
 *
 */
public class HDFS {
	
	private static Logger logger = LoggerFactory.getLogger(HDFS.class);
	
	/**
	 * 文件系统类
	 */
	private FileSystem fileSystem;
	 
	/**
	 * 无参数构造方法
	 */
	public HDFS() {
	   init();
	}
	 
	/**
	 * 初始化
	 */
	private void init() {
	   fileSystem = HDFSServer.getFileSystem();
	}
	 
	/**
	 * 获取HDFS指定目录下文件状态列表
	 * 
	 * @param path 指定目录路径
	 * @return fileStatusList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public FileStatus[] getFileStatus(Path path) throws FileNotFoundException,
	IOException {
	   FileStatus[] fileStatusList = fileSystem.listStatus(path);
	   return fileStatusList;
	}
	 
	/**
	 * 获取指定目录列表路径
	 * 
	 * @param dirPath
	 */
	public List<String> dir(String dirPath) throws IOException {
	  List<String> fileList = null;
	  Path path = new Path(dirPath);
	  if (fileSystem.exists(path)) {
	    fileList = new ArrayList<String>();
	    FileStatus[] list = this.getFileStatus(path);
	  for (FileStatus fileStatus : list) {
	    fileList.add(fileStatus.getPath().toString());
	  }
	  } else {
	     System.out.println("目录不存在");
	  }
	  return fileList;
	}
	 
	/**
	 * 获取文件
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public InputStream getFile(String filePath) throws IOException {
	Path path = new Path(filePath);
	return fileSystem.open(path);
	}
	 
	/**
	 * 更改HDSF文件名称
	 * 
	 * @param src 原文件名称
	 * @param dst 改后文件名称
	 * @return boolean:是否更名字成功
	 * @throws IOException
	 */
	public boolean rename(String src, String dst) throws IOException {
	   Path srcPath = new Path(src);
	   if (fileSystem.exists(srcPath)) {
	     Path dstPath = new Path(dst);
	     return fileSystem.rename(srcPath, dstPath);
	   }
	   System.out.println("原文件不存在");
	   return false;
	}
	 
	/**
	 * 创建HDFS目录
	 * 
	 * @param dir
	 */
	public boolean createDir(String dir) throws IOException {
	   Path path = new Path(dir);
	   if (fileSystem.exists(path)) {
	     System.out.println("此目录已经存在不需要再创建");
	   return true;
	   }
	   return fileSystem.mkdirs(path);
	}
	 
	/**
	 * 上传本地文件到HDFS（注意是服务器本地硬盘，非客户端硬盘)）
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public void uploadLocalFile(String localFileSrc, String HDFSFileDst)
	           throws IOException {
	  Path src = new Path(localFileSrc);
	  Path dst = new Path(HDFSFileDst);
	  fileSystem.copyFromLocalFile(src, dst);
	}

	/**
	 * 批量上传本地文件到HDFS
	 * @param localFileSrcs 本地文件列表
	 * @param HDFSFileDst
	 * @throws IOException
	 */
	public void uploadLocalFile(String[] localFileSrcs,String HDFSFileDst) throws IOException{
	    Path dstPath=new Path(HDFSFileDst);
	    Path[] paths=new Path[localFileSrcs.length];
	    for (int i=0;i<localFileSrcs.length;i++) {
	    	paths[i]=new Path(localFileSrcs[i]);
		}
	    fileSystem.copyFromLocalFile(false, false, paths,dstPath);
	}

	/**
	 * 从HDFS下载文件到本地(注意是服务器本地硬盘，非浏览器客户端硬盘)
	 * 
	 * @param HDFSFilePath
	 * @param localFilePath
	 * @throws IOException
	 */
	public void downFileToLocal(String HDFSFilePath, String localFilePath)
	throws IOException {
	  Path dstPath = new Path(HDFSFilePath);
	  FSDataInputStream in = fileSystem.open(dstPath);
	  OutputStream out = new FileOutputStream(new File(localFilePath));
	  IOUtils.copy(in, out);
	}
	 
	/**
	 * HDFS文件是否存在
	 * 
	 * @param filePath
	 * @return boolean:是否存在
	 * @throws IOException
	 */
	public boolean exists(String filePath) throws IOException {
	  Path path = new Path(filePath);
	  return fileSystem.exists(path);
	}
	 
	/**
	 * 根据路径删除文件或文件夹
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public boolean deleteFile(String filePath) throws IOException {
	  if (this.exists(filePath)) {
	    Path path = new Path(filePath);
	    fileSystem.delete(path, true);
	    return true;
	  }
	  System.out.println("文件不存在");
	  return false;
	}
	 
	/**
	 * 剪切本地文件到HDFS(注意为服务器本地文件);
	 * 
	 * @param localSrc 本地路径
	 * @param HDFSDst 分布式存储路径
	 * @throws IOException
	 */
	public void moveFromLocalFile(String localSrc, String HDFSDst) throws IOException {
	   Path srcPath = new Path(localSrc);
	   Path dstPath = new Path(HDFSDst);
	   fileSystem.moveFromLocalFile(srcPath, dstPath);
	}
	/**
	 * HDFS文件之间的复制
	 * @param src 源文件路径
	 * @param dst 要复制后复制文件的路径
	 * @throws IOException
	 */
	public void copyHDFSFile(String src,String dst) throws IOException{
	   Path srcPath=new Path(src);
	   Path dstPath=new Path(dst);
	   InputStream in=fileSystem.open(srcPath);
	   OutputStream out=fileSystem.create(dstPath);
	   IOUtils.copy(in, out);
	}
	/**
	 * HDFS中移动文件
	 * @param src 源文件路径
	 * @param dst 要移动后的路径
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public void moveHDFSFile(String src,String dst) throws IOException{
	   Path srcPath=new Path(src);
	   Path dstPath=new Path(dst);
	   InputStream in=fileSystem.open(srcPath);
	   OutputStream out=fileSystem.create(dstPath);
	   IOUtils.copy(in, out);
	   fileSystem.delete(srcPath,true);
	}
	/**
	 * 剪切HDFS文件到本地
	 * @param HDFSSrc
	 * @param localDst
	 * @throws IOException
	 */
    public void moveToLocalFile(String HDFSSrc,String localDst) throws IOException{
	    Path srcPath = new Path(HDFSSrc);
	    Path dstPath = new Path(localDst);
	    fileSystem.moveToLocalFile(srcPath, dstPath);
    }
	/**
	 * HDFS创建文件
	 * 
	 * @param in 输入流
	 * @param dst 分布式存储路径
	 * @throws IOException
	 */
	public boolean create(InputStream in, String dst) throws IOException {
	   try {
		   Path dstPath = new Path(dst);
		   FSDataOutputStream out = fileSystem.create(dstPath, new Progressable() {
		        @Override
		        public void progress() {
		         logger.debug("write success.");
		        }
		   });
		   IOUtils.copy(in, out);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	   return true;
	}
	 
	/**
	 * 在HDFS创建文件
	 * 
	 * @param file
	 * @param dst 分布式存储路径
	 * @throws IOException
	 */
	public void create(File file, String dst) throws IOException {
	   InputStream in = new FileInputStream(file);
	   this.create(in, dst);
	}
	 
	/**
	 * 在HDFS创建文件
	 * 
	 * @param src 本地文件路径
	 * @param dst 分布式存储路径
	 * @throws IOException
	 */
	public void create(String src, String dst) throws IOException {
	   File file = new File(src);
	   this.create(file, dst);
	}
	
	/**
	 * 获取文件输出流
	 * @param dst 分布式存储路径
	 * @return
	 * @throws IOException
	 */
	public InputStream read(String dst) throws IOException {
		Path dstPath = new Path(dst);
		InputStream in = fileSystem.open(dstPath);
		return in;
	}
	 
	/**
	 * 获取FileSystem对象
	 * 
	 * @return
	 */
	public FileSystem getFileSystem() {
	   return fileSystem;
	}
	 
	/**
	 * 关闭HDFS
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
	   fileSystem.close();
	}
	 
	public boolean isDir(String src) throws IOException {
	   Path path = new Path(src);
	   return fileSystem.isDirectory(path);
	}
	
	public static void main(String[] args) throws IOException {
		HDFS hdfs = new HDFS();
		// hdfs.uploadLocalFile("D:/picture", "/test"); //
		System.out.println(hdfs.dir("/"));
//		hdfs.createDir("/dir");
//		hdfs.create("F:/logs/to-hdfs-log.txt","/test");
//		System.out.println(hdfs.dir("/test/picture/mypicture"));
		//hdfs.uploadLocalFile(new String[]{"E:/input","E:/output"}, "/");
		//hdfs.rename("/input", "/debug_in");
//		System.out.println(hdfs.dir("/debug_out"));
		//hdfs.deleteFile("/output");
		// hdfs.moveFromLocalFile("E:/test.jpg", "/test/picture/POP海报2590.jpg");
		/*
		 * System.out.println(hdfs.dir("/test"));
		 * System.out.println(hdfs.exists("/test/picture/mypicture")); //
		 * hdfs.delete("/test/picture/mypicture");
		 * System.out.println(hdfs.dir("/test/picture"));
		 */
//		hdfs.downFileToLocal("/usr/hadoop/test/to-hdfs-log.txt", "E:/logfile/aa.txt");
//		hdfs.uploadLocalFile(new String[]{"E:/text","E:/text2"}, "/test");
//		hdfs.deleteFile("/dir");
		hdfs.close();
	}

}
