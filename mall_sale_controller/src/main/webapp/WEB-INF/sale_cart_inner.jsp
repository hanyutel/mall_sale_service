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
	
</script>
<style type="text/css">
	td{vertical-align: middle !important;}
	.form-group{padding: 5px 0;}
</style>
</head>
<body>
<div class="Cbox">
		<table class="table table-striped table-bordered table-hover">
		   <thead>
		     <tr>
			     <th></th>
			       <th>商品图片</th>
			       <th>商品名称</th>
			       <th>商品属性</th>
			       <th>商品价格</th>
			       <th>商品数量</th>
			       <th>操作</th>
			     </tr>
		   </thead>
		   <tbody>
		    <c:forEach items="${cart_list }" var="cart">
			    <tr>
			       <td><input type="checkbox" ${cart.shfxz=='1'?"checked":""} onclick="status_change(this.checked,${cart.sku_id})"></td>
			       <td><img src="upload/image/${cart.shp_tp}" alt="" width="100px"></td>
			       <td>${cart.sku_mch}</td>
			       <td>
			       		颜色：<span style='color:#ccc'>白色</span><br>
			       		尺码：<span style='color:#ccc'>L</span>
			       </td>
			       <td>￥${cart.sku_jg}</td>
			       <td><input type="text" name="min" value="${cart.tjshl}" style="width:50px;text-align:center" onchange="tjshl_change(this.value,${cart.sku_id})"></td>
			       <td>删除</td>
			     </tr>
		 	</c:forEach>	
		     
		   </tbody>
	 	</table>
	</div>
	<div class="Cprice">
		<div class="price">总价：${total_price }</div>
		<div class="jiesuan">
			<form action="goto_check_order.do" method="post">
				<input type="submit" value="结算">
			</form>
		</div>
	</div>
</body>
</html>