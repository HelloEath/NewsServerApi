package com.glut.news.mapper;

import java.util.List;


import com.glut.news.vo.Page;
import com.glut.news.vo.Star;

public interface StarMapper {
	List<Star> selectVideoStarByUserId(Page<Star> page);
	List<Star> selectArticleStarByUserId(Page<Star> page);
	int selectTatolByUserId(int userId);

	int selectStarCountByUserIdAndContentId(Star star);
	int insertStar(Star star);
}
