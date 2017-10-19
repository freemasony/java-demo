<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top" data-selected-multi="true">
        <thead>
            <tr>
                <th data-order-field="Id" align="center">id</th>
                <th data-order-field="productId" >商品Id</th>
                <th data-order-field="productName">名称</th>
                <th data-order-field="onlineTime">上线时间</th>
                <th data-order-field="offlineTime">下线时间</th>
                <th data-order-field="status">状态</th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${products.content}" var="product">
            <tr data-id="${product.id}">
              	<td>${product.id}</td>
                <td>${product.productId}</td>
                <td>${product.productName}</td>
                <td>
				<fmt:formatDate value="${product.onlineTime}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
				</td>
                <td>
				<fmt:formatDate value="${product.offlineTime}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
				</td>
				 <td>
				   <c:forEach items="${statusMap}" var="status">
                        <c:if test="${product.status == status.key}">${status.value}</c:if>
                    </c:forEach>
				 </td>
                <td>
                    <a href="form.html?id=1" class="btn btn-green" data-toggle="navtab" data-id="form" data-reload-warn="本页已有打开的内容，确定将刷新本页内容，是否继续？" data-title="编辑-孙悟空">编辑</a>
                    <a href="${ctx}/mytest/delete/${product.id}" class="btn btn-mini btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该行信息吗？">删</a>
                </td>
            </tr>
            </c:forEach> 
        </tbody>
    </table>
</div>


 
 