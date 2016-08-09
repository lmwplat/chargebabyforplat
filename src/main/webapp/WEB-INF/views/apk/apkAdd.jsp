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
			var code = $("#versionCode").val().trim();
			var top = $("#top").val();

			var reg = /^\d+$/;
			if(code==""){
				alert('请填写版本号！');
				return false;
			}else if(!reg.test(code)){
				alert('请填写整数！');
				return false;
			}else if(parseInt(top)>=parseInt(code)){
				alert('请上传更高版本！');
				return false;
			}

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
			url : sy.contextUrl + '/uploadServlet?path=apk',//上传文件路径
			max_file_size : '100mb',//100b, 10kb, 10mb, 1gb
			chunk_size : 0,//分块大小，小于这个大小的不分块
			unique_names : true,//生成唯一文件名
			multi_selection: false,//是否可以在文件浏览对话框中选择多个文件
			// 如果可能的话，压缩图片大小
			// resize : { width : 320, height : 240, quality : 90 },
			// 指定要浏览的文件类型
			filters : [ {
				title : 'apk files',
				extensions : 'apk'
			} ]
		});

		uploader.init();
		uploader.bind('Init', function(uploader, params) {//初始化
			//$('#filelist').html("<div>当前运行环境: " + params.runtime + "</div>");
			$('#filelist').html("");
		});
		uploader.bind('BeforeUpload', function(uploader, file) {//上传之前
			$('.plupload_delete').hide();
		});
		uploader.bind('FilesAdded', function(uploader, files) {//选择文件后
			$.each(files, function(i, file) {
				$('#filelist').empty();
				$('#filelist').append('<div id="' + file.id + '">' + file.name + ' <b>(' + plupload.formatSize(file.size) + ') </b>' + '&nbsp;<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor: pointer;" class="plupload_delete">删除</span></div>');
				//$('#f1').append('<input type="hidden" name="url" value= "'+"/apkVersion/"+ file.name+'"/>');
				//$('#f1').append('<input type="hidden" name="versionName" value="'+file.name+'"/><br/>');
				$("#versionName").val(file.name);
				//$('#f1').append('<input type="hidden" name="size" value="'+file.size+'"/><br/>');
				$("#size").val(file.size);
			});
			uploader.refresh();
			$('#pickfiles').hide();
		});

		uploader.bind('FilesRemoved', function(uploader, files) {//当文件从上传队列移除后触发
			$('#pickfiles').show();
			uploader.refresh();

		});

		uploader.bind('UploadProgress', function(uploader, file) {//上传进度
			$('#' + file.id + " b").html(file.percent + "%");
		});
		uploader.bind('Error', function(uploader, err) {//出现错误
			$('#filelist').append("<div>Error: " + err.code + ", Message: " + err.message + (err.file ? ", File: " + err.file.name : "") + "</div>");
			uploader.refresh();
		});
		uploader.bind('FileUploaded', function(uploader, file, responseObject) {//上传完毕
			$('#' + file.id + " b").html("100%");
			var result = eval('('+responseObject.response+')');
			$("#url").val(result.saveUrl);
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