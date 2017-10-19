package com.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.SpringEvent.event.RegisterEvent;
import com.entity.Admin;

@Component
public class RegisterService {

	private static Logger logger = LoggerFactory.getLogger(RegisterService.class);

	@Autowired
	private ApplicationContext applicationContext;

	public void publishRegisterEvent(Admin admin) {
		applicationContext.publishEvent(new RegisterEvent(admin));
	}
	
	 
}
