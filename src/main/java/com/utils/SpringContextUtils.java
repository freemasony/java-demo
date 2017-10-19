package com.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class SpringContextUtils implements ApplicationContextAware {

	public static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		// TODO Auto-generated method stub
			this.context=context;
	}
	
	public static <T> T getBean(String beanId,Class<T> clazz){
		return context.getBean(beanId, clazz);
	}
	
	public static ApplicationContext getContext(){
		return context;
	}
	
}
