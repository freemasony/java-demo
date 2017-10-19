<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>你好！</title>
<script type="text/javascript" src="/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/static/zclip/jquery.zclip.js"></script>
<script type="text/javascript"   src="/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript"   src="/static/ueditor/ueditor.all.min.js"> </script> 
<script type="text/javascript"   src="/static/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
	<h>你好!</h>
	<div class="login_form">
		<form action="${ctx}/login" id="login_form" method="post">
			<div class="form-group">
				<label for="username" class="t">用户名：</label> <input id="username"
					value="${username}" name="username" type="text"
					class="form-control x319 in" autocomplete="off">
			</div>
			<div class="form-group">
				<label for="password" class="t">密 码：</label> <input id="password"
					value="" name="password" type="password"
					class="form-control x319 in">
			</div>
			<div class="form-group">
				<label class="t"></label> <label for="rememberMe" class="m"><input
					id="rememberMe" name="rememberMe" type="checkbox" value="true">&nbsp;记住登陆账号!</label>
			</div>
			<div class="form-group space">
				<label class="t"></label> <input type="submit" id="login_ok"
					value="&nbsp;登&nbsp;录&nbsp;" class="btn btn-primary btn-lg">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="&nbsp;重&nbsp;置&nbsp;"
					class="btn btn-default btn-lg">
			</div>
		</form>
	</div>
	<div> 
    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
</div>
	<%-- <a href="${ctx}/mytest/download">下载</a>
	<div class="line">
		<h2>demo1 点击复制当前文本</h2>
		<a href="#none" class="copy" id="dd">点击复制我</a>
	</div>
	<div class="line">
		<h2>demo2 点击复制表单中的文本</h2>
		<a href="#none" class="copy-input">点击复制单中的文本</a> <input type="text"
			class="input" value="输入要复制的内容" />
	</div> --%>
</body>
</html>

<script type="text/javascript">
 
</script>