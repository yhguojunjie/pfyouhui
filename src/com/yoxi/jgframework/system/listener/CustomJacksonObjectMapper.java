package com.yoxi.jgframework.system.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.stereotype.Component;

/**
 * 用于设置jackson的日期格式及其一些必要属性的统一设置
 * @author liyh
 * @Date 2014 2014年4月10日 上午9:07:16
 */
@Component
public class CustomJacksonObjectMapper extends ObjectMapper {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public CustomJacksonObjectMapper() {
		super.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		super.setDateFormat(DATE_FORMAT);
		// 只包含属性不为空的值，去掉为null和""的数据
//		super.setSerializationInclusion(Inclusion);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		super.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		super.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		super.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, true);
		super.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
	}

}