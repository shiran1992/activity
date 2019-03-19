package com.childrecord.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class WebFliter
 */
public class WebFliter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.print("11");
			// TODO Auto-generated method stub
HttpServletResponse resp=(HttpServletResponse) response;
resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8000"); 
resp.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");  
resp.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
resp.setHeader("Access-Control-Allow-Credentials", "true");
//放行
chain.doFilter(request, resp);
}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
