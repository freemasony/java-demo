package com.filepath;

public class FilePathUtils {

	public static void main(String[] args) {
		/*-----------------1-----------------*/
		String path = null;

		System.out.println("1:path=" + path);
		/*-----------------2-----------------*/
		path = System.getProperty("user.dir");
		System.out.println("2:path=" + path);

		System.out
				.println(FilePathUtils.class.getClassLoader().getResource(""));
		System.out.println(ClassLoader.getSystemResource(""));
		System.out.println(FilePathUtils.class.getResource(""));
		System.out.println(FilePathUtils.class.getResource("/"));// Class文件所在路径 
		 
	}
}
