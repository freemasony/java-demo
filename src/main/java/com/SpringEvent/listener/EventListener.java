package com.SpringEvent.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.Service.AccountService;
import com.SpringEvent.event.EmailEvent;
import com.SpringEvent.event.RegisterEvent;
import com.entity.Admin;
import com.jackson.json.databinding.JavaBeanSerializeToJson;
import com.utils.SpringContextUtils;
 

@Component
public class EventListener implements ApplicationListener<ApplicationEvent> {

	@Async
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if(event instanceof RegisterEvent){
			String jsonResult=JavaBeanSerializeToJson.objectTojson(event.getSource());
			System.out.println("注册事件监听：" + jsonResult);  
		}
		if(event instanceof EmailEvent){
			String jsonResult=JavaBeanSerializeToJson.objectTojson(event.getSource());
			System.out.println("邮件事件监听：" + jsonResult);  
			Admin admin=JavaBeanSerializeToJson.jsonToObject(jsonResult, Admin.class);
			AccountService accountService=(AccountService) SpringContextUtils.getContext().getBean("accountService");
			Admin adminobj=accountService.getAdminUserByLoginName(admin.getLoginName());
			System.out.println(adminobj.getName());
		}
	}

}
