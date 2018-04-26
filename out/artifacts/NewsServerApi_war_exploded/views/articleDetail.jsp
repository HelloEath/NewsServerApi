<%@page import="com.glut.news.vo.Article"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <%
  Article a=(Article)request.getAttribute("Article");
   %> 
  <head>
    <base href="<%=basePath%>">
    
    <title> <%=a.getArticle_Title() %>  </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
  <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
  <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
   <!--  <link href="bootstrap-3.3.6/css/bootstrap.min.css" rel="stylesheet">

	
	<script src="js/jquery-2.2.3.js" type="text/javascript" charset="utf-8"></script>
	<script src="bootstrap-3.3.6/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script> -->
	
  </head>

  <body>
   
<div class="row" style="margin:10px"><h4> <%=a.getArticle_Title() %> </h4></div>


 <div class="row" style="margin:10px"><%=a.getArticle_Author_name() %></div>

  <div class="row" style="margin:10px"> 
 <%=a.getArticle_Content() %>  
 </div> 

</html>
