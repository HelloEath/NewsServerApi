package com.glut.news.mapper;

import java.util.List;

import com.glut.news.vo.Article;
import com.glut.news.vo.Page;
import com.glut.news.vo.Search;
import com.glut.news.vo.Video;

public interface SearchMapper {

	List<Article> searchData(Page<Article> page);
	int selectTotall(Page<Article> page);
	List<Search> searchDataByAveraVideo(Page<Search> page);
	List<Search> searchDataByAveraArticle(Page<Search> page);

}
