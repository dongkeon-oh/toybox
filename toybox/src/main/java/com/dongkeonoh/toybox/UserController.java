package com.dongkeonoh.toybox;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dongkeonoh.toybox.service.UserService;
import com.dongkeonoh.toybox.vo.UserVo;

@Controller
public class UserController {

	@Resource(name="UserServiceImpl")
	private UserService userService;

	// 유저 추가
	@RequestMapping(value = "/add_user", method = RequestMethod.GET)
	public ModelAndView put_user_get(HttpServletRequest httpServletRequest) {
		List<UserVo> com_list = userService.getComCode("user_question");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/modify_user");	
		modelAndView.addObject("com_list", com_list);	
		return modelAndView;
	}
	
	// 유저 추가
	@RequestMapping(value = "/put_user", method = RequestMethod.POST)
	public ModelAndView put_user_post(HttpServletRequest httpServletRequest, UserVo userVo) {
		
		int checksum = userService.putUser(userVo);
		if(checksum > 0) {
			//성공
		}else {
			//실패
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("checksum", checksum);	
		return modelAndView;
	}	
	
	// 유저 추가
	@RequestMapping(value = "/get_user", method = RequestMethod.GET)
	public ModelAndView get_user(HttpServletRequest httpServletRequest, UserVo userVo) {
		//userVo.setUsr_id("test");
		UserVo checksum = userService.getUser("test22");
		List<UserVo> com_list = userService.getComCode("user_question");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/modify_user");
		modelAndView.addObject("userVo", checksum);		
		modelAndView.addObject("com_list", com_list);	
		return modelAndView;
	}	
	
	// 유저 추가 get
	@RequestMapping(value = "/modify_user", method = RequestMethod.POST)
	public ModelAndView modUser(HttpServletRequest httpServletRequest, UserVo userVo) {

		int checksum = userService.modifyUser(userVo);
		if(checksum > 0) {
			//성공
		}else {
			//실패
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/modify_user");
		modelAndView.addObject("checksum", checksum);			
		return modelAndView;
	}
}
