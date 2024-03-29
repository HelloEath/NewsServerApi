package com.glut.news.mapper;

import com.glut.news.vo.Article;
import com.glut.news.vo.Page;

import java.util.List;

public interface ArticleMapper {

	List<Article> selectByPage(Page p);
	int articleCounts(Article a);
	void insertArticle(Article a);
	int deleteArticle(Article article);
	List<Article> selectByType(Page<Article> page);
	Article selectById(Article article);
	int selectCommentCount(String contentId);
	int updateArticle(Article a);
	List<Article> selectByKeyWords(Page<Article> page);
	List<Article> selectAll();
	void deleteRepeatArticle();

    List<Article> selectByTuiJian(Page<Article> page);
}
