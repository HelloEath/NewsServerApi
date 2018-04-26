package com.glut.news.commons;

public class CommonUtil {
	
	public static int getStartRowBycurrentPage(int currentpage, int pageSize) {
		 int startRow=0;
	        
	        if (currentpage==1) {
	            
	            return startRow=0;
	        }
	        
	        startRow=(currentpage-1)*pageSize;
	        
	        return startRow;
	}
}
