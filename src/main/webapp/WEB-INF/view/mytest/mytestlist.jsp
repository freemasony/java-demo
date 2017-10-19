<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript"   src="/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript"   src="/static/ueditor/ueditor.all.min.js"> </script> 
<script type="text/javascript"   src="/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<html>
<head>
    <title>注册用户管理</title>
</head>

<body>
 	<div>
 		<h3>尊敬的${user.loginName},您好！</h3> 
 		<c:if test="${not empty product}">
 			<h4>欢迎您使用${product.productName}产品！</h4>
 		</c:if>
 		<!-- <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script> -->
 		<%-- <iframe width='30%' height='20%' src='${ctx}/pdf' /> --%>
 	</div>
 	<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
   /*  var ue = UE.getEditor('editor');
    
    ue.addListener('beforeInsertImage', function (t, arg) {
    	alert(arg[0].src);
    });
    
    ue.addListener('afterUpfile', function (t, arg) {
    	alert(arg[0].url);
    }); */
    
    
    </script>
</body>
</html>
