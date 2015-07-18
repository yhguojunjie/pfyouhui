package com.yoxi.jgframework.annotation;

import java.lang.annotation.Retention;  
import java.lang.annotation.Target;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.ElementType;  

/**
 * 上傳日志捕捉
 * @author william
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
public @interface AddOrUpdateAnnotation {  

}  
