/**
 * datatype扩展 function(gets,obj,curform,regxp){ 参数gets是获取到的表单元素值， obj为当前表单元素，
 * curform为当前验证的表单， regxp为内置的一些正则表达式的引用。
 * 
 */
$.Datatype.need1 = function(gets, obj, curform, regxp) {
	var need = 1, numselected = curform.find("input[name='" + obj.attr("name")
			+ "']:checked").length;
	return numselected >= need ? true : "请至少选择" + need + "项！";
};
$.Datatype.need2 = function(gets, obj, curform, regxp) {
	var need = 2, numselected = curform.find("input[name='" + obj.attr("name")
			+ "']:checked").length;
	return numselected >= need ? true : "请至少选择" + need + "项！";
};

// 整数
$.Datatype.d = /^(-)?(\d*)?\d+$/;
//验证域名
$.Datatype.domain = function(gets, obj, curform, datatype){
	if($.trim(gets) !=""){
		if (!/^[0-9a-zA-Z]+[0-9a-zA-Z\.-]*\.[a-zA-Z]{2,4}$/.test(gets)) {
			return "请输入正确域名";
		}
	}
	return true;
};
// 时间格式
$.Datatype.dateTime = /^(?:(?:1[6-9]|[2-9][0-9])[0-9]{2}([-\.]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:(?:1[6-9]|[2-9][0-9])(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)([-\.]?)0?2\2(?:29))(\s+([01][0-9]:|2[0-3]:)?[0-5][0-9]:[0-5][0-9])?$/;

// -------------------- 两位小数-------//
$.Datatype.float = function(gets, obj, curform, datatype) {
	var disabled = obj.attr('disabled');
	if (disabled == 'disabled') {
		return true;
	}
	/*
	 * 判断数值范围 注意需要给表单元素绑定max来指定最大可输入的值，没有绑定的话使用默认值
	 */
	var maxim = obj.attr("max") || 100000000, minim = obj.attr("min") || 0;

	if (!/^-?\d+(\.\d{1,2})?$/.test(gets)) {
		return "请填写数字，最多保留两位小数！";
	}

	if (parseFloat(gets) < parseFloat(minim)) {
		return "所填数值不能小于" + minim + "！";
	} else if (parseFloat(gets) > parseFloat(maxim)) {
		return "所填数值不能大于" + maxim + "！";
	}
	return true;
};

/**
 * 日期比较
 */
$.Datatype.daterangeExtend = function(gets, obj, curform, datatype) {
	/*
	 * 判断日期范围 注意需要给表单元素绑定max或min属性，或两个同时绑定来指定最大或最小可输入的日期 日期格式：2012/12/29 或
	 * 2012-12-29 或 2012.12.29 或 2012,12,29
	 */
	var gets = parseInt(gets.replace(/[- :,\.]/g, ""), 10);
	var maxim = null;
	if (obj.attr("max") != null) {
		maxim = parseInt(obj.attr("max").replace(/[- :,\.]/g, ""), 10);
		if (parseInt(gets) >= parseInt(maxim)) {
			return "日期不能大于或等于" + obj.attr("max");
		}
	}
	var minim = null;
	if (obj.attr("min") != null) {
		minim = parseInt(obj.attr("min").replace(/[- :,\.]/g, ""), 10);
		if (parseInt(gets) <= parseInt(minim)) {
			return "日期不能小于或等于" + obj.attr("min");
		}
	}


	/*
	 * if(!gets.getDate()){ return "日期格式不对！"; }else{
	 */
	/*
	 * if(maxim==null&&minim==null){ return true; }
	 */
	/* } */

	/*if (maxim == null) {
		
	}else{
		if (gets > maxim) {
			return "日期不能大于" + obj.attr("max");
		}
	}

	if (minim == null) {}else{
		if (gets < minim) {
			return "日期不能小于" + obj.attr("min");
		}
	}*/

	return true;
}

// $.Datatype.test = function(gets, obj, curform, regxp) {
// alert(gets);
// alert(obj.attr("name"));
// alert(curform.attr("name"));
// alert(regxp.zh)
// }

