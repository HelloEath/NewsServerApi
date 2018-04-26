package com.glut.news.service.impl;

import java.sql.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glut.news.commons.DateUtils;
import com.glut.news.mapper.UserMapper;
import com.glut.news.service.IUserService;
import com.glut.news.vo.UserInfo;

@Service()
public class UserServiceImpl implements IUserService{
	UserInfo u2;
	@Resource
	UserMapper userMapper;
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public UserInfo loginService(UserInfo u) {
		
		 u2=userMapper.selectSingleUser(u);

		return u2;
		
	}



	@Override
	public int registerService(UserInfo u, HttpSession hSession) {
		UserInfo userInfo3=new UserInfo();
		userInfo3.setUser_NickName(null);
		userInfo3.setUser_Password(null);
		userInfo3.setUser_PhoneNum(u.getUser_PhoneNum());
		userInfo3.setUser_Email(u.getUser_Email());
		u.setUser_LastLoginTime(DateUtils.getDate());
		u2=userMapper.selectSingleUser(userInfo3);
		if (u2!=null) {
			
		}else {
			int x=userMapper.insertUser(u);
			
				return x;
			
		}
		
		return 0;
	}


	@Override
	public  int updateUserService( UserInfo u) {
		int x=userMapper.updateUser(u);
		
		return x;
	}


	@Override
	public UserInfo selectUserService(UserInfo u) {
		
		return userMapper.selectSingleUser(u);
	}

}
