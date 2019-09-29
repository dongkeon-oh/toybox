package com.dongkeonoh.toybox.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dongkeonoh.toybox.ToyboxGlobalNameSpace;

public class AuthInterceptor extends HandlerInterceptorAdapter implements ToyboxGlobalNameSpace{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession httpSession = request.getSession(false);
		
		if(httpSession.getAttribute(USER) == null) {
	        response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		return true;
	}
}
