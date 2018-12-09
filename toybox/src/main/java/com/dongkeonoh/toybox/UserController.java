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
	
	// 유저 추가 get
	@RequestMapping(value = "/modify_user", method = RequestMethod.GET)
	public ModelAndView modUser(HttpServletRequest httpServletRequest) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/modify_user");
		
		return modelAndView;
	}


	// 유저 추가 post
	@RequestMapping(value = "/put_user", method = RequestMethod.POST)
	public ModelAndView put_user(HttpServletRequest httpServletRequest, UserVo userVo) {
		
		int checksum = userService.putUser(userVo);
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
	
	// 유저 추가 get
	@RequestMapping(value = "/add_user", method = RequestMethod.GET)
	public ModelAndView get_addUser(HttpServletRequest httpServletRequest, Model model) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/add_user_get");
		
		return modelAndView;
	}

	// 유저 추가 post
	@RequestMapping(value = "/add_user", method = RequestMethod.POST)
	public ModelAndView post_addUser(HttpServletRequest httpServletRequest, Model model, UserVo userVo) {
		
		int checksum = userService.addUser(userVo);
		if(checksum > 0) {
			//성공
		}else {
			//실패
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/add_user_post");
		modelAndView.addObject("checksum", checksum);
		
		return modelAndView;
	}	

	// 유저 보기 post
	@RequestMapping(value = "/view_user", method = RequestMethod.POST)
	public ModelAndView post_viewUser(HttpServletRequest httpServletRequest, Model model, UserVo userVo) {
		
		String userId = httpServletRequest.getParameter("userId");
		UserVo user = userService.viewUser(userId);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/view_user_post");
		modelAndView.addObject("user", user);
		
		return modelAndView;
	}

	// 유저 수정 post
	@RequestMapping(value = "/modify_user", method = RequestMethod.POST)
	public ModelAndView post_modifyUser(HttpServletRequest httpServletRequest, Model model, UserVo userVo) {
		
		int checksum = userService.modifyUser(userVo);
		if(checksum > 0) {
			//성공
		}else {
			//실패
		}
		System.out.println(checksum);	

		UserVo user = userService.viewUser(userVo.getUsr_id());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/view_user_post");
		modelAndView.addObject("user", user);
		
		return modelAndView;
	}

	// 유저 삭제 post
	@RequestMapping(value = "/delete_user", method = RequestMethod.POST)
	public ModelAndView post_deleteUser(HttpServletRequest httpServletRequest, Model model, UserVo userVo) {
		
		int checksum = userService.deleteUser(userVo);
		if(checksum > 0) {
			//성공
		}else {
			//실패
		}
		System.out.println(checksum);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/search_user_get");
		
		return modelAndView;
	}

	// 유저 active post
	@RequestMapping(value = "/active_user", method = RequestMethod.POST)
	public ModelAndView post_activeUser(HttpServletRequest httpServletRequest, Model model, UserVo userVo) {
		
		int checksum = userService.activeUser(userVo);
		if(checksum > 0) {
			//성공
		}else {
			//실패
		}
		System.out.println(checksum);

		UserVo user = userService.viewUser(userVo.getUsr_id());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/view_user_post");
		modelAndView.addObject("user", user);
		
		return modelAndView;
	}

	// 유저 찾기 get
	@RequestMapping(value = "/search_user", method = RequestMethod.GET)
	public ModelAndView get_searchUser(HttpServletRequest httpServletRequest, Model model) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/search_user_get");
		
		return modelAndView;
	}

	// 유저 찾기 post
	@RequestMapping(value = "/search_user", method = RequestMethod.POST)
	public ModelAndView post_searchUser(HttpServletRequest httpServletRequest, Model model, UserVo userVo) {
		
		List<UserVo> userList = userService.searchUser(userVo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/search_user_post");
		modelAndView.addObject("userList", userList);
		
		return modelAndView;
	}
}
