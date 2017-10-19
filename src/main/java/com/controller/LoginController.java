package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;



@Controller
@Scope("prototype")
@RequestMapping(value="/login") 
public class LoginController {

	 
	@RequestMapping(method=RequestMethod.GET)
	public String login(){
		/*Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated())
		{
			return "redirect:/mytest/test";
		}*/
		return "redirect:/mytest/test";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "account/login";
	}
	
}
