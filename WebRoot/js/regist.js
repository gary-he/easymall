$(function(){
	var flag=true;
	$("input[name='username']").blur(function(){
		if(formObj.checkNull("username", "用户名不能为空")){
			formObj.checkUsername();
		}
	});
	$("input[name='password']").blur(function(){
		formObj.checkNull("password", "密码不能为空");
	});
	$("input[name='password2']").blur(function(){
		formObj.checkNull("password2", "确认密码不能为空");
		formObj.checkPassword("password", "两次密码不一致");
	});
	$("input[name='nickname']").blur(function(){
		formObj.checkNull("nickname", "昵称不能为空");
	});
	$("input[name='email']").blur(function(){
		formObj.checkNull("email", "邮箱不能为空");
		formObj.checkEmail("email", "邮箱格式不正确");
	});
	$("input[name='valistr']").blur(function(){
		formObj.checkNull("valistr", "验证码不能为空");
	});
	
	$("input[type='submit']").click(function(){
		$("form").submit(function(){
			return formObj.checkForm();
		});
	});
	
	//点击图片换一张
	$("#img").click(function(){
		var timeStr=new Date().getTime();
		var path=$("#context_path").val();
		$(this).attr("src",path+"/ValiImageServlet?time="+timeStr);
	});
	
});

var formObj={
	"checkForm":function(){
		var flag=true;
		//检查用户名是否存在
		if($("#username_msg").text()=="用户名已存在"){
			flag=false;
		}
		//1.非空检验 
		flag=this.checkNull("username","用户名不能为空")&&flag;
		flag=this.checkNull("password","密码不能为空")&&flag;
		flag=this.checkNull("password2","确认密码不能为空")&&flag;
		flag=this.checkNull("nickname","昵称不能为空")&&flag;
		flag=this.checkNull("email","邮箱不能为空")&&flag;
		flag=this.checkNull("valistr","验证码不能为空")&&flag;
		
		//2.两次密码是否一致
		flag=this.checkPassword("password","两次密码不一致")&&flag;
		//3.邮箱格式是否正确
		flag=this.checkEmail("email","邮箱格式不正确")&&flag;
		
		//重新检查用户名是否存在
		if(formObj.checkNull("username", "用户名不能为空")){
			formObj.checkUsername();
		}
		return flag;
	},
	"checkNull":function(name,msg){
		var value=$("input[name='"+name+"']").val();
		//先清空上次提示信息，再进行checkNull判断
		this.setMsg(name,"");
		
		if($.trim(value)==""){
			//设置提示信息
			this.setMsg(name, msg);
			return false;
		}
		return true;
	},
	"checkPassword":function(name,msg){
		
		var psw1=$("input[name='"+name+"']").val();
		var psw2=$("input[name='"+name+"2']").val();
		
		this.setMsg(name+"2","");
		this.checkNull(name+"2", "确认密码不能为空");
		
		if($.trim(psw1)!=""&&$.trim(psw2)!=""){
			
			if(psw1 != psw2){
				this.setMsg(name+"2", msg);
				return false;
			}
		}
		return true;
	},
	"checkEmail":function(name,msg){
		var email=$("input[name='"+name+"']").val();
		
		this.setMsg(name, "");
		this.checkNull(name, "邮箱不能为空");
		
		if($.trim(email)!=""){
			var reg=/^\w+@\w+(\.\w+)+$/;
			if(!reg.test(email)){
				this.setMsg(name, msg);
				return false;
			}
		}
		return true;
	},
	"checkUsername":function(){
		var username=$("input[name='username']").val();
		var path=$("#context_path").val();
		$("#username_msg").load(path+"/AjaxCheckUsernameServlet",{"username":username});
	},
	"setMsg":function(name,msg){
		$("input[name='"+name+"']").nextAll("span").html(msg).css("color","red");
	}
};

