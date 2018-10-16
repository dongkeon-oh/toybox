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

import com.dongkeonoh.toybox.service.CommonService;
import com.dongkeonoh.toybox.vo.CategoryVo;
import com.dongkeonoh.toybox.vo.CommonVo;

@Controller
public class CommonController {

	@Resource(name="CommonServiceImpl")
	private CommonService commonService;

	// 공통코드 관리
	@RequestMapping(value = "/modify_common", method = RequestMethod.GET)
	public ModelAndView modifyCommon(HttpServletRequest httpServletRequest, CommonVo commonVo) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/modify_common");
		modelAndView.addObject("commonVo",commonVo);
		
		return modelAndView;
	}
	
	// 공통코드 저장
	@RequestMapping(value = "/ajax_put_common", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxPutCommon(HttpServletRequest httpServletRequest, CommonVo commonVo) {
		int result = commonService.putCommon(commonVo);
		
		return result;
	}
	
	// 공통코드 추가 get
	@RequestMapping(value = "/list_common", method = RequestMethod.GET)
	public ModelAndView getListCommon(HttpServletRequest httpServletRequest, CategoryVo commonDetailVo) {		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/list_common");
		
		return modelAndView;
	}

	// 공통코드  post
	@RequestMapping(value = "/ajax_list_common", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonVo> ajaxListCommon(HttpServletRequest httpServletRequest
			, @RequestParam("target") String target
			, @RequestParam("keyword") String keyword
			, @RequestParam("order_type") String order_type
			, @RequestParam("start_idx") String start_idx
			, @RequestParam("end_idx") String end_idx
			) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("target", target);
		map.put("keyword", keyword);
		map.put("order_type", order_type);
		map.put("start_idx", start_idx);
		map.put("end_idx", end_idx);
		
		List<CommonVo> result = commonService.getCommonList(map);
		
		return result;
	}
}
