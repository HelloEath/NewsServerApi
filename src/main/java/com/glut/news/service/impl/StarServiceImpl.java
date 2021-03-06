package com.glut.news.service.impl;

import com.glut.news.mapper.StarMapper;
import com.glut.news.service.IStarService;
import com.glut.news.service.IUserService;
import com.glut.news.vo.Page;
import com.glut.news.vo.Star;
import com.glut.news.vo.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Service
public class StarServiceImpl implements IStarService {

	@Resource
	StarMapper starMapper;

	@Resource
	IUserService iUserService;
	@Override
	public int getTatolStar(int parseInt) {
		// TODO Auto-generated method stub
		return starMapper.selectTatolByUserId(parseInt);
	}

	@Override
	public List<Star> selectUserStarService(Page<Star> page2) {
		List<Star> lStars=new ArrayList<Star>();
		List<Star> fHistories=starMapper.selectVideoStarByUserId(page2);
		List<Star> fHsList=starMapper.selectArticleStarByUserId(page2);
		lStars.addAll(fHistories);
		lStars.addAll(fHsList);
		return lStars;
	}

	@Override
	public int putStarService(Star star,HttpSession hSession) {
		// TODO Auto-generated method stub
		int x=starMapper.selectStarCountByUserIdAndContentId(star);
		if (x==1) {
			
		}else {
		UserInfo userInfo=	(UserInfo)hSession.getAttribute("User");
		userInfo.setUser_Stars(userInfo.getUser_Stars()+1);
		hSession.setAttribute("User", userInfo);
			iUserService.updateUserService(userInfo);
			return starMapper.insertStar(star);
		}
		return 0;
	}

}
