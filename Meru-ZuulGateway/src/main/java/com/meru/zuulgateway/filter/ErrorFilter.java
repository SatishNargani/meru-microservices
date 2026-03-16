package com.meru.zuulgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.ZuulFilterResult;
import com.netflix.zuul.exception.ZuulException;

public class ErrorFilter  extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true	;
	}

	@Override
	public Object run() throws ZuulException {
		return new ZuulException("post", 1, "post");
	}

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public ZuulFilterResult runFilter() {
		System.out.println("runFilter : Error Filter");
		return super.runFilter();
	}

}

