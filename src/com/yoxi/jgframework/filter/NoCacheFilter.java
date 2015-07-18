package com.yoxi.jgframework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter implements Filter {

    public void destroy() {

    }

    //浏览器不缓存页面
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException{
       //设置禁止缓存的消息头
       ((HttpServletResponse)response).setHeader("Pragma","no-cache");     
       ((HttpServletResponse)response).setHeader("Cache-Control","no-cache");     
       ((HttpServletResponse)response).setHeader("Expires","0");//禁止缓存     
       
       chain.doFilter(request, response);
       //System.out.print("NoCacheFilter");
    }

    
    public void init(FilterConfig arg0) throws ServletException {
    }

}
