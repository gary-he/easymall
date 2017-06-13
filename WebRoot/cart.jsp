<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>我的购物车</title>
 	<link href="${ app }/css/cart.css" rel="stylesheet" type="text/css">
 	<script type="text/javascript" src="${ app }/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${ app }/js/cart.js"></script>
	</head>
<body>
<input type="hidden" name="path" value="${ app }"/>
<%@include file="/_head.jsp" %>
		<div class="warp">
			<span>${ msg }</span>
			<!-- 标题信息 -->
			<div id="title">
				<input name="allC" type="checkbox" value="" onclick=""/>
				<span id="title_checkall_text">全选</span>
				<span id="title_name">商品</span>
				<span id="title_price">单价（元）</span>
				<span id="title_buynum">数量</span>
				<span id="title_money">小计（元）</span>
				<span id="title_del">操作</span>
			</div>
			<!-- 购物信息 -->
	<c:set var="money" value="0"/>
	<c:forEach items="${ cart }" var="entry">
			<div class="prods">
				<input name="prodC" type="checkbox" value="" onclick=""/>
				<img src="${ app }/ProdImgServlet?imgurl=${ entry.key.imgurl }" width="90" height="90" />
				<span class="prods_name">${ entry.key.name }</span>
				<span class="prods_price">${ entry.key.price }</span>
				<span class="prods_buynum">
				    <input type="hidden" id="hid_${ entry.key.id }" value="${ entry.value }"/> 
					<a href="javascript:void(0)" class="delNum" >-</a>
					<input id="${ entry.key.id }" class="buyNumInp" type="text" value="${ entry.value }" oldNum="${ entry.value }" >
					<a href="javascript:void(0)" class="addNum" >+</a>
				</span>
				<span class="prods_money">${ entry.key.price*entry.value }</span>
				<c:set var="money" value="${ money+entry.key.price*entry.value }"/>
				<span class="prods_del"><a href="javascript:void(0)" class="del" pid="${ entry.key.id }">删除</a></span>
			</div>
	</c:forEach>
			<!-- 总计条 -->
			<div id="total">
				<div id="total_1">
					<input name="allC" type="checkbox" value=""/> 
					<span>全选</span>
					<a id="del_a" href="#">删除选中的商品</a>
					<span id="span_1">总价：</span>
					<span id="span_2">￥${ money }</span>
				</div>
				<div id="total_2">	
					<a id="goto_order" href="${ app }/order_add.jsp">去结算</a>
				</div>
			</div>
		</div>
<%@include file="/_foot.jsp" %>
</body>
</html>