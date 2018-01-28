var ${modleName?lower_case}Table;
var ids;
$(document).ready(
		function() {
			${modleName?lower_case}Table = $("#dataTables").DataTable(
					{
						dom : "iftlp",
						order : [ [ 1, "asc" ] ],
						language : {
							url : "libs/DataTables/1.10.13/language.lang"
						},
						pagingType : "full_numbers_no_ellipses",
						ajax : {
							url : "${modleName?lower_case}/get${modleName}.json",
							type : "GET",
							dataSrc : "",
						},
						columnDefs : [
								{
									className : "ryt-center",
									data : null,
									render : function(data, type, row) {
										return '<input type="checkbox"  class="checkchild"  value="' + data.id + '" />';
									},
									orderable : false,
									searchable : false,
									targets : [ 0 ]
								},
								<#assign iidd=1/>
								<#list tableList as list>
								{
									data : "${list.colName?lower_case}",
									targets : [${list_index + 1}]
								},
								<#assign iidd = "${list_index + 2}"/>
								</#list>
								{
									data : null,
									className : "center",
									render : function(data, type, row) {
										return "<div class=\"am-btn-group am-btn-group-xs\">" 
												+ "<button onclick=\"showEdit('" + data.id + "')\" " 
												+ "class=\"am-btn am-btn-default am-btn-xs am-text-secondary\">"
												+ "<span class=\"am-icon-pencil-square-o\"></span> 编辑" 
												+ "</button>" 
												+ "<button onclick=\"toDelete('" + data.id + "')\" " 
												+ "class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\">"
												+ "<span class=\"am-icon-trash-o\"></span> 删除" 
												+ "</button>" + "</div>";
									},
									targets : [ ${iidd} ],
									orderable : false,
									searchable : false,
								} ],

					});
			
			// 批量删除
			$("#removebtn").click(function() {
				var checkedMenus = $(".checkchild:checked");
				if (checkedMenus.length == 0) {
					return;
				}

				// 选中的id之间以逗号分割
				var selectedIds = "";
				$.each(checkedMenus, function(index, data) {
					selectedIds += data.value + ",";
				});

				// 去掉最后一个逗号
				selectedIds = selectedIds.substring(0, selectedIds.length - 1);
				ids = selectedIds;
				toDelete(ids);
			});

			// 全选
			$(".checkall").click(function() {
				var check = $(this).prop("checked");
				$(".checkchild").prop("checked", check);
			});

			$(".checkallsub").click(function() {
				var check = $(this).prop("checked");
				$(".checkchildsub").prop("checked", check);
			});

			// 表单验证
			var $form = $('#${modleName?lower_case}-form');

			$form.validator({
				// 表单提交的时候验证
				validateOnSubmit : true,
				
				submit : function() {
					var formValidity = this.isFormValid();
					if (formValidity) {
						var saveValue = $("#${modleName?lower_case}-form").serialize();
						$.ajax({
							url : "${modleName?lower_case}/save${modleName}.json",
							dataType : "json",
							data : saveValue,
							async : false,
							type : "POST",
							success : function(data) {
								if (data.code == 1) {
									alert("保存成功！");
									$("#doc-modal-1").modal("close");
									${modleName?lower_case}Table.ajax.reload(null, true);
								} else {
									alert("保存失败！ ");
								}
							}
						});
					}
					// 防止传统表单提交
					return false;
				}
			});

			var validator = $form.data('amui.validator');

			$form.on('focusin focusout', '.am-form-error input', function(e) {
				if (e.type === 'focusin') {
					var $this = $(this);
					var offset = $this.offset();
					var msg = $this.data('foolishMsg') || validator.getValidationMessage($this.data('validity'));

				} else {
					// 去除验证样式
					$tooltip.hide();

				}
			});
		});

// 显示编辑页
function showEdit(id) {
	if (id) {
		$("#${modleName?lower_case}title").html("修改${modleCCName}");
		$.ajax({
			url : "${modleName?lower_case}/get${modleName}InfoById.json",
			dataType : "json",
			data : {"id":id},
			async : false,
			type : "POST",
			success : function(data) {
				<#list tableList as list>
					$("#${list.colName?lower_case}").val(data.${list.colName?lower_case});
				</#list>
				$("#doc-modal-1").modal({
					closeViaDimmer : 0,
					cancelable : false
				});
			}
		});

	} else {
		$("#${modleName?lower_case}title").html("新增${modleCCName}");
		$("#${modleName?lower_case}-form")[0].reset();
		$("#doc-modal-1").modal({
			closeViaDimmer : 0,
			cancelable : false
		});
	}

}

// 提交表单
function toSubmit() {
	var checkedsubMenus = $(".checkchildsub:checked");
//	if (checkedsubMenus.length == 0) {
//		return;
//	}

	// 选中的id之间以逗号分割
	var selectedsubIds = "";
	$.each(checkedsubMenus, function(index, data) {
		selectedsubIds += data.value + ",";
	});

	// 去掉最后一个逗号
	selectedsubIds = selectedsubIds.substring(0, selectedsubIds.length - 1);
	subids = selectedsubIds;
	$("#resa").val(subids);
	$("#${modleName?lower_case}-form").submit();
}

function toDelete(id) {
	ids = id;
	$("#my-confirm").modal({
		relatedTarget : this,
		onConfirm : function(options) {
			$.ajax({
				url : "${modleName?lower_case}/delete.json",
				dataType : "json",
				data : "ids=" + ids,
				type : "POST",
				success : function(data) {
					if (data == 1) {
						${modleName?lower_case}Table.ajax.reload(null, false);
					} else {
						alert("删除失败！")
					}
				}
			});
		},
		onCancel : function() {
		}
	});
}
