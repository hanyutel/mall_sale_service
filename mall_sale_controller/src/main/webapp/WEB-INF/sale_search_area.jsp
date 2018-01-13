<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>韩宇的页面</title>
<script type="text/javascript">
	function b() {
	}
</script>
</head>
<body>
	<div class="search">
		<div class="logo">
			<a href="index.do"><img src="images/logo.jpg" alt=""></a>
		</div>
		<div class="search_on">
			<div class="se">
				<form action="do_search_solr.do" method="post">
					<input type="text" name="search" class="lf"> 
					<input type="submit" class="clik" value="搜索">
				</form>
			</div>
			<div class="se">
				<a href="">取暖神奇</a> <a href="">1元秒杀</a> <a href="">吹风机</a> <a
					href="">玉兰油</a>
			</div>
		</div>
		<jsp:include page="sale_minicart.jsp"></jsp:include>
	</div>
</body>
</html>