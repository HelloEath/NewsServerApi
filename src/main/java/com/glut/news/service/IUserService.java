package com.glut.news.service;

import javax.servlet.http.HttpSession;

import com.glut.news.vo.UserInfo;

public interface IUserService {
	
	

	UserInfo loginService(UserInfo u);
	int registerService(UserInfo u, HttpSession hSession);
	int updateUserService(UserInfo u);
	UserInfo selectUserService(UserInfo u);

}
