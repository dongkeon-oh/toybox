package com.dongkeonoh.toybox.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongkeonoh.toybox.dao.ConditionDao;
import com.dongkeonoh.toybox.dao.ItemDao;
import com.dongkeonoh.toybox.dao.UtilityDao;
import com.dongkeonoh.toybox.dto.ItemDto;
import com.dongkeonoh.toybox.dto.UserDto;
import com.dongkeonoh.toybox.service.ItemService;
import com.dongkeonoh.toybox.vo.ItemVo;

@Service("ItemServiceImpl")
public class ItemServiceImpl implements ItemService{

	@Resource(name="ItemDao")
	private ItemDao itemDao;

	@Resource(name="UtilityDao")
	private UtilityDao utilityDao;

	@Resource(name="ConditionDao")
	private ConditionDao conditionDao;

	@Override
	public List<ItemDto> listItem(HashMap<String, String> search) {
		List<ItemDto> result = itemDao.listItem(search);
		return result;
	}
	
	@Override
	public HashMap<String, Object> listItemBeforeApply(List<String> rent_item_list){
		HashMap<String , String> paramUser = new HashMap<String, String>();
		paramUser.put("search_type", "_SIMPLE_");
		paramUser.put("usr_name", "");

		List<UserDto> userList = utilityDao.getUserSearch(paramUser);	//반납 위치 선택
		List<ItemDto> itemLocation = itemDao.listItemBeforeApply(rent_item_list); //아이템 현재 위치 확인
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userList", userList);
		map.put("itemLocation", itemLocation);
		return map;		
	}
	
	@Override
	public int rentItem(List<ItemDto> rent_item_list) {
		int result= 0;
		for(int i = 0; rent_item_list.size() > i; i++) {
			rent_item_list.get(i).setCdt_condition("rent_requested");
			result = result + conditionDao.putCondition(rent_item_list.get(i));
		}
		if(rent_item_list.size() != result) result = 0;
		return result;
	}
	
	// 요청 취소
	public int requestCancel(String item_id) {
		// 아이템 요청 최소
		ItemDto item = conditionDao.getCurrentCondition(item_id);
		item.setCdt_condition("request_cancel");
		item.setCdt_item(item_id);
		item.setCdt_note("대여 취소");
		int result_cancel = conditionDao.putCondition(item);

		// 새로운 상태 등록 > 현재 프로세스 마무리
		ItemDto put_item =  new ItemDto();
		String init_loc = item.getCdt_location();
		put_item.setCdt_condition("rentable");
		put_item.setCdt_item(item_id);
		put_item.setCdt_user(init_loc);
		put_item.setCdt_location(init_loc);
		put_item.setCdt_return(init_loc);
		put_item.setCdt_fromdate("2100/12/31");
		put_item.setCdt_todate("1989/06/07");
		int result_rentable = conditionDao.putCondition(put_item);

		return result_cancel + result_rentable;
	}

	@Override
	public List<ItemDto> requestItem(HashMap<String, String> search) {
		List<ItemDto> result = itemDao.requestItem(search);
		return result;
	}

	// 요청 내역 확인
	@Override
	public List<ItemVo> responseItem(String user_id){
		List<ItemVo> result = itemDao.responseItem(user_id);
		return result;		
	}
}
