package com.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesConfig {

	public static void main(String[] args) throws Exception {
		
		
		
		
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
	
	
}
