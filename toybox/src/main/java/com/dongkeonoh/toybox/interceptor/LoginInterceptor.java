package com.dongkeonoh.toybox.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dongkeonoh.toybox.ToyboxGlobalNameSpace;

public class LoginInterceptor extends HandlerInterceptorAdapter implements ToyboxGlobalNameSpace{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession httpSession = request.getSession();
		
		if(httpSession.getAttribute(USER) != null) {
			httpSession.removeAttribute(USER);
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession httpSession = request.getSession();		
		Object user = modelAndView.getModelMap().get("userVo");
		if(user != null) {
			httpSession.setAttribute(USER, user);
			
			Cookie cookie = new Cookie(COOKIE, httpSession.getId());
			cookie.setPath("/");
			cookie.setMaxAge(24*60*60);
			
			response.addCookie(cookie);

	        response.sendRedirect(request.getContextPath() + "/list_item");
		}
	}
}
