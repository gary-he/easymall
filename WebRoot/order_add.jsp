<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ app }css/orderAdd.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="/_head.jsp" %>
	<div class="warp">
		<form name="form1" method="post" action="${ app }/OrderAddServlet">
			<h3>增加订单</h3>
			<div id="forminfo">
				<span class="lf">收货地址：</span> <label for="textarea"></label>
				<textarea name="receiverInfo" id="textarea" cols="45" rows="5"></textarea>
				<br> 支付方式：<input name="" type="radio" value="" checked="checked">&nbsp;在线支付
			</div>
			<table width="1200" height="80" border="1" cellpadding="0" cellspacing="0" bordercolor="#d8d8d8">
				<tr>
					<th width="276">商品图片</th>
					<th width="247">商品名称</th>
					<th width="231">商品单价</th>
					<th width="214">购买数量</th>
					<th width="232">小计</th>
				</tr>
				<c:set var="money" value="0"></c:set>
				<c:forEach items="${ cart }" var="entry">
					<tr>
						<td><img alt="" src="${ app }/ProdImgServlet?imgurl=${ entry.key.imgurl }" width="90" height="90"></td>
						<td>${ entry.key.name }</td>
						<td>${ entry.key.price }元</td>
						<td>${ entry.value }件</td>
						<td>${ entry.key.price*entry.value }元</td>
						<c:set var="money" value="${ money+entry.key.price*entry.value }"></c:set>
					</tr>
				</c:forEach>
			</table>

			<div class="Order_price">总价：${ money }元</div>

			<div class="add_orderbox">
				<input name="" type="submit" value="增加订单" class="add_order_but">
			</div>
		</form>
	</div>
<%@ include file="/_foot.jsp" %>
</body>
</html>
