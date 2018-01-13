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
	function do_search_up(attrid,valid,shxm_mch){
		var a='<span id="attr_list_span_'+attrid+'">';
		var b="	<input type='hidden' value='{\"attr_id\":"+attrid+",\"val_id\":"+valid+"}'>";
		var c='	<a href="javascript:do_search_down('+attrid+');">'+shxm_mch+'</a>&nbsp&nbsp;';
		var d='</span>';
		$("#select_div").append(a+b+c+d);
		var select_div_length=$("#select_div").children().length;
		var input_lenth=$("#clear_search").length;
		if(select_div_length>0 && input_lenth<1){ 
			var e='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="clear_search" href="javascript:clear_select();">清空筛选</a>'
			$("#select_div").after(e);
		}
		$("#attr_list_div_"+attrid).hide();
		do_search_by_attr();
// 		var url=window.location.href;
// 		if(url.indexOf("search_ids=")>=0){
// 			window.location.href=url+"and"+attrid+"_"+valid;
// 		}else{
// 			window.location.href=url+"&search_ids="+attrid+"_"+valid;
// 		}
		
	}
	function do_search_down(attrid){
		$("#attr_list_div_"+attrid).show();
		$("#attr_list_span_"+attrid).remove();
		do_search_by_attr();
		var select_div_length=$("#select_div").children().length;
		if(select_div_length==0){
			$("#clear_search").remove();
		}
	}
	function do_search_by_attr(){
		var class_2_id=${class_2_id};
		var input=$(":input[type='hidden']");
		var url="class_2_id="+class_2_id;
		$.each(input,function(i,n){
			var json=$.parseJSON(n.value);
			url=url+"&sku_value_list["+i+"].shxm_id="+json.attr_id+"&sku_value_list["+i+"].shxzh_id="+json.val_id;
		});
		$.post("do_search_by_attr.do",url,function(data){
			$("#sale_search_sku_lsit_div").html(data);
		});
	}
	function clear_select(){
		$("#select_div").empty();
		$("#clear_search").remove();
		$("div[id^=attr_list_div_]").show();
		do_search_by_attr();
	}
	function search_multiselect(attrid){
		$("#attr_list_div_"+attrid+" input[type='checkbox']").each(function(){
			$(this).show();
			$(this).next().show();
			$(this).next().next().hide();
		});
		$("#search_multiselect_false_"+attrid).show();
	}
	function search_multiselect_false(attrid){
		$("#attr_list_div_"+attrid+" input[type='checkbox']").each(function(){
			$(this).hide();
			$(this).attr("checked",false);
			$(this).next().hide();
			$(this).next().next().show();
		});
	}
	function search_multiselect_true(attrid){
		var val="";
		$("#attr_list_div_"+attrid+" input[type='checkbox']:checked").each(function(i,n){
			if(i>0){
				val=val+",";
			}
			val=val+n.value;
		});
		alert(val);
		search_multiselect_false(attrid);
	}
	function update_checkbox_status(valid,attrid){
		$("#multiselect_checkbox_"+valid).click();
		if($("#attr_list_div_"+attrid+" input[type='checkbox']:checked").length>0){
			$("#attr_list_div_"+attrid).children().last().prev().show();
		}else{
			$("#attr_list_div_"+attrid).children().last().prev().hide();
		}
	}
</script>
</head>
<body>
<hr>
	<div  class="filter">
		<h2>电脑、办公 </h2>
		<span class="gt">&gt</span>
		<div class="filter_div">
			${class_2_name }
		</div>
		<span class="gt">&gt</span>
		<div class="filter_div">
		<span id="select_div"></span>
		</div>
		<div id="clear_search_div" class="filter_clear">
			
		</div>
				
	</div>


<div class="Sscreen">
		<div class="title">
			平板电视 商品筛选 共1205个商品
		</div>
		<c:forEach items="${attr_list }" var="attr">
			<div class="list" id="attr_list_div_${attr.id}"> 
				<span>${attr.shxm_mch}:</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<c:forEach items="${attr.attr_value }" var="val" varStatus="index">
					<input type="checkbox" style="display:none" id="multiselect_checkbox_${val.id}" value="${val.id}_" >
					<a href="javascript:update_checkbox_status(${val.id},${attr.id});" style="display:none">${val.shxzh}${val.shxzh_mch}</a>
					<a href="javascript:do_search_up('${attr.id}','${val.id}','${attr.shxm_mch}:${val.shxzh}${val.shxzh_mch}');">${val.shxzh}${val.shxzh_mch}</a>&nbsp;&nbsp;&nbsp;
				</c:forEach>
				<a href="javascript:search_multiselect(${attr.id });" style="margin-left:100px;color:green">多选</a>
				<a href="javascript:search_multiselect_true(${attr.id });" style="margin-left:10px;color:green;display: none">确定</a>
				<a href="javascript:search_multiselect_false(${attr.id });" style="margin-left:10px;color:green;display: none" id="search_multiselect_false_${attr.id }">取消</a>
			</div>
		</c:forEach>
		<div class="list">
			<span class="list_span" id="list_beas">销量</span>
			<span class="list_span">价格</span>
			<span class="list_span">评论数</span>
			<span class="list_span">上架时间</span>
		</div>
	</div>
	

</body>
</html>