package org.qxp.ctrl.util;

import java.io.RandomAccessFile;
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
}
