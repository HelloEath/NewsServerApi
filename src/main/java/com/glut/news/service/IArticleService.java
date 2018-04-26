package com.glut.news.service;

import com.glut.news.vo.Article;
import com.glut.news.vo.Page;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IArticleService {

	List<Article> getArticleService(Page p);
	void putArticleService();
	int getTotalRowService(Article a);
	int deleteArticleService(Article article);
	List<Article> getArticleByTypeService(Page<Article> page);

	Article getDetailArticleService(Article article, HttpSession hSession);
	List<Article> getArticleByKeyWordsSerVice(Page<Article> page);

	void deleteRepeatArticleServer();
}
