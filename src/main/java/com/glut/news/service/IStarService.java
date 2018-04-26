package com.glut.news.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.glut.news.vo.History;
import com.glut.news.vo.Page;
import com.glut.news.vo.Star;

public interface IStarService {



	List<Star> selectUserStarService(Page<Star> page2);

	int putStarService(Star star,HttpSession h);

	int getTatolStar(int parseInt);

}
