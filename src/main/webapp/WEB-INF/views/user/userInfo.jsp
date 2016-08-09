<%@page import="org.aspectj.weaver.patterns.OrAnnotationTypePattern"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.liumw.lmwplat.sys.core.security.SessionSysInfo"%>
<%@ page import="com.liumw.lmwplat.sys.model.Role"%>
<%@ page import="com.alibaba.druid.sql.visitor.functions.Right"%>
<%@ page import="com.liumw.lmwplat.common.model.Tree"%>
<%@ page import="com.liumw.lmwplat.common.utils.DateUtil"%>
<%@ page import="com.liumw.lmwplat.common.utils.BeanUtils"%>
<%@ page import="com.liumw.lmwplat.common.utils.ConfigUtil"%>
<%@ page import="com.liumw.lmwplat.common.utils.StringUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="/common/inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$('#resources').tree({
			parentField : 'pid',
			data : eval("(" + resourceTreeJson + ")")
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table style="width: 100%;">
			<tr>
				<td><fieldset>
						<legend>用户信息</legend>
						<table class="table" style="width: 100%;">
							<tr>
								<th>用户ID</th>
								<th>使用IP</th>
							</tr>
							<tr>
								<th>登录名</th>
								<th>姓名</th>
							</tr>
							<tr>
								<th>创建时间</th>
								<th>最后修改时间</th>
							</tr>
						</table>
					</fieldset></td>
			</tr>
			<tr>
				<td>
					<fieldset>
						<legend>权限信息</legend>
						<table class="table" style="width: 100%;">
							<thead>
								<tr>
									<th>角色</th>
									<th>权限</th>
								</tr>
							</thead>
							<tr>
								<td valign="top">
								</td>
								<td valign="top"><ul id="rights"></ul></td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>