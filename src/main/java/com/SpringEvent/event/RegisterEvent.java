package com.SpringEvent.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;

import com.entity.Admin;

public class RegisterEvent extends ApplicationEvent {

	private Log logger = LogFactory.getLog(RegisterEvent.class);

	private static final long serialVersionUID = 1L;

	public RegisterEvent(Admin admin) {
		super(admin);
		printInfo();
	}

	private void printInfo() {
		logger.info("register event....");
	}

}
