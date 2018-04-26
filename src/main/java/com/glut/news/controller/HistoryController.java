package com.glut.news.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glut.news.commons.CommonUtil;
import com.glut.news.service.IHistoryService;
import com.glut.news.vo.History;
import com.glut.news.vo.Page;
import com.glut.news.vo.UserInfo;

@Controller
@RequestMapping("/history")
public class HistoryController {
	private static int historyCount;

	@Resource
	IHistoryService historyService;
	/*获取历史记录列表*/
	@RequestMapping("/historyList")
	public @ResponseBody Map<String, Object> historyList(Page<History> page,HttpSession hSession,HttpServletRequest h2){
		UserInfo user=(UserInfo) hSession.getAttribute("User");
		Page<History> page2=(Page<History>) hSession.getAttribute("history");
		if (page2==null) {
			page2=new Page<History>();
			//int tatol=historyService.getTatolHistory(user.getUser_Id());
			page2.setTotalRow(historyCount);
			page2.setPageNo(1);
			page2.setPageSize(10);
			int startRow=CommonUtil.getStartRowBycurrentPage(page.getPageNo(), page2.pageSize);
			page2.setStartRow(startRow);
			page2.setAverPageSize(page2.getPageSize()/2);
			
		}else {
			
			page2.setPageNo(page.getPageNo());
			int startRow=CommonUtil.getStartRowBycurrentPage(page.getPageNo(), page2.pageSize);
			page2.setStartRow(startRow);
		}
		History h=new History();
		h.setHistory_Persion(user.getUser_Id());
		page2.setQueryObject(h);
	List<History> histories	=historyService.selectUserHistoryService(page2);

	hSession.setAttribute("history", page2);
		Map<String, Object> map=new HashMap<String, Object>();
		int f= page2.getNextNo();
	if (histories!=null) {
		map.put("nextPage", page2.getNextNo());
		map.put("data", histories);
		map.put("stus", "ok");
		map.put("isHaveNextPage", page2.getNext());
		}
		return map;
		
	}
	
	/*获取历史记录数*/
	@RequestMapping("/tatolHistory")
	public @ResponseBody int tatolHistory(HttpSession hSession){
		UserInfo user=(UserInfo) hSession.getAttribute("User");
		historyCount=user.getUser_Historys();
		return historyCount;
		
		
	}
	/*插入历史记录*/
	@RequestMapping("/putHistory")
	public @ResponseBody int  putHistory(History history,HttpSession hSession){
		
		int x=historyService.putHistoryService(history,hSession);
		
		return x;
		
	}
	
}
