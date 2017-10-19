package com.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * @author a
 *
 */
public class IOTest {

	private static String separator=File.separator;
	
	public static void main(String[] args) {

		//文件夹名
		String dirname="E:"+separator+"hello";
		//文件名
		String filenames=dirname+separator+"hello_";
		//指定目录
		String tdDirname="E:"+separator+"hello";
		
		String filename=dirname+separator+"hello_1.txt";
		
		String filename2=dirname+separator+"hello_2.txt";
		
		String zipfilename=dirname+separator+"hello.zip";
		
		String zipfilename1="E:"+separator+"hello.zip";
		
		CreateFileDir(dirname);
		for (int i = 1; i <=5; i++) {
			CreateNewFile(filenames+i+".txt");
		}
		
		
		

		TeDingDirFileList(tdDirname);
		
		TeDingDirFileListAllPath(tdDirname);
		
		isDir(tdDirname);
		
		isDir(filenames);
		
//		deletFile(filename);
		
		searchTdDirAllFile(new File(dirname));
		
//		writeFile(filename);
		
//		writeFileAppend(filename);
		
		readFile(filename);
		
		readFileIsEnd(filename);
		
		writeFileStr(filename2);
		
		readFileStr(filename2);
		
		copyFile(filename,filename2);
		
		bufferRead(filename2);
		
//		zipFile(filename2,zipfilename);
		
		zipFiles(tdDirname,zipfilename1);
	}

	
	private static void CreateNewFile(String filename){
		File file=new File(filename);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 
	
	private static void deletFile(String filename){
		File file=new File(filename);
		if(file.exists()){
			file.delete();
			System.out.println("文件已删除");
		}else{
			System.out.println("文件不存在!");
		}
	}
	
	private static void CreateFileDir(String dirname){
		File file=new File(dirname);
		file.mkdir();
	}
	
	
	/**
	 * 列出指定目录的全部文件（包括隐藏文件）
	 * @param tdDirname
	 */
	private static void TeDingDirFileList(String tdDirname){
		File file=new File(tdDirname);
		String[] str=file.list();
		for (int i = 0; i < str.length; i++) {
			System.out.println("文件名："+str[i]);
		}
	}
	
	private static void TeDingDirFileListAllPath(String tdDirname){
		File file=new File(tdDirname);
		File[] str=file.listFiles();
		for (int i = 0; i < str.length; i++) {
			System.out.println("文件名全路径："+str[i]);
		}
	}
	
	private static void isDir(String name){
		File file=new File(name);
		if(file.isDirectory()){
			System.out.println(name+"是目录");
		}else{
			System.out.println(name+"不是目录");
		}
	}
	
	
	/**
	 * 搜索指定目录的全部内容
	 * @param tddirname
	 */
	private static void searchTdDirAllFile(File tddirname){
		if(tddirname!=null){
			if(tddirname.isDirectory()){
				File[] filelist=tddirname.listFiles();
				if(filelist!=null){
					for (int i = 0; i < filelist.length; i++) {
						searchTdDirAllFile(filelist[i]);
					}
				}
			}else{
				System.out.println("找到的文件为："+tddirname);
			}
		}
	}
	
	
	private static void writeFile(String filename){
		File f=new File(filename);
		try {
			OutputStream out=new FileOutputStream(f);
			String str="你好！";
			byte[] bytes=str.getBytes();
			out.write(bytes);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 向文件中追加内容
	 * @param filename
	 */
	private static void writeFileAppend(String filename){
		File f=new File(filename);
		try {
			OutputStream out=new FileOutputStream(f,true);
			String str="周建";
			byte[] bytes=str.getBytes();
			out.write(bytes);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void readFile(String filename){
		File f=new File(filename);
		try {
			InputStream in=new FileInputStream(f);
			byte[] b=new byte[(int) f.length()];
			  in.read(b);
			in.close();
			System.out.println("读入长度为："+f.length()); 
	        System.out.println(new String(b));			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void readFileIsEnd(String filename){
		File f=new File(filename);
		try {
			InputStream in=new FileInputStream(f);
			byte[] b=new byte[(int) f.length()];
			int count=0;
			int temp=0;
			while((temp=in.read())!=(-1)){
				b[count++]=(byte) temp;
			}
			in.close();
			System.out.println(new String(b));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void writeFileStr(String filename){
		File f=new File(filename);
		try {
			Writer out=new FileWriter(f);
//			Writer out=new FileWriter(f,true);//向文件中追加内容时使用
			String str="你好啊！";
			out.write(str);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void readFileStr(String filename){
		File f=new File(filename);
		char[] ch=new char[100];
		try {
			Reader read =new FileReader(f);
			int temp=0;
			int count=0;
			while ((temp=read.read())!=-1) {
				ch[count++]=(char) temp;
			}
			read.close();
			System.out.println("内容为:"+new String(ch,0,count));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	
	private static void copyFile(String oldname,String newname){
		File f1=new File(oldname);
		File f2=new File(newname);
		if(!f1.exists()){
			System.out.println("被复制的文件不存在！");
			System.exit(1);
		}
		
		try {
			InputStream in=new FileInputStream(f1);
			OutputStream out =new FileOutputStream(f2);
			if(in!=null&&out!=null){
				int temp=0;
				while ((temp=in.read())!=-1) {
					out.write(temp);
				}
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void bufferRead(String filename){
		try {
			BufferedReader buf=new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String str=null;
			while((str=buf.readLine())!=null) {
				System.out.println(str);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void zipFile(String filename,String zipname){
		File f=new File(filename);
		File zipfile=new File(zipname);
		try {
			InputStream in=new FileInputStream(f);
			ZipOutputStream zipout=new ZipOutputStream(new FileOutputStream(zipfile));
			zipout.putNextEntry(new ZipEntry(f.getName()));
			zipout.setComment("hello");
			int temp=0;
			while ((temp=in.read())!=-1) {
				zipout.write(temp);
			}
			in.close();
			zipout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void zipFiles(String dirname,String zipname){
		 File file = new File(dirname);
	        File zipFile = new File(zipname);
	        InputStream input = null;
			try {
				ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
				zipOut.setComment("hello");
				if(file.isDirectory()){
					File[] fs=file.listFiles();
					for (int i = 0; i < fs.length; i++) {
						input=new FileInputStream(fs[i]);
						zipOut.putNextEntry(new ZipEntry(file.getName()+File.separator+fs[i].getName()));
						int temp=0;
						while ((temp=input.read())!=-1) {
							zipOut.write(temp);
						}
						input.close();
					}
				}
				zipOut.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	}
	
	
	
}
