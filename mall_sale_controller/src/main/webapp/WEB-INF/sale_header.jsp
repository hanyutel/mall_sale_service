<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/css.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>韩宇的页面</title>
<script type="text/javascript">
	$();
	$(function b(){
		var cookie =get_cookie("yh_nch");
		$("#cookie_span_unlogin").html(cookie);
	});
	function get_cookie(key){
		var value="";
		var cookies=document.cookie;
		cookies=cookies.split(";");
		for(var i=0;i<cookies.length;i++){
			var cookie=cookies[i].replace(/\s/,"");
			var cookie_array=cookie.split("=");
			if(cookie_array[0]==key){
				value=cookie_array[1];
			}
		}
		value=decodeURIComponent(value);
		return value;
	}
</script>
</head>
<body>
<div class="top">
		<div class="top_text">
		<c:if test="${empty user }">
		 <a href="javascript:;">注册</a>
		  <a href="goto_login.do">登录</a>
		  <a href="javascript:;"><span id="cookie_span_unlogin" style="color:red"></span>请</a>
		 
		  
		</c:if>
		<c:if test="${not empty user }">
		 <a href="javascript:;">我的订单 </a>
		  <a href="do_logout.do">退出登录</a> 
		 <a href="javascript:;">${user.yh_nch}</a>
		</c:if>  
		</div>
	</div>
    <div class="top_img">
		<img src="images/top_img.jpg" alt="">
	</div>                     
</body>
</html>