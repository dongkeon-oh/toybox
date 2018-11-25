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

//	// 공통코드 관리
//	@RequestMapping(value = "/modify_common", method = RequestMethod.GET)
//	public ModelAndView modifyCommon(HttpServletRequest httpServletRequest, CommonVo commonVo) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("common/modify_common");
//		modelAndView.addObject("commonVo",commonVo);
//		
//		return modelAndView;
//	}

//	// 공통코드 저장
//	@RequestMapping(value = "/ajax_put_common", method = RequestMethod.POST)
//	@ResponseBody
//	public int ajaxPutCommon(HttpServletRequest httpServletRequest, CommonVo commonVo) {
//		int result = commonService.putCommon(commonVo);		
//		return result;
//	}
	
	// 공통코드 추가 get
	@RequestMapping(value = "/list_common", method = RequestMethod.GET)
	public ModelAndView getListCommon(HttpServletRequest httpServletRequest, CategoryVo commonDetailVo) {		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/list_common");
		
		return modelAndView;
	}
	
	// 공통코드 저장
	@RequestMapping(value = "/ajax_put_common_group", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxPutCommonGroup(HttpServletRequest httpServletRequest, CommonVo commonVo) {
		int result = commonService.putCommonGroup(commonVo);		
		return result;
	}
	
	// 공통코드 저장
	@RequestMapping(value = "/ajax_modify_common_group", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxModifyCommonGroup(HttpServletRequest httpServletRequest, CommonVo commonVo) {
		int result = commonService.modifyCommonGroup(commonVo);		
		return result;
	}
	
	// 공통코드 저장
	@RequestMapping(value = "/ajax_delete_common_group", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxDeleteCommonGroup(HttpServletRequest httpServletRequest, CommonVo commonVo) {
		int result = commonService.deleteCommonGroup(commonVo);		
		return result;
	}
	
	// 공통코드 저장
	@RequestMapping(value = "/ajax_dup_common_group", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxDupCommonGroup(HttpServletRequest httpServletRequest, CommonVo commonVo) {
		int result = commonService.dupCommonGroup(commonVo);		
		return result;
	}

	// 공통코드 추가 get
	@RequestMapping(value = "/ajax_list_common_group", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonVo> ajaxListCommonGroup(HttpServletRequest httpServletRequest
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

		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] keyword 	// " + keyword);
		System.out.println("[LOG] keytype 	// " + keytype);
		System.out.println("[LOG] start_idx // " + start_idx);
		System.out.println("[LOG] end_idx 	// " + end_idx);
		System.out.println("[LOG] ======================================");
		
		List<CommonVo> result = commonService.listCommonGroup(map);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		return result;
	}
	
	


	// 공통코드 추가 get
	@RequestMapping(value = "/ajax_list_common_code", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonVo> ajaxListCommonCode(HttpServletRequest httpServletRequest, CommonVo commonVo) {		
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] cgr_group 	// " + commonVo.getCgr_group());
		System.out.println("[LOG] ======================================");
		
		List<CommonVo> result = commonService.listCommonCode(commonVo);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		return result;
	}
}
