package com.yoxi.pfhudongtui.utils;

import java.io.File;
import java.io.InputStream;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSUtils {
	
	public static String CONF_FILENAME = FileUtils.getClassPath(FastDFSUtils.class)+File.separatorChar+"fdfs_client.conf";

	/**
	 * 根据文件路径地址上传文件
	 * @param conf_filename(配置文件地址：src\\com\\lectek\\fdfs_client.conf)
	 * @param uploadFileName(上传文件地址：D:\\social\\11.jpg)
	 * @return
	 */
	public static String uploadFile(final String uploadFileName){
		//返回保存文件ID
		String fileId = "";
		//获取文件类型
		String fileExtName = "";
		if (uploadFileName.contains(".")) {  
	        fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
	    } else {
	    	System.out.println("Fail to upload file, because the format of filename is illegal.");  
	        return fileId; 
	    }
		//设置元信息
		NameValuePair []metaList = new  NameValuePair[2];
	    metaList[0] = new NameValuePair("fileName", uploadFileName);  
	    metaList[1] = new NameValuePair("fileExtName", fileExtName);  
		
		try
		{
			//建立连接
			ClientGlobal.init(CONF_FILENAME);
			//System.out.println("charset=" + ClientGlobal.g_charset);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer,storageServer);
			//上传（group1/M00/00/00/wKgC3lDhQu-ARM9qAABIAZ6-5bk963.jpg）
			fileId = client.upload_file1(uploadFileName, fileExtName, metaList);
			System.out.println("upload success. file id is: " + fileId);
	        trackerServer.close();
		}catch(Exception io){
			io.printStackTrace();
		}
		return fileId;
	}
	
	public static String uploadFile(final InputStream in,final String uploadFileName){
		//返回保存文件ID
		String fileId = "";
		String fileExtName = "";
		if (uploadFileName.contains(".")) {  
	        fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
	    } else {
	    	System.out.println("Fail to upload file, because the format of filename is illegal.");  
	        return fileId; 
	    }
		//设置元信息
		NameValuePair []metaList = new  NameValuePair[2];
	    metaList[0] = new NameValuePair("fileName", uploadFileName);  
	    metaList[1] = new NameValuePair("fileExtName", fileExtName);  
		
		try
		{
			//建立连接
			ClientGlobal.init(CONF_FILENAME);
			//System.out.println("charset=" + ClientGlobal.g_charset);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer,storageServer);
			//上传（group1/M00/00/00/wKgC3lDhQu-ARM9qAABIAZ6-5bk963.jpg）
			fileId = client.upload_file1(FileUtils.InputStreamToByte(in), fileExtName, metaList);
			System.out.println("upload success. file id is: " + fileId);
	        trackerServer.close();
		}catch(Exception io){
			io.printStackTrace();
		}
		return fileId;
	}	
	
	public static void downloadFile(String uploadFileName){
		
	}
	
	public static void deleteFile(String fileId){
		try
		{
			int errno;
			//建立连接
			ClientGlobal.init(CONF_FILENAME);
			//System.out.println("charset=" + ClientGlobal.g_charset);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer,storageServer);
			if ((errno=client.delete_file1(fileId)) == 0)
  			{
  				System.err.println("Delete file success");
  			}
  			else
  			{
  				System.err.println("Delete file fail, error no: " + errno);
  			}
	        trackerServer.close();
		}catch(Exception io){
			io.printStackTrace();
		}
	}
	
	public static void main(String[] args)throws Exception {
		String local_file_name ="D:\\social\\rjy.jpg";
		String fileId = FastDFSUtils.uploadFile(local_file_name);
		System.out.println("fileId:"+fileId);
		//FastDFSUtils.deleteFile(fileId);
		//InputStream in = new FileInputStream(local_file_name);
		//String fileId1 =FastDFSUtils.uploadFile(in, "11.jpg");
		//System.out.println("fileId1:"+fileId1);
		//FastDFSUtils.deleteFile("group1/M00/00/01/wKgC3lFU8jqAbS2QAABIAZ6-5bk775.jpg");
		File file = new File(local_file_name);
		System.out.println(file.length());
		System.out.println(Long.valueOf(file.length()).intValue());
		System.out.println(file.getName());
		System.out.println(local_file_name.substring(local_file_name.lastIndexOf(".") + 1));
		System.out.println(file.exists());
	}
	
}
