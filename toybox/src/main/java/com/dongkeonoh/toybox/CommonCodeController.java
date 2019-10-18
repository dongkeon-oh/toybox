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

import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.service.CommonService;

@Controller
public class CommonCodeController {

	@Resource(name="CommonServiceImpl")
	private CommonService commonService;
	
	// 공통코드 관리자 화면
	@RequestMapping(value = "/admin_list_common", method = RequestMethod.GET)
	public ModelAndView admin_list_common(HttpServletRequest httpServletRequest, ModelAndView modelAndView) {
		modelAndView.setViewName("admin/list_common_code");		
		return modelAndView;
	}

	// 공통코그 그룹/////////////////////////////////////////////
	// 공통코드 그룹 리스트 출력
	@RequestMapping(value = "/ajax_list_common_group", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonCodeDto> ajaxListCommonGroup(HttpServletRequest httpServletRequest
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
		
		List<CommonCodeDto> result = commonService.listCommonGroup(map);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		return result;
	}


	// 공통코드 그룹 조회
	@RequestMapping(value = "/ajax_get_common_group", method = RequestMethod.POST)
	@ResponseBody
	public CommonCodeDto ajaxGetCommonGroup(HttpServletRequest httpServletRequest
			, @RequestParam("cgr_group") 	String cgr_group
	) {		
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] cgr_group 	// " + cgr_group);
		System.out.println("[LOG] ======================================");
		
		CommonCodeDto result = commonService.getCommonGroup(cgr_group);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.getCgr_group() 		// " + result.getCgr_group());
		System.out.println("[LOG] result.getCgr_group_name() 	// " + result.getCgr_group_name());
		System.out.println("[LOG] result.getCgr_note() 			// " + result.getCgr_note());
		System.out.println("[LOG] ======================================");
		return result;
	}
	
	// 공통코드 그룹 생성
	@RequestMapping(value = "/ajax_put_common_group", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxPutCommonGroup(HttpServletRequest httpServletRequest, CommonCodeDto commonCodeDto) {
		int result = commonService.putCommonGroup(commonCodeDto);		
		return result;
	}
	
	// 공통코드 그룹 수정
	@RequestMapping(value = "/ajax_modify_common_group", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxModifyCommonGroup(HttpServletRequest httpServletRequest, CommonCodeDto commonCodeDto) {
		int result = commonService.modifyCommonGroup(commonCodeDto);		
		return result;
	}
	
	// 공통코드 그룹명 중복 확인
	@RequestMapping(value = "/ajax_dup_common_group", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxDupCommonGroup(HttpServletRequest httpServletRequest, CommonCodeDto commonCodeDto) {
		int result = commonService.dupCommonGroup(commonCodeDto);		
		return result;
	}
	
	// 공통코드 그룹 삭제
	@RequestMapping(value = "/ajax_delete_common_group", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxDeleteCommonGroup(HttpServletRequest httpServletRequest, CommonCodeDto commonCodeDto) {
		int result = commonService.deleteCommonGroup(commonCodeDto);		
		return result;
	}
	
	

	// 공통코그 /////////////////////////////////////////////
	// 공통코드 추가 get
	@RequestMapping(value = "/ajax_list_common_code", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonCodeDto> ajaxListCommonCode(HttpServletRequest httpServletRequest
			, @RequestParam("cgr_group") 	String cgr_group
	) {		
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] cgr_group 	// " + cgr_group);
		System.out.println("[LOG] ======================================");
		
		List<CommonCodeDto> result = commonService.listCommonCode(cgr_group);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		return result;
	}
	
	// 공통코드 추가 get
	@RequestMapping(value = "/ajax_get_common_code", method = RequestMethod.POST)
	@ResponseBody
	public CommonCodeDto ajaxViewCommonCodeDetail(HttpServletRequest httpServletRequest
			, @RequestParam("ccd_code") 	String ccd_code
	) {		
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] ccd_code 	// " + ccd_code);
		System.out.println("[LOG] ======================================");
		
		CommonCodeDto result = commonService.getCommonCode(ccd_code);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.getCcd_code() 		// " + result.getCcd_code());
		System.out.println("[LOG] result.getCcd_codename() 	// " + result.getCcd_codename());
		System.out.println("[LOG] result.getCcd_order() 	// " + result.getCcd_order());
		System.out.println("[LOG] result.getCcd_detail1() 	// " + result.getCcd_detail1());
		System.out.println("[LOG] result.getCcd_detail2() 	// " + result.getCcd_detail2());
		System.out.println("[LOG] result.getCcd_detail3() 	// " + result.getCcd_detail3());
		System.out.println("[LOG] result.getCcd_note() 		// " + result.getCcd_note());
		System.out.println("[LOG] ======================================");
		return result;
	}
	
	// 공통코드 추가 get
	@RequestMapping(value = "/ajax_put_common_code", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxPutCommonCode(HttpServletRequest httpServletRequest, CommonCodeDto commonCodeDto) {		
		int result = commonService.putCommonCode(commonCodeDto);		
		return result;
	}
	
	// 공통코드 저장
	@RequestMapping(value = "/ajax_dup_common_order_and_name", method = RequestMethod.POST)
	@ResponseBody
	public CommonCodeDto ajaxDupCommonCode(HttpServletRequest httpServletRequest, CommonCodeDto commonCodeDto) {
		CommonCodeDto result = commonService.dupCommonOrderAndName(commonCodeDto);			
		return result;
	}

	// 공통코드 추가 get
	@RequestMapping(value = "/ajax_modify_common_code", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxModifyCommonCodeDetail(HttpServletRequest httpServletRequest, CommonCodeDto commonCodeDto) {		
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] getCcd_code 		// " + commonCodeDto.getCcd_code());
		System.out.println("[LOG] getCcd_codename 	// " + commonCodeDto.getCcd_codename());
		System.out.println("[LOG] getCcd_order 		// " + commonCodeDto.getCcd_order());
		System.out.println("[LOG] getCcd_detail1 	// " + commonCodeDto.getCcd_detail1());
		System.out.println("[LOG] getCcd_detail2 	// " + commonCodeDto.getCcd_detail2());
		System.out.println("[LOG] getCcd_detail3 	// " + commonCodeDto.getCcd_detail3());
		System.out.println("[LOG] getCcd_note 		// " + commonCodeDto.getCcd_note());
		System.out.println("[LOG] ======================================");
		
		int result = commonService.modifyCommonCode(commonCodeDto);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result : " + result);
		System.out.println("[LOG] ======================================");
		return result;
	}
	
	// 공통코드 저장
	@RequestMapping(value = "/ajax_delete_common_code", method = RequestMethod.POST)
	@ResponseBody
	public int ajaxDeleteCommonCode(HttpServletRequest httpServletRequest, CommonCodeDto commonCodeDto) {
		int result = commonService.deleteCommonCode(commonCodeDto);		
		return result;
	}
}
