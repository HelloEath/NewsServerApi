package com.glut.news.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.glut.news.mapper.SearchMapper;
import com.glut.news.service.IArticleService;
import com.glut.news.service.ISearchService;
import com.glut.news.service.IVideoService;
import com.glut.news.vo.Article;
import com.glut.news.vo.Page;
import com.glut.news.vo.Search;
import com.glut.news.vo.Video;

import net.sf.ehcache.search.impl.SearchManager;

@Service
public class SearchServiceImpl implements ISearchService {

	@Resource
	SearchMapper  searchMapper;
	
	

	@Override
	public List<Article> getSearchDataService(Page<Article> page) {
		
		return searchMapper.searchData(page);
	}

	@Override
	public int getAllCountService(Page<Article> page) {
		// TODO Auto-generated method stub
		return searchMapper.selectTotall(page);
	}
	

}
