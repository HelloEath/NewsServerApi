package com.glut.news.service;

import java.util.List;

import com.glut.news.vo.Comments;
import com.glut.news.vo.Page;

public interface ICommentService {

	int getCommentTotalService(Comments c);

	List<Comments> getCommentService(Page<Comments> page2);

	int deleteCommentService(Comments comment);
	
	int insertCommentService(Comments comment);

	int updateCommentService(String contentId, int type);

}
