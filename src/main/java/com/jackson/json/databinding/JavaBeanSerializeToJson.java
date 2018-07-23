package com.jackson.json.databinding;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaBeanSerializeToJson {

	public static String convert() throws Exception {
		String json=null;
		// 使用ObjectMapper来转化对象为Json
		ObjectMapper mapper = new ObjectMapper();
		// 添加功能，让时间格式更具有可读性
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(dateFormat);

		Country country = new Country("China");
		country.setBirthDate(dateFormat.parse("1949-10-01"));
		country.setLakes(new String[] { "Qinghai Lake", "Poyang Lake",
				"Dongting Lake", "Taihu Lake" });

		List<String> nation = new ArrayList<String>();
		nation.add("Han");
		nation.add("Meng");
		nation.add("Hui");
		nation.add("WeiWuEr");
		nation.add("Zang");
		country.setNation(nation);

		Province province = new Province();
		province.name = "Shanxi";
		province.population = 37751200;
		Province province2 = new Province();
		province2.name = "ZheJiang";
		province2.population = 55080000;
		List<Province> provinces = new ArrayList<Province>();
		provinces.add(province);
		provinces.add(province2);
		country.setProvinces(provinces);
		
		country.addTraffic("Train(KM)", 112000);
		country.addTraffic("HighWay(KM)", 4240000);
		// 为了使JSON视觉上的可读性，增加一行如下代码，注意，在生产中不需要这样，因为这样会增大Json的内容
//		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		// 配置mapper忽略空属性
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		// 默认情况，Jackson使用Java属性字段名称作为 Json的属性名称,也可以使用Jackson annotations(注解)改变Json属性名称
		Writer writer=new StringWriter();
		mapper.writeValue(writer, country);
		json=writer.toString();
		writer.close();
		return json;
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(convert());
//		System.out.println(jsonToObject2(null, Country.class));
		Map<String,String> map=new HashMap<>();
		map.put("url","http://www.baidu.com");
		map.put("noticeType","1");
		System.out.println(objectTojson(map));
	}

	
	
	public static <T> T jsonToObject(String json,Class<T> c){
		/*
			json="{\"country_id\":\"China\""
				+ ",\"birthDate\":\"1949-10-01\""
				+ ",\"nation\":[\"Han\",\"Meng\",\"Hui\",\"WeiWuEr\",\"Zang\"],"
				+ "\"lakes\":[\"Qinghai Lake\",\"Poyang Lake\",\"Dongting Lake\",\"Taihu Lake\"],"
				+ "\"provinces\":[{\"name\":\"Shanxi\",\"population\":37751200},{\"name\":\"ZheJiang\",\"population\":55080000}],"
				+ "\"traffic\":{\"HighWay(KM)\":4240000,\"Train(KM)\":112000}}";
			*/
		T o=null;
		if(StringUtils.isEmpty(json)){
			return o;
		} 
		try {
			ObjectMapper mapper=new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			o=mapper.readValue(json, c); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
		
	}
	
	public static <T> T jsonToObject2(String json,Class<T> c){
		json="{\"country_id\":\"China\""
				+ ",\"birthDate\":\"1949-10-01\""
				+ ",\"nation\":[\"Han\",\"Meng\",\"Hui\",\"WeiWuEr\",\"Zang\"],"
				+ "\"lakes\":[\"Qinghai Lake\",\"Poyang Lake\",\"Dongting Lake\",\"Taihu Lake\"],"
				+ "\"provinces\":[{\"name\":\"Shanxi\",\"population\":37751200},{\"name\":\"ZheJiang\",\"population\":55080000}],"
				+ "\"traffic\":{\"HighWay(KM)\":4240000,\"Train(KM)\":112000}}";

		T o=null;
		if(StringUtils.isEmpty(json)){
			return o;
		} 
		try {
			ObjectMapper mapper=new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			o=mapper.readValue(json, new TypeReference<T>(){});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
		
	}
	
	
	public static String objectTojson(Object o){
		if(o==null){
			return null;
		}
		ObjectMapper mapper=new ObjectMapper();
		Writer writer=new StringWriter();
		String json=null;
		try {
			mapper.writeValue(writer, o);
			json=writer.toString();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return json;
	}
	
}