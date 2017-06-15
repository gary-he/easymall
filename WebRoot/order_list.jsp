<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ app }/css/orderList.css" rel="stylesheet" type="text/css">
</head>
<body style="text-align:center;">
	<c:forEach items="${ oinList }" var="info">
		<dl class="Order_information">
			<dt>
				<h3>订单信息</h3>
			</dt>
			<dd>
				订单编号：${ info.order.id }<br />
				 下单时间：${ info.order.orderTime }<br /> 
				 订单金额：${ info.order.money }<br /> 
				 支付状态：
				 	<c:if test="${ info.order.payState==0 }">
						<font color="red">未支付</font>&nbsp;&nbsp;&nbsp;
						<a href="/orderAction_delOrder.action?id=${oi.order.id }"><img src="img/orderList/sc.jpg" width="69" height="19"></a> 
				 		<a href="/forwardAction_forward.action?path=pay.jsp&id=${oi.order.id }&money=${oi.order.money }"> <img src="img/orderList/zx.jpg" width="69" height="19"></a><br /> 
					</c:if>
					<c:if test="${ info.order.payState==1 }">
						<font color="blue">已支付</font>
					</c:if>
				 收货地址：${ info.order.receiverInfo }<br/> 
				支付方式：在线支付
			</dd> 
		</dl>
	
		<table width="1200" border="0" cellpadding="0"
			cellspacing="1" style="background:#d8d8d8;color:#333333">
			<tr>
				<th width="276" height="30" align="center" valign="middle" bgcolor="#f3f3f3">商品图片</th>
				<th width="247" align="center" valign="middle" bgcolor="#f3f3f3">商品名称</th>
				<th width="231" align="center" valign="middle" bgcolor="#f3f3f3">商品单价</th>
				<th width="214" align="center" valign="middle" bgcolor="#f3f3f3">购买数量</th>
				<th width="232" align="center" valign="middle" bgcolor="#f3f3f3">总价</th>
			</tr>
		<c:forEach items="${ info.map }" var="item">
			<tr>
				<td align="center" valign="middle" bgcolor="#FFFFFF">
					<img src="${ app }/ProdImgServlet?imgurl=${ item.key.imgurl }" width="90" height="105">
				</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${ item.key.name }</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${ item.key.price }元</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${ item.value }件</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${ item.key.price*item.value }元</td>
			</tr>
		</c:forEach>
		</table>
		<div class="Order_price">${ info.order.money }元</div>
	</c:forEach>
</body>
</html>
