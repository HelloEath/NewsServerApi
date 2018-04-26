package com.glut.news.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.glut.news.vo.UserInfo;


public interface UserMapper {
	int insertUser(UserInfo u);
	List<UserInfo> selectMultiUser(UserInfo u);
	UserInfo selectSingleUser(UserInfo u);
	int updateUser(UserInfo u);
	int deleteUser(int id);

}
