$().ready(function(){
	var pid=$("input[name='pid']").val();
	var path=$("input[name='path']").val();
	$("#addCart").click(function(){
		window.location.href=path+"/CartAddServlet?pid="+pid;
	});
});