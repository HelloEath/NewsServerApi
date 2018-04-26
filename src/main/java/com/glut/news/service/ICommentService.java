package com.glut.news.service;

import com.glut.news.vo.Comments;
import com.glut.news.vo.Page;

import java.util.List;

public interface ICommentService {

	int getCommentTotalService(Comments c);

	List<Comments> getCommentService(Page<Comments> page2);

	int deleteCommentService(Comments comment);
	
	int insertCommentService(Comments comment);

	int updateCommentService(String contentId, int type);
	void deleteRepeatCommentServer();

}
