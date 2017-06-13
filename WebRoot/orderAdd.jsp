<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ app }/css/orderAdd.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="warp">
		<form name="form1" method="post" action="${ app }/OrderAddServlet">
			<h3>增加订单</h3>
			<div id="forminfo">
				<span class="lf">收货地址：</span> <label for="textarea"></label>
				<textarea name="textarea" id="textarea" cols="45" rows="5"></textarea>
				<br> 支付方式：<input name="" type="radio" value="" checked="checked">&nbsp;在线支付
			</div>
			<table width="1200" height="80" border="1" cellpadding="0" cellspacing="0" bordercolor="#d8d8d8">
				<tr>
					<th width="276">商品图片</th>
					<th width="247">商品名称</th>
					<th width="231">商品单价</th>
					<th width="214">购买数量</th>
					<th width="232">总价</th>
				</tr>
				<tr>
					<td>四星手机</td>
					<td>手机数码</td>
					<td>998.0元</td>
					<td>2件</td>
					<td>1996.0元</td>
				</tr>
				<tr>
					<td>阿斯蒂芬个</td>
					<td>手机数码</td>
					<td>123.0元</td>
					<td>1件</td>
					<td>123.0元</td>
				</tr>
			</table>

			<div class="Order_price">总价：2119.0元</div>

			<div class="add_orderbox">
				<input name="" type="submit" value="增加订单" class="add_order_but">
			</div>
		</form>
	</div>
</body>
</html>
