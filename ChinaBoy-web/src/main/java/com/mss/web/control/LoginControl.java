package com.mss.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControl extends BaseControl{

	@RequestMapping(value="/toLogin")
	public String addDemo(){
		return "login";
	}
}
