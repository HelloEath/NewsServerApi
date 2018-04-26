package com.glut.news.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.glut.news.service.INetBugsService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Resource
	INetBugsService iNetBugsService;
	@RequestMapping("/testbugsvideo")
	public void testBugsVideo(){
		
		iNetBugsService.bugsTouTiaoWeb();
	}

}
