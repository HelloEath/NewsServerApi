package com.glut.news.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glut.news.commons.CommonUtil;
import com.glut.news.service.IVideoService;
import com.glut.news.vo.Page;
import com.glut.news.vo.Video;

@Controller
@RequestMapping("/video")
public class VideoController {
	
	@Resource
	IVideoService iVideoService;
	
	/*获取推荐视频列表*/
	
	@RequestMapping("/getVideo")
	public @ResponseBody Map<String, Object> getVideos(Page<Video> page,HttpSession hSession){
		Page<Video> page2=(Page<Video>) hSession.getAttribute("VideoPage");
		if (page2==null) {
			page2=new Page<Video>();
			int tatoll=iVideoService.getTaltolVideoService();
			int startRow=CommonUtil.getStartRowBycurrentPage(page2.getPageNo(), 10);
			page2.setStartRow(startRow);
			page2.setTotalRow(tatoll);
			page2.setPageNo(1);
			page2.setPageSize(10);
		}else {
			page2.setPageNo(page.getPageNo());
			int startRow=CommonUtil.getStartRowBycurrentPage(page2.getPageNo(), 10);
			page2.setStartRow(startRow);
		}
		List<Video> vList=iVideoService.getVideoService(page2);
		  Map<String, Object> map=new HashMap<String, Object>();
		  if (vList.size()!=0) {
			  map.put("stus", "ok");
		        map.put("data", vList);
		        map.put("nextpage", page2.getNextNo());
		        map.put("isHaveNextPage", page2.getNext());
		}else {
			  map.put("stus", "error");
		}
	       
	    
		return map;
	}
/*获取视频分类列表*/
	
	@RequestMapping("/getTypeVideo")
	public @ResponseBody Map<String, Object> getTypeVideo(Page<Video> page,String session,HttpSession hSession,Video video){
		
		Page<Video> page2 =(Page<Video>) hSession.getAttribute("VideoPage"+video.getVideo_Type());
		if (page2==null) {
			page2=new Page<Video>();
			int tatoll=iVideoService.getTaltolVideoService();
			int startRow=CommonUtil.getStartRowBycurrentPage(page2.getPageNo(), 10);
			page2.setStartRow(startRow);
			page2.setTotalRow(tatoll);
			page2.setPageNo(1);
			page2.setPageSize(10);
		}else {
			page2.setPageNo(page.getPageNo());
			int startRow=CommonUtil.getStartRowBycurrentPage(page2.getPageNo(), 10);
			page2.setStartRow(startRow);
		}
		page2.setQueryObject(video);
		hSession.setAttribute("VideoPage"+video.getVideo_Type(), page2);
		List<Video> vList=iVideoService.getTypeVideoService(page2);
		
        Map<String, Object> map=new HashMap<String, Object>();
        if (vList.size()!=0) {
			  map.put("stus", "ok");
		        map.put("data", vList);
		        map.put("nextpage", page2.getNextNo());
		        map.put("isHaveNextPage", page2.getNext());

		}else {
			  map.put("stus", "error");
		}
        
		return map;
	}
	
/*获取详情页视频推荐*/
	
	@RequestMapping("/getDetailVideo")
	public @ResponseBody Map<String, Object> getDetailVideo(HttpSession hSession,Video  video){
		
		@SuppressWarnings("unchecked")
		Page<Video> page=(Page<Video>)hSession.getAttribute("Page"+video.getVideo_KeyWord());
		if (page==null) {
			page=new Page<Video>();
			int tatol=iVideoService.getKeyWordsTotalService(video);
			page.setTotalRow(tatol);
			page.setPageNo(1);
			page.setPageSize(10);
		}else {
			page.setPageNo(page.getNextNo());
			
		}
		
		page.setQueryObject(video);
		List<Video> vList=iVideoService.getDetailVideoService(page);
		
        Map<String, Object> map=new HashMap<String, Object>();
        if (vList.size()!=0) {
			  map.put("stus", "ok");
		        map.put("data", vList);
		       hSession.setAttribute("Page"+video.getVideo_KeyWord(), page);
		}else {
			  map.put("stus", "error");
		}
        
		return map;
	}
	
	/*发表视频*/
	
	@RequestMapping("/putVideo")
	public @ResponseBody int putVideo(Video video){
		int x=iVideoService.putVideoService(video);
		return x;
	}
	
	
	/*删除视频*/
	
	@RequestMapping("/deleteVideo")
	public @ResponseBody int deleteVideo(Video video){
		int x=iVideoService.deleteVideoService(video);
		return x;
	}
	
/*获取视频详情*/
	
	@RequestMapping("/getVideoDetailInfo")
	public @ResponseBody Map<String, Object> getVideoDetail(Video video,HttpSession hSession){
		Video x=iVideoService.getVideoDetailService(video,hSession);
		Map<String, Object> map=new HashMap<String, Object>();
		List<Video> vList=new ArrayList<Video>();
		vList.add(x);
		map.put("stus", "ok");
		map.put("data", vList);
		
		return map;
	}

}
