package com.dongkeonoh.toybox;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dongkeonoh.toybox.service.UserService;
import com.dongkeonoh.toybox.vo.UserVo;

@Controller
public class UserController {

	@Resource(name="UserServiceImpl")
	private UserService userService;


	// 유저 추가
	@RequestMapping(value = "/join_user", method = RequestMethod.GET)
	public ModelAndView join_user(HttpServletRequest httpServletRequest) {
//		List<UserVo> com_list = userService.putUser("user_question");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/modify_user");	
//		modelAndView.addObject("com_list", com_list);	
		return modelAndView;
	}
	
	// 유저 추가
	@RequestMapping(value = "/put_user", method = RequestMethod.POST)
	public ModelAndView put_user(HttpServletRequest httpServletRequest, UserVo userVo) {
		
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
	
	// 유저 active
	@RequestMapping(value = "/ajax_active_user", method = RequestMethod.POST)
	@ResponseBody
	public int ajax_active_user(HttpServletRequest httpServletRequest, UserVo userVo) {
		int result = userService.activeUser(userVo);		
		return result;
	}
	
	// 유저 조회
	@RequestMapping(value = "/ajax_get_user", method = RequestMethod.POST)
	@ResponseBody
	public UserVo ajax_get_user(HttpServletRequest httpServletRequest, String user_id) {
		UserVo result = userService.getUser(user_id);
		return result;
	}	

	// 유저 리스트
	@RequestMapping(value = "/admin_list_user", method = RequestMethod.GET)
	public ModelAndView admin_list_user(HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/list_user");	
		return modelAndView;
	}
	
	// 유저 리스트 보기
	@RequestMapping(value = "/ajax_list_user", method = RequestMethod.POST)
	@ResponseBody
	public List<UserVo> ajax_list_user(HttpServletRequest httpServletRequest
			, @RequestParam("keyword") 		String keyword
			, @RequestParam("keytype") 		String keytype
			, @RequestParam("start_idx") 	String start_idx
			, @RequestParam("end_idx") 		String end_idx
	) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		map.put("keytype", keytype);
		map.put("start_idx", start_idx);
		map.put("end_idx", end_idx);
		
		List<UserVo> result = userService.listUser(map);		
		return result;
	}
}
