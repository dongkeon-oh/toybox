package com.dongkeonoh.toybox;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dongkeonoh.toybox.service.TestService;
import com.dongkeonoh.toybox.vo.CommonVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Resource(name="TestServiceImpl")
	private TestService testService;
	
	@RequestMapping(value = "/commonTest", method = RequestMethod.GET)
	public ModelAndView testList(HttpServletRequest httpServletRequest, Model model) {
		List<CommonVo> eList = testService.testList();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("tesJsp");
		modelAndView.addObject("eList", eList);
		
		return modelAndView;
	}	
}
