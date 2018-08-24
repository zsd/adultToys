package com.zsd.comm.hdfs;

import java.io.IOException;
import java.net.URI;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;


/**
 * HDFS服务器
 *
 */
public class HDFSServer {
	
	private static Configuration configuration;
	private static FileSystem fileSystem;
	private static final String HDFS_URL = "hdfs://10.0.8.83:8020";
//	private static final String HDFS_URL = "hdfs://10.0.8.117:9000";
//	private static final String HDFS_URL = new PropertiesLoader("META-INF/config/attach.properties").getProperty("attach.hdfs.url");
	 
	/**
	 * HDFS服务器读取初始化
	 */
	private static void init() {
		try {
//			configuration= new JobConf(HDFSServer.class);
			configuration= new Configuration();
			fileSystem = FileSystem.get(URI.create(HDFS_URL), configuration);
			System.out.println("读取服务器成功");
		} catch (IOException e) {
			System.out.println("读取服务器失败");
			e.printStackTrace();
		}
	}
	public static FileSystem getFileSystem(){
		if(fileSystem==null){
			init();
		}
		return fileSystem;
	}

}
