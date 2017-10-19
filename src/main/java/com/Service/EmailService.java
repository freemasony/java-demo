package com.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.SpringEvent.event.EmailEvent;
import com.entity.Admin;

@Component
public class EmailService {

	private static Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	public void pulishEmailEvent(Admin admin){
		applicationContext.publishEvent(new EmailEvent(admin));
	}
}
