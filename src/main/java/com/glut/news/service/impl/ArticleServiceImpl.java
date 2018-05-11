package com.glut.news.service.impl;

import com.glut.news.mapper.ArticleMapper;
import com.glut.news.mapper.HistoryMapper;
import com.glut.news.mapper.UserMapper;
import com.glut.news.service.IArticleService;
import com.glut.news.service.INetBugsService;
import com.glut.news.vo.Article;
import com.glut.news.vo.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


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
		Article article=mArticleMapper.selectById(article2);
		article.setArticle_Looks(article.getArticle_Looks()+1);
		mArticleMapper.updateArticle(article);
		return article;
	}
	@Override
	public List<Article> getArticleByKeyWordsSerVice(Page<Article> page) {
		// TODO Auto-generated method stub
		return mArticleMapper.selectByKeyWords(page);
	}

	@Override
	public void deleteRepeatArticleServer() {

		mArticleMapper.deleteRepeatArticle();
	}

	@Override
	public List<Article> getArticleByTuiJianService(Page<Article> page) {
		return mArticleMapper.selectByTuiJian(page);
	}

	@Override
	public List<Article> getAllArticleService() {
		return mArticleMapper.selectAll();
	}


}
