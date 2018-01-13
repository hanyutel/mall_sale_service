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
	function minicart_show(){
		$.post("get_minicart.do",function(data){
			$("#minicart_show_div").html(data);
		})		
		$("#minicart_show_div").show();
	}
	function minicart_hide(){
		$("#minicart_show_div").hide();
	}
</script>
</head>
<body>
	<div class="card">
		<a target="_blank" href="goto_cart.do" onmouseover="minicart_show()" onmouseout="minicart_hide()">购物车<div class="num">0</div></a>
		<!--购物车商品-->
		<div class="cart_pro">
			<div id="minicart_show_div"></div>
		</div>
	</div>

</body>
</html>