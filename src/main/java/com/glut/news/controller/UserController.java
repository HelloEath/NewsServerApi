package com.glut.news.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.glut.news.service.IUserService;
import com.glut.news.vo.UserInfo;
import com.google.gson.Gson;

@Controller()
@RequestMapping("/user")
public class UserController {

	@Resource
	IUserService iUserService;
	Map<String, String> map;

	/*
	 * 用户登录 x=1:用户存在且密码正确 x=0:用户存在，密码有误 x=2:用户不存在
	 */
	@RequestMapping("/login")
	public @ResponseBody String login(@RequestBody String requestbody, UserInfo u, HttpSession hSession,
			HttpServletRequest hRequest) {
		Gson gson = new Gson();
		UserInfo userInfo2 = gson.fromJson(requestbody, UserInfo.class);

		UserInfo x = iUserService.loginService(userInfo2);
		Map<String, Object> map = new HashMap<String, Object>();

		if (x != null) {
			if (x.getUser_Password().equals(userInfo2.getUser_Password())) {

				hSession.setAttribute("User", x);
				map.put("stus", "1");
				map.put("user", x);

			} else {
				map.put("stus", "0");
			}

		} else {
			map.put("stus", "2");
		}
		return gson.toJson(map);

	}

	/*
	 * 注册用户 返回值：1：用户注册成功 0：该用户已注册
	 */
	@RequestMapping("/register")
	public @ResponseBody String register(@RequestBody String requestbody, UserInfo u, HttpSession hSession) {
		Gson gson = new Gson();
		UserInfo userInfo2 = gson.fromJson(requestbody, UserInfo.class);
		int x = iUserService.registerService(userInfo2, null);
		userInfo2.setUser_NickName(null);
		UserInfo userInfo = iUserService.selectUserService(userInfo2);
		Map<String, Object> map = new HashMap<String, Object>();

		if (x == 1) {
			map.put("stus", "1");
			map.put("user", userInfo);
			hSession.setAttribute("User", userInfo);

		} else {
			map.put("stus", "0");
			map.put("user", userInfo);
		}
		return gson.toJson(map);
	}

	/* 用户更新资料 */
	@RequestMapping("/updateUser")
	public @ResponseBody String updateUser(@RequestBody String requestbody, HttpSession hSession) {

		Gson gson = new Gson();
		UserInfo userInfo2 = gson.fromJson(requestbody, UserInfo.class);
		UserInfo userInfo3 = (UserInfo) hSession.getAttribute("User");
		/*
		 * if (userInfo2.getUser_Interest()!=null) {
		 * userInfo3.setUser_Interest(userInfo2.getUser_Interest()); } if
		 * (userInfo2.getUser_NickName()!=null) {
		 * userInfo3.setUser_NickName(userInfo2.getUser_NickName()); } if
		 * (userInfo2.getUser_Password()!=null) {
		 * userInfo3.setUser_Password(userInfo2.getUser_Password()); } if
		 * (userInfo2.getUser_PhoneNum()!=null) {
		 * userInfo3.setUser_PhoneNum(userInfo2.getUser_PhoneNum()); } if
		 * (userInfo2.getUser_Picture()!=null) {
		 * userInfo3.setUser_Picture(userInfo2.getUser_Picture()); } if
		 * (userInfo2.getUser_Email()!=null) {
		 * userInfo3.setUser_Email(userInfo2.getUser_Email()); }
		 * 
		 * if (userInfo2.getUser_Sex()!=null) {
		 * userInfo3.setUser_Sex(userInfo2.getUser_Sex()); } if
		 * (userInfo2.getUser_Birthday()!=null) {
		 * userInfo3.setUser_Birthday(userInfo2.getUser_Birthday()); } if
		 * (userInfo2.getUser_District()!=null) {
		 * userInfo3.setUser_District(userInfo2.getUser_District()); }
		 * 
		 * if (userInfo2.getUser_Describe()!=null) {
		 * userInfo3.setUser_Describe(userInfo2.getUser_Describe()); } if
		 * (userInfo2.getUser_Picture()!=null) {
		 * userInfo3.setUser_Picture(userInfo2.getUser_Picture()); }
		 */
		Map<String, Object> map = new HashMap<String, Object>();

		int x = iUserService.updateUserService(userInfo2);
		if (x == 1) {
			map.put("stus", "1");
			map.put("user", userInfo2);
			hSession.setAttribute("User", userInfo2);
		} else {
			map.put("stus", "0");

		}
		return gson.toJson(map);
	}

	@RequestMapping("/deleteUser")
	public int deleteUser(UserInfo u) {
		return 1;
	}

	@RequestMapping("/alterUserLogo")
	public @ResponseBody String laterUserLogo(@RequestParam("uploadFile") MultipartFile file,
			HttpServletRequest hRequest) {
		String path = "D:\\picture\\";
		System.out.print("path:" + path);
		String fileNameString = file.getOriginalFilename();
		System.out.println("fileName:" + fileNameString);
		File dir = new File(path, fileNameString);
		System.out.println("dir.exits:" + dir.exists());
		if (!dir.exists()) {
			dir.mkdir();
		}
		System.out.println("dir.exits:" + dir.exists());
		try {
			file.transferTo(dir);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ok";
	}

	@RequestMapping("/logOut")
	public @ResponseBody Map<String, String> logOut(@RequestBody String requestbody, HttpSession hSession) {
		Gson gson = new Gson();
		UserInfo userInfo = (UserInfo) hSession.getAttribute("User");
		if (userInfo != null) {
			userInfo = null;
			hSession.setAttribute("User", "");
		}
		UserInfo userInfo2 = gson.fromJson(requestbody, UserInfo.class);
		Map<String, String> map = new HashMap<String, String>();
		if (userInfo == null) {
			map.put("stus", "1");

		} else {
			map.put("stus", "0");

		}
		return map;
	}

}
