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

import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.service.UserAdminService;

@Controller
public class UserAdminController {

	@Resource(name="UserAdminServiceImpl")
	private UserAdminService userAdminService;

	// 유저 리스트
	@RequestMapping(value = "/admin_list_user", method = RequestMethod.GET)
	public ModelAndView admin_list_user(HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/list_user");	
		return modelAndView;
	}
	
	// 유저 리스트 보기
	@RequestMapping(value = "/ajax_list_user", method = RequestMethod.POST)
	@ResponseBody
	public List<UserDto> ajax_list_user(HttpServletRequest httpServletRequest
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
		
		List<UserDto> result = userAdminService.listUser(map);		
		return result;
	}
	
	// 유저 조회
	@RequestMapping(value = "/ajax_get_user", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> ajax_get_user(HttpServletRequest httpServletRequest, String userId) {		
		HashMap<String, Object> result = userAdminService.getUser(userId);
		return result;
	}	
	
	// 유저 active
	@RequestMapping(value = "/ajax_modify_user", method = RequestMethod.POST)
	@ResponseBody
	public int ajax_modify_user(HttpServletRequest httpServletRequest, UserDto userDto) {
		int result = userAdminService.modifyUserAdmin(userDto);		
		return result;
	}
}
