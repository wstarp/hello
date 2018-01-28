<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>多媒体POS机内管系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="ie-comp">
<link rel="stylesheet" href="libs/DataTables/1.10.13/css/jquery.dataTables.min.css" />
<link rel="stylesheet" href="libs/amazeUI/2.7.2/css/app.css" />
</head>
<body>
	<!--[if lte IE 9]>
		<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
	  以获得更好的体验！</p>
	<![endif]-->
	<!-- content start -->
	<div class="admin-content-body am-margin">
		<!-- 标题 -->
		<div class="am-cf am-padding-bottom-0">
			<strong class="am-text-primary am-text-lg">${modleCCName}</strong><small></small>
		</div>
		<hr>
		<!-- 表格 -->
		<div class="am-g">
			<div id="btn">
				<div class="am-btn-group am-btn-group-xs">
					<button onclick="showEdit()" class="am-btn am-btn-secondary" type="button">
						<span class="am-icon-plus"></span> 新增
					</button>
					<button id="removebtn" class="am-btn am-btn-warning" type="button">
						<span class="am-icon-trash-o"></span> 删除
					</button>
				</div>
			</div>
			<table id="dataTables" data-page-length='10' class="display cell-border compact" >
				<thead>
					<tr>
						<th style="width: 10px">
							<div class="am-center am-margin-left-lg" title="全选">
								<label class="am-checkbox"> <input type="checkbox" class="checkall"  data-am-ucheck>
								</label>
							</div>
						</th>
						<#list tableList as list>
							<th>${list.cloComment}</th>
						</#list>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- content end -->
	<!-- 删除窗口 -->
	<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
		<div class="am-modal-dialog">
			<div class="am-modal-bd">确定要删除选中的数据吗？</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>取消</span> <span class="am-modal-btn" data-am-modal-confirm>确定</span>
			</div>
		</div>
	</div>
	<!-- 模态窗口  modal start -->
	<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
		<div class="am-modal-dialog  ryt-automodlewin">
			<div class="am-modal-hd">
				<a href="javascript: void(0)" class="am-close" data-am-modal-close>&times;</a>
			</div>
			<div class="am-modal-bd">
				<div class="am-cf">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">管理${modleCCName}</strong> / <small id="${modleName?lower_case}title"></small>
					</div>
				</div>
				<hr>
				<form class="am-form" id="${modleName?lower_case}-form" action="">
					<div class="am-tabs am-margin" data-am-tabs>
						<div class="am-tabs-bd" style="border: 1px solid #DDDDDD; font-size: 15px;">
							<div class="am-tab-panel am-fade  am-in  am-active">
									<input id="id" name="id" type="hidden">
									<div class="am-g am-margin-top">
										<#list tableList as list>
											<div class="am-u-md-6 am-margin-top-xs">
												<div class="am-u-md-5 am-text-right">${list.cloComment}</div>
												<div id="" class="am-u-md-7">
													<input id="${list.colName?lower_case}" name="${list.colName?lower_case}" type="text" class="am-input-sm am-form-field"  required>
												</div>
											</div>
										</#list>
								</div>
							</div>
						</div>
					</div>
					<div class="am-margin">
						<button type="button" onclick="toSubmit()" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
						<button type="reset" class="am-btn am-btn-primary am-btn-xs" data-am-modal-close>放弃保存</button>
					</div>
				</form>

			</div>
		</div>
	</div>
	<!-- modal end -->
</body>
<!-- [if lt IE 9]>
			<script src="libs/jquery/jquery-1.10.1.min.js"></script>
			<script src="libs/other/modernizr.js"></script>
			<script src="libs/amazeUI/2.7.2/js/amazeui.ie8polyfill.min.js"></script>
		<![endif] -->
<!-- [if (gte IE 9)|!(IE)]><! -->
<script src="libs/amazeUI/2.7.2/js/jquery.min.js"></script>
<!-- <![endif] -->
<script src="libs/amazeUI/2.7.2/js/amazeui.min.js"></script>
<script src="libs/DataTables/1.10.13/js/jquery.dataTables.min.js"></script>
<script src="libs/DataTables/jquery.dataTables.extend.js"></script>
<script src="libs/DataTables/1.10.13/js/app.js"></script>
<script src="js/${foder}/${modleName?lower_case?cap_first}.js"></script>

</html>