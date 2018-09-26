package com.dongkeonoh.toybox;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dongkeonoh.toybox.service.UserService;
import com.dongkeonoh.toybox.vo.UserVo;

@Controller
public class UserController {

	@Resource(name="UserServiceImpl")
	private UserService userService;

	@RequestMapping(value = "/add_user", method = RequestMethod.GET)
	public ModelAndView get_addUser(HttpServletRequest httpServletRequest, Model model) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/add_user_get");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/add_user", method = RequestMethod.POST)
	public ModelAndView post_addUser(HttpServletRequest httpServletRequest, Model model, UserVo userVo) {
		
		int resultRes = userService.addUser(userVo);
		if(resultRes > 0) {
			//성공
		}else {
			//실패
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/add_user_post");
		modelAndView.addObject("resultRes", resultRes);
		
		return modelAndView;
	}	
	
	@RequestMapping(value = "/search_user", method = RequestMethod.GET)
	public ModelAndView get_searchUser(HttpServletRequest httpServletRequest, Model model) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/search_user_get");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/search_user", method = RequestMethod.POST)
	public ModelAndView post_searchUser(HttpServletRequest httpServletRequest, Model model, UserVo userVo) {
		
		List<UserVo> userList = userService.searchUser(userVo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/search_user_post");
		modelAndView.addObject("userList", userList);
		
		return modelAndView;
	}
}
