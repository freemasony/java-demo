package com.Annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import com.Annotations.FruitName;

public class FruitInfoUtil {

	public static void getFruitInfo(Class<?> clazz) {
		String strFruitName = " 水果名称：";
		String strFruitColor = " 水果颜色：";
		String strFruitProvicer = "供应商信息：";
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field:fields) {
			if(field.isAnnotationPresent(FruitName.class)){
				FruitName fruitName=field.getAnnotation(FruitName.class);
				strFruitName=strFruitName+fruitName.name();
				System.out.println(strFruitName);
			} else if (field.isAnnotationPresent(FruitColor.class)){
				 FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
                 strFruitColor=strFruitColor+fruitColor.fruitColor().toString();
                 System.out.println(strFruitColor);
			}else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvicer=" 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FruitInfoUtil.getFruitInfo(Apple.class);
		System.out.println("======");
		Field[] fields = Apple.class.getDeclaredFields();
		for (Field field:fields) {
		for(Annotation annotation:field.getAnnotations()){
			System.out.println(annotation);
		}
	}
		}

}