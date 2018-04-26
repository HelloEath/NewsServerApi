package com.glut.news.controller;

import java.awt.print.Pageable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glut.news.commons.CommonUtil;
import com.glut.news.service.IArticleService;
import com.glut.news.service.INetBugsService;
import com.glut.news.vo.Article;
import com.glut.news.vo.Page;
import com.glut.news.vo.UserInfo;
import com.google.gson.Gson;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Resource
	IArticleService iArticleService;
	@Resource
	INetBugsService iNetBugsService;
	
	@RequestMapping("/tuijian")
	//获取首页推荐文章
	public @ResponseBody Map<String, Object> getArticle(Page<Article> p,HttpSession hSession,Article a){
		UserInfo userInfo=(UserInfo)hSession.getAttribute("User");

		Page<Article> page=(Page<Article>) hSession.getAttribute("ArticlePage1");
		if (page==null) {
			page=new Page<Article>();
			int totalRow=iArticleService.getTotalRowService(null);
			
			page.setTotalRow(totalRow);
			page.setPageSize(10);
			page.setPageNo(1);
			int startRow=CommonUtil.getStartRowBycurrentPage(page.getPageNo(),page.getPageSize());
			page.setStartRow(startRow);
		}else {
			page.setPageNo(p.getPageNo());
			int startRow=CommonUtil.getStartRowBycurrentPage(page.getPageNo(),page.getPageSize());
			page.setStartRow(startRow);
		}
		//page.setQueryObject(a);
		Gson gson=new Gson();
	Map<String, Double> m=new HashMap<String, Double>();
	m=gson.fromJson(userInfo.getUser_Interest(),m.getClass());
	int interestCount=0;
	
	
	for (String k: m.keySet()) {
		double d=m.get(k);
		interestCount+=m.get(k).intValue();
		
	}
	int pageSize=page.getPageSize();
	 List<Object> aList = new ArrayList<>();
	for (String k: m.keySet()) {
		if ("".equals(k) || "null".equalsIgnoreCase(k)) {
			
		}else {
			
		
		int eachTypeCount=0;
		if (interestCount==0) {//第一次登陆
			eachTypeCount=(int)(pageSize/m.size());
		}else {
			int erestddd=m.get(k).intValue();
			double interestRate=(double)erestddd/(double)interestCount;
			double dd=((double)interestRate*pageSize);
			eachTypeCount=(int) dd;
		}
		if (eachTypeCount!=0) {
			page.setPageSize(eachTypeCount);
			a.setArticle_Type(k);
			page.setQueryObject(a);
			aList.addAll(iArticleService.getArticleService(page));
		}
		
		}
	}
	   //List<Article> aList=	iArticleService.getArticleService(page);
		page.setPageSize(10);
	   hSession.setAttribute("ArticlePage1", page);
	   Map<String, Object> map=new HashMap<String, Object>();
	  
       if (aList.size()!=0) {
 		  map.put("stus", "ok");
 		  map.put("nextpage", page.getNextNo());
 	       map.put("data", aList);
 	       map.put("isHaveNextPage", page.getNext());
 	}else {
 		  map.put("stus", "error");
 	}
	   return map;
		
	}
	
	@RequestMapping("/putArticle")
	public void putArticle(){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Long starTime=System.currentTimeMillis();
				System.out.println("爬取开始时间："+starTime);
				iNetBugsService.bugsPengPaiWeb();
				iNetBugsService.bugsZakerWeb();
				iNetBugsService.bugsFirstNewsWeb();
				iNetBugsService.bugsTouTiaoWeb();
				Long endTime=System.currentTimeMillis();
				System.out.println("爬取结束时间："+endTime);
			     float excTime=(float)(endTime-starTime)/1000;
			     
			     if (excTime>60) {
			 		System.out.println("总用时："+excTime/60+"分钟");

				}else {
					System.out.println("总用时："+excTime+"秒");

				}
			}
		}).start();
	
	}
	
	@RequestMapping("/deleteArticle")
	public @ResponseBody int deleteArticle(Article article){
		int x=iArticleService.deleteArticleService(article);
		return x;
	}
	
	/*分类文章获取*/
	@RequestMapping("/typeArticle")
	public @ResponseBody Map<String, Object> typeArticle(Article article,HttpSession hSession,Page<Article> p){
		Page<Article> page=(Page<Article>) hSession.getAttribute(article.getArticle_Type()+"page");
		if (page==null) {
			page=new Page<Article>();
			int total=iArticleService.getTotalRowService(article);
			int startRow=CommonUtil.getStartRowBycurrentPage(page.getPageNo(), 10);
			page.setStartRow(startRow);
			page.setTotalRow(total);
			page.setPageSize(10);
			page.setPageNo(1);
		}else {
			page.setPageNo(p.getPageNo());
			int startRow=CommonUtil.getStartRowBycurrentPage(page.getPageNo(), 10);
			page.setStartRow(startRow);
		}
		page.setQueryObject(article);
		List<Article> aList=iArticleService.getArticleByTypeService(page);
		for (int i = 0; i < aList.size(); i++) {
			aList.get(i).setArticle_Abstract("");
			aList.get(i).setArticle_Content("");
		}
	hSession.setAttribute(article.getArticle_Type()+"page", page);
	 Map<String, Object> map=new HashMap<String, Object>();
	 boolean isHaveNextPage=page.getNext();
	 if (aList.size()!=0) {
		  map.put("stus", "ok");
		  map.put("data", aList);
		    map.put("nextpage", page.getNextNo());
		    map.put("isHaveNextPage", isHaveNextPage);
	}else {
		  map.put("stus", "error");
	}
   
   
	return map;
	}
	
	/*获取文章详情页*/
	@RequestMapping("/detailArticle")
	public String  detailArticle(Article article,ModelMap model,HttpSession hSession){
		article =iArticleService.getDetailArticleService(article,hSession);
		article.setArticle_Content(article.getArticle_Content().replace("lazy", "img-fluid"));;
		model.addAttribute("Article", article);
		return "forward:/views/articleDetail.jsp";
	}

}
