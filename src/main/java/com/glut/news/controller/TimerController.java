package com.glut.news.controller;

import com.glut.news.service.IArticleService;
import com.glut.news.service.ICommentService;
import com.glut.news.service.INetBugsService;
import com.glut.news.service.IVideoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class TimerController {
	@Resource
	INetBugsService iNetBugsService;

	@Resource
	IArticleService articleService;

	@Resource
	IVideoService iVideoService;

	@Resource
	ICommentService iCommentService;
	/**爬取澎湃新闻和Zaker
	   * 每三个小时执行一次
	   */
	@Scheduled(cron="0 0 0/3 * * ?")
	public void BugsNews(){
		
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
				articleService.deleteRepeatArticleServer();
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
	


}
