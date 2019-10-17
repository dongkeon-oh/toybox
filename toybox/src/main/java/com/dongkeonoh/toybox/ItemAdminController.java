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

import com.dongkeonoh.toybox.dto.CommonCodeDto;
import com.dongkeonoh.toybox.dto.ItemDto;
import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.service.ItemAdminService;
import com.dongkeonoh.toybox.service.ItemService;
import com.dongkeonoh.toybox.vo.UserVo;
import com.dongkeonoh.toybox.vo.ItemVo;

import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Controller
public class ItemAdminController implements ToyboxGlobalNameSpace {

	@Resource(name="ItemAdminServiceImpl")
	private ItemAdminService itemAdminService;
	
	// 아이템 조회
	@RequestMapping(value = "/admin_list_item", method = RequestMethod.GET)
	public ModelAndView adminListItem(ModelAndView modelAndView) {
		modelAndView.setViewName("admin/list_item");				
		return modelAndView;
	}
	
	// 공통코드 리스트 
	@RequestMapping(value = "/ajax_commoncode_search", method = RequestMethod.POST)
	@ResponseBody
	public List<CommonCodeDto> getCommonCodeSearch(HttpServletRequest httpServletRequest, String search_type, CommonCodeDto commonCodeDto) {	
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] getCgr_group 	// " 	+ commonCodeDto.getCgr_group());
		System.out.println("[LOG] getCgr_group 	// " 	+ commonCodeDto.getCgr_group());
		System.out.println("[LOG] search_type // " 		+ search_type);
		System.out.println("[LOG] ======================================");
		
		List<CommonCodeDto> result = itemAdminService.getCommonCodeSearch(search_type, commonCodeDto);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		
		return result;
	}
	
	// 공통코드 리스트 
	@RequestMapping(value = "/ajax_user_search", method = RequestMethod.POST)
	@ResponseBody
	public List<UserDto> getUserSearch(HttpServletRequest httpServletRequest, String search_type, String usr_name) {	
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] search_type // " 		+ search_type);
		System.out.println("[LOG] usr_name // " 		+ usr_name);
		System.out.println("[LOG] ======================================");
		
		if(usr_name ==  null) usr_name = "";		
		List<UserDto> result = itemAdminService.getUserSearch(search_type, usr_name);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		
		return result;
	}
	
	// 공통코드 리스트 
	@RequestMapping(value = "/ajax_item_search", method = RequestMethod.POST)
	@ResponseBody
	public List<ItemDto> getItemSearch(HttpServletRequest httpServletRequest, String search_type, String itm_name) {	
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] search_type // " 		+ search_type);
		System.out.println("[LOG] itm_name // " 		+ itm_name);
		System.out.println("[LOG] ======================================");
		
		if(itm_name ==  null) itm_name = "";
		List<ItemDto> result = itemAdminService.getItemSearch(search_type, itm_name);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result.size());
		System.out.println("[LOG] ======================================");
		
		return result;
	}
	
	@RequestMapping(value = "/ajax_put_item", method = RequestMethod.POST)
	@ResponseBody
	public int putItem(HttpServletRequest httpServletRequest, ItemDto itemDto) {	
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] itemDto // " 		+ itemDto.getItm_name());
		System.out.println("[LOG] ======================================");
		
		int result = itemAdminService.putItem(itemDto);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result);
		System.out.println("[LOG] ======================================");
		
		return result;
	}
	
	@RequestMapping(value = "/ajax_list_item_admin", method = RequestMethod.POST)
	@ResponseBody
	public List<ItemDto> listItem(HttpServletRequest httpServletRequest
			,String start_idx
			,String end_idx
			,String keyword
	) {	
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] start_idx // " 		+ start_idx);
		System.out.println("[LOG] end_idx // " 			+ end_idx);
		System.out.println("[LOG] ======================================");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("start_idx", start_idx);
		map.put("end_idx", end_idx);
		map.put("keyword", keyword);
		
		List<ItemDto> result = itemAdminService.listItem(map);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result);
		System.out.println("[LOG] ======================================");
		
		return result;
	}
	
	@RequestMapping(value = "/ajax_detail_item", method = RequestMethod.POST)
	@ResponseBody
	public ItemDto detailItem(HttpServletRequest httpServletRequest 
			,String itm_id
	) {	
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] itm_id // " 		+ itm_id);
		System.out.println("[LOG] ======================================");

		ItemDto result = itemAdminService.detailItem(itm_id);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.getItm_id()	// " + result.getItm_id());
		System.out.println("[LOG] ======================================");
		
		return result;
	}
	
	@RequestMapping(value = "/ajax_update_item", method = RequestMethod.POST)
	@ResponseBody
	public int updateItem(HttpServletRequest httpServletRequest, ItemDto itemDto) {	
		System.out.println("[LOG] ==REQUEST=============================");
		System.out.println("[LOG] itemDto // " 		+ itemDto.getItm_name());
		System.out.println("[LOG] ======================================");
		
		int result = itemAdminService.updateItem(itemDto);		
		
		System.out.println("[LOG] ==REEPONS=============================");
		System.out.println("[LOG] result.size() 	// " + result);
		System.out.println("[LOG] ======================================");
		
		return result;
	}
}
