package com.glut.news.mapper;

import java.util.List;

import com.glut.news.vo.Page;
import com.glut.news.vo.Video;

public interface VideoMapper {

	List<Video> selectByPage(Page<Video> page);
	int videoCounts(Page<Video> page);
	int insertVideo(Video video);
	int updateVideo(Video video);
	int deleteVideo(Video video);
	List<Video> selectByType(Page<Video> page);
	int selectCommentCount(String contentId);
	Video selectById(Video v);
	List<Video> selectByKeyWords(Page<Video> page);
	int selectByKeywordsTatol(Video video);
	
}
