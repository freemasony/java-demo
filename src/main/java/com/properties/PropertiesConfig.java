package com.properties;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PropertiesConfig {

	public static void getProperties() throws Exception{


		/*-------------1------------------*/
		Locale locale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle("resource.sysconfig");// ����properties��չ��
		String value = bundle.getString("name");
		System.out.println("1:name:"+value);
		/*-------------2------------------*/
		InputStream in =new BufferedInputStream(new FileInputStream("D:\\workspaces\\ZJ\\src\\resource\\sysconfig.properties"));
		ResourceBundle rb=new PropertyResourceBundle(in);
		value=rb.getString("name");
		System.out.println("2:name:"+value);

		/*-------------3------------------*/
		in=PropertiesConfig.class.getResourceAsStream("/resource/sysconfig.properties");
		Properties p=new Properties();
		p.load(in);
		value=p.getProperty("name");
		System.out.println("3:name:"+value);
		/*-------------4------------------*/
		in=PropertiesConfig.class.getClassLoader().getResourceAsStream("resource/sysconfig.properties");
		p=new Properties();
		p.load(in);
		value=p.getProperty("name");
		System.out.println("4:name:"+value);

	     /*-------------5------------------*/
		PropertiesConfiguration config=new PropertiesConfiguration("D:\\workspaces\\ZJ\\src\\resource\\sysconfig.properties");
		value=  config.getString("name");
		System.out.println("5:name:"+value);


	}

	public static void main(String[] args) throws Exception {
			Properties p=new Properties();
			String path=PropertiesConfig.class.getClassLoader().getResource("").getPath()+"sysconfig.properties";
			InputStream in=new FileInputStream(path);
			p=new Properties();
			p.load(in);
			String value=p.getProperty("name");
			String age=p.getProperty("age");
			System.out.println("******befor:"+value);
			System.out.println("******befor:"+age);

			OutputStream out=new FileOutputStream(path);
			p.put("name","aaa");
			p.store(out,"test");

			out.close();

			in=PropertiesConfig.class.getResourceAsStream("/sysconfig.properties");
			p=new Properties();
			p.load(in);
			value=p.getProperty("name");
			age=p.getProperty("age");
			System.out.println("******after:"+value);
			System.out.println("******after:"+age);
			System.out.println("******name:"+p.toString());
			System.out.println("******int:"+Integer.parseInt(""));



	}
	
	
}
