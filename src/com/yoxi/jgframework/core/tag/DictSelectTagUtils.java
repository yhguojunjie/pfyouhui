package com.yoxi.jgframework.core.tag;

import java.util.List;

import com.yoxi.jgframework.system.entity.TSCodeDetail;
import com.yoxi.jgframework.system.entity.TSCodeType;
import com.yoxi.jgframework.core.utils.StringUtils;

public class DictSelectTagUtils {
	public static StringBuffer end(String typeGroupCode, String name,
			String id, Integer defaultVal, String selectClass, Boolean query,
			String datatype, String onChange, String dictionaryCode) {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isBlank(selectClass)) {
			selectClass = "easyui-combobox"; // 默认select样式
		}
		TSCodeType tsCodeType = TSCodeType.allTSCodeType.get(typeGroupCode
				.toLowerCase());
		List<TSCodeDetail> tsCodeDetails = TSCodeType.allTSCodeDetail
				.get(typeGroupCode.toLowerCase());
		if (tsCodeType == null) {

		} else {
			sb.append("<script type=\"text/javascript\">" + "$(function() { "
					+ "$(\'#" + id + "\').combotree({"
					+ "onChange:function(newValue,oldValue){"
					+ " $(this).val(newValue);");
			if (StringUtils.isNotEmpty(onChange)) {
				sb.append(onChange + "(newValue,oldValue);");
			}
			sb.append("}");
			sb.append("});");
			sb.append("});");
			sb.append("</script>");
			sb.append("<select name=\"" + name + "\" class=\"" + selectClass
					+ "\" datatype=\"" + datatype + "\"  panelHeight=\"auto\"");
			if (!StringUtils.isBlank(id)) {
				sb.append(" id=\"" + id + "\"");
			}
			sb.append(">");
			if (query) {
				sb.append(" <option value=\"\" >全部</option>");
			} else {
				sb.append(" <option value=\"\" ></option>");
			}
			for (TSCodeDetail tsCodeDetail : tsCodeDetails) {
				if (tsCodeDetail.getDeleteFlag() == false) {
					if (!StringUtils.isBlank(dictionaryCode)) {// limj 20140401
						if (tsCodeDetail.getCode() != null
								&& defaultVal != null
								&& tsCodeDetail.getCode().equals(
										defaultVal.toString())) {
							sb.append(" <option value=\""
									+ tsCodeDetail.getCode()
									+ "\" selected=\"selected\">");
						} else {
							sb.append(" <option value=\""
									+ tsCodeDetail.getCode() + "\">");
						}
					} else {
						if (tsCodeDetail.getId() != null
								&& tsCodeDetail.getId().equals(defaultVal)) {
							sb.append(" <option value=\""
									+ tsCodeDetail.getId()
									+ "\" selected=\"selected\">");
						} else {
							sb.append(" <option value=\""
									+ tsCodeDetail.getId() + "\">");
						}// limj 20140320
					}

					sb.append(tsCodeDetail.getCodeName());
					sb.append(" </option>");
				}
			}

			sb.append("</select>");
		}

		return sb;
	}
}
