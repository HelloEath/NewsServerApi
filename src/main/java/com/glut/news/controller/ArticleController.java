package com.glut.news.controller;

import com.glut.news.commons.CommonUtil;
import com.glut.news.service.IArticleService;
import com.glut.news.service.ICommentService;
import com.glut.news.service.INetBugsService;
import com.glut.news.service.IVideoService;
import com.glut.news.vo.Article;
import com.glut.news.vo.Page;
import com.glut.news.vo.UserInfo;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {
	/*慎重打开*/
	/*private LuceneDao luceneDao=new LuceneDao();*/

	@Resource
	IArticleService iArticleService;
	@Resource
	INetBugsService iNetBugsService;

	@Resource
	IVideoService iVideoService;

	@Resource
	ICommentService iCommentService;

    List<Article> al=null;
   //获取首页推荐文章
	@RequestMapping("tuijian/isinterest")
	public @ResponseBody
	Map<String, Object> getArticle(Page<Article> p, HttpSession hSession, Article a){
		UserInfo userInfo=(UserInfo)hSession.getAttribute("User");
		List<Object> aList=null;
		Page<Article> page=(Page<Article>) hSession.getAttribute("ArticlePage1");
		if (page==null) {
			page=new Page<Article>();
			int totalRow=iArticleService.getTotalRowService(null);
			 aList = new ArrayList<>();
			 al=iArticleService.getAllArticleService();
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

		List<Article> junshi=new ArrayList<>();
		List<Article> keji=new ArrayList<>();
		List<Article> yule=new ArrayList<>();
		List<Article> lvyou=new ArrayList<>();
		List<Article> hulinawang=new ArrayList<>();

		for (int i=0;i<al.size();i++){

			if ("军事".equals(al.get(i).getArticle_Type())){
				junshi.add(al.get(i));
			}

			if ("科技".equals(al.get(i).getArticle_Type())){
				keji.add(al.get(i));
			}
			if ("娱乐".equals(al.get(i).getArticle_Type())){
				yule.add(al.get(i));
			}
			if ("旅游".equals(al.get(i).getArticle_Type())){
				hulinawang.add(al.get(i));
			}
			if ("互联网".equals(al.get(i).getArticle_Type())){
				lvyou.add(al.get(i));
			}
		}
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
			//*page.setPageSize(eachTypeCount);
			/*a.setArticle_Type(k);
			page.setQueryObject(a);
			try {
				List<Object> o=	luceneDao.findIndex(k,page.getStartRow(),page.pageSize);
			} catch (Exception e) {
				e.printStackTrace();
			}*/

			if (k.equals("科技")){
				for (int i=1;i<=eachTypeCount;i++){
					aList.add(keji.get(i));


				}

			}
			if (k.equals("娱乐")){

				for (int i=1;i<=eachTypeCount;i++){
					aList.add(yule.get(i));


				}
			}
			if (k.equals("互联网")){
				for (int i=1;i<=eachTypeCount;i++){

					aList.add(hulinawang.get(i));

				}

			}
			if (k.equals("旅游")){

				for (int i=1;i<=eachTypeCount;i++){
					aList.add(lvyou.get(i));

				}

			}




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





	/*分类文章获取*/
	@RequestMapping("/tuijian/nointerest")
	public @ResponseBody Map<String, Object> getTuiJianArticle(HttpSession hSession,Page<Article> p){
		Page<Article> page=(Page<Article>) hSession.getAttribute("tuijianpage");
		if (page==null) {
			page=new Page<Article>();
			int total=iArticleService.getTotalRowService(null);
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
		List<Article> aList=iArticleService.getArticleByTuiJianService(page);

		hSession.setAttribute("tuijianpage", page);
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
				//数据库去重
				iArticleService.deleteRepeatArticleServer();
				iVideoService.deleteRepeatVideoServer();
				//iCommentService.deleteRepeatCommentServer();
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
	
	//分类文章获取
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
	public  String  detailArticle(Article article, ModelMap model, HttpSession hSession){
	    article =iArticleService.getDetailArticleService(article,hSession);
		article.setArticle_Content(article.getArticle_Content().replace("lazy", "img-fluid"));
		model.addAttribute("Article", article);
		return "forward:/views/articleDetail.jsp";
	}

}
