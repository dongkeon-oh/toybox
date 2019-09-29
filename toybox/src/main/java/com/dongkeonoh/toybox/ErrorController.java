package com.dongkeonoh.toybox;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ErrorController {	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView errorPage(HttpServletRequest httpServletRequest, Model model) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		
		return modelAndView;
	}	
}
