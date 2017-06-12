<%@page import="com.sun.org.glassfish.gmbal.IncludeSubclass"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ app }/css/prodInfo.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ app }/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${ app }/js/prodInfo.js"></script>
</head>
<body>
	<input type="hidden" name="path" value="${ app }"/>
	<!--静态包含 -->
	<%@ include file="/_head.jsp" %>
	<!--动态包含
 	<c:import url="${ app }/_head.jsp"></c:import> 
 	-->
	<div id="warp">
		<div id="left">
			<div id="left_top">
				<img src="${ app }/ProdImgServlet?imgurl=${ prod.imgurl }"/>
			</div>
			<div id="left_bottom">
				<img id="lf_img" src="${ app }/img/prodInfo/lf.jpg"/>
				<img id="mid_img" src="${ app }/ProdImgServlet?imgurl=${ prod.imgurl }" width="60px" height="60px"/>
				<img id="rt_img" src="${ app }/img/prodInfo/rt.jpg"/>
			</div>
		</div>
	<form action="javascript:void(0)"  method="post">
		<div id="right">
			<div id="right_top">
				<span id="prod_name">${ prod.name } <br/></span>
				<br>
				<span id="prod_desc">${ prod.description }<br/></span>
			</div>
			<div id="right_middle">
				<span id="right_middle_span">
					EasyMall 价：
				<span class="price_red">￥${ prod.price }
				<br/>
			    运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：满 100 免运费<br />
			    服&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：由EasyMall负责发货，并提供售后服务<br />
			    购买数量：
	            <a href="javascript:void(0)" id="delNum" onclick="">-</a>
	            <input type="text" id="buyNumInp" name="buyNum" value="1">
		        <a href="javascript:void(0)" id="addNum" onclick="">+</a>
			</div>
			<div id="right_bottom">
				<input type="hidden" name="pid" value="${ prod.id }"/>
				<input id="addCart" class="add_cart_but" type="submit" value=""/>
			</div>
		</div>
	</form>
	</div>
	<%@ include file="/_foot.jsp" %>
<!-- 	<c:import url="${ app }/_foot.jsp"></c:import> -->
</body>
</html>