<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="/common/inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;

	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看用户信息',
			url : '${pageContext.request.contextPath}/admin/admin/adminShowPage/' + id
		});
	};


	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : '${pageContext.request.contextPath}/admin/chargeAdmin/dataGrid',
			striped : true,
			rownumbers : true,
			nowrap : false,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'desc',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '100',
				title : '编码*',
				field : 'chargeNo',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '150',
				title : '创建时间',
				field : 'createTime',
				sortable : true
			}, {
				width : '100',
				title : '充电点名称',
				field : 'name',
				sortable : true,
				resizable : true
			}, {
				width : '100',
				title : '所属区域',
				field : 'area',
				sortable : true,
				resizable : true
			}, {
				width : '200',
				title : '地址',
				field : 'address',
				sortable : true,
				resizable : true
			}, {
				width : '50',
				title : '交流电已建',
				field : 'acBuilded',
				sortable : true,
				resizable : true
			}, {
				width : '50',
				title : '交流电在建',
				field : 'acBuilding',
				sortable : true,
				resizable : true
			}, {
				width : '50',
				title : '直流电已建',
				field : 'dcBuilded',
				sortable : true,
				resizable : true
			}, {
				width : '50',
				title : '直流电在建',
				field : 'dcBuilding',
				sortable : true,
				resizable : true
			}, {
				width : '50',
				title : '经度',
				field : 'longitude',
				sortable : true,
				resizable : true
			}, {
				width : '50',
				title : '纬度',
				field : 'latitude',
				sortable : true,
				resizable : true
			}, {
				width : '100',
				title : '开发时间',
				field : 'beginTime',
				sortable : true,
				resizable : true
			}, {
				width : '100',
				title : '服务电话',
				field : 'tel',
				sortable : true,
				resizable : true
			}, {
				width : '100',
				title : '使用标准',
				field : 'standardName',
				sortable : true,
				resizable : true
			}, {
				width : '100',
				title : '收费标准',
				field : 'feeStandard',
				sortable : true,
				resizable : true
			}, {
				width : '100',
				title : '详情',
				field : 'detail',
				sortable : true,
				resizable : true
			}, {
				width : '100',
				title : '建设单位',
				field : 'depart',
				sortable : true,
				resizable : true
			}, {
				title : '操作',
				field : 'action',
				width : '90',
				formatter : function(value, row) {
					var str = '';
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>