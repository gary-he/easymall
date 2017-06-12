<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ app }/css/prodList.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="/_head.jsp" %>
	<div id="content">
		<div id="search_div">
			<form method="post" action="${ app }/ProdListServlet">
				<span class="input_span">商品名：<input type="text" name="name"/></span>
				<span class="input_span">商品种类：<input type="text" name="category"/></span>
				<span class="input_span">商品价格区间：<input type="text" name="minprice"/> - <input type="text" name="maxprice"/></span>
				<input type="submit" value="查 询">
			</form>
		</div>
		<div id="prod_content">
			<c:forEach items="${ prodList }" var="prod">
				<div class="prod_div">
					<a href="${ app }/ProdInfoServlet?pid=${ prod.id }">
						<img width="218px" height="218px" src="${ app }/ProdImgServlet?imgurl=${ prod.imgurl }"></img>
					</a>
					<div id="prod_name_div">
						<a href="${ app }/ProdInfoServlet?pid=${ prod.id }">
							${ prod.name }
						</a>
						
					</div>
					<div id="prod_price_div">
						￥${ prod.price }元
					</div>
					<div>
						<div id="gotocart_div">
							<a href="${ app }/CartAddServlet?pid=${ prod.id }">加入购物车</a>
						</div>					
						<div id="say_div">
							133人评价
						</div>					
					</div>
				</div>
			</c:forEach>
		</div>
		<div style="clear: both"></div>
	</div>
	<%@ include file="/_foot.jsp" %>
</body>
</html>
