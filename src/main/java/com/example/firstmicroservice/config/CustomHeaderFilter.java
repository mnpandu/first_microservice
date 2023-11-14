package com.example.firstmicroservice.config;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
@WebFilter("/*")
public class CustomHeaderFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// Read headers from the request
		String customHeader = httpRequest.getHeader("X-CorrelationId");

		// Do something with the header value
		System.out.println("X-CorrelationId: " + customHeader);

		// Continue with the filter chain
		
		HttpServletResponse httpServletResponse=(HttpServletResponse) response;
		httpServletResponse.setHeader("X-CorrelationId", customHeader);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialization code, if needed
	}

	@Override
	public void destroy() {
		// Cleanup code, if needed
	}
}