package com.controller;

public class Test {

//	private static String fontpath=Test.class.getClass().getResource("static/font/simsun.ttc").getPath();
	
	public static void main(String[] args) {
		  
		try {
//			html2PDF();
			String linkUr=" http://dwz.cn/banner0909?ddd=1&&fff=12";
			linkUr=linkUr.substring(linkUr.indexOf("?")+1,linkUr.length());
			System.out.println(linkUr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	  
}
