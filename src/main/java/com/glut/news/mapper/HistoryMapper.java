package com.glut.news.mapper;

import java.util.List;


import com.glut.news.vo.History;
import com.glut.news.vo.Page;

public interface HistoryMapper {
	List<History> selectVideoByUserId(Page<History> page);
	List<History> selectArticleByUserId(Page<History> page);
	int selectTatolByUserId(int userId);

	int selectedHistoryByUserIdWithContentId(History h);
	int insertHistory(History history);
}
