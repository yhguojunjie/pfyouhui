//﻿var jq = jQuery.noConflict();
/**
 * 增删改工具栏
 */
window.onerror = function() {
	return true;
};
var iframe;// iframe操作对象
var win;// 窗口对象
var gridname = "";// 操作datagrid对象名称
// update-begin--Author:chenxu Date:20130402 for：父窗口将子窗口遮盖
var windowapi = frameElement.api, W = windowapi.opener;// 内容页中调用窗口实例对象接口
// update-end--Author:chenxu Date:20130402 for：父窗口将子窗口遮盖
function upload(curform) {
	upload();
}
/**
 * 添加事件打开窗口
 * 
 * @param title 弹出框名称
 * @param addurl//目标页面地址
 * @param gname datagrid name
 * @param width 弹框宽度
 * @param height 弹框高度
 * 
 */
function add(title,addurl,gname,width,height) {
	gridname = gname;
	createwindow(title, addurl,width, height);
}
function updateEntity(title, url, id,width, height) {
	// update-begin--Author:tanghong Date:20130429 for：#75表格选中，跳转tab,无法编辑
	var rowData = $('#' + id).datagrid('getSelected');
	// update-begin--Author:tanghong Date:20130504 for：#17
	// 有的时候，明细行明选择，点击编辑却提示未选中行
	if (!rowData) {
		tip('请选择编辑项目');
		return;
	}
	// update-end--Author:tanghong Date:20130504 for：#17 有的时候，明细行明选择，点击编辑却提示未选中行
	url += '&id=' + rowData.id;
	// update-end--Author:tanghong Date:20130429 for：#75表格选中，跳转tab,无法编辑
	createwindow(title, url,width, height);
}
/**
 * 树列表添加事件打开窗口
 * 
 * @param title
 *            编辑框标题
 * @param addurl//目标页面地址
 */
// update-begin--Author:liutao Date:20130405 for：为树列表增加特殊的添加方法
function addTreeNode(title, addurl, gname, height) {
	if (rowid != '') {
		addurl += '&id=' + rowid;
	}
	gridname = gname;
	createwindow(title, addurl, height);
}
// update-end--Author:liutao Date:20130405 for：为树列表增加特殊的添加方法
/**
 * 更新事件打开窗口
 * 
 * @param title
 *            编辑框标题
 * @param addurl//目标页面地址
 * @param id//主键字段
 */
function update(title, url, id, height) {
	// update-begin--Author:tanghong Date:20130429 for：#75表格选中，跳转tab,无法编辑
	var rowData = $('#' + id).datagrid('getSelected');
	// update-begin--Author:tanghong Date:20130504 for：#17
	// 有的时候，明细行明选择，点击编辑却提示未选中行
	if (!rowData) {
		tip('请选择编辑项目');
		return;
	}
	// update-end--Author:tanghong Date:20130504 for：#17 有的时候，明细行明选择，点击编辑却提示未选中行
	url += '&id=' + rowData.id;
	// update-end--Author:tanghong Date:20130429 for：#75表格选中，跳转tab,无法编辑
	createwindow(title, url, height);
}

// update-begin--Author:sun Date:20130503 for：增加表单详细查看功能

/**
 * 如果页面是详细查看页面，无效化所有表单元素，只能进行查看
 */
$(function() {
	if (location.href.indexOf("load=detail") != -1) {
		$(":input").attr("disabled", "true");
	}
});

function before(datagridName, url) {
	var getSelections = $('#' + datagridName).datagrid("getSelections");
	if (getSelections.length < 2) {
		multiLayerTip('请选择2个编辑项目');
		return;
	}

	if (getSelections.length > 2) {
		multiLayerTip('多选了，请选择2个项目进行编辑');
		return;
	}
	var idFieldName = $('#' + datagridName).datagrid("options").idField;
	var startSeq = getSelections[0]['sequence'];
	var endSeq = getSelections[1]['sequence'];
	
	var beforeId = getSelections[0][idFieldName];
	var currentId = getSelections[1][idFieldName];
	
	if (parseInt(startSeq) > parseInt(endSeq)) {
		beforeId = getSelections[1][idFieldName];
		currentId = getSelections[0][idFieldName];
		startSeq = getSelections[1]['sequence'];
		endSeq = getSelections[0]['sequence'];
	}

	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		data : "beforeId=" + beforeId + "&currentId=" + currentId,
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			$('#' + datagridName).datagrid("reload");
			$('#' + datagridName).datagrid("unselectAll");
		}
	});
}

function after(datagridName, url) {
	var getSelections = $('#' + datagridName).datagrid("getSelections");
	if (getSelections.length < 2) {
		multiLayerTip('请选择2个编辑项目');
		return;
	}

	if (getSelections.length > 2) {
		multiLayerTip('多选了，请选择2个项目进行编辑');
		return;
	}

	var idFieldName = $('#' + datagridName).datagrid("options").idField;
	var startSeq = getSelections[0]['sequence'];
	var endSeq = getSelections[1]['sequence'];
	var currentId;
	var backId;
	if (parseInt(startSeq) < parseInt(endSeq)) {
		currentId = getSelections[0][idFieldName];
		backId = getSelections[1][idFieldName];
	} else {
		backId = getSelections[0][idFieldName];
		currentId = getSelections[1][idFieldName];
	}
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		data : "backId=" + backId + "&currentId=" + currentId,
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			$('#' + datagridName).datagrid("reload");
			$('#' + datagridName).datagrid("unselectAll");
		}
	});
}

function moveOrder(orderName,datagridName,url) {
	var orderNo = $('#' + orderName).attr("value");
	if(!orderNo || typeof(orderNo)=="undefined" || isNaN(orderNo)){
		multiLayerTip('请输入目标排序号,目标排序号必须是数字！');
		return;
	}
		
	var getSelections = $('#' + datagridName).datagrid("getChecked");
	if (getSelections.length < 1) {
		multiLayerTip('请选择需要排序的项目！');
		return;
	}
	var idFieldName = $('#' + datagridName).datagrid("options").idField;
    var minOrder = getSelections[0]['sequence'];
    var maxOrder = getSelections[0]['sequence']; 
    
    for(var i = 1; i<getSelections.length;i++){
    	if (parseInt(minOrder)>parseInt(getSelections[i]['sequence']))
    		minOrder = getSelections[i]['sequence'];
    	if (parseInt(maxOrder)<parseInt(getSelections[i]['sequence']))
    		maxOrder = getSelections[i]['sequence'];
    }
    
	if (parseInt(orderNo)==parseInt(minOrder) || parseInt(orderNo)==parseInt(maxOrder)) {
		multiLayerTip('您输入目标排序号错误，和需要排序的项目重叠！');
		return;
	}
	if (parseInt(orderNo)>parseInt(minOrder) && parseInt(orderNo)<parseInt(maxOrder)) {
		multiLayerTip('您输入目标排序号错误，位于需要排序的项目之间！');
		return;
	}
	//往后移动到orderNo之后
	if (parseInt(orderNo)>parseInt(maxOrder)){
		var currentIds="";
		for(var i = 0; i<getSelections.length;i++){
			currentIds += getSelections[i][idFieldName]+";";
			//alert(getSelections[i]['sequence'])
		}
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			data : "currentIds=" + currentIds + "&backOrderNo=" + orderNo,
			url : url,// 请求的action路径
			error : function() {// 请求失败处理函数
			},
			success : function(data) {
				$('#' + datagridName).datagrid("reload");
				$('#' + datagridName).datagrid("unselectAll");
			}
		});		
	}
	else if (parseInt(orderNo)<parseInt(minOrder)){ //往前移动到 orderNo之前
		var currentIds="";
		for(var i = 0; i<getSelections.length;i++){
			currentIds += getSelections[i][idFieldName]+";";
			//alert(getSelections[i]['sequence'])
		}
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			data : "currentIds=" + currentIds + "&beforeOrderNo=" + orderNo,
			url : url,// 请求的action路径
			error : function() {// 请求失败处理函数
			},
			success : function(data) {
				$('#' + datagridName).datagrid("reload");
				$('#' + datagridName).datagrid("unselectAll");
			}
		});
	}	
}

/**
 * 修改事件打开窗口
 * 
 * @param title
 *            编辑框标题
 * @param url//目标页面地址
 * @param id//主键字段
 */
function modify(title, url, gname, height) {
	gridname = gname;
	var checkedRows = $('#' + gridname).datagrid('getChecked');
	if (checkedRows.length < 1) {
		tip('请选择编辑项目');
		return;
	}

	if (checkedRows.length > 1) {
		tip('多选了，请选择一个项目进行编辑');
		return;
	}
	var idFieldName = $("#" + gridname).datagrid("options").idField;
	var id = checkedRows[0][idFieldName];
	url += '&' + idFieldName + '=' + id;
	createwindow(title, url, height);
//	$('#' + gridname).datagrid("reload");
//	$('#' + gridname).datagrid("unselectAll");
}

function multiModify(title, url, gname, height) {
	gridname = gname;
	var checkedRows = $('#' + gridname).datagrid('getChecked');
	if (checkedRows.length < 1) {
		tip('请选择编辑项目');
		return;
	}
	
	var idFieldName = $("#" + gridname).datagrid("options").idField;
	$.each(checkedRows,function(i,item){
		var id = checkedRows[i][idFieldName];
		url += '&' + idFieldName + 's=' + id;
		
	});
	//console.info("url="+url);
	createwindow(title, url, height);
}

function modifyDatagrid(title, url, gname, height) {
	gridname = gname;
	var checkedRows = $('#' + gridname).datagrid('getChecked');
	if (checkedRows.length < 1) {
		tip('请选择编辑项目');
		return;
	}

	if (checkedRows.length > 1) {
		tip('多选了，请选择一个项目进行编辑');
		return;
	}
	var idFieldName = $("#" + gridname).datagrid("options").idField;
	var id = checkedRows[0][idFieldName];
	url += '&' + idFieldName + '=' + id;
	createwindowGrid(title, url, height,gridname);
}

function createwindowGrid(title, addurl, height,gridname) {
	if (typeof (windowapi) == 'undefined') {
		$.dialog({
			content : 'url:' + addurl,
			lock : true,
			height : height ? height : "auto",
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				saveObj();
				iframe.parent.$('#' + gridname).datagrid("unselectAll");
				iframe.parent.$('#' + gridname).datagrid("reload");
				return false;
			},
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	} else {
		W.$.dialog({
			content : 'url:' + addurl,
			lock : true,
			height : height ? height : "auto",
			parent : windowapi,
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				saveObj();
				iframe.parent.$('#' + gridname).datagrid("unselectAll");
				iframe.parent.$('#' + gridname).datagrid("reload");
				return false;
			},
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	}

}
/**
 * 查看详细事件打开窗口
 * 
 * @param title
 *            查看框标题
 * @param addurl//目标页面地址
 * @param id//主键字段
 */
/*
 * function detail(title,url, id) { var rowData =
 * $('#'+id).datagrid('getSelected'); if (rowData.id == '') { tip('请选择查看项目');
 * return; } url += '&load=detail&id='+rowData.id;
 * createdetailwindow(title,url); }
 */
/**
 * 查看详情事件打开窗口
 * 
 * @param title
 *            编辑框标题
 * @param addurl//目标页面地址
 * @param id//主键字段
 */
function detail(title, url, gname, height) {
	gridname = gname;
	var checkedRows = $('#' + gridname).datagrid('getChecked');
	if (checkedRows.length < 1) {
		tip('请选择查看的项目');
		return;
	}

	if (checkedRows.length > 1) {
		tip('多选了，请选择一个项目进行查看');
		return;
	}

	var idFieldName = $("#" + gridname).datagrid("options").idField;
	var id = checkedRows[0][idFieldName];

	url += '&' + idFieldName + '=' + id;
	createdetailwindow(title, url, height);
	$('#' + gridname).datagrid("reload");
	$('#' + gridname).datagrid("unselectAll");
}
/**
 * 查看时的弹出窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function createdetailwindow(title, addurl, height) {
	if (typeof (windowapi) == 'undefined') {
		$.dialog({
			content : 'url:' + addurl,
			lock : true,
			height : height ? height : "auto",
			title : title,
			opacity : 0.3,
			cache : false,
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	} else {
		W.$.dialog({
			content : 'url:' + addurl,
			lock : true,
			parent : windowapi,
			title : title,
			opacity : 0.3,
			cache : false,
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	}

}
// update-end--Author:sun Date:20130503 for：增加表单详细查看功能
/**
 * 全屏编辑
 * 
 * @param title
 *            编辑框标题
 * @param addurl//目标页面地址
 * @param id//主键字段
 */
function editfs(title, url) {
	var name = gridname;
	if (rowid == '') {
		tip('请选择编辑项目');
		return;
	}
	url += '&id=' + rowid;
	openwindow(title, url, name, 800, 500);
}
// 删除调用函数
function delObj(url, name) {
	createdialog('删除确认 ', '确定删除该记录吗 ?', url, name);
}
// 删除调用函数
function confuploadify(url, id) {
	$.dialog.confirm('确定删除吗', function() {
		deluploadify(url, id);
	}, function() {
	});
}
/**
 * 执行删除附件
 * 
 * @param url
 * @param index
 */
function deluploadify(url, id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				$("#" + id).remove();// 移除SPAN
				m.remove(id);// 移除MAP对象内字符串
			}

		}
	});
}
// 普通询问操作调用函数
function confirm(url, content, name) {
	createdialog('提示信息 ', content, url, name);
}
/**
 * 提示信息
 */
function tip(msg) {
	$.dialog.setting.zIndex = 1980;
	$.dialog.tips(msg, 2);
}

/**
 * 层级比较多时，用这个提示信息
 * 
 * @param msg
 */
function multiLayerTip(msg) {
	$.dialog.setting.zIndex = 19800;
	$.dialog.tips(msg, 2);
	$.dialog.setting.zIndex = 1980;
}

/**
 * 创建添加或编辑窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
// update-begin--Author:chenxu Date:20130402 for：父窗口将子窗口遮盖
function createwindow(title, addurl,width,height) {
	if (typeof (windowapi) == 'undefined') {
		$.dialog({
			content : 'url:' + addurl,
			lock : true,
			width : width ? width : "auto",
			height : height ? height : "auto",
			skin : 'jtop',
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				saveObj();
				return false;
			},
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	} else {
		W.$.dialog({
			content : 'url:' + addurl,
			lock : true,
			width : width ? width : "auto",
			height : height ? height : "auto",
			skin : 'jtop',
			parent : windowapi,
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				saveObj();
				return false;
			},
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	}

}
function createbuilapkFn(title, addurl, height) {
	if (typeof (windowapi) == 'undefined') {
		$.dialog({
			content : 'url:' + addurl,
			lock : true,
			height : height ? height : "auto",
			title : title,
			opacity : 0.3,
			cache : false,
			okVal : '打包',
			ok : function(target) {
				iframe = this.iframe.contentWindow;
				$(".ui_loading").show();
				$(".ui_state_highlight").val('打包中...');
				$(".ui_state_highlight").attr("disabled", "disabled");
				$(".ui_state_highlight").next("input").hide();
				saveObj();
				return false;
			},
			cancelVal : '取消',
			cancel : true
		/* 为true等价于function(){} */
		});
	} else {
		W.$.dialog({
			content : 'url:' + addurl,
			lock : true,
			parent : windowapi,
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				saveObj();
				return false;
			},
			cancelVal : '取消',
			cancel : true
		/* 为true等价于function(){} */
		});
	}
}
// update-end--Author:chenxu Date:20130402 for：父窗口将子窗口遮盖
/**
 * 创建上传页面窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function openuploadwin(title, url, name, width, height) {
	gridname = name;
	$.dialog({
		content : 'url:' + url,
		cache : false,
		button : [ {
			name : '开始上传',
			callback : function() {
				iframe = this.iframe.contentWindow;
				iframe.upload();
				return false;
			},
			focus : true
		}, {
			name : '取消上传',
			callback : function() {
				iframe = this.iframe.contentWindow;
				iframe.cancel();
			}
		} ]
	});
}
/**
 * 创建查询页面窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function opensearchdwin(title, url, width, height) {
	$.dialog({
		content : 'url:' + url,
		title : title,
		lock : true,
		height : height,
		cache : false,
		width : width,
		opacity : 0.3,
		button : [ {
			name : '查询',
			callback : function() {
				iframe = this.iframe.contentWindow;
				iframe.searchs();
			},
			focus : true
		}, {
			name : '取消',
			callback : function() {

			}
		} ]
	});
}
/**
 * 创建不带按钮的窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function openwindow(title, url, name, width, height) {
	gridname = name;
	if (typeof (windowapi) == 'undefined') {
		if (typeof (width) == 'undefined' && typeof (height) != 'undefined') {
			$.dialog({
				content : 'url:' + url,
				title : title,
				cache : false,
				lock : true,
				width : 'auto',
				height : height
			});
		}
		if (typeof (height) == 'undefined' && typeof (width) != 'undefined') {
			$.dialog({
				content : 'url:' + url,
				title : title,
				lock : true,
				width : width,
				cache : false,
				height : 'auto'
			});
		}
		if (typeof (width) == 'undefined' && typeof (height) == 'undefined') {
			$.dialog({
				content : 'url:' + url,
				title : title,
				lock : true,
				width : 'auto',
				cache : false,
				height : 'auto'
			});
		}
	
		if (typeof (width) != 'undefined' && typeof (height) != 'undefined') {
			$.dialog({
				width : width,
				height : height,
				content : 'url:' + url,
				title : title,
				cache : false,
				lock : true
			});
		}
	}else{

		if (typeof (width) == 'undefined' && typeof (height) != 'undefined') {
			W.$.dialog({
				content : 'url:' + url,
				parent : windowapi,
				title : title,
				opacity : 0.3,
				cache : false,
				lock : true,
				width : 'auto',
				height : height
			});
		}
		if (typeof (height) == 'undefined' && typeof (width) != 'undefined') {
			W.$.dialog({
				content : 'url:' + url,
				parent : windowapi,
				title : title,
				opacity : 0.3,
				lock : true,
				width : width,
				cache : false,
				height : 'auto'
			});
		}
		if (typeof (width) == 'undefined' && typeof (height) == 'undefined') {
			W.$.dialog({
				content : 'url:' + url,
				parent : windowapi,
				title : title,
				opacity : 0.3,
				lock : true,
				width : 'auto',
				cache : false,
				height : 'auto'
			});
		}
	
		if (typeof (width) != 'undefined' && typeof (height) != 'undefined') {
			W.$.dialog({
				width : width,
				height : height,
				content : 'url:' + url,
				parent : windowapi,
				title : title,
				opacity : 0.3,
				cache : false,
				lock : true
			});
		}
	
	}
}

/**
 * 创建询问窗口
 * 
 * @param title
 * @param content
 * @param url
 */
function createdialog(title, content, url, name) {
	$.dialog.setting.zIndex = 19800;
	$.dialog.confirm(content, function() {
		doSubmit(url, name);
		// update-begin--Author:zhangdaihao Date:20130413 for：删除数据rowid未清空
		rowid = '';
		$.dialog.setting.zIndex = 1980;
		// update-begin--Author:zhangdaihao Date:20130413 for：删除数据rowid未清空
	}, function() {
	});
}
/**
 * 执行保存
 * 
 * @param url
 * @param gridname
 */
function saveObj() {
	$('#btn_sub', iframe.document).click();
}

/**
 * 执行AJAX提交FORM
 * 
 * @param url
 * @param gridname
 */
function ajaxSubForm(url) {
	$('#myform', iframe.document).form('submit', {
		url : url,
		onSubmit : function() {
			iframe.editor.sync();
		},
		success : function(r) {
			tip('操作成功');
			reloadTable();
		}
	});
}
/**
 * 执行查询
 * 
 * @param url
 * @param gridname
 */
function search() {

	$('#btn_sub', iframe.document).click();
	iframe.search();
}

/**
 * 执行选择操作，根据用户选择的ID，操作
 * 
 * @param title
 * @param url
 * @param index
 */
function doSubmitChecked(title, url, name) {
	var node = $('#' + name).datagrid('getChecked');
	var idFieldName = $("#" + name).datagrid("options").idField;
	if (node.length == 0) {
		tip('请先选择需要' + title + '的记录！');
		return;
	}
	var cnodes = '';
	for ( var i = 0; i < node.length; i++) {
		cnodes += node[i][idFieldName] + ',';
	}
	cnodes = cnodes.substring(0, cnodes.length - 1);
	createdialog(title, '确定' + title + '选中的记录吗 ?', url + '&ids=' + cnodes, name);
}

/**
 * 执行选择操作，根据用户选择的ID，操作
 * 
 * @param title
 * @param content
 * @param url
 * @param index
 */
function comfirm(title, content, url, name) {
	var node = $('#' + name).datagrid('getChecked');
	var idFieldName = $("#" + name).datagrid("options").idField;
	if (node.length == 0) {
		tip('请先选择需要' + title + '的记录！');
		return;
	}
	var cnodes = '';
	for ( var i = 0; i < node.length; i++) {
		cnodes += node[i][idFieldName] + ',';
	}
	cnodes = cnodes.substring(0, cnodes.length - 1);
	createdialog(title, content, url + '&ids=' + cnodes, name);
}

/**
 * 执行选择操作，判断checkField的值不等于checkFieldValue，就执行操作
 * 
 * @param title
 * @param url
 * @param index
 */
function doSubmitWithChecked(title, url, name, checkField, checkFieldValue) {
	var node = $('#' + name).datagrid('getChecked');
	var idFieldName = $("#" + name).datagrid("options").idField;
	if (node.length == 0) {
		tip('请先选择需要' + title + '的记录！');
		return;
	}
	var cnodes = '';
	for ( var i = 0; i < node.length; i++) {
		if (node[i][checkField] == checkFieldValue) {
			tip('记录已' + title + '！');
			return;
		}
		cnodes += node[i][idFieldName] + ',';
	}
	cnodes = cnodes.substring(0, cnodes.length - 1);
	url = url + '&' + checkField + '=' + checkFieldValue;
	createdialog(title, '确定' + title + '选中的记录吗 ?', url + '&ids=' + cnodes, name);
}

/**
 * 执行操作
 * 
 * @param url
 * @param index
 */
function doSubmit(url, name) {
	gridname = name;
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			var d = $.parseJSON(data);
			if (d.success) {
				var msg = d.msg;
				tip(msg);
				reloadTable();
			}
		}
	});
}
/**
 * 退出确认框
 * 
 * @param url
 * @param content
 * @param index
 */
function exit(url, content) {
	$.dialog.confirm(content, function() {
		window.location = url;
	}, function() {
	});
}
/**
 * 模板页面ajax提交
 * 
 * @param url
 * @param gridname
 */
function ajaxdoSub(url, formname) {
	$('#' + formname).form('submit', {
		url : url,
		onSubmit : function() {
			editor.sync();
		},
		success : function(r) {
			tip('操作成功');
		}
	});
}
/**
 * ajax提交FORM
 * 
 * @param url
 * @param gridname
 */
function ajaxdoForm(url, formname) {
	$('#' + formname).form('submit', {
		url : url,
		onSubmit : function() {
		},
		success : function(r) {
			tip('操作成功');
		}
	});
}

function opensubwin(title, url, saveurl, okbutton, closebutton) {
	$.dialog({
		content : 'url:' + url,
		title : title,
		lock : true,
		opacity : 0.3,
		button : [ {
			name : okbutton,
			callback : function() {
				iframe = this.iframe.contentWindow;
				win = frameElement.api.opener;// 来源页面
				$('#btn_sub', iframe.document).click();
				return false;
			}
		}, {
			name : closebutton,
			callback : function() {
			}
		} ]

	});
}

function openauditwin(title, url, saveurl, okbutton, backbutton, closebutton) {
	$.dialog({
		content : 'url:' + url,
		title : title,
		lock : true,
		opacity : 0.3,
		button : [ {
			name : okbutton,
			callback : function() {
				iframe = this.iframe.contentWindow;
				win = $.dialog.open.origin;// 来源页面
				$('#btn_sub', iframe.document).click();
				return false;
			}
		}, {
			name : backbutton,
			callback : function() {
				iframe = this.iframe.contentWindow;
				win = frameElement.api.opener;// 来源页面
				$('#formobj', iframe.document).form('submit', {
					url : saveurl + "&code=exit",
					onSubmit : function() {
						$('#code').val('exit');
					},
					success : function(r) {
						$.dialog.tips('操作成功', 2);
						win.location.reload();
					}
				});

			}
		}, {
			name : closebutton,
			callback : function() {
			}
		} ]

	});
}
// 添加标签
function addOneTab(subtitle, url, icon) {
	if (icon == '') {
		icon = 'icon folder';
	}
	window.top.$.messager.progress({
		text : '页面加载中....',
		interval : 300
	});
	window.top.$('#maintabs').tabs({
		onClose : function(subtitle, index) {
			window.top.$.messager.progress('close');
		}
	});
	if (window.top.$('#maintabs').tabs('exists', subtitle)) {
		window.top.$('#maintabs').tabs('select', subtitle);
		window.top.$('#maintabs').tabs('update', {
			tab : window.top.$('#maintabs').tabs('getSelected'),
			options : {
				title : subtitle,
				href : url,
				// content : '<iframe name="tabiframe" scrolling="no"
				// frameborder="0" src="' + url + '"
				// style="width:100%;height:99%;"></iframe>',
				closable : true,
				icon : icon
			}
		});
	} else {
		if (url.indexOf('isIframe') != -1) {
			window.top
					.$('#maintabs')
					.tabs(
							'add',
							{
								title : subtitle,
								content : '<iframe src="'
										+ url
										+ '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
								closable : true,
								icon : icon
							});
		} else {
			window.top.$('#maintabs').tabs('add', {
				title : subtitle,
				href : url,
				closable : true,
				icon : icon
			});
		}
	}
}
// 关闭自身TAB刷新父TABgrid
function closetab(title) {
	// 暂时先不刷新
	// window.top.document.getElementById('tabiframe').contentWindow.reloadTable();
	// window.top.document.getElementById('maintabs').contentWindow.reloadTable();
	window.top.$('#maintabs').tabs('close', title);
	// tip("添加成功");
}

/**
 * 点击选择事件打开窗口
 * 
 * @param title编辑框标题
 * @param url//目标页面地址
 * @param gname//表格名称
 * @param width//宽度
 * @param height//长度
 */
function configWindow(title, url, gname, width, height) {
	gridname = gname;
	var checkedRows = $('#' + gridname).datagrid('getChecked');
	if (checkedRows.length < 1) {
		tip('请选择编辑项目');
		return;
	}

	if (checkedRows.length > 1) {
		tip('多选了，请选择一个项目进行编辑');
		return;
	}
	var idFieldName = $("#" + gridname).datagrid("options").idField;
	var id = checkedRows[0][idFieldName];
	url += '&' + idFieldName + '=' + id;
	openwindow(title, url, gname, width, height);
	$('#' + gridname).datagrid("unselectAll");
}

function createAddWindow(title, addurl, gname, width, height, saveurl,
		opengname) {
	W.$
			.dialog({
				width : width,
				height : height,
				content : 'url:' + addurl,
				lock : true,
				parent : windowapi,
				title : title,
				opacity : 0.3,
				cache : false,
				ok : function() {
					iframe = this.iframe.contentWindow;
					// saveObj();
					// saveSelected(iframe);
					var checkedRows = iframe.$('#' + opengname).datagrid(
							'getChecked');
					if (checkedRows.length < 1) {
						multiLayerTip('请选择编辑项目');
						return false;
					}
					var idFieldName = iframe.$("#" + opengname).datagrid(
							"options").idField;
					var bookIds = '';
					for ( var i = 0; i < checkedRows.length; i++) {
						bookIds += checkedRows[i][idFieldName] + ',';
					}
					bookIds = bookIds.substring(0, bookIds.length - 1);
					saveurl += '&ids=' + bookIds;
					var tag = false;
					$.ajax({
						async : false,
						cache : false,
						type : 'POST',
						url : saveurl,// 请求的action路径
						error : function() {// 请求失败处理函数
						},
						success : function(data) {
							// tip(data.msg);
							// $.dialog.setting.zIndex = 19800;
							// $.dialog.tips(data.msg, 3);
							tag = true;
							$("#" + gname).datagrid("reload");
						}
					});
					return tag;
				},
				close : function() {

				},
				cancelVal : '关闭',
				cancel : true
			/* 为true等价于function(){} */
			});

}

var idvalue;
function gridloadSort(gridname, sorturl) {
	if (idvalue)
		$("#" + gridname).datagrid("selectRecord", idvalue);
	curId = null;
	var startDragIndex = -1;
	var grid = $('#' + gridname);
	$('#' + gridname).datagrid("getPanel").find("tbody").sortable({
		stop : function(event, ui) {
			var rows = $('#' + gridname).datagrid('getRows');

			if (startDragIndex < 0 || startDragIndex > rows.length)
				return;
			var idname = $('#' + gridname).datagrid('options').idField;
			var index = ui.item.index();

			if (startDragIndex < index) {
				var curId = rows[startDragIndex][idname];
				var dataString = {
					"currentId" : rows[startDragIndex][idname],
					"backId" : rows[index][idname]
				};
				// alert('往后拉【' + rows[startDragIndex][idname] + '】放在【' +
				// rows[index][idname] + '】之后');
				$.post(sorturl, dataString, function(data) {
					// $.dialog.alert(data.msg);
					idvalue = curId;
					tip(data.msg);
					$("#" + gridname).datagrid("reload");
				}, "json")
			} else if (startDragIndex > index) {
				var curId = rows[startDragIndex][idname];
				var dataString = {
					"currentId" : rows[startDragIndex][idname],
					"beforeId" : rows[index][idname]
				};
				// alert('往前拉【' + rows[startDragIndex][idname] + '】放在【' +
				// rows[index][idname] + '】之前');
				$.post(sorturl, dataString, function(data) {
					idvalue = curId;
					tip(data.msg);
					$("#" + gridname).datagrid("reload");
				}, "json");

			} else {

			}
		},
		start : function(event, ui) {
			startDragIndex = ui.item.index();
		}
	});
	$('#' + gridname).datagrid("getPanel").find("tbody").disableSelection();
}