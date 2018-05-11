package com.glut.news.controller;

import com.glut.news.commons.CommonUtil;
import com.glut.news.service.ICommentService;
import com.glut.news.vo.Comments;
import com.glut.news.vo.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/comment")
@Controller
public class CommentController {

	@Resource
	ICommentService iCommentService;
	
	
	/*发表评论*/
	@RequestMapping("/insertComment")
	public @ResponseBody Map<String, Object> insertComment(Comments comments ){
		int x=iCommentService.insertCommentService(comments);
		
		Map<String, Object> map=new HashMap<String, Object>();
		if(x==1){
		map.put("stus", "ok")	;
		}else {
			map.put("stus", "error");

		}
		return map;
		
		
	}
	
	/*获取对应文章评论*/
	@RequestMapping("/getComment")
	public @ResponseBody Map<String, Object> getComment(Page<Comments> page,HttpSession hSession,Comments comments){
		
		Page<Comments> page2=(Page<Comments>) hSession.getAttribute("commentPage"+comments.getComment_Article());
		if (page2==null) {
			page2=new Page<Comments>();
			int total=iCommentService.getCommentTotalService(comments);
			int startRow=CommonUtil.getStartRowBycurrentPage(page.getPageNo(), 10);

			page2.setStartRow(startRow);
			page2.setTotalRow(total);
			page2.setPageNo(1);
			page2.setPageSize(10);
		}else {
			page2.setPageNo(page.getPageNo());
			int startRow=CommonUtil.getStartRowBycurrentPage(page.getPageNo(), 10);
			page2.setStartRow(startRow);
		}
		page2.setQueryObject(comments);
		List<Comments> cList=iCommentService.getCommentService(page2);
		hSession.setAttribute("commentPage"+comments.getComment_Article(), page2);
		Map<String, Object> map=new HashMap<String, Object>();
		if (cList.size()!=0) {
			map.put("stus", "ok");
			map.put("data", cList);
			map.put("nextpage", page2.getNextNo());
			map.put("isHaveNextPage", page2.getNext());
		}else {
			map.put("stus", "error");
		}
		return map;
	}
	
	/*删除评论*/
	@RequestMapping("/deleteComment")
	public @ResponseBody Map<String, Object> deleteComment(Comments comment){
		int x=iCommentService.deleteCommentService(comment);
		Map<String, Object> map=new HashMap<String, Object>();
		if(x==1){
		map.put("stus", "ok")	;
		}else {
			map.put("stus", "error");

		}
		return map;
	}

/*更新文章/视频评论数*/
	@RequestMapping("/updateComment")
	public @ResponseBody int updateComment(String  contentId,int type){
		int x=iCommentService.updateCommentService(contentId,type);
		return x;
	}
	
	
}
