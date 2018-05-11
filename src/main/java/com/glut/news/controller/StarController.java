package com.glut.news.controller;

import com.glut.news.commons.CommonUtil;
import com.glut.news.service.IStarService;
import com.glut.news.vo.Page;
import com.glut.news.vo.Star;
import com.glut.news.vo.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/star")
public class StarController {

	@Resource
	IStarService starService;
	private int tatol;

	/* 查看收藏列表 */
	@RequestMapping("/starList")
	public @ResponseBody Map<String, Object> starList(Page<Star> page, HttpSession hSession) {
		UserInfo user = (UserInfo) hSession.getAttribute("User");
		int userId = user.getUser_Id();
		Page<Star> page2 = (Page<Star>) hSession.getAttribute("star");
		if (page2 == null) {
			page2 = new Page<Star>();
			page2.setTotalRow(tatol);
			page2.setPageNo(1);
			page2.setPageSize(10);
			int startRow = CommonUtil.getStartRowBycurrentPage(page.getPageNo(), page2.pageSize);
			page2.setAverPageSize(page2.getPageSize()/2);

			page2.setStartRow(startRow);
		} else {
			page2.setTotalRow(tatol);

			page2.setPageNo(page.getPageNo());
			int startRow = CommonUtil.getStartRowBycurrentPage(page.getPageNo(), page2.pageSize);
			if (page2.getPageNo()==1){
				page2.setStartRow(startRow);
			}else {
				page2.setStartRow(startRow/2);

			}
		}
		Star star = new Star();
		star.setStar_UserId(userId);
		page2.setQueryObject(star);
		List<Star> stars = starService.selectUserStarService(page2);
		hSession.setAttribute("star", page2);
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (stars != null) {
			map.put("nextPage", page2.getNextNo());
			map.put("data", stars);
			map.put("stus", "success");
			map.put("isHaveNextPage", page2.getNext());
		} else {
			map.put("stus", "error");
		}
		return map;

	}

	/* 查看收藏数量 */
	@RequestMapping("/starCount")
	public @ResponseBody int starCount(HttpSession hSession) {
		UserInfo user = (UserInfo) hSession.getAttribute("User");
		if(user==null){
			tatol=0;

		}else {
			tatol=user.getUser_Stars();

		}
		return tatol;
	}

	/* 插入收藏文章/视频 */
	@RequestMapping("/putStar")
	public @ResponseBody int putStar(Star star, HttpSession hSession) {
		int x = starService.putStarService(star, hSession);
		return x;
	}
}
