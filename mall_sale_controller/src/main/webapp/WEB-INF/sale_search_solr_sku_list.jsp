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
	<div class="Sbox">
	${search}:<br>
		<c:forEach items="${sku_list }" var="sku">
			<a href="goto_search_detail.do?sku_id=${sku.id}&spu_id=${sku.shp_id }">
				<div class="list">
						<div class="img"><img src="upload/image/${sku.shp_tp}" alt="" width="200px" ></div>
						<div class="price">¥${sku.jg}</div>
						<div class="title">${sku.sku_mch}</div>
							${sku.kc}
				</div>
			</a>
		</c:forEach>
	</div>

</body>
</html>