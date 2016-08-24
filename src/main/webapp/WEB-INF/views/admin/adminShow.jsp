<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="/common/inc.jsp"></jsp:include>
<script type="text/javascript">
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
			url = '${pageContext.request.contextPath}/admin/admin/show';
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条

			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			submitNow($dialog, $grid, $pjq);
		}
	};
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>用户基本信息</legend>
			<input type="hidden" name="id" value="<%=id%>" />
			<table class="table" style="width: 100%;">
					<tr>
					<th>登陆名称</th>
					<td><input name="loginName" value="${admin.loginName}" readonly="readonly" /></td>
					<th>姓名</th>
					<td><input name="name" value="${admin.name}" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>手机</th>
					<td><input name="telphone" value="${admin.telphone}" readonly="readonly"/></td>
					<th>email</th>
					<td><input name="email" value="${admin.email}" readonly="readonly"/></td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>