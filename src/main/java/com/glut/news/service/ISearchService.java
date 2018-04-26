package com.glut.news.service;

import java.util.List;

import com.glut.news.vo.Article;
import com.glut.news.vo.Page;
import com.glut.news.vo.Search;
import com.glut.news.vo.Video;

public interface ISearchService {

	List<Article> getSearchDataService(Page<Article> page);
	int getAllCountService(Page<Article> page);
}
