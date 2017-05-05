package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.entity.Demo;
import com.test.mapper.DemoMapper;

@Controller
public class TestController {

	@Autowired
	private DemoMapper demoMapper;
	
	@RequestMapping("/test")
	public String test(){
		Demo demo = new Demo();
		demo.setId("1");
		demo.setName("sky");
		demoMapper.insert(demo);
		return "test";
	}
}
