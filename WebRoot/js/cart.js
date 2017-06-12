$().ready(function(){
	var path=$("input[name='path']").val();
	$(".del").click(function(){
		if(window.confirm("您确定删除该商品吗？")){
			var pid=$(this).attr("pid");
			window.location.href=path+"/CartDeleteServlet?pid="+pid;
		}
	});
	
	$(".delNum").click(function(){
		//获取输入框对象
		var $inpObj=$(this).next();
		//获取商品的id
		var pid=$inpObj.attr("id");
		//获取修改前的购买数量
		var oldNum=$inpObj.val();
		//计算新的数量
		var newNum=oldNum-1;
		//判断如果小于等于0，则删除商品
		if(newNum<=0){
			window.location.href=path+"/CartDeleteServlet?pid="+pid;
		}else{
			window.location.href=path+"/CartEditServlet?pid="+pid+"&newNum="+newNum;
		}
	});
	
	$(".addNum").click(function(){
		var $inpObj=$(this).prev();
		var pid=$inpObj.attr("id");
		var oldNum=$inpObj.val();
		var newNum=parseInt(oldNum)+1;//防止“+”号进行字符串拼接运算
		window.location.href=path+"/CartEditServlet?pid="+pid+"&newNum="+newNum;
		
	});
	
	$(".buyNumInp").blur(function(){
		//输入框正则表达式
		var reg=/^[1-9][0-9]*$/;
		//获取修改前商品数量
		var oldNum=$(this).attr("oldNum");
		//获取新的商品数量
		var newNum=$(this).val();
		//获取商品id
		var pid=$(this).attr("id");
		//判断正则
		if(!reg.test(newNum)){
			alert("请输入正整数！");
			$(this).val(oldNum);
		}else{
			window.location.href=path+"/CartEditServlet?pid="+pid+"&newNum="+newNum;
		}
	});
});