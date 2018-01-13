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
	function b(){}
</script>
</head>
<body>
	<c:if test="${empty minicart_list }">
		<h2>您的购物车空空如也</h2>
	</c:if>
	 <c:forEach items="${minicart_list }" var="minicart">
			<div class="one">
				<img src="upload/image/${minicart.shp_tp}"  width="70px" />
				<span class="one_name">
					${minicart.sku_mch}
				</span>
				<span class="one_prece">
					<b>￥${minicart.sku_jg}</b><br />
					${minicart.tjshl}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
				</span>
			</div>
		 </c:forEach>		
		<div class="gobottom">
			共<span>${total_number}</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
			共计￥<span>${total_price }</span>
			<button class="goprice">去购物车</button>
		</div>

</body>
</html>