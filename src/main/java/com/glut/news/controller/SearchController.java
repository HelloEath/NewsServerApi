package com.glut.news.controller;

import com.glut.news.commons.CommonUtil;
import com.glut.news.service.ISearchService;
import com.glut.news.vo.Article;
import com.glut.news.vo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Resource
	ISearchService iSearchService;
	
	@RequestMapping("/searchData")
	public @ResponseBody Map<String, Object> searchData(Page<Article> page,Article article,HttpSession hSession){
		Page<Article> page2=(Page<Article>)hSession.getAttribute("SearchPage"+article.getArticle_Type());
		if (page2==null) {
			page2=new Page<Article>();
			page2.setQueryObject(article);
			int total=iSearchService.getAllCountService(page2);
			page2.setTotalRow(total);
			page2.setPageNo(1);
			page2.setPageSize(10);
			page2.setAverPageSize(page2.getPageSize()/2);
			int startRow=CommonUtil.getStartRowBycurrentPage(page2.getPageNo(), page2.getPageSize());
			page2.setStartRow(startRow);
		}else {
			page2.setPageNo(page.getPageNo());
			int startRow=CommonUtil.getStartRowBycurrentPage(page.getPageNo(), 10);
			page2.setStartRow(startRow);
		}
		page2.setQueryObject(article);
	
		List<Article> aList=iSearchService.getSearchDataService(page2);
		Map<String, Object> map=new HashMap<String, Object>();
		if (aList.size()!=0) {
			map.put("data", aList);
			map.put("stus", "ok");
			map.put("nextpage", page2.getNextNo());
			map.put("isHaveNextPage", page2.getNext());
			hSession.setAttribute("SearchPage"+article.getArticle_Type(), page2);
		}else {
			map.put("stus", "error");

		}
		
	
		return map;
		
	}
}
