package org.qxp.ctrl.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class IOUtil {

	public static String ReadFile(String filename){
		try{
			RandomAccessFile aFile = new RandomAccessFile(filename, "r");
			FileChannel inChannel = aFile.getChannel();
			long fileSize = inChannel.size();
			ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
			inChannel.read(buffer);
			buffer.rewind();
			buffer.flip();

			byte[] bt=buffer.array();
			String result = new String(bt,0,(int)fileSize);
			inChannel.close();
			aFile.close();
			return result;
		 }catch (Exception exc){
			 exc.printStackTrace();
			 System.exit(1);
			 return null;
		 } 
	}
	
	public static String decode(ByteBuffer buffer) {
		Charset charset = null;
		CharsetDecoder decoder = null;
		CharBuffer charBuffer = null;
		try {
			charset = Charset.forName("UTF-8");
			decoder = charset.newDecoder();
			charBuffer = decoder.decode(buffer);
			System.out.println( " charBuffer= "   +  charBuffer);
			System.out.println(charBuffer.toString());
			return charBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	} 
	
	/** 
     * 下载远程文件并保存到本地  
     * @param remoteFilePath 远程文件路径  
     * @param localFilePath 本地文件路径 
     */
    public static void downloadRemoteFile(String remoteFilePath, String localFilePath){
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try{
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1){
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                bis.close();
                bos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    /** 
     * 移动本地文件
     * @param remoteFilePath 远程文件路径  
     * @param localFilePath 本地文件路径 
     */
    public static void downloadLocalFile(String remoteFilePath, String localFilePath){
    	File remoteFile = new File(remoteFilePath);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try{
            bis = new BufferedInputStream(new FileInputStream(remoteFile));
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1){
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                bis.close();
                bos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
