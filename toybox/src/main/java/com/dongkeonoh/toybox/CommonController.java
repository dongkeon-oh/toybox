package com.dongkeonoh.toybox;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dongkeonoh.toybox.service.CommonService;
import com.dongkeonoh.toybox.vo.CommonDetailVo;
import com.dongkeonoh.toybox.vo.CommonVo;

@Controller
public class CommonController {

	@Resource(name="CommonServiceImpl")
	private CommonService commonService;

	// 공통코드 추가 get
	@RequestMapping(value = "/add_common", method = RequestMethod.GET)
	public ModelAndView get_addCommon(HttpServletRequest httpServletRequest, CommonDetailVo commonDetailVo) {
		commonDetailVo.setCde_upperid("0");
		List<CommonDetailVo> category = commonService.getCategoryCommon(commonDetailVo);
		if(category == null) {
			
		}else {
			
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/add_common");
		modelAndView.addObject("category", category);
		
		return modelAndView;
	}
	
	// 공통코드 추가 get
	@RequestMapping(value = "/list_common", method = RequestMethod.GET)
	public ModelAndView getListCommon(HttpServletRequest httpServletRequest, CommonDetailVo commonDetailVo) {		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/list_common");
		
		return modelAndView;
	}

	// 공통코드 수정 post
	@RequestMapping(value = "/modify_common", method = RequestMethod.POST)
	public ModelAndView post_modifyCommon(HttpServletRequest httpServletRequest, Model model, CommonVo commonVo) {
		
		int checksum = commonService.addCommon(commonVo);
		if(checksum > 0) {
			//성공
		}else {
			//실패
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/modify_common");
		modelAndView.addObject("checksum", checksum);
		
		return modelAndView;
	}	
	
	// 공통코드 카테고리 post
	@RequestMapping(value = "/add_common_category", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonDetailVo> ajaxAddCommonCategory(HttpServletRequest httpServletRequest, Model model, CommonDetailVo commonDetailVo) {
		List<CommonDetailVo> result = commonService.addCommonDetail(commonDetailVo);
		
		return result;
	}

	// 공통코드 카테고리 post
	@RequestMapping(value = "/get_common_category", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonDetailVo> ajaxGetCommonCategory(HttpServletRequest httpServletRequest, Model model, CommonDetailVo commonDetailVo) {
		List<CommonDetailVo> result = commonService.getCategoryCommon(commonDetailVo);
		
		return result;
	}

	// 공통코드 카테고리 post
	@RequestMapping(value = "/ajax_add_common", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxAddCommon(HttpServletRequest httpServletRequest, CommonVo commonVo) {
		int result = commonService.addCommon(commonVo);
		
		return result;
	}

	// 공통코드  post
	@RequestMapping(value = "/ajax_list_common", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonVo> ajaxListCommon(HttpServletRequest httpServletRequest, HashMap<String, String> map) {
		List<CommonVo> result = commonService.getCommonList(map);
		
		return result;
	}
}
