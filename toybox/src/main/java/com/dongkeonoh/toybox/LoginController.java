package com.dongkeonoh.toybox;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dongkeonoh.toybox.service.LoginService;
import com.dongkeonoh.toybox.vo.UserVo;


/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {	
	
	@Resource(name="LoginServiceImpl")
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest httpServletRequest) {		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");		
		return modelAndView;
	}	
	
	@RequestMapping(value = "/login_prog", method = RequestMethod.POST)
	public ModelAndView login_prog(UserVo user_info, ModelAndView modelAndView) {
		UserVo userVo = loginService.getLogin(user_info);
		if(userVo != null) {
			modelAndView.addObject("userVo", userVo);
			modelAndView.setViewName("item/list_item");	
		}else {
			modelAndView.addObject("login_fail", "login_fail");
			modelAndView.setViewName("login");	
		}	
		return modelAndView;
	}		
}
