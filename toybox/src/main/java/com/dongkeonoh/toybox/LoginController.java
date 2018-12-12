package com.dongkeonoh.toybox;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView testList(HttpServletRequest httpServletRequest) {		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");		
		return modelAndView;
	}	
}
