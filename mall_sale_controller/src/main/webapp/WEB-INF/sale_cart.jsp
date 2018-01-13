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
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>韩宇的页面</title>
<script type="text/javascript">
function status_change(f,sku_id){
	var shfxz=f?'1':'0';
	$.post("updata_cart_status.do",{shfxz:shfxz,sku_id:sku_id},function(data){
		$("#cart_list_div").html(data);
	});
}
function tjshl_change(tjshl,sku_id){
	$.post("updata_cart_tjshl.do",{tjshl:tjshl,sku_id:sku_id},function(data){
		$("#cart_list_div").html(data);
	});
}
</script>
</head>
<body>
	<jsp:include page="sale_header.jsp"></jsp:include>
	<jsp:include page="sale_search_area.jsp"></jsp:include>
	<div id="cart_list_div">
		<jsp:include page="sale_cart_inner.jsp" />
	</div>
	<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="images/foot.jpg" alt=""></div>
	</div>
</body>
</html>