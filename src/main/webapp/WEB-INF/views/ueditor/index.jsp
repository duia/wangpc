<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%    
String path = request.getContextPath();    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
</head>
<body>
<div class="content" id="content1"><script id="editor1" type="text/plain" style="width:550px;height:100px;">内容1</script></div>
<div class="content" id="content2">内容2</div>
<div class="content" id="content3">内容3</div>
<div class="content" id="content4">内容4</div>
<div class="content" id="content5">内容5</div>
<script type="text/javascript">window.PROJECT_CONTEXT = "${path}/";</script>
<script src="/static/plugins/UEditor/ueditor.config.js"></script>
<script src="/static/plugins/UEditor/ueditor.all.js"></script>
<script src="/static/plugins/UEditor/lang/zh-cn/zh-cn.js"></script>
<script src="/static/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
//alert(document.cookie.username);
var ue = UE.getEditor('editor1');
ue.ready(function(){
    //阻止工具栏的点击向上冒泡
    $(this.container).click(function(e){
        e.stopPropagation()
    })
});
$('body').delegate('.content', 'click', function(e){
    var $target = $(e.target);
    var content = $target.html();
    console.log(content);
    var currentParnet = $(ue.container).parent().parent();
    var currentContent = ue.getContent();
    $target.html('');
    $target.append($(ue.container).parent());
    ue.reset();
    setTimeout(function(){
        ue.setContent(content);
    },500)
    $(currentParnet).html(currentContent);
})
</script>
</body>
</html>	