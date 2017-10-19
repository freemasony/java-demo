package com.SpringEvent.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;

import com.entity.Admin;

public class EmailEvent extends ApplicationEvent {

	private Log logger = LogFactory.getLog(EmailEvent.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 3797299551736741225L;

	
	public EmailEvent(Admin admin) {
		super(admin);
		// TODO Auto-generated constructor stub
		printInfo();
	}

	private void printInfo() {
		logger.info("send email event....");
	}
	
}
