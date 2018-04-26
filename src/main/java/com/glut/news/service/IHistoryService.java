package com.glut.news.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.glut.news.vo.History;
import com.glut.news.vo.Page;

public interface IHistoryService {

	int getTatolHistory(int userId);

	List<History> selectUserHistoryService(Page<History> page2);

	int putHistoryService(History history,HttpSession hSession);

}
