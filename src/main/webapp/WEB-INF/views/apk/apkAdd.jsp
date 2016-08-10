<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	$(function() {

		parent.$.messager.progress({
			text : '数据加载中....'
		});

		parent.$.messager.progress('close');

	});

	var uploader;//上传对象
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {

			if(uploader.files.length == 0){
				alert('请选择至少一个文件进行上传！');
				return false;
			}
			var top = $("#top").val();

			var reg = /^\d+$/;

			if (uploader.files.length > 0) {// 判断队列中是否有文件需要上传
				uploader.start();
				uploader.bind('StateChanged', function() {// 在所有的文件上传完毕时，提交表单
					if (uploader.files.length !=uploader.total.uploaded) {
						alert("上传文件失败！");
						return  false;
					}else{
						//setTimeout(function(){
						var url;
						url = '${contextPath}/admin/apkVersionAdmin/addApk';
						$.post(url, sy.serializeObject($('form')), function(result) {
							if (result.success) {
								$pjq.messager.alert('提示', result.msg, 'success');
								$grid.datagrid('load');
								$dialog.dialog('destroy');
							} else {
								$pjq.messager.alert('提示', result.msg, 'error');
							}
						}, 'json');
					}
				});
			}
		}
	};




	$(function() {

		uploader = new plupload.Uploader({
			browse_button : 'pickfiles',//选择文件的按钮
			container : 'container',//文件上传容器
			runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
			//flash_swf_url : '${pageContext.request.contextPath}/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
			//silverlight_xap_url : '${pageContext.request.contextPath}/jslib/plupload_1_5_7/plupload/js/plupload.silverlight.xap',//silverlight环境路径设置
			url : '${pageContext.request.contextPath}/uploadServlet',//上传文件路径
			max_file_size : '3gb',//100b, 10kb, 10mb, 1gb
			chunk_size : '1mb',//分块大小，小于这个大小的不分块
			unique_names : true,//生成唯一文件名
			// 如果可能的话，压缩图片大小
			// resize : { width : 320, height : 240, quality : 90 },
			// 指定要浏览的文件类型
			filters : [ {
				title : 'Image files',
				extensions : 'jpg,gif,png'
			}, {
				title : 'Zip files',
				extensions : 'apk,7z'
			} ]
		});
		uploader.bind('Init', function(up, params) {//初始化
			//$('#filelist').html("<div>当前运行环境: " + params.runtime + "</div>");
			$('#filelist').html("");
		});
		uploader.bind('BeforeUpload', function(uploader, file) {//上传之前
			$('.plupload_delete').hide();
		});
		uploader.bind('FilesAdded', function(up, files) {//选择文件后
			$.each(files, function(i, file) {
				$('#filelist').append('<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b>' + '&nbsp;<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor: pointer;" class="plupload_delete">删除</span></div>');
			});
			up.refresh();
		});
		uploader.bind('UploadProgress', function(up, file) {//上传进度
			$('#' + file.id + " b").html(file.percent + "%");
		});
		uploader.bind('Error', function(up, err) {//出现错误
			$('#filelist').append("<div>Error: " + err.code + ", Message: " + err.message + (err.file ? ", File: " + err.file.name : "") + "</div>");
			up.refresh();
		});
		uploader.bind('FileUploaded', function(up, file, info) {//上传完毕
			$('#' + file.id + " b").html("100%");

			var response = $.parseJSON(info.response);
			if (response.status) {
				$('#f1').append('<input type="hidden" name="fileUrl" value="'+response.fileUrl+'"/>');
				$('#f1').append('<input type="hidden" name="fileName" value="'+file.name+'"/><br/>');
			}
		});
		uploader.init();
		$('#uploadfiles').click(function(e) {
			uploader.start();
			e.preventDefault();
		});
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>APK基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>版本号名称</th>
					<td><input name="versionName" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>描述</th>
					<td><textarea name="description" rows="5" cols="10" style="width:200px;height:100px"> </textarea></td>
				</tr>
				<tr>
					<th>APK上传</th>
					<td><div id="container">
						<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom'">选择文件</a>
						<div id="filelist"></div>
					</div>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>