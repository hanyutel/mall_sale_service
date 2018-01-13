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
<link rel="stylesheet" type="text/css" href="css/css.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>韩宇的页面</title>
<script type="text/javascript">
	function select_address(id,dz_mch,shjr,lxfsh){
		$("#address_span").html(dz_mch+"&nbsp;&nbsp;"+shjr+"（收）&nbsp;"+lxfsh+"<input type='hidden' name='address_id' value='"+id+"'></input>");
	}
</script>
</head>
<body>
	<jsp:include page="sale_header.jsp"></jsp:include>
	<jsp:include page="sale_search_area.jsp"></jsp:include>
	<div class="message">
		<div class="msg_title">收货人信息</div>
		<c:forEach items="${address_list}" var="address">
			<div class="msg_addr">
				<span class="msg_left">
					${address.shjr}&nbsp;${address.lxfsh} </span> <span
					class="msg_right"> <input type="radio"
					onclick="select_address(${address.id},'${address.dz_mch}','${address.shjr}','${address.lxfsh}')"
					name="a" /> ${address.dz_mch }
				</span>
			</div>
		</c:forEach>
		<div class="msg_line"></div>

		<div class="msg_title">送货清单</div>
		<c:forEach items="${order.flow_list }" var="flow">
			<div class="msg_list">
				<div class="msg_list_left">
					配送方式
					<div class="left_title">${flow.psfsh }</div>
				</div>
				<c:forEach items="${flow.order_info_list}" var="info">
					<div class="msg_list_right">
						<div class="msg_img" style="width: 80px; height: 80px">
							<img src="upload/image/${info.shp_tp }" width="90px" />
						</div>
						<div class="msg_name">${info.sku_mch }</div>
						<div class="msg_price">￥${info.sku_jg}</div>
						<div class="msg_mon">*${info.sku_shl}</div>
						<div class="msg_state">有货</div>
					</div>
				</c:forEach>
			</div>
		</c:forEach>
		<div class="msg_line"></div>

		<div class="msg_sub">
			<form action="save_order.do" method="post">
				<div class="msg_sub_tit">
					应付金额： <b>￥${order.zje}</b>
				</div>
				<div class="msg_sub_adds">
					寄送至 ： <span id="address_span"></span> &nbsp;<span id="shjr_span"></span>（收）
					<span id="lxfsh_span"></span>
				</div>
				<!-- <input type="submit" value="提交订单" style="cursor:pointer;"  class="msg_btn"> -->
				<button class="msg_btn" style="cursor: pointer;">提交订单</button>
			</form>
		</div>
	</div>

</body>
</html>