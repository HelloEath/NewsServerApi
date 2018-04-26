package com.glut.news.mapper;

import java.util.List;

import com.glut.news.vo.Comments;
import com.glut.news.vo.Page;
import com.glut.news.vo.Video;

public interface CommentsMapper {
	
	List<Comments> selectByPage(Page<Comments> page2);
	int commentCounts(Comments comments);
	int insertComment(Comments comment);
	int deleteComment(Comments comments);

}
