package com.dongkeonoh.toybox;

import java.util.ArrayList;
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

import com.dongkeonoh.toybox.service.ItemService;
import com.dongkeonoh.toybox.vo.UserVo;
import com.dongkeonoh.toybox.vo.ItemVo;

import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Controller
public class ItemController implements ToyboxGlobalNameSpace {

	@Resource(name="ItemServiceImpl")
	private ItemService itemService;

	// 아이템 조회
	@RequestMapping(value = "/list_item", method = RequestMethod.GET)
	public ModelAndView getListItem(ModelAndView modelAndView, HttpServletRequest httpServletRequest
//			, @RequestParam("keyword") 		String keyword
	) {
		modelAndView.addObject("mapping_name", "아이템 대여");
		modelAndView.addObject("mapping_menu", "list_item");
		modelAndView.setViewName("item/list_item");		
		
		return modelAndView;
	}

	// 아이템 조회
	// 아이템 리스트 출력
	@RequestMapping(value = "/ajax_list_item", method = RequestMethod.POST)
	@ResponseBody
	public List<ItemVo> ajaxListItem(HttpServletRequest httpServletRequest
			, @RequestParam("keyword") 		String keyword
			, @RequestParam("start_idx") 	String start_idx
			, @RequestParam("end_idx") 		String end_idx
			, @RequestParam("rent_date") 	String rent_date
			, @RequestParam("return_date") 	String return_date
	) {		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyword", 		keyword);
		map.put("start_idx", 	start_idx);
		map.put("end_idx", 		end_idx);
		map.put("rent_date", 	rent_date);
		map.put("return_date", 	return_date);

		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] keyword 	// " 	+ keyword);
		System.out.println("[LOG] start_idx // " 	+ start_idx);
		System.out.println("[LOG] end_idx // " 		+ end_idx);
		System.out.println("[LOG] rent_date // " 	+ rent_date);
		System.out.println("[LOG] return_date // " 	+ return_date);
		System.out.println("[LOG] ======================================");
		
		List<ItemVo> result = itemService.listItem(map);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		
		return result;
	}

	// 아이템 대여 신청
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/apply_item", method = RequestMethod.POST)
	public ModelAndView apply_item(ModelAndView modelAndView, HttpServletRequest httpServletRequest
			, @RequestParam("rent_data") 		String rent_data
	) {
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] rent_data // " + rent_data);
		System.out.println("[LOG] ======================================");
		
		try {
			JSONParser jParser = new JSONParser();
			JSONObject jObject = (JSONObject)jParser.parse(rent_data);
			
			List<String> rent_item_list = (List<String>)jObject.get("item_list");

			// AOP 이동
			List<ItemVo> result = itemService.listItemBeforeApply(rent_item_list);	
			List<UserVo> return_point = itemService.listItemReturnPoint();	
			
			modelAndView.addObject("rent_date", jObject.get("rent_date"));
			modelAndView.addObject("return_date",  jObject.get("return_date"));
			modelAndView.addObject("result",  result);
			modelAndView.addObject("retun_point",  return_point);

			modelAndView.addObject("mapping_name", "아이템 대여");
			modelAndView.addObject("mapping_menu", "list_item");
			modelAndView.setViewName("item/apply_item");
		}catch (ParseException e) {
			// 에러페이지로 이동
			// sendredirection
			modelAndView.addObject("error_msg",  "대여신청 목록 작성 중");
			modelAndView.setViewName("error");
		}		
		return modelAndView;
	}	
	
	// 아이템 대여 신청
	@RequestMapping(value = "/rent_item", method = RequestMethod.POST)
	public ModelAndView rent_item(ModelAndView modelAndView, HttpServletRequest httpServletRequest
			, @RequestParam("rent_date") 	String rent_date
			, @RequestParam("return_date") 	String return_date
			, @RequestParam("result") 		String result
			, @RequestParam("note") 		String note
	) {		
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] rent_date // " 	+ rent_date);
		System.out.println("[LOG] return_date // " 	+ return_date);
		System.out.println("[LOG] result // " 		+ result);
		System.out.println("[LOG] ======================================");
		
		try {
			// 세션내 사용자 아이디 추출
			UserVo rent_user = (UserVo)httpServletRequest.getSession().getAttribute(USER);
			
			// 파라미터 파싱
			JSONParser jParser = new JSONParser();
			JSONObject jObject = (JSONObject)jParser.parse(result);			
			List<HashMap<String, String>> result_data = (List<HashMap<String, String>>)jObject.get("result");
			
			// 데이터 세팅
			List<ItemVo> list = new ArrayList<ItemVo>();
			
			for(HashMap<String, String> item : result_data) {
				ItemVo item_info = new ItemVo();
					
				item_info.setCdt_item(item.get("cdt_item"));
				item_info.setCdt_user(rent_user.getUsr_id());
				item_info.setCdt_location(item.get("cdt_location"));
				item_info.setCdt_return(item.get("cdt_return"));
				item_info.setCdt_note(note);
				item_info.setCdt_todate(return_date);
				item_info.setCdt_fromdate(rent_date);
				
				list.add(item_info);
			}

			// 대여신청
			int rent_result = 0;
			if(list.size() != 0) {
				rent_result = itemService.rentItem(list);

				// 대여신청 실패시
				if(rent_result == 0) {
					//리다이렉트
				}
			}			

			modelAndView.addObject("rent_date",  rent_date);
			modelAndView.addObject("return_date",  return_date);
			modelAndView.addObject("rent_item_count",  list.size());
			
			modelAndView.addObject("mapping_name", "아이템 대여");
			modelAndView.addObject("mapping_menu", "list_item");
			modelAndView.setViewName("item/result_item");
		}catch (ParseException e) {
			// 에러페이지로 이동
			modelAndView.addObject("error_msg",  "대여신청 중");
			modelAndView.addObject("error_code", "1");
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}	
	
	// 아이템 요청 사항 조회로 이동
	@RequestMapping(value = "/request_item", method = RequestMethod.GET)
	public ModelAndView request_item(ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
		
		modelAndView.addObject("mapping_menu", "request_item");
		modelAndView.addObject("mapping_name", "대여 목록");
		modelAndView.setViewName("item/request_item");	
		
		return modelAndView;
	}

	// 아이템 요청 사항 조회로 이동
	// 요청 아이템 리스트 출력
	@RequestMapping(value = "/ajax_request_item", method = RequestMethod.POST)
	@ResponseBody
	public List<ItemVo> ajaxRequestItem(HttpServletRequest httpServletRequest
			, @RequestParam("keyword") 		String keyword
			, @RequestParam("start_idx") 	String start_idx
			, @RequestParam("end_idx") 		String end_idx
			, @RequestParam("search_date") 	String search_date
	) {	
		// 세션 구하기
		UserVo rent_user = (UserVo)httpServletRequest.getSession().getAttribute(USER);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyword", 		keyword);
		map.put("start_idx", 	start_idx);
		map.put("end_idx", 		end_idx);
		map.put("search_date", 	search_date);
		map.put("user", 		rent_user.getUsr_id());		

		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] keyword 	// " 	+ keyword);
		System.out.println("[LOG] start_idx // " 	+ start_idx);
		System.out.println("[LOG] end_idx // " 		+ end_idx);
		System.out.println("[LOG] search_date // " 	+ search_date);
		System.out.println("[LOG] ======================================");
		
		List<ItemVo> result = itemService.requestItem(map);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		
		return result;
	}
	
	// 요청 내역 확인
	@RequestMapping(value = "/response_item", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView response_item(ModelAndView modelAndView, HttpServletRequest httpServletRequest) {	
		
		// 세션 구하기
		UserVo rent_user = (UserVo)httpServletRequest.getSession().getAttribute(USER);
		String user_id = rent_user.getUsr_id();
		
		List<ItemVo> result = itemService.responseItem(user_id);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		
		modelAndView.addObject("result", result);
		modelAndView.addObject("mapping_name", "요청 목록");
		modelAndView.addObject("mapping_menu", "response_item");
		modelAndView.setViewName("item/response_item");	
		
		return modelAndView;
	}
	
	// 아이템 대여 신청
	@RequestMapping(value = "/put_Item", method = RequestMethod.POST)
	public int put_Item(ModelAndView modelAndView, ItemVo item) {		
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] rent_date // "+ item.getItm_name()				 );    
		System.out.println("[LOG] rent_date // "+ item.getItm_type()				 );    
		System.out.println("[LOG] rent_date // "+ item.getItm_owner()				 );   
		System.out.println("[LOG] rent_date // "+ item.getItm_mainitem()			 );
		System.out.println("[LOG] rent_date // "+ item.getItm_note()				 );	
		System.out.println("[LOG] rent_date // "+ item.getItm_image()				 );
		System.out.println("[LOG] ======================================");
		
		int result = -1;
		try {
			result = itemService.putItem(item);
		}catch (Exception e) {
			// 에러페이지로 이동
			modelAndView.addObject("error_msg",  "대여신청 중");
			modelAndView.addObject("error_code", "1");
			modelAndView.setViewName("error");
		}
		
		return result;
	}	
	
	// 아이템 조회
	@RequestMapping(value = "/admin_list_item", method = RequestMethod.GET)
	public ModelAndView admin_list_item(ModelAndView modelAndView) {
		modelAndView.setViewName("common/list_item");		
		
		return modelAndView;
	}
	
	// 아이템 조회
	// 아이템 리스트 출력
	@RequestMapping(value = "/ajax_admin_item", method = RequestMethod.POST)
	@ResponseBody
	public List<ItemVo> ajaxAdminItem(HttpServletRequest httpServletRequest
			, @RequestParam("keyword") 		String keyword
			, @RequestParam("start_idx") 	String start_idx
			, @RequestParam("end_idx") 		String end_idx
	) {		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyword", 		keyword);
		map.put("start_idx", 	start_idx);
		map.put("end_idx", 		end_idx);

		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] keyword 	// " 	+ keyword);
		System.out.println("[LOG] start_idx // " 	+ start_idx);
		System.out.println("[LOG] end_idx // " 		+ end_idx);
		System.out.println("[LOG] ======================================");
		
		List<ItemVo> result = itemService.adminItem(map);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		
		return result;
	}
	
	// 아이템 상세 조회
	@RequestMapping(value = "/ajax_detail_item", method = RequestMethod.POST)
	@ResponseBody
	public ItemVo ajaxdetailItem(
			@RequestParam("itm_id") 		String itm_id
	) {		
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] itm_id 	// " 	+ itm_id);
		System.out.println("[LOG] ======================================");
		
		ItemVo result = itemService.getItem(itm_id);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.getCdt_id() 	// " + result.getCdt_id());
		System.out.println("[LOG] ======================================");
		
		return result;
	}
}
