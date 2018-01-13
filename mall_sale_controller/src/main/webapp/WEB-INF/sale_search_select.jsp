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
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>韩宇的页面</title>
<script type="text/javascript">
$(function b(){
	$.getJSON("js/json/class_1.js",function(data){
		$(data).each(function(i,n){
			$("#sku_select_class_1").append("<li onmouseover='class_1_change(this.value)' value="+n.id+"><a href=''>"+n.flmch1+"</a></li>");
			
		});
	});
});
function class_1_change(id){
	$("#sku_select_class_2").empty();
	$.getJSON("js/json/class_2_"+id+".js",function(data){
		$(data).each(function(i,n){
			$("#sku_select_class_2").append("<li  value="+n.id+"><a href='goto_class_search.do?class_2_id="+n.id+"&class_2_name="+n.flmch2+"'>"+n.flmch2+"</a></li>");
		});
	});
}
</script>
</head>
<body>
<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini">
						<ul name="flbhl" id="sku_select_class_1">
								<li >
									<div class="two_nav" name="flbh2" id="sku_select_class_2">
									</div>
								</li>
						</ul>
					</div>
				</div>
				<ul>
					<li><a href="">服装城</a></li>
					<li><a href="">美妆馆</a></li>
					<li><a href="">超市</a></li>
					<li><a href="">全球购</a></li>
					<li><a href="">闪购</a></li>
					<li><a href="">团购</a></li>
					<li><a href="">拍卖</a></li>
					<li><a href="">金融</a></li>
					<li><a href="">智能</a></li>
				</ul>
			</div>
		</div>
	</div>
<!-- <ul style="float:left;margin-left:10px" name="flbhl" id="sku_select_class_1"></ul> -->
<!-- <ul style="float:left;" name="flbh2" id="sku_select_class_2"></ul> -->
</body>
</html>