package com.yejunfeng.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharsetFilter implements Filter
{
	private String charsetEncoding;
	private boolean enabled;
	
	public void destroy() 
	{
		charsetEncoding = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		if (enabled||charsetEncoding!=null) {
			request.setCharacterEncoding(charsetEncoding);
			response.setCharacterEncoding(charsetEncoding);
		}
		chain.doFilter(request, response);

	}

	public void init(FilterConfig config) throws ServletException 
	{
		charsetEncoding=config.getInitParameter("charsetEncoding");
		enabled="true".equalsIgnoreCase(config.getInitParameter("enabled").trim());

	}

}
