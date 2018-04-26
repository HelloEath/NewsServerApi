package com.glut.news.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.glut.news.vo.Article;
import com.glut.news.vo.Page;
import com.glut.news.vo.Video;

public interface IArticleService {

	List<Article> getArticleService(Page p);
	void putArticleService();
	int getTotalRowService(Article a);
	int deleteArticleService(Article article);
	List<Article> getArticleByTypeService(Page<Article> page);

	Article getDetailArticleService(Article article, HttpSession hSession);
	List<Article> getArticleByKeyWordsSerVice(Page<Article> page);

}
