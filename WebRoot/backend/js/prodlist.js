$(function(){
	var path=$("#path").val();
	$(".pnum").blur(function(){
		//获取代表当前input的对象
		var _self=this;
		//获取商品数量
		var pnum=$(this).val();
		//从隐藏域中获取旧值
		var oldPnumObj=$(this).next("input");
		var oldPnum=$(this).next("input").val();
		//检查格式是否为合法数值
		var reg=/^0$|^[1-9][0-9]*$/;
		if(reg.test(pnum)){
			//判断输入框的值是否有变化
			if(pnum!=oldPnum){
				//获取商品id,根据id修改商品数量
				var pid=$(this).attr("id");
				//利用ajax异步修改商品库存数量
				$.post(path+"/AjaxProdPnumEditServlet",{
					"pid":pid,
					"pnum":pnum
				},function(result){
					if("true"==result){
						//更新隐藏域中的值
						oldPnumObj.val(pnum);
						alert("修改成功！");
					}else{
						alert("修改失败！");
						//恢复旧值
						$(_self).val(oldPnum);
					}
				});
				
			}
		}else{
			alert("请输入正整数！");
			//输入框恢复到修改之前的商品数量
			$(this).val(oldPnum);
		}
	});
	
	
	//绑定删除商品点击事件
	$(".del").click(function(){
		var _self=this;
		//提示确认删除,点“确定”执行删除操作
		if(confirm("确定删除该商品吗？")){
			//获取对应商品的id
			var pid=$(_self).parents("tr").find(".pnum").attr("id");
			//通过异步ajax将商品id提交到对应servlet删除商品
			$.post(
				path+"/AjaxProdDelServlet", 
				{"pid": pid}, 
				function(result) {
					if("true"==result){
						alert("删除成功！");
						$(_self).parents("tr").remove();
					}else{
						alert("删除失败！");
					}
			});

		}
	});
})