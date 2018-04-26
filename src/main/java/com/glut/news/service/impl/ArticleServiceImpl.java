package com.glut.news.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.expr.NewArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.glut.news.commons.NetUtils;
import com.glut.news.mapper.ArticleMapper;
import com.glut.news.mapper.HistoryMapper;
import com.glut.news.mapper.UserMapper;
import com.glut.news.service.IArticleService;
import com.glut.news.service.INetBugsService;
import com.glut.news.vo.Article;
import com.glut.news.vo.History;
import com.glut.news.vo.Page;
import com.glut.news.vo.UserInfo;
import com.glut.news.vo.Video;
import com.google.gson.Gson;


@Service
public class ArticleServiceImpl implements IArticleService {
@Resource
ArticleMapper mArticleMapper;

@Resource
INetBugsService iNetBugsService;
@Resource
HistoryMapper historyMapper;

@Resource
UserMapper userMapper;

static List<String> urls=new ArrayList<String>();
	public ArticleServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<Article> getArticleService(Page p) {
		
		return mArticleMapper.selectByPage(p);
	}
	@Override
	public void putArticleService() {

		iNetBugsService.bugsPengPaiWeb();
		
		
	}
	


	
	
	@Override
	public int getTotalRowService(Article a) {
		return mArticleMapper.articleCounts(a);
	}
	@Override
	public int deleteArticleService(Article article) {
		return mArticleMapper.deleteArticle(article);
		
	}
	@Override
	public List<Article> getArticleByTypeService(Page<Article> page) {
		// TODO Auto-generated method stub
		return mArticleMapper.selectByType(page);
	}
	
	@Override
	public Article getDetailArticleService(Article  article2,HttpSession hSession) {
		return mArticleMapper.selectById(article2);
	}
	@Override
	public List<Article> getArticleByKeyWordsSerVice(Page<Article> page) {
		// TODO Auto-generated method stub
		return mArticleMapper.selectByKeyWords(page);
	}
	
	

}
