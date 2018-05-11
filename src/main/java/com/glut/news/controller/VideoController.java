package com.glut.news.controller;

import com.glut.news.commons.CommonUtil;
import com.glut.news.service.IVideoService;
import com.glut.news.vo.Page;
import com.glut.news.vo.Video;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/video")
public class VideoController {
	
	@Resource
	IVideoService iVideoService;
	
	/*获取推荐视频列表(登录用户)*/
	
	@RequestMapping("/tuijian/isinterest")
	public @ResponseBody Map<String, Object> getVideos(Page<Video> page,HttpSession hSession){
		Page<Video> page2=(Page<Video>) hSession.getAttribute("VideoPage");
		if (page2==null) {
			page2=new Page<Video>();
			int tatoll=iVideoService.getTaltolVideoService(null);
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
              hSession.setAttribute("VideoPage",page2);
		}else {
			  map.put("stus", "error");
		}
	       
	    
		return map;
	}



	/*获取推荐视频列表(未登录用户)*/

	@RequestMapping("/tuijian/nointerest")
	public @ResponseBody Map<String, Object> getVideos2(Page<Video> page,HttpSession hSession){
		Page<Video> page2=(Page<Video>) hSession.getAttribute("VideoPage");
		if (page2==null) {
			page2=new Page<Video>();
			int tatoll=iVideoService.getTaltolVideoService(null);
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
			hSession.setAttribute("VideoPage",page2);
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
			int tatoll=iVideoService.getTaltolVideoService(video);
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

	@RequestMapping("/testUploadVideo")
	public void testUploadVideo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FileNotFoundException {
		httpServletResponse.setContentType("text/html");
		try {
			ServletInputStream sis = httpServletRequest.getInputStream();
			OutputStream os=new FileOutputStream(new File(""));
			BufferedOutputStream bos = new BufferedOutputStream(os);

			byte[] buf= new byte[1024];
			int length = 0;
			length = sis.readLine(buf, 0, buf.length);//使用sis的读取数据的方法
			while(length!=-1) {
				bos.write(buf, 0, length);
				length = sis.read(buf);
			}
			sis.close();
			bos.close();
			os.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}



}
