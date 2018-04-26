package com.glut.news.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.glut.news.vo.Page;
import com.glut.news.vo.Video;

public interface IVideoService {

	int getTaltolVideoService();

	List<Video> getVideoService(Page<Video> page2);

	int putVideoService(Video video);

	int updateVideoService(Video video);

	int deleteVideoService(Video video);

	List<Video> getTypeVideoService(Page<Video> page2);

	int updateVideoPlaysService(Video video);
	List<Video> getVideoByKeyWords(Page<Video> page);


	List<Video> getDetailVideoService(Page<Video> page);

	int getKeyWordsTotalService(Video video);

	Video getVideoDetailService(Video video, HttpSession hSession);

}
