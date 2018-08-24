$.extend({
	withub : {
		version : "1.0",
		messager : {}
	}
});

function guidDialogId() {
	var s4 = function() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	};
	return "Dialog-"
			+ (s4() + s4() + "-" + s4() + "-" + s4() + "-" + s4() + "-" + s4()
					+ s4() + s4());
}

$.extend($.withub, {
	orgDialog : /**
	 *
	 *创建机构对话框
	 *使用方法：
	 *$.withub.orgDialog({
	 *			id:"orgDialog",
	 *          获取部门下所有人员，例如：获取当前登录人员所在部门下的所有人员
	 *          参数：orgId(不允许为空)   部门Id
	 *			url:"${ctx}/commonData/orgUserTreeByOrgId?orgId=4028a8d546f69be10146f69f23850001"
	 *          获取组织机构数
	 *			url:"${ctx}/commonData/orgTree"
	 *          根据机构深度获取部门，例如：获取层深为2的组织机构
	 *          参数：treeDeep，树的深度，从1开始
	 *			url:"${ctx}/commonData/deepOrgTree?treeDeep=2"
	 *          根据机构深度获取部门与该部门的子部门，例如：获取层深为1的组织机构以及它的子机构
	 *          参数：treeDeep，树的深度，从1开始
	 *			url:"${ctx}/commonData/deepAllOrgTree?treeDeep=1"
	 *          获取当前部门的子部门以及人员
	 *          参数：parentId（可选），parentId为空，默认是root
	 *			url:"${ctx}/commonData/orgUserTree"
	 *          获取机构岗位树
	 *          参数：parentId（可选），parentId为空，默认是root
	 *		    url:"${ctx}/commonData/orgPositionTree"
	 *          根据角色选择人员
	 *          参数：roleIds[],多个角色用","分割，例如：roleIds=1,2,3,4
	 *			url:"${ctx}/commonData/roleUserTree?roleIds=4028a8d546db4a650146db4b6f250000"
	 *          根据岗位选择人员
	 *          参数：positionIds[]
	 *			url:"${ctx}/commonData/positionUserTree?positionIds=4028a8d546f69be10146f6a01e320003,4028a8d546f69be10146f6a098dd0004"
	 *		},function(resultNode){
	 *			alert(JSON.stringify(resultNode));
	 *		});
	 * @param options  
	 * @param callback(resultNodes)
	 */
	function(options, callback) {
		var dialogId = guidDialogId();
		if (options.id)
			dialogId = options.id;
		var opts = $.extend({
			title : "选择机构",
			height : 400,
			width : 250,
			closable : true,
			modal : true,
			mask : true,
			cascadeCheck : true,
			onlyLeafCheck : false,
			checkbox : true,
			closed : true,
			buttons : [ {
				iconCls : "icon-ok",
				text : "确定",
				plain : true,
				handler : function() {
					var nodes = tree.tree("getChecked");
					var resultNode = [];
					$.each(nodes, function(i, node) {
						if (tree.tree("isLeaf", node.target)) {
							resultNode.push(node);
						}
					});
					if (callback)
						callback(resultNode);
					$("#" + dialogId).dialog("close");
				}
			}, {
				iconCls : "icon-cancel",
				text : "关闭",
				plain : true,
				handler : function() {
					$("#" + dialogId).dialog("close");
				}
			} ],
			onBeforeClose : function() {
				$(this).find(".combo-f").each(function() {
					var panel = $(this).data().combo.panel;
					panel.panel("destroy");
				});
				$(this).empty();
			},
			onMove : function(left, right) {
				$('.validatebox-tip').remove();
			}
		}, options || {});
		var $dialog = $("<div/>").css("padding", "5px").appendTo($("body"));
		var $treeLayout = $("<div/>").attr({
			"region" : "center",
			"border" : "false"
		}).appendTo(
				$("<div/>").attr("class", "easyui-layout").attr("fit", "true")
						.appendTo($dialog));
		var $tree = $("<ul/>").attr("id", "orgTree").appendTo($treeLayout);
		var dialog = $dialog.dialog($.extend(opts, {
			onClose : function() {
				$dialog.dialog('destroy');
			},
			onOpen : function() {

			}
		})).attr('id', dialogId);
		var tree = $tree.tree({
			url : options.url,
			animate : true,
			checkbox : opts.checkbox,
			cascadeCheck : opts.cascadeCheck,
			onlyLeafCheck : opts.onlyLeafCheck,
			line : true
		});
		dialog.dialog("open");
	}
});

$.extend($.withub, {
	ajax : function(options, callback) {
		var opts = $.extend({
			type : "POST",
			timeout : 60000,
			dataType : "json",
			cache : false,
			progress : true,
			error : function(data) {
				$.messager.show({
					title : '超时信息',
					msg : '请求超时！'
				});
			},
			success : function(sinfo) {
				if (sinfo.schema == "msg" || sinfo.schema == "all") {
					$.withub.messager.show(sinfo.data.msgData);
				}

				if (callback) {
					var result = false;
					if (sinfo.data.msgData.msgCode[0] == "N")
						result = true;

					callback(result, sinfo.data);
				}
			},
			complete : function() {
				if (this.progress)
					$.messager.progress('close');
			}
		}, options || {});
		if (opts.progress)
			$.messager.progress();
		$.ajax(opts);
	}
});

$.extend($.withub, {
	submit : function(options) {
		var opts = $.extend({
			type : "POST",
			timeout : 60000,
			dataType : "json",
			cache : false,
			error : function(data) {
				$.messager.show({
					title : '超时信息',
					msg : '请求超时！'
				});
			},
			success : function(sinfo) {
				if (sinfo.schema == "msg" || sinfo.schema == "all") {
					$.withub.messager.show(sinfo.data.msgData);
				}

				if (this.callback) {
					if (sinfo.data.msgData.msgCode[0] == "N") {
						this.callback(sinfo.data);
					} else {
						$.withub.messager.show(sinfo.data.msgData);
					}
				}
			},
			complete : function() {
				$.messager.progress('close');
			},
			callback : function(data) {
			}
		}, options || {});

		$.messager.progress();
		$.ajax(opts);
	}
});

$
		.extend(
				$.withub.messager,
				{
					show : function(params) {
						var content = params.msg;
						if (params.msgCode[0] == 'E') {
							content = '<div><h3>' + params.msg + '<h3></div>';
							content += '<div style="margin-top: 5px;">参考代码：'
									+ params.msgCode + '</div>';
							content += '<div style="float:right;margin-bottom: 5px;"><a href="#" style="color:red;" onclick="$(\'#exception\').window(\'open\');">查看异常信息</a></div>';

							var dlg = document.getElementById('exception');
							if (dlg) {
								dlg.innerHTML = params.exception;
							} else {
								dlg = document.createElement('div');
								dlg.id = "exception";
								dlg.setAttribute("title", "异常信息");
								dlg.setAttribute("class", "easyui-window");
								dlg
										.setAttribute("style",
												"width:480px;height:320px;padding:10px;");
								dlg.innerHTML = params.exception;

								document.body.appendChild(dlg);

								$('#exception').window({
									collapsible : false,
									minimizable : false,
									closed : true,
									modal : true
								});
							}
						}

						$.messager.show({
							title : "消息",
							msg : content,
							width : 260,
							height : 120
						});
					}
				});

$.fn.extend({
	serializeNInfo : function() {
		var json = {}, add = function(key, value) {
			value = jQuery.isFunction(value) ? value() : (value == null ? ""
					: value);
			json[key] = value;
		};

		var array = this.serializeArray();
		jQuery.each(array, function() {
			add(this.name, this.value);
		});

		return {
			data : JSON.stringify(json)
		};
	}
});

$.fn.extend({
	toJson : function() {
		var json = {}, add = function(key, value) {
			value = jQuery.isFunction(value) ? value() : (value == null ? ""
					: value);
			json[key] = value;
		};

		var array = this.serializeArray();
		jQuery.each(array, function() {
			add(this.name, this.value);
		});

		return json;
	}
});
